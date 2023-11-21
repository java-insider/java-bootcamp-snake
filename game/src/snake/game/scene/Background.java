package snake.game.scene;

import snake.graphics.basic.Color;
import snake.graphics.drawable.Rect;

public class Background extends Rect {

    public Background(int width, int height, Color color) {
        super(0, 0, width, height);
        setColor(color);
    }
}
