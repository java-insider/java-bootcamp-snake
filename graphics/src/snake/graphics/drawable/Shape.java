package snake.graphics.drawable;

import snake.graphics.basic.Color;
import snake.graphics.basic.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Shape extends Drawable {
    private final List<Rect> rects;

    public Shape(Color color) {
        super(color);
        rects = new ArrayList<>();
    }

    public void addRect(Rect rect) {
        rect.setColor(getColor());
        rects.add(rect);
    }

    public List<Rect> rects() {
        return rects;
    }

    public Rect firstRect() {
        return rects.getFirst();
    }

    public Rect lastRect() {
        return rects.getLast();
    }

    public boolean intersects(Rect other) {
        return rects
            .stream()
            .anyMatch(r -> r.intersects(other));
    }

    @Override
    public void draw(Graphics g) {
        rects.forEach(r -> r.draw(g));
    }

    public Rect duplicateRect(Rect baseRect, Point direction) {
        int baseX = baseRect.location().x();
        int baseY = baseRect.location().y();
        int baseWidth = baseRect.dimension().width();
        int baseHeight = baseRect.dimension().height();

        Point p = new Point(baseX + direction.x() * baseWidth, baseY + direction.y() * baseHeight);
        return new Rect(p, baseRect.dimension());
    }
}
