package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Rectangle;
import net.snowyhollows.bento.gdx.util.LevelWalker;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;

public class Collision implements Component {

    public final static Family family = Family.all(Collision.class).get();
    public final static ComponentMapper<Collision> mapper = ComponentMapper.getFor(Collision.class);

    public float width = 16;
    public float height = 16;
    public float horizontalOffset;
    public float verticalOffset;
    public int vsubdivision = 1;
    public int hsubdivision = 1;

    public final Rectangle rect = new Rectangle();

    public Collision(float width, float height, float horizontalOffset, float verticalOffset) {
        this.width = width;
        this.height = height;
        this.horizontalOffset = horizontalOffset;
        this.verticalOffset = verticalOffset;
        rect.width = width;
        rect.height = height;
    }

    @WithFactory
    public Collision(
            @ByName("collision.width") float width,
            @ByName("collision.height") float height,
            @ByName("collision.horizontal_offset") float horizontalOffset,
            @ByName("collision.vertical_offset") float verticalOffset,
            @ByName("collision.hsubdivision") int hsubdivision,
            @ByName("collision.vsubdivision") int vsubdivision) {
        this.width = width;
        this.height = height;
        this.horizontalOffset = horizontalOffset;
        this.verticalOffset = verticalOffset;
        this.hsubdivision = hsubdivision;
        this.vsubdivision = vsubdivision;
        rect.width = width;
        rect.height = height;
    }

    private LevelWalker.CollisionPoint[] collisionPoints;

    public LevelWalker.CollisionPoint[] getCollisionPoints() {
        if (collisionPoints == null) {
            final float top = -(height / 2) + verticalOffset;
            final float bottom = (height / 2) + verticalOffset;
            final float left = -(width / 2) + horizontalOffset;

            final float right = (width / 2) + horizontalOffset;

            float hstep = width / hsubdivision;
            float vstep = height / vsubdivision;

            collisionPoints = new LevelWalker.CollisionPoint[(hsubdivision + 1) * (vsubdivision + 1)];

            int i = 0;
            for (int row = 0; row <= vsubdivision ; row++) {
                for (int col = 0; col <= hsubdivision; col++) {
                    collisionPoints[i++] =
                        new LevelWalker.CollisionPoint(
                                left + col * hstep,
                                top + row * vstep
                        );
                }
            }
        }
        return collisionPoints;
    }

}
