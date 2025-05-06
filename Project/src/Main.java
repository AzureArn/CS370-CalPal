import javax.swing.*;

// Driver class
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
                UIController controller = new UIController(view);
            }
        });
    }
}