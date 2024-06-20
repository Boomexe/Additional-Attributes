package net.boomexe.mixin;

import net.boomexe.AdditionalAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow @Nullable public abstract EntityAttributeInstance getAttributeInstance(EntityAttribute attribute);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    private static final UUID SPRINTING_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");

    @Inject(method = "setSprinting", at = @At("TAIL"))
    private void modifySprintingSpeedBoost(boolean sprinting, CallbackInfo ci) {
        if (this.isPlayer()) {
            @Nullable final EntityAttributeInstance sprintSpeedBoost = this.getAttributeInstance(AdditionalAttributes.SPRINT_SPEED_BOOST);
            EntityAttributeModifier sprintingSpeedBoost = createSprintingSpeedBoost(sprintSpeedBoost.getValue());

            EntityAttributeInstance entityAttributeInstance = ((LivingEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            if (entityAttributeInstance.getModifier(SPRINTING_SPEED_BOOST_ID) != null) {
                entityAttributeInstance.removeModifier(sprintingSpeedBoost);
            }

            if (sprinting) {
                entityAttributeInstance.addTemporaryModifier(sprintingSpeedBoost);
            }
        }
    }

    private static EntityAttributeModifier createSprintingSpeedBoost(double sprintSpeedMultiplier) {
        return new EntityAttributeModifier(SPRINTING_SPEED_BOOST_ID, "Sprinting speed boost", sprintSpeedMultiplier, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
