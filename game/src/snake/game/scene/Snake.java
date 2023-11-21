package snake.game.scene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snake.config.SnakeConfig;
import snake.game.core.Direction;
import snake.graphics.basic.Color;
import snake.graphics.basic.Dimension;
import snake.graphics.basic.Point;
import snake.graphics.drawable.Rect;
import snake.graphics.drawable.Shape;

import static snake.game.core.Direction.*;

public class Snake extends Shape {
    private static final Logger LOGGER = LoggerFactory.getLogger(Snake.class);

    private final SnakeConfig config;

    private Direction direction;
    private int piecesToElongate;
    private int speed;

    public Snake(SnakeConfig config) {
        super(Color.valueOf(config.color()));

        this.config = config;
        direction = NONE;
        speed = 10;

        Point p = new Point(config.initialPosition().x(), config.initialPosition().y());
        Dimension d = new Dimension(config.pieceSizeInPixels(), config.pieceSizeInPixels());

        Rect rect = new Rect(p, d);
        addRect(rect);

        Point direction = new Point(-1, 0);

        for (int i = 0; i < config.initialPieces(); i++) {
            rect = duplicateRect(rect, direction);
            addRect(rect);
        }
    }

    public void move() {
        if (direction != NONE) {
            Rect head = firstRect();
            Rect tail = lastRect();
            Rect.shift(rects());

            Rect newHead = duplicateRect(head, direction.point());
            rects().set(0, newHead);

            if (piecesToElongate > 0) {
                rects().add(tail);
                piecesToElongate--;
            }
        }
    }

    public void elongate() {
        LOGGER.debug("Elongating...");
        piecesToElongate = config.elongatePieces();
    }

    public void faster() {
        speed += config.incrementSpeed();
        LOGGER.debug("Faster! {}", speed);
    }

    public void up() {
        if (direction.canChangeTo(UP)) {
            direction = UP;
        }
    }

    public void down() {
        if (direction.canChangeTo(DOWN)) {
            direction = DOWN;
        }
    }

    public void left() {
        if (direction.canChangeTo(LEFT) && direction != NONE) {
            direction = LEFT;
        }
    }

    public void right() {
        if (direction.canChangeTo(RIGHT)) {
            direction = RIGHT;
        }
    }

    public boolean collidedWithItself() {
        Rect head = firstRect();

        boolean collided = rects()
            .stream()
            .skip(1)
            .anyMatch(head::intersects);

        if (collided) {
            LOGGER.debug("Collided with itself!");
        }

        return collided;
    }

    public boolean collidedWithWindowBounds(Rect drawingArea) {
        boolean collided = false;

        int pieceSize = config.pieceSizeInPixels();
        Rect head = firstRect();

        int headX = head.location().x();
        int headY = head.location().y();

        int areaX1 = drawingArea.minX();
        int areaY1 = drawingArea.minY() - pieceSize * 2;

        int areaX2 = drawingArea.maxX();
        int areaY2 = drawingArea.maxY();

        if (headX <= areaX1 || headX + pieceSize >= areaX2) {
            collided = true;
        }

        if (headY <= areaY1 || headY + pieceSize >= areaY2) {
            collided = true;
        }

        if (collided) {
            LOGGER.debug("Collided with window!");
        }

        return collided;
    }

    public int speed() {
        return speed;
    }
}
