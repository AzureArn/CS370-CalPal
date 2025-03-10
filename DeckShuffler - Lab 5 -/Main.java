import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;
import javax.swing.ImageIcon;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

    public static class CardPanel extends JPanel {
        private ArrayList<ImageIcon> cardImages = new ArrayList<>();
        private ArrayList<JLabel> cardLabels = new ArrayList<>();

        public CardPanel() {
            setLayout(null);
            loadCardImages();
            displayCards();
        }

        // Loads all PNG images from the 'cards' directory into the 'cardImages' list
        private void loadCardImages() {
            /*  relative directory starts from current working directory,
                this value differs depending on the code running environment,
                this is why it didn't work once the code moved machines
                System.getProperty("user.dir") gives current working directory
            * */
            // search for the right directory using walk
            String cardsDir = "";
            try {
                Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.dir")));
                paths = paths.filter(str -> str.toString().endsWith("DeckShuffler - Lab 5 -/cards"));
                Optional<Path> p = paths.findAny();
                if (p.isPresent()) {
                    cardsDir = p.get().toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

            File folder = new File(cardsDir);
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            if (files != null) {
                for (File file : files) {
                    cardImages.add(new ImageIcon(file.getAbsolutePath()));
                }
            } else {
                System.out.println("It was null");
                System.exit(1);
            }
        }

        // Positions and displays the card images on the panel in a grid-like layout
        private void displayCards() {
            int startX = 50;
            int startY = 50;
            int cardWidth = 89; //100;
            int cardHeight = 134; //150;
            int gap = 15;
            int rowSize = 13;

            for (JLabel label : cardLabels)
                remove(label);

            cardLabels.clear();

            // Resizes the card images and creates JLabels to display them
            for (int i = 0; i < cardImages.size(); i++) {
                Image img = cardImages.get(i).getImage().getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(img);

                JLabel label = new JLabel(resizedIcon);
                label.setBounds(startX + (i % rowSize) * (cardWidth + gap), startY + (i / rowSize) * (cardHeight + gap), cardWidth, cardHeight);
                cardLabels.add(label);
                add(label);
            }
            revalidate();
            repaint();
        }

        // Shuffles the order of the card images and updates the display
        public void shuffleCards() {
            Collections.shuffle(cardImages);
            displayCards();
        }

        // painting occurs here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int startX = 20;
            int startY = 20;
            int width = getWidth() - (2 * startX);
            int height = getHeight() - (2 * startY);

            g2d.setColor(new Color(29, 92, 29));
            g2d.fillRect(startX, startY, width, height);

        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CardPanel panel = new CardPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the program exit the JVM on close

        int sizeX = 1450; // 1920; // A standard resolution to use
        int sizeY = 815; // 1080;

        panel.setLayout(null); // necessary to allow for custom positioning of button
        frame.setSize(sizeX, sizeY);
        frame.setResizable(false); // prevents changing window from changing res size

        //button to redraw
        JButton redrawButton = new JButton("Shuffle");
        redrawButton.addActionListener(new ActionListener() { //action for button
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.shuffleCards();
            }
        });

        int locX = (sizeX / 3) + (sizeX / 9);
        int locY = (sizeY - 150);
        redrawButton.setLocation(locX, locY);
        redrawButton.setSize(150, 75);
        frame.getContentPane().add(panel);
        panel.add(redrawButton);

        frame.setVisible(true);


    }
}