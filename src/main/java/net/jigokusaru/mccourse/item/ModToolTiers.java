package net.jigokusaru.mccourse.item;

import net.jigokusaru.mccourse.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier BLACK_OPAL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BLACK_OPAL_TOOL, 600, 4f,1.5f,20,
            () -> Ingredient.of(ModItems.BLACK_OPAL));
}
