package snake.graphics.handler;

import snake.graphics.window.Key;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Consumer;

import static java.time.Instant.EPOCH;

public class KeyHandler {
    private final JFrame owner;
    private final int keyEventInterval;
    private KeyListener currentKeyListener;
    private Instant lastKeyboardEventTime;

    public KeyHandler(JFrame owner, int keyEventInterval) {
        this.owner = owner;
        this.lastKeyboardEventTime = EPOCH;
        this.keyEventInterval = keyEventInterval;
    }

    public void defineKeyPressedHandler(Consumer<Key> keyPressedHandler) {
        owner.removeKeyListener(currentKeyListener);

        currentKeyListener = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                Instant now = Instant.now();

                if (lastKeyboardEventTime.plusMillis(keyEventInterval).isBefore(now)) {
                    fromAwtKey(e.getKeyCode()).ifPresent(keyPressedHandler);
                    lastKeyboardEventTime = now;
                }
            }
        };
        owner.addKeyListener(currentKeyListener);
    }

    private Optional<Key> fromAwtKey(int key) {
        return switch (key) {
            case KeyEvent.VK_UP -> Optional.of(Key.UP);
            case KeyEvent.VK_DOWN -> Optional.of(Key.DOWN);
            case KeyEvent.VK_LEFT -> Optional.of(Key.LEFT);
            case KeyEvent.VK_RIGHT -> Optional.of(Key.RIGHT);
            case KeyEvent.VK_ESCAPE -> Optional.of(Key.ESC);
            default -> Optional.empty();
        };
    }
}
