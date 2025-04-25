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
    }

    // create methods for each button / other component that sends action here

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
            view.getLoginMessageLabel().setText("User " + name + " created");
        }
        else{
            // use the message label to tell the user it was not successful
            view.getLoginMessageLabel().setText("Could not add user," +
                    " name is either taken or name/password includes ':' character");
        }
    }

    // loginButton's behavior
    // attempts to log user in, switches screen if successful
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
            view.getLoginMessageLabel().setText("Could not log in");
        }
    }

    // ***MAIN VIEW***

    // dateDropdown's behavior
    // update the date label, calories consumed label, and calories burned label to match the entry's data
    private void selectDate(){
        String date = (String) view.getDateDropdown().getSelectedItem();
        CalorieDataEntry entry = userDataManager.getEntry(date);
        // check if entry could not be found, should not occur in normal operation
        if(entry == null){
            System.out.println("Something went wrong");
            System.exit(10);
        }
        view.getDateLabel().setText("Date Selected: " + entry.getEntryDate());
        view.getCaloriesConsumedLabel().setText("Calories Consumed: " + entry.getCaloriesConsumed());
        view.getCaloriesBurnedLabel().setText("Calories Burned: " + entry.getCaloriesBurned());
    }
}
