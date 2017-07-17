package net.snowyhollows.bento.gdx.util;

public class LevelWalker {
    private float lastDx;
    private float lastDy;
    private boolean leftCollision;
    private boolean rightCollision;
    private boolean topCollision;
    private boolean bottomCollision;

    public static class CollisionPoint {

        public final float x;
        public final float y;

        public CollisionPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public interface Collider {

        public boolean collides(float x, float y);

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

    public void move(Collider collider, CollisionPoint[] collisionPoints, float dx, float dy) {
        leftCollision = false;
        rightCollision = false;
        topCollision = false;
        bottomCollision = false;

        if (dx < 0) {
            for (int i = 0; i < collisionPoints.length; i++) {
                CollisionPoint collisionPoint = collisionPoints[i];
                float targetX = x + dx + collisionPoint.x;
                float targetY = y + collisionPoint.y;
                if (collider.collides(targetX, targetY)) {
                    leftCollision = true;
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
                if (collider.collides(targetX, targetY)) {
                    rightCollision = true;
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
                if (collider.collides(targetX, targetY)) {
                    topCollision = true;
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
                if (collider.collides(targetX, targetY)) {
                    bottomCollision = true;
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
        return leftCollision;
    }

    public boolean isRightCollision() {
        return rightCollision;
    }

    public boolean isTopCollision() {
        return topCollision;
    }

    public boolean isBottomCollision() {
        return bottomCollision;
    }
}
