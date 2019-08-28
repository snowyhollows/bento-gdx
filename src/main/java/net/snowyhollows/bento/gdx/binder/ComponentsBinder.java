package net.snowyhollows.bento.gdx.binder;

import net.snowyhollows.bento.gdx.component.*;
import net.snowyhollows.bento2.Bento;

public class ComponentsBinder {

	private final Bento bento;

	public ComponentsBinder(Bento bento) {
		this.bento = bento;
	}

	public void bind() {
		bento.register("component.boundingBox", BoundingBoxFactory.IT);
		bento.register("component.button", ButtonFactory.IT);
		bento.register("component.cameraFocus", CameraFocusFactory.IT);
		bento.register("component.collision", CollisionFactory.IT);
		bento.register("component.deadly", DeadlyFactory.IT);
		bento.register("component.debugDisplay", DebugDisplayFactory.IT);
		bento.register("component.eventTarget", EventTargetFactory.IT);
		bento.register("component.hero", HeroFactory.IT);
		bento.register("component.looks", LooksFactory.IT);
		bento.register("component.mortal", MortalFactory.IT);
		bento.register("component.named", NamedFactory.IT);
		bento.register("component.noCollision", NoCollisionsFactory.IT);
		bento.register("component.noGravity", NoCollisionsFactory.IT);
		bento.register("component.position", PositionFactory.IT);
		bento.register("component.speed", SpeedFactory.IT);
		bento.register("component.rotation", RotationFactory.IT);
        bento.register("component.customDisplay", CustomDisplayFactory.IT);
	}
}
