package net.boomexe.mixin;

import net.boomexe.AdditionalAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract boolean isPlayer();

    @Inject(method = "getStepHeight", at = @At("RETURN"), cancellable = true)
    private void modifyStepHeight(CallbackInfoReturnable<Float> cir) {
        if (this.isPlayer()) {
            @Nullable final EntityAttributeInstance STEP_HEIGHT = ((LivingEntity) (Object) (this)).getAttributeInstance(AdditionalAttributes.STEP_HEIGHT);

            cir.setReturnValue(STEP_HEIGHT != null ? (float) STEP_HEIGHT.getValue() : cir.getReturnValue());
        }
    }
}
