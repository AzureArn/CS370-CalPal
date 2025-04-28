import javax.swing.*;
import java.awt.*;

// view class, creates components to be interacted with by UIController
public class View {
    // the frame that contains every component
    private JFrame frame;
    // the login view
    private JPanel loginPanel;
    // the main view
    private JPanel mainPanel;

    // login view components
    private JLabel userLabel;
    private JTextField userField;

    private JLabel passwordLabel;
    private JTextField passwordField;

    private JButton loginButton;
    private JButton createUserButton;

    private JLabel loginMessageLabel;

    // main view components
    private JLabel userNameDisplayLabel;
    private JLabel selectDayLabel;
    private JComboBox<String> dateDropdown;
    private JLabel dateLabel;
    private JLabel caloriesConsumedLabel;
    private JLabel caloriesBurnedLabel;
    private JLabel netCalorieIntakeLabel;
    private JLabel setCaloriesConsumedLabel;
    private JTextField setCaloriesConsumedField;
    private JButton setCaloriesConsumedButton;
    private JLabel setCaloriesBurnedLabel;
    private JTextField setCaloriesBurnedField;
    private JButton setCaloriesBurnedButton;

    //  main view's tabbed pane components
    private JTabbedPane dataEntryPane;

    private JPanel foodPanel;
    private JLabel selectFoodLabel;
    private JComboBox foodDropdown;
    private JLabel foodInfoLabel;
    private JLabel foodEatenLabel;
    private JTextField foodEatenField;
    private JButton foodEatenButton;


    private JPanel exercisePanel;
    private JLabel selectExerciseLabel;
    private JComboBox exerciseDropdown;
    private JLabel exerciseInfoLabel;
    private JLabel exercisePerformedLabel;
    private JTextField exercisePerformedField;
    private JButton exercisePerformedButton;
    private JButton exerciseDiagramButton;


