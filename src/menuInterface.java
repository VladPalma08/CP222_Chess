import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class menuInterface extends JFrame {

    JButton button;

    public void createButton() {
        JButton button = new JButton();
        button.setFont(new Font("Tahoma", Font.ITALIC, 11));
        button.setForeground(Color.white);
        button.setText("Testing");
        button.setBackground(Color.decode("#4c4c4c"));
        button.setBounds(203,410,380,63 );
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setVisible(true);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainInterface board = new mainInterface();
            }
        });

        this.add(button);
    }

    public menuInterface() {
        // setting the panel's dimensions
        Dimension def = new Dimension(800,800);
        this.setUndecorated(true);
        this.setBounds(0,0,800,800);
        this.setPreferredSize(def);
        this.setMaximumSize(def);
        this.setMinimumSize(def);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        ImageIcon menuImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("menu.jpg")));
        JLabel background = new JLabel("", menuImage, JLabel.CENTER);
        background.setBounds(0, 0, 800, 800);

        this.setVisible(true);
        this.setContentPane(background);
        createButton();
        this.pack();
    }

    public static void main(String[] args) {
        menuInterface main = new menuInterface();
    }

}
