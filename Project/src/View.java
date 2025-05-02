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
    private JLabel calPalLabel;
    private JLabel userLabel;
    private JTextField userField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton createUserButton;
    private JLabel loginPageStatusLabel;
    private JLabel loginError;

    // main view components
    private JPanel dataPanel;


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
    private JComboBox<Exercise> exerciseDropdown;
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
        //loginPanel.setLayout(new BorderLayout(5,5));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        JPanel userNameEntryPanel = new JPanel(); // panel for username entry
        JPanel passwordEntryPanel = new JPanel(); // panel for password entry
        //loginMenu.setLayout(new BoxLayout(loginMenu, BoxLayout.Y_AXIS));
        Dimension minSize = new Dimension(5, 100);
        Dimension prefSize = new Dimension(5, 100);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
        //loginMenu.add(new Box.Filler(minSize, prefSize, maxSize));

//        loginMessageLabel = new JLabel("Cal Pal");
//        loginMessageLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
//        loginMessageLabel.setForeground(Color.GREEN);
//        userNameEntryPanel.add(loginMessageLabel);

        //label at top displaying app's name, Cal Pal
        calPalLabel = new JLabel("Cal Pal");
        calPalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(calPalLabel);

        //Username Label
        userLabel = new JLabel("Username:");
        userNameEntryPanel.add(userLabel);
        //Username Field
        userField = new JTextField(15);
        userNameEntryPanel.add(userField);
        userNameEntryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(userNameEntryPanel);

        //Password Label
        passwordLabel = new JLabel("Password:");
        passwordEntryPanel.add(passwordLabel);
        //Password Field
        passwordField = new JPasswordField(15);
        passwordEntryPanel.add(passwordField);
        loginPanel.add(passwordEntryPanel);


        //Buttons
        //New User button
        createUserButton = new JButton("Create User");
        createUserButton.setFocusable(false);
        createUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(createUserButton);
        // Login button
        loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(loginButton);

        // Status label
        loginPageStatusLabel = new JLabel("");
        loginPageStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginPageStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(loginPageStatusLabel);

        // only add the loginPanel to the frame to start
        frame.getContentPane().add(loginPanel);
        // ***END LOGIN VIEW SECTION*** //


        //***MAIN VIEW SECTION***

        mainPanel.setLayout(new BorderLayout(5,5));

        userNameDisplayLabel = new JLabel();
        userNameDisplayLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        mainPanel.add(userNameDisplayLabel, BorderLayout.NORTH);

        dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(1,2));


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

        DatabaseDataManager db = new DatabaseDataManager();

        foodDropdown = new JComboBox(db.getFoods().toArray());
        foodDropdown.setEditable(true);
        // gets first item in list
        foodInfoLabel = new JLabel("Grams per serving: " + db.viewFood(db.getFoods().getFirst().getName()).getGramsPerServing() + ", Calories per serving: " + db.viewFood(db.getFoods().getFirst().getName()).getCaloriesPerServing());

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

        exerciseDropdown = new JComboBox(db.getExercises().toArray());

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
//        mainPanel.add(userNameDisplayLabel);
//        mainPanel.add(selectDayLabel);
//        mainPanel.add(dateDropdown);
//        mainPanel.add(dateLabel);
//        mainPanel.add(caloriesConsumedLabel);
//        mainPanel.add(caloriesBurnedLabel);
//        mainPanel.add(netCalorieIntakeLabel);
//        mainPanel.add(setCaloriesConsumedLabel);
//        mainPanel.add(setCaloriesConsumedField);
//        mainPanel.add(setCaloriesConsumedButton);
//        mainPanel.add(setCaloriesBurnedLabel);
//        mainPanel.add(setCaloriesBurnedField);
//        mainPanel.add(setCaloriesBurnedButton);
//
//        mainPanel.add(dataEntryPane);

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

    public JLabel getLoginPageStatusLabel() {
        return loginPageStatusLabel;
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

    public JComboBox getFoodDropdown() {
        return foodDropdown;
    }

    public JComboBox getExerciseDropdown() {
        return exerciseDropdown;
    }

    public JButton getExerciseDiagramButton() {
        return exerciseDiagramButton;
    }

    public JLabel getFoodInfoLabel() {
        return foodInfoLabel;
    }

    public JLabel getExerciseInfoLabel() {
        return exerciseInfoLabel;
    }

    public JButton getFoodEatenButton() {
        return foodEatenButton;
    }

    public JTextField getFoodEatenField() {
        return foodEatenField;
    }

    public JButton getExercisePerformedButton() {
        return exercisePerformedButton;
    }

    public JTextField getExercisePerformedField() {
        return exercisePerformedField;
    }
}
