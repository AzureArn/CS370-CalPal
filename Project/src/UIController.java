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
        //
        view.getDateDropdown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    // create methods for each button / other component that sends action here

    // createUserButton's function
    // adds a new user
    private void addUser(){
        // get the name and password from the field
        String name = view.getUserField().getText();
        String password = view.getPasswordField().getText();

        // try to create the user using the loginManager
        boolean success = loginManager.addUser(name, password);

        if(success){
            // use the message label to tell the user it was successful
            view.getLoginMessageLabel().setText("User " + name.strip() + " created");
        }
        else{
            // use the message label to tell the user it was not successful
            view.getLoginMessageLabel().setText("Could not add user," +
                    " name is either taken or name/password includes ':' character");
        }
    }

    // loginButton's function
    // attempts to log user in, switches screen if successful
    private void logIn(){
        // get the name and password from the field
        String name = view.getUserField().getText();
        String password = view.getPasswordField().getText();

        // try to log in the user using the loginManager
        boolean success = loginManager.logIn(name, password);

        if(success){
            //instantiate dataManager using the username
            userDataManager = new UserDataManager(name.strip());
            view.getUserNameDisplayLabel().setText("User: " + name.strip());

            // TODO: Insert entries in the form of the date strings into the combobox
            // TODO: Not getting recent dates, issue in TextFileHandler?
            // insert the user's data entry dates into the dateDropdown component
            ArrayList<String> dates = userDataManager.getAllDates();
            if(dates.isEmpty()){
                System.out.println("Could not get dates for entry collection");
                return;
            }
            dates.forEach(
                    (date) -> view.getDateDropdown().addItem(date)
            );

            // swap the view
            view.swapView();
        }
        else{
            // use the message label to tell the user login was not successful
            view.getLoginMessageLabel().setText("Could not log in");
        }
    }
}
