package snake.graphics.drawable;

import snake.graphics.basic.Color;
import snake.graphics.basic.Point;

import java.awt.*;

public class Text extends Drawable {
    private final String text;
    private final Point location;

    public Text(String text, Point location, Color color) {
        super(color);
        this.text = text;
        this.location = location;
    }

    @Override
    public void draw(Graphics g) {
        g.drawString(text, location.x(), location.y());
    }
}
