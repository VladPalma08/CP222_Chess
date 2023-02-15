import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class mainInterface extends JFrame {

    private JPanel mainPanel;

    public mainInterface() {
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
        this.setContentPane(mainPanel);

        mainPanel.setBounds(92, 80, 740, 740);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setVisible(true);

        JPanel[] gridHori = new JPanel[8];
        JPanel[] gridVerti = new JPanel[8];

        int forWidth = mainPanel.getWidth()/8;
        int forHeight = mainPanel.getHeight()/8;

        for(int i=0; i<gridHori.length; i++) {
            gridHori[i] = new JPanel();
            gridHori[i].setSize(forWidth,forHeight);
            gridHori[i].setBackground(Color.BLACK);
            mainPanel.add(gridHori[i]);
        }


//
//            JPanel testing = new JPanel();
//
//            testing.setSize(forWidth,forHeight);
//            testing.setBackground(Color.BLACK);
//
//            mainPanel.add(testing);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



//    public void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.drawRect(100, 0, 400, 400);
//        int z = 2;
//        int y = 0;
//        for (int i = 0; i < 8; i++) {
//            for (int x = 100; x < 550; x += 50) {
//                z++;
//                g2.drawRect(x, y, 50, 50);
//                if ((z % 2) == 0) {
//                    g2.fillRect(x, y, 50, 50);
//                }
//            }
//            y = y + 50;
//        }
//    }

    public static void main(String[] args) {
        mainInterface test = new mainInterface();
    }
}
