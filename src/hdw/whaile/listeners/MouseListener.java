package listeners;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import frames.FrameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MouseListener {

    public static void start() {
        try {
            GlobalScreen.setEventDispatcher(new SwingDispatchService());
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ignored) {}

        GlobalScreen.addNativeMouseListener(new NativeMouseListener() {
            @Override
            public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {}

            @Override
            public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
                if (nativeMouseEvent.getButton() == 1) {
                    dokey(FrameManager.l, "ln.png");
                } else if (nativeMouseEvent.getButton() == 2) {
                    dokey(FrameManager.r, "rn.png");
                } else {
                    dokey(FrameManager.c, "cn.png");
                }
            }

            @Override
            public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {}
        });
    }

    private static void dokey(JPanel panel, String imgname) {
        new Thread(() -> {
            setBackground(panel, imgname);

            try { sleep(700); } catch (InterruptedException ignored) {}

            resetBackground(panel);
        }).start();
    }

    private static void setBackground(JPanel panel, String imageName) {
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setLayout(new BorderLayout());

        BufferedImage image = loadImage(imageName);
        JLabel background = new JLabel(new ImageIcon(image));
        panel.add(background);
        panel.validate();
    }

    private static void resetBackground(JPanel panel) {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        JLabel background = new JLabel();
        panel.add(background);
        panel.validate();
    }

    private static BufferedImage loadImage(String imageName) {
        try {
            return ImageIO.read(FrameManager.class.getClassLoader().getResource(imageName));
        } catch (IOException ignored) {}
        return null;
    }
}