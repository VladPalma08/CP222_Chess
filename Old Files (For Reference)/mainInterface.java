import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.ArrayList;
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
        this.setSize(900, 900);
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
        layeredPane.setSize(720, 720);

        mainBoard = new JPanel();

        mainBoard.setLayout(new GridLayout(8, 8));
        mainBoard.setPreferredSize(layeredPane.getSize());
        mainBoard.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());

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
        mainPanel.setBounds(98, 79, 720, 720);
        mainPanel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    public void setcolor(ArrayList<Integer> squares){
        for(Integer square: squares){
            mainBoard.getComponent(square).setBackground(Color.blue);
        }


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


