package snake.graphics.window.internal;

import snake.graphics.basic.Color;
import snake.graphics.drawable.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class Renderer {
    private final List<Drawable> drawables;
    private final Graphics graphics;

    public Renderer(Graphics graphics) {
        drawables = synchronizedList(new ArrayList<>());
        this.graphics = graphics;
    }

    public void render() {
        synchronized (drawables) {
            for (Drawable d : drawables) {
                graphics.setColor(toAwtColor(d.getColor()));
                d.draw(graphics);
            }
        }
    }

    public void add(Drawable drawable) {
        drawables.add(drawable);
    }

    public void remove(Drawable drawable) {
        drawables.remove(drawable);
    }

    private java.awt.Color toAwtColor(Color color) {
        return switch (color) {
            case BLACK -> java.awt.Color.BLACK;
            case WHITE -> java.awt.Color.WHITE;
            case GREEN -> java.awt.Color.GREEN;
            case RED -> java.awt.Color.RED;
        };
    }
}
