import javax.swing.*;
import java.awt.*;
import static javax.swing.JLayeredPane.DEFAULT_LAYER;

public class mainInterface extends JFrame {

    JPanel mainPanel;
    JLayeredPane layeredPane;
    JPanel mainBoard;

    public mainInterface() {
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
        this.setContentPane(mainPanel);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        mainPanel.add(layeredPane);
        layeredPane.setSize(740,740);

        mainBoard = new JPanel();
        mainBoard.setLayout(new GridLayout(8, 8));
        mainBoard.setPreferredSize(layeredPane.getSize());
        mainBoard.setBounds(0,0,layeredPane.getWidth(), layeredPane.getHeight());

        Color firstSqaure = Color.decode("#eeeed2");
        Color secondSquare = Color.decode("#769656");

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                JPanel boardSquare = new JPanel(new BorderLayout());
                boardSquare.setBackground((i + j) % 2 == 0 ? firstSqaure : secondSquare);
                mainBoard.add(boardSquare);
            }
        }

        layeredPane.add(mainBoard, DEFAULT_LAYER);
        mainPanel.setBounds(92, 80, 740, 740);

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


//    JPanel p = new JPanel() {
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            Dimension arcs = new Dimension(100, 100); //Border corners arcs {width,height}, change this to whatever you want
//            int width = getWidth();
//            int height = getHeight();
//            Graphics2D graphics = (Graphics2D) g;
//            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//            //Draws the rounded panel with borders.
//            graphics.setColor(Color.green);
//            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
//            graphics.setColor(Color.green);
//            graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border
//        }
//    };