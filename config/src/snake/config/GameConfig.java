package snake.config;

public record GameConfig(
    WindowConfig window,
    ApplicationConfig application,
    FoodConfig food,
    GameOverConfig gameOver,
    SnakeConfig snake
) {}
