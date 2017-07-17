package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gradient {

    private final int cols;
    private final int rows;
    private final float vstep;
    private final float hstep;

    private final float[] gradient;

    private void clearGradient() {
        for (int i = 0; i < gradient.length; i++) {
            if (gradient[i] != Float.MIN_VALUE) {
                gradient[i] = Float.MAX_VALUE;
            }

        }
    }

    public interface CollisionSource {
        boolean isFree(float x, float y);
    }

    public Gradient(float width, float height) {
        int cols = 30;
        int rows = 20;
        this.cols = cols;
        this.rows = rows;
        vstep = width / cols;
        hstep = height / rows;
        gradient = new float[cols * rows];
    }

    public float getFloat(float col, float row) {
        return get((int) (col / hstep), (int) (row / vstep));
    }

    private float get(int col, int row) {
        if (col < 0 || col >= cols || row < 0 || row >= rows) {
            return Float.MIN_VALUE;
        }
        return gradient[row * cols + col];

    }

    private void set(int col, int row, float val) {
        if (col < 0 || col >= cols || row < 0 || row >= rows) {
            return;
        }
        gradient[row * cols + col] = val;
    }

    public void updateGradient(CollisionSource collisionSource) {
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                if (collisionSource.isFree(col * hstep, row * vstep)) {
                    set(col, row, Float.MAX_VALUE);
                } else {
                    set(col, row, Float.MIN_VALUE);
                }
            }
        }
        debugGradient();
    }

    public void update(float fromX, float fromY) {
        clearGradient();
        int col = (int) (fromX / hstep);
        int row = (int) (fromY / vstep);
        set(col, row, 1);
        update(col, row, 0);
        debugGradient();
    }

    private void debugGradient() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float k = get(col, row);
                if (k != Float.MIN_VALUE) {
                    sb.append('0');
                } else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }
        System.out.println("gradient: ");
        System.out.println(sb);
    }

    Direction[] directions = Direction.values();

    private void update(int col, int row, float distance) {
        float res = get(col, row);
        if (res == Float.MIN_VALUE) {
            return;
        }
        if (res > distance) {
            set(col, row, distance);
            for (Direction direction : directions) {
                update(col + direction.dx, row + direction.dy, (float) (distance + (direction.isCardinal() ? 1 : Math.sqrt(2))));
            }
        }
    }

    private static Random random = new Random();

    private Vector2 temp = new Vector2();

    public List<Vector2> move(Vector2 vec) {
        List<Vector2> vectors = new ArrayList<Vector2>();
        int col = (int) (vec.x / hstep);
        int row = (int) (vec.y / vstep);
        float res = get(col, row);;
        if (res == Float.MAX_VALUE) {
            return vectors;
        }
        while (res > 0 && res != Float.MIN_VALUE) {
            vectors.add(new Vector2(col * hstep, row * vstep));
            Direction best = null;
            float bestDistance = res;
            for (int i = 0; i < directions.length; i++) {
                Direction direction = directions[i];
                float target = get(col + direction.dx, row + direction.dy);
                if (target != Float.MIN_VALUE && target < bestDistance) {
                    bestDistance = target;
                    best = direction;
                }
            }
            if (best != null) {
                col += best.dx;
                row += best.dy;
            } else {
                break;
            }
            res = get(col, row);;
        } 

        return vectors;
    }

}
