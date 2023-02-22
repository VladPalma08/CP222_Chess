import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class menuInterface extends JFrame {

    JButton startWS;
    JButton startBS;
    JButton exit;

    Sounds sounds = new Sounds();

    public void menuButtons(JButton button, int x, int y, Dimension d, ActionListener handler) {
        button.addActionListener(handler);

        button.setBounds(x, y, d.width, d.height);
        button.setSize(d);

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setVisible(true);
        this.add(button);
    }

    public menuInterface() throws IOException {
        // playing main menu music
        sounds.playMenuMusic();

        // setting the panel's dimensions
        Dimension def = new Dimension(800,800);
        this.setUndecorated(true);
        this.setBounds(0,0,800,800);
        this.setPreferredSize(def);
        this.setMaximumSize(def);
        this.setMinimumSize(def);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        ImageIcon menuImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("menu_2.jpg")));
        JLabel background = new JLabel("", menuImage, JLabel.CENTER);
        background.setBounds(0, 0, 800, 800);

        this.setVisible(true);
        this.setContentPane(background);

        JButton startWS = new JButton();
        menuButtons(startWS, 203, 404, new Dimension(388, 63), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainInterface main = new mainInterface();
                    main.setAginstAi(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);

                }
                sounds.stopMenuMusic();
                menuInterface.super.setVisible(false);
            }
        });

        JButton startBS = new JButton();
        menuButtons(startBS, 203, 505, new Dimension(388, 63), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainInterface main = new mainInterface();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                sounds.stopMenuMusic();
                menuInterface.super.setVisible(false);
            }
        });

        JButton exit = new JButton();
        menuButtons(exit, 260, 611, new Dimension(280, 60), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.pack();
    }

    public static void main(String[] args) throws IOException {
        menuInterface main = new menuInterface();
    }
}
