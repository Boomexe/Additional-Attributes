package net.boomexe;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdditionalAttributes implements ModInitializer {
	public static final String MOD_ID = "additionalattributes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityAttribute ADDITIONAL_JUMPS = createEntityAttribute("additional_jumps", 0.0, 0.0, 16.0);
	public static final EntityAttribute SPRINT_SPEED = createEntityAttribute("sprint_speed", 0.30000001192092896, 0.01, 10);
	// STEP HEIGHT ATTRIBUTE BEING ADDED TO VANILLA IN FUTURE UPDATE
	public static final EntityAttribute STEP_HEIGHT = createEntityAttribute("step_height", 0.6, 0, 16);

	private static EntityAttribute createEntityAttribute(String name, double base, double min, double max) {
		return new ClampedEntityAttribute("attribute.name.generic." + MOD_ID + "." + name, base, min, max).setTracked(true);
	}

	@Override
	public void onInitialize() {
		Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID, "additional_jumps"), ADDITIONAL_JUMPS);
		Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID, "sprint_speed"), SPRINT_SPEED);
		// STEP HEIGHT ATTRIBUTE BEING ADDED TO VANILLA IN FUTURE UPDATER
		Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID, "step_height"), STEP_HEIGHT);
	}
}