import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// the controller that makes use of the UserDataManager and UserLoginManager
public class UIController {
    // view is the UI view that the controller interacts with
    View view;
    // the loginManager and dataManager interact with the user credential data and calorie data respectively
    UserLoginManager loginManager;
    UserDataManager userDataManager;
    DatabaseDataManager databaseManager;

    public UIController(View view){
        this.view = view;
        loginManager = new UserLoginManager();
        databaseManager = new DatabaseDataManager();
        // instantiate data manager later when a successful login occurs
        userDataManager = null;

        // set various action listeners for components

        // ***LOGIN VIEW***

        // createUserButton
        view.getCreateUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        // loginButton
        view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn();
            }
        });

        //***MAIN VIEW***

        // dateDropdown
        view.getDateDropdown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDate();
            }
        });

        // setCaloriesConsumedButton
        view.getSetCaloriesConsumedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCaloriesConsumed();
            }
        });

        // setCaloriesBurnedButton
        view.getSetCaloriesBurnedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCaloriesBurned();
            }
        });


        // showDiagramButton
        view.getExerciseDiagramButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDiagram();
            }
        });

        // foodDropdown
        view.getFoodDropdown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFood();
            }
        });

        // exerciseDropdown
        view.getExerciseDropdown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectExercise();
            }
        });

        // foodEatenButton
        view.getFoodEatenButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCaloriesConsumed();
            }
        });

        // exercisePerformedButton
        view.getExercisePerformedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCaloriesBurned();
            }
        });
    }

    // create methods for each interactive component that sends action here

    // ***LOGIN VIEW***

    // createUserButton's behavior
    // adds a new user
    private void addUser(){
        // get the name and password from the field
        String name = view.getUserField().getText().strip().toLowerCase();
        String password = view.getPasswordField().getText();

        // try to create the user using the loginManager
        boolean success = loginManager.addUser(name, password);

        if(success){
            // use the message label to tell the user it was successful
            view.getLoginPageStatusLabel().setText("User " + name + " created");
            view.getLoginPageStatusLabel().setForeground(Color.GREEN);
        }
        else{
            // use the message label to tell the user it was not successful
            view.getLoginPageStatusLabel().setText("Could not add user," +
                    " name is either taken or name/password includes ':' character");
            view.getLoginPageStatusLabel().setForeground(Color.RED);
        }
    }

    // loginButton's behavior
    // attempts to log user in, switches screen if successful
    // sets up the date dropdown menu
    private void logIn(){
        // get the name and password from the field
        String name = view.getUserField().getText().strip().toLowerCase();
        String password = view.getPasswordField().getText();

        // try to log in the user using the loginManager
        boolean success = loginManager.logIn(name, password);

        if(success){
            //instantiate dataManager using the username
            userDataManager = new UserDataManager(name);
            view.getUserNameDisplayLabel().setText("User: " + name);

            // insert the user's data entry dates into the dateDropdown component
            ArrayList<String> dates = userDataManager.getAllDates();
            if(dates.isEmpty()){
                System.out.println("Could not get dates for entry collection");
                return;
            }
            dates.forEach(
                    (date) -> view.getDateDropdown().addItem(date)
            );

            // select last item in dateDropdown component by default, most recent date
            view.getDateDropdown().setSelectedIndex(view.getDateDropdown().getItemCount() - 1);

            // swap the view
            view.swapView();
        }
        else{
            // use the message label to tell the user login was not successful
            view.getLoginPageStatusLabel().setText("Could not log in");
            view.getLoginPageStatusLabel().setForeground(Color.RED);
        }
    }

    // ***MAIN VIEW***

    // dateDropdown's behavior
    // updates the date label, calories consumed label, and calories burned label to match the entry's data
    private void selectDate(){
        String date = (String) view.getDateDropdown().getSelectedItem();
        CalorieDataEntry entry = userDataManager.getEntry(date);
        // check if entry could not be found, should not occur in normal operation
        if(entry == null){
            System.out.println("Something went wrong, entry could not be found");
            System.exit(10);
        }
        view.getDateLabel().setText("Date Selected: " + entry.getEntryDate());
        view.getCaloriesConsumedLabel().setText("Calories Consumed: " + entry.getCaloriesConsumed());
        view.getCaloriesBurnedLabel().setText("Calories Burned: " + entry.getCaloriesBurned());
        view.getNetCalorieIntakeLabel().setText("Net Intake: " + entry.getNetCalories());
    }

    // setCaloriesConsumedButton's behavior
    // manually sets the calories consumed
    private void setCaloriesConsumed(){
        String input = view.getSetCaloriesConsumedField().getText();
        int calories = 0;
        try{
            calories = Integer.parseInt(input);
            if(calories < 0){
                // create popup to show error if negative number is inputted
                JOptionPane.showMessageDialog(view.getFrame(), "Enter a non-negative number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else{
                userDataManager.saveCalorieData(calories, userDataManager.getCurrentEntry().getCaloriesBurned());
                // move the dropdown selection to the current date
                view.getDateDropdown().setSelectedIndex(view.getDateDropdown().getItemCount() - 1);
                // refresh the displayed calorie data
                selectDate();
            }
        } catch (Exception nfe){
            JOptionPane.showMessageDialog(view.getFrame(), "Enter a non-negative whole number", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    // setCaloriesConsumedButton's behavior
    // manually sets the calories burned
    private void setCaloriesBurned(){
        String input = view.getSetCaloriesBurnedField().getText();
        int calories = 0;
        try{
            calories = Integer.parseInt(input);
            if(calories < 0){
                // create popup to show error if negative number is inputted
                JOptionPane.showMessageDialog(view.getFrame(), "Enter a non-negative number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else{
                userDataManager.saveCalorieData(userDataManager.getCurrentEntry().getCaloriesConsumed(), calories);
                // move the dropdown selection to the current date
                view.getDateDropdown().setSelectedIndex(view.getDateDropdown().getItemCount() - 1);
                // refresh the displayed calorie data
                selectDate();
            }
        } catch (Exception nfe){
            JOptionPane.showMessageDialog(view.getFrame(), "Enter a non-negative whole number", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // foodDropdown's behavior
    private void selectFood(){
        FoodItem item = (FoodItem) view.getFoodDropdown().getSelectedItem();
        view.getFoodInfoLabel().setText("Grams per serving: " + item.getGramsPerServing() + ", Calories per serving: " + item.getCaloriesPerServing());
    }

    // foodEatenButton's behavior
    private void addCaloriesConsumed(){
        FoodItem item = (FoodItem) view.getFoodDropdown().getSelectedItem();
        String servings = view.getFoodEatenField().getText();
        int calsConsumed = 0;
        try{
            calsConsumed = Integer.parseInt(servings) * item.getCaloriesPerServing();
            if(calsConsumed < 0){
                // create popup to show error if negative number is inputted
                JOptionPane.showMessageDialog(view.getFrame(), "Enter a non-negative number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else{
                userDataManager.saveCalorieData(userDataManager.getCurrentEntry().getCaloriesConsumed() + calsConsumed, userDataManager.getCurrentEntry().getCaloriesBurned());
                // move the dropdown selection to the current date
                view.getDateDropdown().setSelectedIndex(view.getDateDropdown().getItemCount() - 1);
                // refresh the displayed calorie data
                selectDate();
            }
        } catch (Exception nfe){
            JOptionPane.showMessageDialog(view.getFrame(), "Enter a non-negative whole number", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // exerciseDropdown's behavior
    private void selectExercise(){
        Exercise exercise = (Exercise) view.getExerciseDropdown().getSelectedItem();
        view.getExerciseInfoLabel().setText("Calories burned per rep / mile ran: " + exercise.getCaloriesPerUnitExercise());
    }

    // exercisePerformedButton's behavior
    private void addCaloriesBurned(){
        Exercise exercise = (Exercise) view.getExerciseDropdown().getSelectedItem();
        String exercisePerformed = view.getExercisePerformedField().getText();
        int calsBurned = 0;
        try{
            calsBurned = Integer.parseInt(exercisePerformed) * exercise.getCaloriesPerUnitExercise();
            if(calsBurned < 0){
                // create popup to show error if negative number is inputted
                JOptionPane.showMessageDialog(view.getFrame(), "Enter a non-negative number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else{
                userDataManager.saveCalorieData(userDataManager.getCurrentEntry().getCaloriesConsumed(), userDataManager.getCurrentEntry().getCaloriesBurned() + calsBurned);
                // move the dropdown selection to the current date
                view.getDateDropdown().setSelectedIndex(view.getDateDropdown().getItemCount() - 1);
                // refresh the displayed calorie data
                selectDate();
            }
        } catch (Exception nfe){
            JOptionPane.showMessageDialog(view.getFrame(), "Enter a non-negative whole number", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // exerciseDiagramButton's behavior
    private void showDiagram(){
        // frame to contain image
        JFrame popup = new JFrame();
        ImageIcon image = new ImageIcon(TextFileHandler.getFile("default.png").getAbsolutePath());
        JLabel imageLabel = new JLabel(image);
        popup.setSize(image.getIconWidth(), image.getIconHeight());
        popup.add(imageLabel);
        popup.setLocationRelativeTo(null);

        popup.setVisible(true);
    }
}
