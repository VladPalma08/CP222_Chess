import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static javax.swing.JLayeredPane.DEFAULT_LAYER;

public class mainInterface extends JFrame {

    JPanel mainPanel;
    JLayeredPane layeredPane;
    JPanel mainBoard;
    ImageIcon img;
    JLabel background;

    public mainInterface() {
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

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


