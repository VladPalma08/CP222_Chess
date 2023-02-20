import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

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

    Game game = new Game();
    Piece[][] board = game.board;

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

    public void boardupdate(Piece p, int po) {

        // add pieces to individual squares
        ImageIcon duke = new ImageIcon(p.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(duke, JLabel.CENTER);
        image.setBounds(0, 0, 90, 90);
        JPanel panel = (JPanel) mainBoard.getComponent(po); // change index (currently 0) to access a specific square
        // add the image to the panel
        panel.add(image);

    }

    public void refreshBoard(){
        int po1 = 0;
        for (Piece[] p : board) {
            for (Piece p1 : p) {
                if (p1 != null) {
                    boardupdate(p1, po1);
                }
                po1++;
            }
        }
        repaint();
    }

    public void boardRemove(int po){

        JPanel panel = (JPanel) mainBoard.getComponent(po);
        panel.remove(panel.getComponent(0));


    }
    public void setcolor(ArrayList<Integer> squares){
        for(Integer square: squares){
            mainBoard.getComponent(square).setBackground(Color.blue);

        }


    }
    public void movepeice(int pos1, int pos2) {
        Piece tomove = board[pos1 / 8][pos1 % 8];
        board[pos1 / 8][pos1 % 8] = null;
        board[pos2 / 8][pos2 % 8]=tomove;
        board[pos2 / 8][pos2 % 8].setpostion(pos2);
        boardRemove(pos1);
        refreshBoard();
        System.out.println(tomove.getPosition());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (isLeftMouseButton(e)) {
//            Component pieceClicked = getComponentAt(e.getPoint());
//            for (Piece[] p : board) {
//                for (Piece p1 : p) {
//                     if(pieceClicked == mainBoard.getComponent(p1.getPosition())){
//                        JPanel panel = (JPanel) mainBoard.getComponent(game.getTile(p1));
//
//                    }
//                }
//            }
//        }
//        Move move = Move.
        //find a way to get component of clicked tile and use it as an int
        //invokeLater -> redraw board
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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


