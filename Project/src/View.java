import javax.swing.*;
import javax.swing.border.*;
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

    // main view components
    private JLabel userNameDisplayLabel;
    private JLabel selectDayLabel;
    private JComboBox<String> dateDropdown;
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
    private JLabel foodFilterLabel;
    private JTextField foodFilterField;
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
        frame = new JFrame("Cal Pal");

        // the login view
        loginPanel = new JPanel();
        // the main view
        mainPanel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the program exit the JVM on close

        final int SIZE_X = 800; // 1920; // A standard resolution to use
        final int SIZE_Y = 600; // 1080;
        frame.setSize(SIZE_X, SIZE_Y);
        //frame.setResizable(false); // prevents changing window from changing res size
        frame.setLocationRelativeTo(null); // makes the window centered upon creation, only do after sizing the frame


        // ***LOGIN VIEW SECTION***
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(new Color(99, 177, 177));
        JPanel userNameEntryPanel = new JPanel(); // panel for username entry, used for laying out UI
        JPanel passwordEntryPanel = new JPanel(); // panel for password entry
        JPanel credentialEntryPanel = new JPanel(); // contains buttons for entering the credentials
        // this is done to adjust the space underneath these panels
        userNameEntryPanel.setMaximumSize(new Dimension(SIZE_X, 50));
        passwordEntryPanel.setMaximumSize(new Dimension(SIZE_X, 50));
        credentialEntryPanel.setMaximumSize(new Dimension(SIZE_X, 50));

        //label at top displaying app's name, Cal Pal
        loginPanel.add(new Box.Filler((new Dimension(1, 10)), (new Dimension(1, 10)), (new Dimension(1, 50))));
        calPalLabel = new JLabel("Cal Pal");
        calPalLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        calPalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(calPalLabel);

        // add space between top label and other components
        loginPanel.add(new Box.Filler((new Dimension(1, 50)), (new Dimension(1, 200)), (new Dimension(1, 200))));

        JPanel loginBoxPanel = new JPanel();
        loginBoxPanel.setLayout(new BoxLayout(loginBoxPanel, BoxLayout.Y_AXIS));
        loginBoxPanel.setBackground(new Color(230, 230, 230));
        loginBoxPanel.add(new Box.Filler((new Dimension(1, 1)), (new Dimension(1, 10)), (new Dimension(1, 20))));

        //Username Label
        userLabel = new JLabel("Username:");
        userNameEntryPanel.add(userLabel);
        //Username Field
        userField = new JTextField(15);
        userNameEntryPanel.add(userField);
        userNameEntryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBoxPanel.add(userNameEntryPanel);

        //Password Label
        passwordLabel = new JLabel("Password:");
        passwordEntryPanel.add(passwordLabel);
        //Password Field
        passwordField = new JPasswordField(15);
        passwordEntryPanel.add(passwordField);
        loginBoxPanel.add(passwordEntryPanel);


        //Buttons
        //New User button
        createUserButton = new JButton("Create User");
        createUserButton.setFocusable(false);
        createUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        credentialEntryPanel.add(createUserButton);
        // Login button
        loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        credentialEntryPanel.add(loginButton);

        loginBoxPanel.add(credentialEntryPanel);

        // Status label
        loginPageStatusLabel = new JLabel("");
        loginPageStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginPageStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBoxPanel.add(loginPageStatusLabel);

        loginBoxPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)));

        loginBoxPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center in BoxLayout
        loginPanel.add(loginBoxPanel);

        // only add the loginPanel to the frame to start
        frame.getContentPane().add(loginPanel);
        // ***END LOGIN VIEW SECTION*** //


        //***MAIN VIEW SECTION***

        mainPanel.setLayout(new BorderLayout(5,5));

        userNameDisplayLabel = new JLabel();
        userNameDisplayLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        mainPanel.add(userNameDisplayLabel, BorderLayout.NORTH);

        // The panel that contains every other component than the username label
        // Split between upper panel and lower tabbed pane
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        // upper panel that contains the date dropdown and user's data, will be split in half between the two
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));

        // left and right sub panels in the upperPanel, respectively
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        JPanel userDataPanel = new JPanel();
        userDataPanel.setLayout(new BoxLayout(userDataPanel, BoxLayout.Y_AXIS));


        selectDayLabel = new JLabel("Select Day:");
        dateDropdown = new JComboBox<String>();

        datePanel.setMaximumSize(new Dimension(1000,225));

        datePanel.add(selectDayLabel);
        datePanel.add(dateDropdown);

        caloriesConsumedLabel = new JLabel("consumedplaceholder");
        caloriesConsumedLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        caloriesBurnedLabel = new JLabel("burnedplaceholder");
        caloriesBurnedLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        netCalorieIntakeLabel = new JLabel("netcalorieplaceholder");
        netCalorieIntakeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // place all data display labels in one panel
        JPanel userDataDisplayPanel = new JPanel();
        userDataDisplayPanel.setMaximumSize(new Dimension(1000,75));
        userDataDisplayPanel.add(caloriesConsumedLabel);
        userDataDisplayPanel.add(caloriesBurnedLabel);
        userDataDisplayPanel.add(netCalorieIntakeLabel);

        setCaloriesConsumedLabel = new JLabel("Manually Set The Calories Consumed:");
        setCaloriesConsumedField = new JTextField(5);
        setCaloriesConsumedButton = new JButton("Set");
        setCaloriesConsumedButton.setFocusable(false);

        // place all components related to setting calories consumed into one panel
        JPanel setCalConsumedPanel = new JPanel();
        setCalConsumedPanel.setMaximumSize(new Dimension(1000,75));
        setCalConsumedPanel.add(setCaloriesConsumedLabel);
        setCalConsumedPanel.add(setCaloriesConsumedField);
        setCalConsumedPanel.add(setCaloriesConsumedButton);

        setCaloriesBurnedLabel = new JLabel("Manually Set The Calories Burned:");
        setCaloriesBurnedField = new JTextField(5);
        setCaloriesBurnedButton = new JButton("Set");
        setCaloriesBurnedButton.setFocusable(false);

        // place all components related to setting calories burned into one panel
        JPanel setCalBurnedPanel = new JPanel();
        setCalBurnedPanel.setMaximumSize(new Dimension(1000,75));
        setCalBurnedPanel.add(setCaloriesBurnedLabel);
        setCalBurnedPanel.add(setCaloriesBurnedField);
        setCalBurnedPanel.add(setCaloriesBurnedButton);

        userDataPanel.add(userDataDisplayPanel);
        userDataPanel.add(setCalConsumedPanel);
        userDataPanel.add(setCalBurnedPanel);

        upperPanel.add(datePanel);
        upperPanel.add(userDataPanel);

        mainContentPanel.add(upperPanel);

        // Data entry section in the main view
        dataEntryPane = new JTabbedPane();
        dataEntryPane.setBackground(new Color(99, 177, 177));
        dataEntryPane.setForeground(new Color(69, 64, 64));
        // food panel
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(new BoxLayout( foodPanel, BoxLayout.Y_AXIS));
        foodPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        selectFoodLabel = new JLabel("Select Food Item:");
        selectFoodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        DatabaseDataManager db = new DatabaseDataManager();

        foodDropdown = new JComboBox(db.getFoods().toArray());
        foodDropdown.setMaximumSize(new Dimension(SIZE_X, 50));
        foodFilterLabel = new JLabel("Search:");
        foodFilterField = new JTextField(10);
        foodFilterField.setMaximumSize(new Dimension(SIZE_X, 50));
        // gets first item in list
        foodInfoLabel = new JLabel("Grams Per Serving: " + db.viewFood(db.getFoods().getFirst().getName()).getGramsPerServing() + ", Calories per serving: " + db.viewFood(db.getFoods().getFirst().getName()).getCaloriesPerServing());

        foodEatenLabel = new JLabel("Servings Eaten:");
        foodEatenField = new JTextField(5);
        foodEatenField.setMaximumSize(new Dimension(SIZE_X, 50));
        foodEatenField.setToolTipText("Enter positive number");
        foodEatenButton = new JButton("Enter");
        foodEatenButton.setFocusable(false);

        foodPanel.add(selectFoodLabel);

        foodPanel.add(foodDropdown);
        foodPanel.add(foodFilterLabel);
        foodPanel.add(foodFilterField);
        foodPanel.add(foodInfoLabel);
        foodPanel.add(foodEatenLabel);
        foodPanel.add(foodEatenField);
        foodPanel.add(foodEatenButton);


        // exercise panel
        JPanel exercisePanel = new JPanel();
        exercisePanel.setLayout(new BoxLayout(exercisePanel, BoxLayout.Y_AXIS));
        exercisePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        selectExerciseLabel = new JLabel("Select Exercise:");

        exerciseDropdown = new JComboBox(db.getExercises().toArray());
        exerciseDropdown.setMaximumSize(new Dimension(SIZE_X, 50));

        exerciseInfoLabel = new JLabel("exercise info Placeholder");
        exerciseDiagramButton = new JButton("View Diagram");
        exerciseDiagramButton.setFocusable(false);

        exercisePerformedLabel = new JLabel("exercise performed Placeholder");
        exercisePerformedField = new JTextField(5);
        exercisePerformedField.setMaximumSize(new Dimension(SIZE_X, 50));
        exercisePerformedField.setToolTipText("Enter positive number");
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


        dataEntryPane.setMaximumSize(new Dimension(1000,300));
        mainContentPanel.add(dataEntryPane);

        mainPanel.add(mainContentPanel);
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

    public JLabel getExercisePerformedLabel() {
        return exercisePerformedLabel;
    }

    public JTextField getFoodFilterField() {
        return foodFilterField;
    }
}
