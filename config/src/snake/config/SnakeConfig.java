package snake.config;

import snake.config.model.XY;

public record SnakeConfig(
    int initialPieces,
    int pieceSizeInPixels,
    XY initialPosition,
    int elongatePieces,
    String color,
    int initialSpeed,
    int incrementSpeed
) {}
