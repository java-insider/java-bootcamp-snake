package snake.game.scene;

import snake.config.GameOverConfig;
import snake.config.WindowConfig;
import snake.graphics.basic.Color;
import snake.graphics.basic.Point;
import snake.graphics.drawable.Text;

public class GameOverText extends Text {

    public GameOverText(int score, WindowConfig windowConfig, GameOverConfig gameOverConfig) {
        super(
            String.format(gameOverConfig.textTemplate(), score),
            new Point(windowConfig.width() / 2 - 70, windowConfig.width() / 2),
            Color.valueOf(gameOverConfig.color())
        );
    }
}
