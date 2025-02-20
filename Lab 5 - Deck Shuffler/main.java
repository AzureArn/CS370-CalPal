import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;




public class main {

    public static class CardPanel extends JPanel{

        // painting occurs here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int startX = 20;
            int startY = 20;
            int width = getWidth();
            int height = getHeight();

            g2d.setColor(new Color(29, 92, 29));
            g2d.fillRect(startX, startY, width, height);
            g2d.draw(new Rectangle2D.Double(startX, startY, width, height));

        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CardPanel panel = new CardPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the program exit the JVM on close

        int sizeX = 1920; // A standard resolution to use
        int sizeY = 1080;

        panel.setLayout(null); // necessary to allow for custom positioning of button
        frame.setSize(sizeX, sizeY);
        frame.setResizable(false); // prevents changing window from changing res size

        //button to redraw
        JButton redrawButton = new JButton("Shuffle");
        redrawButton.addActionListener(new ActionListener() { //action for button
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });

        int locX = (sizeX / 2);
        int locY = (sizeY-100);
        redrawButton.setLocation(locX, locY);
        redrawButton.setSize(500, 50);
        frame.getContentPane().add(panel);
        panel.add(redrawButton);

        frame.setVisible(true);

    }
}
