package snake.game.scene;

import snake.config.FoodConfig;
import snake.graphics.basic.Color;
import snake.graphics.basic.Point;
import snake.graphics.drawable.Rect;

import static snake.game.util.RandomUtils.random;

public class Food extends Rect {
    private final Rect drawingArea;
    private final FoodConfig config;
    private int eatenTimes;

    public Food(Rect drawingArea, Snake snake, FoodConfig config) {
        this.drawingArea = drawingArea;
        this.config = config;

        moveToRandomLocation(snake);
        dimension(config.sizeInPixels(), config.sizeInPixels());
        setColor(Color.valueOf(config.color()));
    }

    public void moveToRandomLocation(Snake snake) {
        int distanceFromBorder = 5;

        do {
            int x = random(
                drawingArea.minX() + distanceFromBorder,
                drawingArea.maxX() - config.sizeInPixels() - distanceFromBorder
            );

            int y = random(
                drawingArea.minY() + distanceFromBorder,
                drawingArea.maxY() - config.sizeInPixels() - distanceFromBorder
            );

            location(new Point(x, y));
        } while (snake.intersects(this));
    }

    public void eatIfFood(Snake snake) {
        if (snake.intersects(this)) {
            eatenTimes++;
            moveToRandomLocation(snake);
            snake.elongate();
            snake.faster();
        }
    }

    public int getEatenTimes() {
        return eatenTimes;
    }
}
