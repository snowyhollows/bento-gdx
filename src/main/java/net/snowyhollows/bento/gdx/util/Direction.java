package net.snowyhollows.bento.gdx.util;

public enum Direction {
    N(0,1), NE(1,1), E(1,0), SE(1,-1), S(0,-1), SW(-1,-1), W(-1,0), NW(-1,1);
    
    public final int dx;
    public final int dy;

    private Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    public boolean isCardinal() {
        return dx == 0 || dy == 0;
    }
    
    public Direction nextCardinalClockwise() {
        switch (this) {
            case N:
                return Direction.E;
            case E:
                return Direction.S;
            case S:
                return Direction.W;
            case W:
                return Direction.N;
        }
        throw new IllegalStateException("not cardinal direction: " + this);
    }
    
    
}
