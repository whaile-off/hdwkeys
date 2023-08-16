package frames;

import javax.swing.*;
import java.awt.*;

public class FrameManager {

    public static JFrame frame = new JFrame("HDWkeys");

    public static JLabel keys = text("start", 30f, 375, 70, 70);

    public static JPanel l = addPanel(27, 177, 156, 206, new ImageIcon(FrameManager.class.getClassLoader().getResource("l.png")).getImage());
    public static JPanel r = addPanel(197, 177, 161, 206, new ImageIcon(FrameManager.class.getClassLoader().getResource("r.png")).getImage());
    public static JPanel c = addPanel(165, 230, 50, 105, new ImageIcon(FrameManager.class.getClassLoader().getResource("c.png")).getImage());
    public static JPanel top = addPanel(80, 40, 220, 70, new ImageIcon(FrameManager.class.getClassLoader().getResource("top.png")).getImage());

    public static void start() {
        FlatLaf.start();

        createFrame(frame);

        frame.add(top, 0);
        frame.add(r, 1);
        frame.add(l, 2);
        frame.add(c, 0);

        keys.setHorizontalAlignment(JLabel.CENTER);
        frame.add(keys, 0);

        frame.setVisible(true);
    }

    public static JLabel text(String text, Float size, int w, int h, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(0, 35, w, h);
        label.setFont(label.getFont().deriveFont(size));
        return label;
    }

    private static void createFrame(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new OverlayLayout(frame.getContentPane()));
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
    }

    private static JPanel addPanel(int x, int y, int w, int h, Image img) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(img, 0, 0, this);
            }
        };

        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBounds(x, y, w, h);

        return panel;
    }
}