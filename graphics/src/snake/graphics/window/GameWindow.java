package snake.graphics.window;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snake.graphics.drawable.Drawable;
import snake.graphics.drawable.Rect;
import snake.graphics.handler.KeyHandler;
import snake.graphics.handler.WindowHandler;
import snake.graphics.window.internal.JFrameGameWindow;

import java.util.function.Consumer;

public class GameWindow {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameWindow.class);

    private final JFrameGameWindow window;
    private final KeyHandler keyHandler;
    private final WindowHandler windowHandler;

    public GameWindow(String title, int width, int height, int keyEventInterval) {
        window = new JFrameGameWindow(title, width, height);
        keyHandler = new KeyHandler(window, keyEventInterval);
        windowHandler = new WindowHandler(window);
    }

    public void addDrawable(Drawable drawable) {
        LOGGER.debug("Adding drawable: {}", drawable.getName());
        window.getRenderer().add(drawable);
    }

    public void removeDrawable(Drawable drawable) {
        LOGGER.debug("Removing drawable: {}", drawable.getName());
        window.getRenderer().remove(drawable);
    }

    public Rect drawingArea() {
        return window.getDrawingArea();
    }

    public void update() {
        window.repaint();
    }

    public void onKeyPressed(Consumer<Key> keyPressedHandler) {
        keyHandler.defineKeyPressedHandler(keyPressedHandler);
    }

    public void onWindowClosed(Runnable windowClosedHandler) {
        windowHandler.defineWindowClosedHandler(windowClosedHandler);
    }

    public void close() {
        window.dispose();
    }
}
