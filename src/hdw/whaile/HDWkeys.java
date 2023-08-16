import frames.FlatLaf;
import frames.FrameManager;
import listeners.KeyboardListener;
import listeners.MouseListener;

public class HDWkeys {
    public static void main(String[] args) {

        FlatLaf.start();
        FrameManager.start();

        MouseListener.start();
        KeyboardListener.start();

        while (true) {}
    }
}
