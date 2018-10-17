package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.math.Rectangle;

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
        T collisionWith(float x, float y);
        Rectangle collisionBox(float x, float y, Rectangle rectangle);
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

    private Rectangle tmpRect = new Rectangle();

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
                Rectangle collisionRect = collider.collisionBox(targetX, targetY, tmpRect);
                if (collisionRect != null) {
                    leftCollision = collider.collisionWith(targetX, targetY);
                    float allowableX = (float) collisionRect.x + collisionRect.width;
                    dx = Math.max(dx, allowableX - x - collisionPoint.x);
                }
            }
        }

        if (dx > 0) {
            for (int i = 0; i < collisionPoints.length; i++) {
                CollisionPoint collisionPoint = collisionPoints[i];
                float targetX = x + dx + collisionPoint.x;
                float targetY = y + collisionPoint.y;
                Rectangle collisionRect = collider.collisionBox(targetX, targetY, tmpRect);
                if (collisionRect != null) {
                    rightCollision = collider.collisionWith(targetX, targetY);;
                    float allowableX = (float) collisionRect.x;
                    dx = Math.min(dx, allowableX - x - collisionPoint.x - 0.01f);
                }
            }
        }
        
        if (dy > 0) {
            for (int i = 0; i < collisionPoints.length; i++) {
                CollisionPoint collisionPoint = collisionPoints[i];
                float targetX = x + dx + collisionPoint.x;
                float targetY = y + dy + collisionPoint.y;
                Rectangle collisionRect = collider.collisionBox(targetX, targetY, tmpRect);
                if (collisionRect != null) {
                    topCollision = collider.collisionWith(targetX, targetY);;
                    float allowableY = (float) collisionRect.y;
                    dy = Math.min(dy, allowableY - y - collisionPoint.y - 0.01f);
                }
            }
        }
        if (dy < 0) {
            for (int i = 0; i < collisionPoints.length; i++) {
                CollisionPoint collisionPoint = collisionPoints[i];
                float targetX = x + dx + collisionPoint.x;
                float targetY = y + dy + collisionPoint.y;
                Rectangle collisionRect = collider.collisionBox(targetX, targetY, tmpRect);
                if (collisionRect != null) {
                    bottomCollision = collider.collisionWith(targetX, targetY);;
                    float allowableY = (float) collisionRect.y + collisionRect.height;
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
