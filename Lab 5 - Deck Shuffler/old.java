import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;




public class main {

    static int width = 1360;
    static int height = 768;

    public static class CardPanel extends JPanel{

        // painting occurs here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int startX = 40;
            int startY = 20;

            g2d.setColor(new Color(29, 92, 29));
            g2d.fillRect(startX, startY,width - 80,height - 100);

        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CardPanel panel = new CardPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the program exit the JVM on close

        panel.setLayout(null); // necessary to allow for custom positioning of button
        frame.setSize(width, height);
        frame.setResizable(false); // prevents changing window from changing res size

        //button to redraw
        JButton redrawButton = new JButton("Shuffle");
        redrawButton.addActionListener(new ActionListener() { //action for button
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });

        int locX = ((width/5) * 2);
        int locY = (height - 75);
        redrawButton.setLocation(locX, locY);
        redrawButton.setSize(272, 40);
        frame.getContentPane().add(panel);
        panel.add(redrawButton);

        frame.setVisible(true);

    }
}
