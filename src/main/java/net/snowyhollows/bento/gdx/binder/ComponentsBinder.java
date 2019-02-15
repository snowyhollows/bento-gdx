/*
 * Copyright (c) 2009-2019 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.bento.gdx.binder;

import net.snowyhollows.bento.gdx.component.BoundingBoxFactory;
import net.snowyhollows.bento.gdx.component.ButtonFactory;
import net.snowyhollows.bento.gdx.component.CameraFocusFactory;
import net.snowyhollows.bento.gdx.component.CollisionFactory;
import net.snowyhollows.bento.gdx.component.DeadlyFactory;
import net.snowyhollows.bento.gdx.component.DebugDisplayFactory;
import net.snowyhollows.bento.gdx.component.EventTarget;
import net.snowyhollows.bento.gdx.component.EventTargetFactory;
import net.snowyhollows.bento.gdx.component.HeroFactory;
import net.snowyhollows.bento.gdx.component.LooksFactory;
import net.snowyhollows.bento.gdx.component.MortalFactory;
import net.snowyhollows.bento.gdx.component.NamedFactory;
import net.snowyhollows.bento.gdx.component.NoCollisionsFactory;
import net.snowyhollows.bento.gdx.component.PositionFactory;
import net.snowyhollows.bento.gdx.component.SpeedFactory;
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
	}
}
