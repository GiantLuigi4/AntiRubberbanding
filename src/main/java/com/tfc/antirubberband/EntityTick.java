package com.tfc.antirubberband;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class EntityTick {
	public static Vec3d pos1 = null;
	
	public static Vec3d tick(boolean end, String uuid, Vec3d pos, Object mixin) {
		if (MinecraftClient.getInstance().player != null && uuid.equals(MinecraftClient.getInstance().player.getUuidAsString())) {
			if (!end) {
				if (pos1 == null) {
					pos1 = pos;
				}
			} else if (pos1 != null && mixin instanceof PlayerEntity && pos1.distanceTo(pos) <= 20 && pos1.distanceTo(pos) >= (((PlayerEntity) mixin).getMovementSpeed() * 10f)) {
				try {
					((Entity) mixin).move(MovementType.SELF, pos1.subtract(pos));
					((Entity) mixin).updatePosition(pos1.x, pos1.y, pos1.z);
					((Entity) mixin).resetPosition(pos1.x, pos1.y, pos1.z);
					((Entity) mixin).setPos(pos1.x, pos1.y, pos1.z);
				} catch (Throwable ignored) {
				}
				pos1 = null;
			} else {
				pos1 = pos;
			}
		}
		return null;
	}
}
