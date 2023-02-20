import javax.swing.*;
import java.awt.*;

public class Chessboard {

    // setting a JFrame interface
    public void createBoard() {
        mainInterface main = new mainInterface();
//        main.mainPane
    }

//    protected void paintChildren(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.white);
//
//        int filledSquare = placeholder.getWidth()/8;
//
//        for (int i = 0; i < 4; i++) {
//            g2.fillRect(2 * i * filledSquare, 0, filledSquare, filledSquare);
//        }
//    }



    public static void main(String[] args) {
        Chessboard test = new Chessboard();
        test.createBoard();
    }


}
