import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import static javax.swing.JLayeredPane.DEFAULT_LAYER;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class mainInterface extends JFrame implements MouseListener, MouseMotionListener {

    JPanel mainPanel;
    JLayeredPane layeredPane;
    JPanel mainBoard;

    JButton menuButton;
    JButton newGame;
    JButton exit;

    int xAdjustment;
    int yAdjustment;

    Piece chessPiece;

    Game game = new Game();
    Piece[][] board = game.board;

    Sounds sounds = new Sounds();

    public mainInterface() {
        sounds.playGameMusic();

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
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("chess_2.jpg")));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 900, 990);

        this.setVisible(true);
        this.setContentPane(background);
        this.add(mainPanel);

        // creating a LayeredPane that holds a grid of JPanels, representing the individual chessboard squares
        layeredPane = new JLayeredPane();
        layeredPane.addMouseListener(this);
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
                menuInterface main = new menuInterface();
                sounds.stopGameMusic();
                mainInterface.super.setVisible(false);
            }
        });

        // creates a new instance of the game, resetting the board
        createButton(newGame, 105, 5, new Dimension(108, 40), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainInterface.super.setVisible(false);
                mainInterface newInstance = new mainInterface();
                sounds.stopGameMusic();
            }
        });

        // terminates the entire application
        createButton(exit, 220, 5, new Dimension(112, 40), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

//        ImageIcon duke = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("capturePiece.png")));
//        JLabel image = new JLabel("", duke, JLabel.CENTER);
//        image.setBounds(0, 0, 90, 90);
//        JPanel panel = (JPanel) mainBoard.getComponent( 20 );
//        panel.add(image);

        // adding the pieces to the board (are we keeping this?)
        refreshBoard();

        ImageIcon piece = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("chessMove.png")));
        JLabel image = new JLabel("", piece, JLabel.CENTER);
        image.setBounds(0, 0, 90, 90);
        JPanel panel = (JPanel) mainBoard.getComponent( 18);
        panel.add(image);

//        ImageIcon piece2 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("chessMove.png")));
//        JLabel image2 = new JLabel("", piece2, JLabel.CENTER);
//        image2.setBounds(0, 0, 90, 90);
//        JPanel panel2 = (JPanel) mainBoard.getComponent( 12);
//        panel2.add(image2);

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
    }

    public void boardUpdate(Piece p, int po) {
        // add pieces to individual squares
        ImageIcon duke = new ImageIcon(p.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(duke, JLabel.CENTER);
        image.setBounds(0, 0, 90, 90);
        JPanel panel = (JPanel) mainBoard.getComponent(po); // change index (currently 0) to access a specific square
        // add the image to the panel
        panel.add(image);
    }

    public void refreshBoard() {
        int po1 = 0;
        for (Piece[] p : board) {
            for (Piece p1 : p) {
                if (p1 != null) {
                    boardUpdate(p1, po1);
                }
                po1++;
            }
        }
        repaint();
    }

    public void boardRemove(int po) {
        JPanel panel = (JPanel) mainBoard.getComponent(po);
        // TODO: might need to change the below code to int po
        panel.remove(panel.getComponent(0));
    }

    public void setColor(ArrayList<Integer> squares) {
        for(Integer square: squares){
            mainBoard.getComponent(square).setBackground(Color.blue);
        }
    }

    public void movePiece(int pos1, int pos2) {
        Piece toMove = board[pos1 / 8][pos1 % 8];
        board[pos1 / 8][pos1 % 8] = null;
        board[pos2 / 8][pos2 % 8]=toMove;
        board[pos2 / 8][pos2 % 8].setpostion(pos2);
        boardRemove(pos1);
        refreshBoard();
        System.out.println(toMove.getPosition());
    }

    // THIS IS THE LINE
    // this is the method for changing the square color
    @Override
    public void mousePressed(MouseEvent e) {
        // setColor(getComponent());
        if (isLeftMouseButton(e)) {

             JLayeredPane panel = (JLayeredPane) e.getSource();
             Component c = panel.getComponentAt(e.getPoint());

             c.getComponentAt(c.getMousePosition().getLocation()).setBackground(Color.blue);



            System.out.println("testing");
        }
    }


    // unused MouseEvents from implementation
    public void mouseDragged(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

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


