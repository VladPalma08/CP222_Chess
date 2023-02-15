import javax.swing.*;
import java.awt.*;

public class mainInterface extends JFrame {

    private JPanel mainPanel;

    public mainInterface() {
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
        this.setContentPane(mainPanel);

        mainPanel.setBounds(92, 80, 740, 740);
//        mainPanel.setSize(740,740);
//        mainPanel.setLocation(90,80);
        mainPanel.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void paintChildren(Graphics g) {
//        super.paintChildren(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);

        int filledSquare = mainPanel.getWidth()/8;

        for (int i = 0; i < 4; i++) {
            g2.fillRect(2 * i * filledSquare, 0, filledSquare, filledSquare);
        }
    }

    public static void main(String[] args) {
        mainInterface test = new mainInterface();
    }
}
