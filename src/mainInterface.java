import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static javax.swing.JLayeredPane.DEFAULT_LAYER;

public class mainInterface extends JFrame {

    // instantiating variables, panels and buttons
    JPanel mainPanel;
    JLayeredPane layeredPane;
    JPanel mainBoard;

    JButton menuButton;
    JButton newGame;
    JButton exit;

    public mainInterface() {
        // removing title bar from the program
        this.setUndecorated(true);

        // setting frame dimensions to maintain appearance among operating systems
        Dimension def = new Dimension(900,900);
        this.setBounds(0,0,900,900);
        this.setPreferredSize(def);
        this.setMaximumSize(def);
        this.setMinimumSize(def);

        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // creating a new instance of an image and setting the interface's background
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("chess.jpg")));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 900, 990);

        this.setVisible(true);
        this.setContentPane(background);
        this.add(mainPanel);

        // creating a LayeredPane that holds a grid of JPanels, representing the individual chessboard squares
        layeredPane = new JLayeredPane();
        mainPanel.add(layeredPane);
        mainPanel.setLayout(new BorderLayout());
        layeredPane.setSize(720,720);

        mainBoard = new JPanel();

        mainBoard.setLayout(new GridLayout(8, 8));
        mainBoard.setPreferredSize(layeredPane.getSize());
        mainBoard.setBounds(0,0,layeredPane.getWidth(), layeredPane.getHeight());

        // creating custom colors for the squares
        Color firstSqaure = Color.decode("#eeeed2");
        Color secondSquare = Color.decode("#769656");

        // main logic behind the creation of the panels, filling each square within the mainBoard 8 x 8 grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel boardSquare = new JPanel(new BorderLayout());
                boardSquare.setBackground((i + j) % 2 == 0 ? firstSqaure : secondSquare);
                mainBoard.add(boardSquare);
            }
        }

        layeredPane.add(mainBoard, DEFAULT_LAYER);
        mainPanel.setBounds(116, 91, 720, 720);

        mainPanel.setVisible(true);

        // creating JButtons and assigning them actions to execute when pressed
        JButton menuButton = new JButton();
        JButton newGame = new JButton();
        JButton exit = new JButton();

        // returns the user to the main menu (menuInterface)
        createButton(menuButton, 0, 5, new Dimension(100, 40), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainInterface.super.setVisible(false);
                menuInterface main = new menuInterface();
            }
        });

        // creates a new instance of the game, resetting the board
        createButton(newGame, 105, 5, new Dimension(108, 40), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainInterface.super.setVisible(false);
                mainInterface newInstance = new mainInterface();
            }
        });

        // terminates the entire application
        createButton(exit, 220, 5, new Dimension(112, 40), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.pack();
    }

    // creating buttons with similar characteristics, parameters for specific details like dimensions and position
    public void createButton(JButton button, int x, int y, Dimension d, ActionListener handler) {
        button.addActionListener(handler);

        button.setBounds(x, y, d.width, d.height);
        button.setSize(d);

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setVisible(true);
        this.add(button);

//        button.setFont(new Font("Tahoma", Font.ITALIC, 11));
//        button.setForeground(Color.white);
//        button.setBackground(Color.decode("#4c4c4c"));
    }

    public static void main(String[] args) {
        mainInterface test = new mainInterface();
    }
}

//    add pieces to individual squares
//    ImageIcon duke = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("kb.svg")));
//    JLabel image = new JLabel("", duke, JLabel.CENTER);
//        image.setBounds(0, 0, 90, 90);

//    mainBoard.getComponent( 15 ).setBackground(Color.blue);

//    previous logic for creating chess squares
//    JPanel[] gridHori = new JPanel[4];
//    JPanel[] gridVerti = new JPanel[4];
//
//    int forWidth = mainPanel.getWidth()/8;
//    int forHeight = mainPanel.getHeight()/8;
//    int count = 0;

//    for(int i = 0; i < gridHori.length; i++) {
//    gridHori[i] = new JPanel();
//    gridHori[i].setBounds(count,0,forWidth,forHeight);
//    gridHori[i].setBackground(Color.BLACK);
//    mainPanel.add(gridHori[i]);
//    count += (forWidth*2); }