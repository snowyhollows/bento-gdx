package net.snowyhollows.bento.gdx.system;


import net.snowyhollows.bento.gdx.util.LevelWalker;
import net.snowyhollows.bento.gdx.util.TileCollisionManagerFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum PhysicsFactory implements BentoFactory<Physics> {
    IT;

    @Override
    public Physics createInContext(Bento bento) {
        return new Physics(
                bento.get(TileCollisionManagerFactory.IT),
                new LevelWalker(),
                bento.getFloat("physics.min_vertical"),
                bento.getFloat("physics.gravity")
        );
    }
}
