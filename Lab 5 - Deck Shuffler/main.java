import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;




public class Main {

    public static class CardPanel extends JPanel{

        // painting occurs here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(new Color(0,200,0));
            g2d.draw(new Rectangle2D.Double(0,0,500, 500));

        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CardPanel panel = new CardPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the program exit the JVM on close
        int size = 500;
        panel.setLayout(null); // necessary to allow for custom positioning of button
        frame.setSize(size, size);
        frame.setResizable(false); // prevents changing window from 500x500 size
        //button to redraw
        JButton redrawButton = new JButton("Redraw");
        redrawButton.addActionListener(new ActionListener() { //action for button
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });

        int locX = 0;
        int locY = size - (size / 5);
        redrawButton.setLocation(locX, locY);
        redrawButton.setSize(500, 50);
        frame.getContentPane().add(panel);
        panel.add(redrawButton);

        frame.setVisible(true);

    }
}
