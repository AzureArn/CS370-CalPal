import javax.swing.*;
import java.awt.*;

// view class, creates components to be interacted with by UIController
public class View {
    private JFrame frame = new JFrame("Cal Pal");
    // the login view
    private JPanel loginPanel = new JPanel();
    // the main view
    private JPanel mainPanel = new JPanel();

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
    private JComboBox dateDropdown;
    private JLabel dateLabel;
    private JLabel caloriesConsumedLabel;
    private JLabel caloriesBurnedLabel;

    private JTabbedPane dataEntryPane;


    public View(){
        // TODO: give the view a proper layout later
        // loginPanel.setLayout(new GridBagLayout());
        // mainPanel.setLayout(new GridBagLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the program exit the JVM on close

        final int SIZE_X = 1000; // 1920; // A standard resolution to use
        final int SIZE_Y = 800; // 1080;
        frame.setSize(SIZE_X, SIZE_Y);
        //frame.setResizable(false); // prevents changing window from changing res size
        frame.setLocationRelativeTo(null); // makes the window centered upon creation, only do after sizing the frame


        // ***LOGIN VIEW SECTION***
        // username input components
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

        // only add the loginPanel the frame to start
        frame.getContentPane().add(loginPanel);

        //***MAIN VIEW SECTION***
        userNameDisplayLabel = new JLabel();

        JLabel selectDayLabel = new JLabel("Select Day:");
        JComboBox dateDropdown = new JComboBox();
        JLabel dateLabel = new JLabel("dateplaceholder");
        JLabel caloriesConsumedLabel = new JLabel("consumedplaceholder");
        JLabel caloriesBurnedLabel = new JLabel("burnedplaceholder");

        JTabbedPane dataEntryPane = new JTabbedPane();


        mainPanel.add(userNameDisplayLabel);
        mainPanel.add(selectDayLabel);
        mainPanel.add(dateDropdown);
        mainPanel.add(dateLabel);
        mainPanel.add(caloriesConsumedLabel);
        mainPanel.add(caloriesBurnedLabel);

        mainPanel.add(dataEntryPane);

        frame.setVisible(true);
    }

    // swaps from the login view to the main view
    public void swapView(){
        // remove first panel, add the second, then revalidate to change view to main screen
        frame.getContentPane().remove(loginPanel);
        frame.getContentPane().add(mainPanel);
        frame.revalidate();
    }

    // Getters for various components
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
}
