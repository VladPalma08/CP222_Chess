import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Objects;

import static javax.swing.JLayeredPane.DEFAULT_LAYER;

public class mainInterface extends JFrame {

    JPanel mainPanel;
    JLayeredPane layeredPane;
    JPanel mainBoard;

    JButton menuButton;
    JButton newGame;
    JButton exit;

//    Method for creating buttons:
    public void createButton(JButton button, int x, int y, Dimension d, ActionEvent action) {
        button = new JButton("Testing");
        button.setFont(new Font("Tahoma", Font.ITALIC, 11));
        button.setForeground(Color.white);
        button.setBackground(Color.decode("#4c4c4c"));
        button.setBounds(x, y, d.width, d.height);
        button.setSize(d);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setVisible(true);

        button.addActionListener(e -> action.getActionCommand());

        this.add(button);
    }

    public mainInterface() {
        this.setUndecorated(true);
        Dimension def = new Dimension(900,900);
        this.setBounds(0,0,900,900);
        this.setPreferredSize(def);
        this.setMaximumSize(def);
        this.setMinimumSize(def);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setResizable(false);

        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("chess.jpg")));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, -45, 900, 990);

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

        layeredPane.add(mainBoard, DEFAULT_LAYER);
        mainPanel.setBounds(116, 91, 720, 720);

        // add pieces to individual squares
        ImageIcon duke = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("kb.svg")));
        JLabel image = new JLabel("", duke, JLabel.CENTER);
        image.setBounds(0, 0, 90, 90);

        mainPanel.setVisible(true);

        createButton(menuButton, 0,5, new Dimension(100,40), new ActionEvent(this, 0, "System.exit(0)"));

//        createButton(newGame, 105, 5, new Dimension(108,40));
//        createButton(exit, 220, 5, new Dimension(112,40));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String[] args) {
        mainInterface test = new mainInterface();
    }
}

//    mainBoard.getComponent( 15 ).setBackground(Color.blue);
//    mainBoard.getComponent( 50 ).setBackground(Color.orange);
//    mainBoard.getComponent( 26 ).setBackground(Color.pink);

//    change index (currently 0) to access a specific square
//    JPanel panel = (JPanel) mainBoard.getComponent( 0 );
//    panel.setBackground(Color.BLUE);
//    panel.add(image);

//    previous logic for creating chess squares
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