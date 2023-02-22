import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static javax.swing.JLayeredPane.DEFAULT_LAYER;
import static javax.swing.SwingUtilities.isLeftMouseButton;

public class mainInterface extends JFrame implements MouseListener, MouseMotionListener {

    JPanel mainPanel;
    JLayeredPane layeredPane;
    JPanel mainBoard;

    JButton menuButton;
    JButton newGame;
    JButton exit;

    Game game = new Game();
    Piece[][] board = game.board;
    Piece movedPiece;
    ColorOfPiece colorTurn=ColorOfPiece.WHITE;

    Sounds sounds = new Sounds();
    public boolean firstclick=true;
    public Integer pieceselected=null;
    public  ArrayList selectedmoves=null;
    boolean AginstAi=false;

    public void setAginstAi(boolean aginstAi) {
        AginstAi = aginstAi;
    }

    public mainInterface() throws IOException {
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
        layeredPane.addMouseListener(this);
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
        refreshBoard();
        mainPanel.setVisible(true);

        // creating JButtons and assigning them actions to execute when pressed
        JButton menuButton = new JButton();
        JButton newGame = new JButton();
        JButton exit = new JButton();

        // returns the user to the main menu (menuInterface)
        createButton(menuButton, 0, 5, new Dimension(100, 40), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuInterface main = new menuInterface();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                sounds.stopGameMusic();
                mainInterface.super.setVisible(false);
            }
        });

        // creates a new instance of the game, resetting the board
        createButton(newGame, 105, 5, new Dimension(108, 40), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainInterface.super.setVisible(false);
                try {
                    if(AginstAi) {
                        mainInterface newInstance = new mainInterface();
                        newInstance.setAginstAi(true);
                    }else{
                        mainInterface newInstance = new mainInterface();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

    public void backround(ArrayList input, int input1,int input2) {
        for(Object i : input){
            boardRemove((int) i);
        }
        for(Object i : input) {
            if (board[(int) i / 8][(int) i % 8] != null) {
                boardupdate(board[(int) i / 8][(int) i % 8], board[(int) i / 8][(int) i % 8].getPosition());
            }
        }
        Color firstSqaure = Color.decode("#eeeed2");
        Color secondSquare = Color.decode("#769656");

        Component boardSquare = mainBoard.getComponent(input2);
        boardSquare.setBackground((input2 / 8 + input2 % 8) % 2 == 0 ? firstSqaure : secondSquare);
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
        ImageIcon duke = new ImageIcon(p.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(duke, JLabel.CENTER);
        image.setBounds(0, 0, 90, 90);
        // change index (currently 0) to access a specific square
        JPanel panel = (JPanel) mainBoard.getComponent(po);
        // add the image to the panel
        panel.add(image);
    }

    public void refreshBoard(){
        for (Piece[] p : board) {
            for (Piece p1 : p) {
                if (p1 != null) {
                    boardupdate(p1, p1.getPosition());
                }
            }
        }
        repaint();
    }

    public void boardRemove(int po){
        JPanel panel = (JPanel) mainBoard.getComponent(po);
        Component[] compnents = panel.getComponents();
        //System.out.println(panel);
        for(Component i: compnents){
            panel.remove(i);
        }
        panel.revalidate();
        panel.repaint();
        //panel.remove(image);
    }

    public void setColor(ArrayList<Integer> squares){
        for(Integer square: squares){
            if(board[square/8][square%8]==null){
                ImageIcon duke = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("chessMove.png")));
                JLabel image = new JLabel("", duke, JLabel.CENTER);
                image.setBounds(0, 0, 90, 90);
                JPanel panel = (JPanel) mainBoard.getComponent(square);
                panel.add(image);
                mainBoard.repaint();
            }
            else {
                ImageIcon duke2 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("capturePiece.png")));
                JLabel image2 = new JLabel("", duke2, JLabel.CENTER);
                image2.setBounds(0, 0, 90, 90);
                JPanel panel2 = (JPanel) mainBoard.getComponent(square);
                panel2.add(image2);
                mainBoard.repaint();
            }
        }
    }

    public void movepeice(int pos1, int pos2) {
        if(board[pos2 / 8][pos2 % 8]!=null) {
            boardRemove(pos2);
            mainBoard.repaint();
            sounds.playCaptureSound();
        }
        else { sounds.playMoveSound(); }
        Piece tomove = board[pos1 / 8][pos1 % 8];
        board[pos1 / 8][pos1 % 8] = null;
        board[pos2 / 8][pos2 % 8]=tomove;
        board[pos2 / 8][pos2 % 8].setPostion(pos2);
        boardRemove(pos1);
        refreshBoard();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Color highlightSquare = Color.decode("#baca44");

        if (colorTurn == ColorOfPiece.WHITE) {
            if (isLeftMouseButton(e) && firstclick) {
                int x = e.getX();
                int y = e.getY();
                int ypanel = y / 90;
                int xpanel = x / 90;

                int panelnumber = (ypanel * 8) + xpanel;
                pieceselected = panelnumber;
                Piece selected = board[pieceselected / 8][pieceselected % 8];
                if (selected == null) {
                    return;
                }
                if (selected.getColor() == ColorOfPiece.WHITE) {
                    firstclick = false;
                    selectedmoves = board[ypanel][xpanel].legalMove(board);
                    setColor(selectedmoves);
                    mainBoard.getComponent(panelnumber).setBackground(highlightSquare);
                }

            } else if (!firstclick) {
                int x = e.getX();
                int y = e.getY();
                int ypanel = y / 90;
                int xpanel = x / 90;
                int panelnumber = (ypanel * 8) + xpanel;
                int moveselected;
                moveselected = panelnumber;
                if (pieceselected != null && board[pieceselected / 8][pieceselected % 8].legalMove(board).contains(moveselected) && !putincheck(game, pieceselected, moveselected, board[pieceselected / 8][pieceselected % 8].getColor())) {
                    movepeice(pieceselected, moveselected);
                    firstclick = true;
                    backround(selectedmoves, moveselected, pieceselected);
                    if (incheck(ColorOfPiece.BLACK)){
                        sounds.playCheckSound();
                        System.out.println("Black in check!");
                    }
                    if (incheckmate(ColorOfPiece.BLACK)){
                        sounds.playGameEndSound();
                        sounds.stopGameMusic();
                        System.out.println("BLACK IN CHECKMATE!");
                    }
                    colorTurn = ColorOfPiece.BLACK;
                    if(AginstAi){
                        Aiturn();
                        if (incheck(ColorOfPiece.BLACK)){
                            sounds.playCheckSound();
                            System.out.println("Black in check!");
                        }
                        if (incheckmate(ColorOfPiece.BLACK)){
                            sounds.playGameEndSound();
                            sounds.stopGameMusic();
                            System.out.println("BLACK IN CHECKMATE!");
                        }
                        colorTurn=ColorOfPiece.WHITE;
                    }
                }
                if (pieceselected == moveselected) {
                    firstclick = true;
                    backround(selectedmoves, moveselected, pieceselected);
                }
            }
        }
        else if (colorTurn == ColorOfPiece.BLACK) {
            if (isLeftMouseButton(e) && firstclick) {
                int x = e.getX();
                int y = e.getY();
                int ypanel = y / 90;
                int xpanel = x / 90;

                int panelnumber = (ypanel * 8) + xpanel;
                pieceselected = panelnumber;
                Piece selected = board[pieceselected / 8][pieceselected % 8];
                if (selected == null) {
                    return;
                }
                if (selected.getColor() == ColorOfPiece.BLACK) {
                    firstclick = false;
                    selectedmoves = board[ypanel][xpanel].legalMove(board);
                    setColor(selectedmoves);
                    mainBoard.getComponent(panelnumber).setBackground(highlightSquare);
                }

            } else if (!firstclick) {
                int x = e.getX();
                int y = e.getY();
                int ypanel = y / 90;
                int xpanel = x / 90;
                int panelnumber = (ypanel * 8) + xpanel;
                int moveselected;
                moveselected = panelnumber;
                if (pieceselected != null && board[pieceselected / 8][pieceselected % 8].legalMove(board).contains(moveselected) && !putincheck(game, pieceselected, moveselected, board[pieceselected / 8][pieceselected % 8].getColor())) {
                    movepeice(pieceselected, moveselected);
                    firstclick = true;
                    backround(selectedmoves, moveselected, pieceselected);
                    if (incheck(ColorOfPiece.WHITE)){
                        sounds.playCheckSound();
                        System.out.println("White in check!");
                    }
                    if (incheckmate(ColorOfPiece.WHITE)){
                        sounds.playGameEndSound();
                        sounds.stopGameMusic();
                        System.out.println("WHITE IN CHECKMATE!");
                    }
                    colorTurn = ColorOfPiece.WHITE;
                }
                if (pieceselected == moveselected) {
                    firstclick = true;
                    backround(selectedmoves, moveselected, pieceselected);
                }
            }
        }

    }

    private void Aiturn() {
        Random rand = new Random();
        ArrayList possiblpieces =game.getBlackPieces();
        int randomNum = rand.nextInt(possiblpieces.size());
        Piece selectpeice = (Piece) possiblpieces.get(randomNum);
        int piecetomove= selectpeice.getPosition();
        ArrayList possibleMoves=board[(piecetomove/8)][(piecetomove%8)].legalMove(board);
        if(possibleMoves.size()>0) {
                int randomNum1 = rand.nextInt(possibleMoves.size());
                int placetomove = (int) possibleMoves.get(randomNum1);
                if(!putincheck(game,piecetomove,placetomove,ColorOfPiece.BLACK)){
                movepeice(piecetomove, placetomove);
            }else{
                    Aiturn();
                }
        }else{
            Aiturn();
        }



    }

    public boolean putincheck(Game input, int current, int togo, ColorOfPiece currencolor){
        Game hypothitical = new Game(input.board);
        Piece movingpeice=hypothitical.board[current/8][current%8];
        hypothitical.board[current/8][current%8]=null;
        hypothitical.board[togo/8][togo%8]=movingpeice;
        movingpeice.setPostion(togo);
        int kingpostion = hypothitical.getking(currencolor);
        ArrayList<Integer> moves=null;
        if(currencolor==ColorOfPiece.WHITE){
            moves=hypothitical.getBlackMoves();
        }else if (currencolor==ColorOfPiece.BLACK){

            moves=hypothitical.getWhiteMoves();
        }
        if(moves.contains(kingpostion)){
            return true;
        }
        else {
            return false;
        }



    }

    public boolean incheck(ColorOfPiece color){
        ArrayList<Integer> moves=null;
        if(color==ColorOfPiece.WHITE){

            moves=game.getBlackMoves();
        }else if (color==ColorOfPiece.BLACK) {

            moves = game.getWhiteMoves();
        }
        if(moves.contains(game.getking(color))){
            return true;
        }else{
            return false;
        }
    }
    public boolean incheckmate(ColorOfPiece color){
        ArrayList<Integer> moves=null;
        if(color==ColorOfPiece.WHITE){

            moves=game.getBlackMoves();
        }else if (color==ColorOfPiece.BLACK) {

            moves = game.getWhiteMoves();
        }
        if(moves.contains(game.getking(color))&&moves.containsAll(board[game.getking(color)/8][game.getking(color)%8].legalMove(board))){
            return true;

        }
        else return false;

    }


    // unused methods from the Listener implementation
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) throws IOException {
        mainInterface test = new mainInterface();
    }

}




