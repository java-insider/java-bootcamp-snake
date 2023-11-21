package snake.game.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snake.config.GameConfig;
import snake.config.loader.ConfigLoader;
import snake.game.scene.Background;
import snake.game.scene.Food;
import snake.game.scene.GameOverText;
import snake.game.scene.Snake;
import snake.graphics.basic.Color;
import snake.graphics.window.GameWindow;

public class Game {
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    private static final int MAX_SNAKE_SPEED = 50;

    private GameWindow gameWindow;
    private Snake snake;
    private Food food;
    private GameConfig gameConfig;
    private volatile boolean running;

    public void start() {
        LOGGER.debug("Starting the game....");

        gameConfig = ConfigLoader.load();
        LOGGER.debug("Game config loaded: {}", gameConfig);

        gameWindow = new GameWindow(
            gameConfig.window().title(),
            gameConfig.window().width(),
            gameConfig.window().height(),
            gameConfig.application().minMillisBetweenKeyPressedEvents()
        );

        setupKeyPressedHandler();
        setupWindowClosedHandler();
        addElementsToScreen();
        startGameLoop();
    }

    private void setupKeyPressedHandler() {
        gameWindow.onKeyPressed(k -> {
            LOGGER.debug("Key pressed: {}", k);
            switch (k) {
                case UP -> snake.up();
                case DOWN -> snake.down();
                case LEFT -> snake.left();
                case RIGHT -> snake.right();
                case ESC -> gameWindow.close();
            }
        });
    }

    private void setupWindowClosedHandler() {
        gameWindow.onWindowClosed(() -> running = false);
    }

    private void addElementsToScreen() {
        gameWindow.addDrawable(
            new Background(
                gameConfig.window().width(),
                gameConfig.window().height(),
                Color.valueOf(gameConfig.window().backgroundColor())
            )
        );

        snake = new Snake(gameConfig.snake());
        gameWindow.addDrawable(snake);

        food = new Food(gameWindow.drawingArea(), snake, gameConfig.food());
        gameWindow.addDrawable(food);
    }

    private void startGameLoop() {
        running = true;
        do {
            updateScene();
        } while (!isGameOver());

        processGameOver();

        LOGGER.debug("The End!");
    }

    private void updateScene() {
        snake.move();
        food.eatIfFood(snake);
        gameWindow.update();
        sleep(MAX_SNAKE_SPEED - snake.speed());
    }

    private boolean isGameOver() {
        return !running || snake.collidedWithItself() || snake.collidedWithWindowBounds(gameWindow.drawingArea());
    }

    private void processGameOver() {
        gameWindow.removeDrawable(snake);
        gameWindow.removeDrawable(food);
        gameWindow.addDrawable(new GameOverText(food.getEatenTimes(), gameConfig.window(), gameConfig.gameOver()));
        gameWindow.update();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
