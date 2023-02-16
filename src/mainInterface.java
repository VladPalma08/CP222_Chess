import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static javax.swing.JLayeredPane.DEFAULT_LAYER;

public class mainInterface extends JFrame {

    JPanel mainPanel;
    JLayeredPane layeredPane;
    JPanel mainBoard;
    ImageIcon img;
    JLabel background;
    BufferedImage myPicture;
    JLabel picLabel;

    public mainInterface() {
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("chess.jpg")));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 900, 900);

        this.setVisible(true);
        this.setContentPane(background);
        this.add(mainPanel);

        layeredPane = new JLayeredPane();
        mainPanel.add(layeredPane);
        mainPanel.setLayout(new BorderLayout());
        layeredPane.setSize(720,720);

        JButton button = new JButton("Menu");
        button.setFont(new Font("Tahoma", Font.ITALIC, 11));
        button.setForeground(Color.white);
        button.setBackground(Color.decode("#4c4c4c"));
        button.setSize(90,20);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setVisible(true);
        this.add(button);

        JButton button2 = new JButton("New Game");
        button2.setFont(new Font("Tahoma", Font.ITALIC, 11));
        button2.setForeground(Color.white);
        button2.setBackground(Color.decode("#4c4c4c"));
        button2.setSize(110,20);
        button2.setBounds(90,0,110,20);
//        button2.setOpaque(false);
//        button2.setContentAreaFilled(false);
//        button2.setBorderPainted(false);
        button2.setVisible(true);
        this.add(button2);

        mainBoard = new JPanel();

        mainBoard.setLayout(new GridLayout(8, 8));
        mainBoard.setPreferredSize(layeredPane.getSize());
        mainBoard.setBounds(0,0,layeredPane.getWidth(), layeredPane.getHeight());

        Color firstSqaure = Color.decode("#eeeed2");
        Color secondSquare = Color.decode("#769656");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel boardSquare = new JPanel(new BorderLayout());
                boardSquare.setBackground((i + j) % 2 == 0 ? firstSqaure : secondSquare);
                mainBoard.add(boardSquare);
            }
        }

        mainBoard.getComponent( 15 ).setBackground(Color.blue);
        mainBoard.getComponent( 50 ).setBackground(Color.orange);
        mainBoard.getComponent( 26 ).setBackground(Color.pink);

        layeredPane.add(mainBoard, DEFAULT_LAYER);
        mainPanel.setBounds(98, 79, 720, 720);



        // add pieces to individual squares
        ImageIcon duke = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("kb.jpg")));
        JLabel image = new JLabel("", duke, JLabel.CENTER);
        image.setBounds(0, 0, 90, 90);
        JPanel panel = (JPanel) mainBoard.getComponent( 0 ); // change index (currently 0) to access a specific square
        panel.setBackground(Color.BLUE);

        // add the image to the panel
        panel.add(image);

        mainPanel.setVisible(true);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        mainInterface test = new mainInterface();
    }
}

//    // previous logic for creating chess squares
//    JPanel[] gridHori = new JPanel[4];
//    JPanel[] gridVerti = new JPanel[4];
//
//    int forWidth = mainPanel.getWidth()/8;
//    int forHeight = mainPanel.getHeight()/8;
//    int count = 0;
//
//        for(int i = 0; i < gridHori.length; i++) {
//        gridHori[i] = new JPanel();
//        gridHori[i].setBounds(count,0,forWidth,forHeight);
//        gridHori[i].setBackground(Color.BLACK);
//        mainPanel.add(gridHori[i]);
//        count += (forWidth*2);
//        }


