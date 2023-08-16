package listeners;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import frames.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class KeyboardListener {

    private static Set<Integer> pressedKeys = new HashSet<>();

    public static void start() {
        try {
            GlobalScreen.setEventDispatcher(new SwingDispatchService());
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException idnored) {}

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent e) {
                int keyCode = e.getKeyCode();
                pressedKeys.add(keyCode);

                if (pressedKeys.size() > 1) {
                    StringBuilder combo = new StringBuilder();
                    for (int key : pressedKeys) {
                        combo.append(NativeKeyEvent.getKeyText(key)).append(" + ");
                    }
                    combo.setLength(combo.length() - 3);
                    FrameManager.keys.setText(String.valueOf(combo));
                } else {
                    String keyName = NativeKeyEvent.getKeyText(keyCode);
                    FrameManager.keys.setText(String.valueOf(keyName));
                }
            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent e) {
                int keyCode = e.getKeyCode();
                pressedKeys.remove(keyCode);
            }

            @Override
            public void nativeKeyTyped(NativeKeyEvent e) {}
        });
    }
}