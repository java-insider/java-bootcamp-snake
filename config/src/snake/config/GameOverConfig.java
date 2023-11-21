package snake.config;

import snake.config.model.XY;

public record GameOverConfig(
    String textTemplate,
    String color,
    XY positionOffset
) {}
