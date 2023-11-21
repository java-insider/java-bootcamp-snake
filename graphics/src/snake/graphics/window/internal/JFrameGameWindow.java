package snake.graphics.window.internal;

import snake.graphics.drawable.Rect;

import javax.swing.*;
import java.awt.*;

public class JFrameGameWindow extends JFrame {
    private final Image buffer;
    private final Graphics graphics;
    private final Renderer renderer;
    private final Rect drawingArea;

    public JFrameGameWindow(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        buffer = createImage(width, height);
        graphics = buffer.getGraphics();
        renderer = new Renderer(graphics);

        int upperY = height - getContentPane().getSize().height;
        drawingArea = new Rect(0, upperY, width, height - upperY);
    }

    @Override
    public void paint(Graphics g) {
        if (graphics == null || renderer == null) {
            return;
        }

        renderer.render();
        g.drawImage(buffer, 0, 0, null);
    }

    public Rect getDrawingArea() {
        return drawingArea;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
