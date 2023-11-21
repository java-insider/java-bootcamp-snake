package snake.game.core;

import snake.graphics.basic.Point;

public enum Direction {
    NONE(0, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    final Point point;

    Direction(int x, int y) {
        point = new Point(x, y);
    }

    public boolean canChangeTo(Direction other) {
        return !(point.x() + other.point.x() == 0 && point.y() + other.point.y() == 0);
    }

    public Point point() {
        return point;
    }
}