    public View(){
        // TODO: give the view a proper layout later
        // loginPanel.setLayout(new GridBagLayout());
        // mainPanel.setLayout(new GridBagLayout());
        frame = new JFrame("Cal Pal");

        // the login view
        loginPanel = new JPanel();
        // the main view
        mainPanel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the program exit the JVM on close

        final int SIZE_X = 1000; // 1920; // A standard resolution to use
        final int SIZE_Y = 800; // 1080;
        frame.setSize(SIZE_X, SIZE_Y);
        //frame.setResizable(false); // prevents changing window from changing res size
        frame.setLocationRelativeTo(null); // makes the window centered upon creation, only do after sizing the frame


        // ***LOGIN VIEW SECTION***
        // Setup defaults
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Username Label
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Username:"),gbc);
        //Username Field
        gbc.gridx = 2;
        gbc.gridy = 1;
        userField = new JTextField(15);
        loginPanel.add(userField,gbc);

        //Password Label
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(new JLabel("Password:"),gbc);
        //Password Field
        gbc.gridx = 2;
        gbc.gridy = 2;
        passwordField = new JPasswordField(15);
        loginPanel.add(passwordField,gbc);

        //Buttons
        //New User button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        createUserButton = new JButton("Create User");
        createUserButton.setFocusable(false);
        loginPanel.add(createUserButton, gbc);
        // Login button
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginPanel.add(loginButton, gbc);

        // Error label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Span across columns if needed
        loginMessageLabel = new JLabel("");
        loginMessageLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginMessageLabel.setForeground(Color.RED); // error color
        loginPanel.add(loginMessageLabel, gbc);

        /* username input components
        userLabel = new JLabel("User Name:");
        userField = new JTextField();
        userField.setPreferredSize(new Dimension(100,25));

        // password input components
        passwordLabel = new JLabel("Password:");
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(100,25));

        //button to log in
        loginButton = new JButton("Log In");
        loginButton.setFocusable(false); // removes little border around text

        // button to create user
        createUserButton = new JButton("Create User");
        createUserButton.setFocusable(false); // removes little border around text

        // indicates if error occurred when adding user/logging in
        loginMessageLabel = new JLabel();


        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        loginPanel.add(loginButton);
        loginPanel.add(createUserButton);
        loginPanel.add(loginMessageLabel);
        */

        // only add the loginPanel the frame to start
        frame.getContentPane().add(loginPanel);

        //***MAIN VIEW SECTION***
        userNameDisplayLabel = new JLabel();

        selectDayLabel = new JLabel("Select Day:");
        dateDropdown = new JComboBox<String>();
        dateLabel = new JLabel("dateplaceholder");
        caloriesConsumedLabel = new JLabel("consumedplaceholder");
        caloriesBurnedLabel = new JLabel("burnedplaceholder");
        netCalorieIntakeLabel = new JLabel("netcalorieplaceholder");

        setCaloriesConsumedLabel = new JLabel("Manually Set The Calories Consumed:");
        setCaloriesConsumedField = new JTextField(5);
        setCaloriesConsumedButton = new JButton("Set");
        setCaloriesConsumedButton.setFocusable(false);

        setCaloriesBurnedLabel = new JLabel("Manually Set The Calories Burned:");
        setCaloriesBurnedField = new JTextField(5);
        setCaloriesBurnedButton = new JButton("Set");
        setCaloriesBurnedButton.setFocusable(false);

        // Data entry section in the main view
        dataEntryPane = new JTabbedPane();

        // food panel
        foodPanel = new JPanel();

        selectFoodLabel = new JLabel("Select/Search For Food Item:");
        String[] testFood = {"Apple", "Pear"};
        foodDropdown = new JComboBox(testFood);
        foodDropdown.setEditable(true);
        foodInfoLabel = new JLabel("food info Placeholder");

        foodEatenLabel = new JLabel("food eaten Placeholder");
        foodEatenField = new JTextField(5);
        foodEatenButton = new JButton("Enter");
        foodEatenButton.setFocusable(false);

        foodPanel.add(selectFoodLabel);
        foodPanel.add(foodDropdown);
        foodPanel.add(foodInfoLabel);
        foodPanel.add(foodEatenLabel);
        foodPanel.add(foodEatenField);
        foodPanel.add(foodEatenButton);


        // exercise panel
        exercisePanel = new JPanel();
        selectExerciseLabel = new JLabel("Select Exercise:");
        String[] testExercise = {"a", "b"};
        exerciseDropdown = new JComboBox(testExercise);

        exerciseInfoLabel = new JLabel("exercise info Placeholder");
        exerciseDiagramButton = new JButton("View Diagram");
        exerciseDiagramButton.setFocusable(false);

        exercisePerformedLabel = new JLabel("exercise performed Placeholder");
        exercisePerformedField = new JTextField(5);
        exercisePerformedButton = new JButton("Enter");
        exercisePerformedButton.setFocusable(false);

        exercisePanel.add(selectExerciseLabel);
        exercisePanel.add(exerciseDropdown);
        exercisePanel.add(exerciseInfoLabel);
        exercisePanel.add(exerciseDiagramButton);
        exercisePanel.add(exercisePerformedLabel);
        exercisePanel.add(exercisePerformedField);
        exercisePanel.add(exercisePerformedButton);


        // add the panels to the tabbed pane
        dataEntryPane.addTab("Food", null, foodPanel, "Use this tab to search food data and" +
                " to input the amount of food eaten");
        dataEntryPane.addTab("Exercise", null, exercisePanel, "Use this tab to search exercise data, " +
                "to input the amount of exercise performed, and to view exercise diagrams");

        // add components to the main panel
        mainPanel.add(userNameDisplayLabel);
        mainPanel.add(selectDayLabel);
        mainPanel.add(dateDropdown);
        mainPanel.add(dateLabel);
        mainPanel.add(caloriesConsumedLabel);
        mainPanel.add(caloriesBurnedLabel);
        mainPanel.add(netCalorieIntakeLabel);
        mainPanel.add(setCaloriesConsumedLabel);
        mainPanel.add(setCaloriesConsumedField);
        mainPanel.add(setCaloriesConsumedButton);
        mainPanel.add(setCaloriesBurnedLabel);
        mainPanel.add(setCaloriesBurnedField);
        mainPanel.add(setCaloriesBurnedButton);

        mainPanel.add(dataEntryPane);

        frame.setVisible(true);
    }

    // swaps from the login view to the main view
    public void swapView(){
        // remove first panel, add the second, then revalidate to change view to main screen
        frame.getContentPane().remove(loginPanel);
        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();
    }

    // Getters for various components


    public JFrame getFrame() {
        return frame;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JButton getCreateUserButton() {
        return createUserButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getUserLabel() {
        return userLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JLabel getLoginMessageLabel() {
        return loginMessageLabel;
    }

    public JTextField getUserField() {
        return userField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getUserNameDisplayLabel() {
        return userNameDisplayLabel;
    }

    public JComboBox getDateDropdown() {
        return dateDropdown;
    }

    public JLabel getDateLabel(){
        return dateLabel;
    }

    public JLabel getCaloriesBurnedLabel() {
        return caloriesBurnedLabel;
    }

    public JLabel getCaloriesConsumedLabel() {
        return caloriesConsumedLabel;
    }

    public JLabel getNetCalorieIntakeLabel() {
        return netCalorieIntakeLabel;
    }

    public JTextField getSetCaloriesConsumedField() {
        return setCaloriesConsumedField;
    }

    public JButton getSetCaloriesConsumedButton() {
        return setCaloriesConsumedButton;
    }

    public JTextField getSetCaloriesBurnedField() {
        return setCaloriesBurnedField;
    }

    public JButton getSetCaloriesBurnedButton() {
        return setCaloriesBurnedButton;
    }
}
