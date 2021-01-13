package fr.sinikraft.magicwitchcraft.procedures;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class MagicalLavaAdditionalGenerationConditionProcedure extends MagicWitchcraftModElements.ModElement {
	public MagicalLavaAdditionalGenerationConditionProcedure(MagicWitchcraftModElements instance) {
		super(instance, 321);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if ((Math.random() < 0.01)) {
			return (true);
		}
		return (false);
	}
}
