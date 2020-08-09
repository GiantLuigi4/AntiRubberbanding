package com.tfc.antirubberband.mixin;

import com.tfc.antirubberband.EntityTick;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
	
	@Shadow
	public abstract Vec3d getPosVector();
	
	@Shadow
	public abstract String getUuidAsString();
	
	@Inject(at = @At("HEAD"), method = "setPos")
	private void travel_start(CallbackInfo info) {
		EntityTick.tick(false, this.getUuidAsString(), this.getPosVector(), this);
	}
	
	@Inject(at = @At("TAIL"), method = "setPos")
	private void travel_end(CallbackInfo info) {
		EntityTick.tick(true, this.getUuidAsString(), this.getPosVector(), this);
	}
}
