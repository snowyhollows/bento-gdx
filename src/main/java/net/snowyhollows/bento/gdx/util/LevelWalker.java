package net.snowyhollows.bento.gdx.util;

public class LevelWalker<T> {
    private float lastDx;
    private float lastDy;
    private T leftCollision;
    private T rightCollision;
    private T topCollision;
    private T bottomCollision;

    public static class CollisionPoint {

        public final float x;
        public final float y;

        public CollisionPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public interface Collider<T> {

        public T collisionAt(float x, float y);

        public boolean collides(float x, float y, float dx, float dy);

        public float borderAbove(float x, float y);

        public float borderBelow(float x, float y);

        public float borderLeft(float x, float y);

        public float borderRight(float x, float y);
    }

    private float x;
    private float y;

    public void jumpTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void move(Collider<T> collider, CollisionPoint[] collisionPoints, float dx, float dy) {
        leftCollision = null;
        rightCollision = null;
        topCollision = null;
        bottomCollision = null;

        if (dx < 0) {
            for (int i = 0; i < collisionPoints.length; i++) {
                CollisionPoint collisionPoint = collisionPoints[i];
                float targetX = x + dx + collisionPoint.x;
                float targetY = y + collisionPoint.y;
                if (collider.collides(targetX, targetY, dx, dy)) {
                    leftCollision = collider.collisionAt(targetX, targetY);
                    float allowableX = collider.borderRight(targetX, targetY);
                    dx = Math.max(dx, allowableX - x - collisionPoint.x);
                }
            }
        }

        if (dx > 0) {
            for (int i = 0; i < collisionPoints.length; i++) {
                CollisionPoint collisionPoint = collisionPoints[i];
                float targetX = x + dx + collisionPoint.x;
                float targetY = y + collisionPoint.y;
                if (collider.collides(targetX, targetY, dx, dy)) {
                    rightCollision = collider.collisionAt(targetX, targetY);;
                    float allowableX = collider.borderLeft(targetX, targetY);
                    dx = Math.min(dx, allowableX - x - collisionPoint.x - 0.01f);
                }
            }
        }
        
        if (dy > 0) {
            for (int i = 0; i < collisionPoints.length; i++) {
                CollisionPoint collisionPoint = collisionPoints[i];
                float targetX = x + dx + collisionPoint.x;
                float targetY = y + dy + collisionPoint.y;
                if (collider.collides(targetX, targetY, dx, dy)) {
                    topCollision = collider.collisionAt(targetX, targetY);;
                    float allowableY = collider.borderBelow(targetX, targetY);
                    dy = Math.min(dy, allowableY - y - collisionPoint.y - 0.01f);
                }
            }
        }
        if (dy < 0) {
            for (int i = 0; i < collisionPoints.length; i++) {
                CollisionPoint collisionPoint = collisionPoints[i];
                float targetX = x + dx + collisionPoint.x;
                float targetY = y + dy + collisionPoint.y;
                if (collider.collides(targetX, targetY, dx, dy)) {
                    bottomCollision = collider.collisionAt(targetX, targetY);;
                    float allowableY = collider.borderAbove(targetX, targetY);
                    dy = Math.max(dy, allowableY - y - collisionPoint.y);
                }
            }
        }
        x += dx;
        y += dy;
        
        this.lastDx = dx;
        this.lastDy = dy;
    }

    public float getLastDx() {
        return lastDx;
    }

    public float getLastDy() {
        return lastDy;
    }

    public boolean isLeftCollision() {
        return leftCollision != null;
    }

    public boolean isRightCollision() {
        return rightCollision != null;
    }

    public boolean isTopCollision() {
        return topCollision != null;
    }

    public boolean isBottomCollision() {
        return bottomCollision != null;
    }

    public T getLeftCollision() {
        return leftCollision;
    }

    public T getRightCollision() {
        return rightCollision;
    }

    public T getTopCollision() {
        return topCollision;
    }

    public T getBottomCollision() {
        return bottomCollision;
    }
}
