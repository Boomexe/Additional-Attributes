package net.boomexe.mixin;

import net.boomexe.AdditionalAttributes;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	@Inject(method = "createPlayerAttributes", at = @At("RETURN"))
	private static void addAdditionalAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
		info.getReturnValue().add(AdditionalAttributes.ADDITIONAL_JUMPS);
		info.getReturnValue().add(AdditionalAttributes.SPRINT_SPEED_BOOST);
		info.getReturnValue().add(AdditionalAttributes.STEP_HEIGHT);
	}
}