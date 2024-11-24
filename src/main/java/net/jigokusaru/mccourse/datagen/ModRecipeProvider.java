package net.jigokusaru.mccourse.datagen;

import net.jigokusaru.mccourse.MCCourseMod;
import net.jigokusaru.mccourse.block.ModBlocks;
import net.jigokusaru.mccourse.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    List<ItemLike> BLACK_OPAL_SMELTABLE = List.of(ModItems.RAW_BLACK_OPAL,
            ModBlocks.BLACK_OPAL_ORE,ModBlocks.BLACK_OPAL_END_ORE,ModBlocks.BLACK_OPAL_NETHER_ORE,ModBlocks.BLACK_OPAL_DEEPSLATE_ORE);


    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLACK_OPAL_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.BLACK_OPAL)
                .unlockedBy("has_block_opal", has(ModItems.BLACK_OPAL.get()))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACK_OPAL.get(), 9)
                .requires(ModBlocks.BLACK_OPAL_BLOCK.get())
                .unlockedBy("has_black_opal_block", has(ModBlocks.BLACK_OPAL_BLOCK.get()))
                .save(recipeOutput);

        oreSmelting(recipeOutput,BLACK_OPAL_SMELTABLE,RecipeCategory.MISC,ModItems.BLACK_OPAL.get(), 0.25f,200,"black_opal");
        oreBlasting(recipeOutput,BLACK_OPAL_SMELTABLE,RecipeCategory.MISC,ModItems.BLACK_OPAL.get(), 0.25f,100,"black_opal");


        slab(recipeOutput,RecipeCategory.BUILDING_BLOCKS,ModBlocks.BLACK_OPAL_SLAB.get(),ModBlocks.BLACK_OPAL_BLOCK.get());
        stairBuilder(ModBlocks.BLACK_OPAL_STAIRS.get(),Ingredient.of(ModBlocks.BLACK_OPAL_BLOCK.get())).group("black_opal")
                .unlockedBy("has_black_opal_block", has(ModBlocks.BLACK_OPAL_BLOCK.get())).save(recipeOutput);

        pressurePlate(recipeOutput, ModBlocks.BLACK_OPAL_PRESSURE_PLATE.get(), ModBlocks.BLACK_OPAL_BLOCK.get());
        buttonBuilder(ModBlocks.BLACK_OPAL_BUTTON.get(),Ingredient.of(ModBlocks.BLACK_OPAL_BLOCK)).group("black_opal")
                .unlockedBy("has_black_opal_block", has(ModBlocks.BLACK_OPAL_BLOCK.get())).save(recipeOutput);

        wall(recipeOutput,RecipeCategory.BUILDING_BLOCKS,ModBlocks.BLACK_OPAL_WALL.get(),ModBlocks.BLACK_OPAL_BLOCK);
        fenceBuilder(ModBlocks.BLACK_OPAL_FENCE.get(),Ingredient.of(ModBlocks.BLACK_OPAL_BLOCK)).group("black_opal")
                .unlockedBy("has_black_opal_block", has(ModBlocks.BLACK_OPAL_BLOCK.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.BLACK_OPAL_FENCE_GATE.get(),Ingredient.of(ModBlocks.BLACK_OPAL_BLOCK)).group("black_opal")
                .unlockedBy("has_black_opal_block", has(ModBlocks.BLACK_OPAL_BLOCK.get())).save(recipeOutput);
        doorBuilder(ModBlocks.BLACK_OPAL_DOOR.get(),Ingredient.of(ModItems.BLACK_OPAL)).group("black_opal")
                .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.BLACK_OPAL_TRAPDOOR.get(),Ingredient.of(ModItems.BLACK_OPAL)).group("black_opal")
                .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(recipeOutput);

        toolSetBuilder(ModItems.BLACK_OPAL, ModItems.BLACK_OPAL_SWORD, ModItems.BLACK_OPAL_SHOVEL, ModItems.BLACK_OPAL_PICKAXE, ModItems.BLACK_OPAL_AXE, ModItems.BLACK_OPAL_HOE, ModItems.BLACK_OPAL_PAXEL,ModItems.BLACK_OPAL_HAMMER, recipeOutput);



    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory recipeCategory, ItemLike result,
                                      float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, recipeCategory, result,
                experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory recipeCategory, ItemLike result,
                                      float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, recipeCategory, result,
                experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, MCCourseMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void toolSetBuilder(DeferredItem<Item> item,DeferredItem<Item> sword,DeferredItem<Item> shovel,DeferredItem<Item> pickaxe,DeferredItem<Item> axe,DeferredItem<Item> hoe,DeferredItem<Item> paxel,DeferredItem<Item> hammer,RecipeOutput recipeOutput){
        if(sword != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, sword)
                    .pattern("A")
                    .pattern("A")
                    .pattern("S")
                    .define('A', item)
                    .define('S', Items.STICK)
                    .unlockedBy("has_block_opal", has(item.get()))
                    .save(recipeOutput);
        }
        if(shovel != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                    .pattern("A")
                    .pattern("S")
                    .pattern("S")
                    .define('A', item)
                    .define('S', Items.STICK)
                    .unlockedBy("has_block_opal", has(item.get()))
                    .save(recipeOutput);
        }if(pickaxe != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                    .pattern("AAA")
                    .pattern(" S ")
                    .pattern(" S ")
                    .define('A', item)
                    .define('S', Items.STICK)
                    .unlockedBy("has_block_opal", has(item.get()))
                    .save(recipeOutput);
        }
        if(axe != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                    .pattern("AA")
                    .pattern("SA")
                    .pattern("S ")
                    .define('A', item)
                    .define('S', Items.STICK)
                    .unlockedBy("has_block_opal", has(item.get()))
                    .save(recipeOutput);
        }
        if(hoe != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                    .pattern("AA")
                    .pattern("S ")
                    .pattern("S ")
                    .define('A', item)
                    .define('S', Items.STICK)
                    .unlockedBy("has_block_opal", has(item.get()))
                    .save(recipeOutput);
        }
        if(paxel != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, paxel)
                    .pattern("ABC")
                    .pattern(" S ")
                    .pattern(" S ")
                    .define('A', pickaxe)
                    .define('B', axe)
                    .define('C', shovel)
                    .define('S', Items.STICK)
                    .unlockedBy("has_block_opal", has(item.get()))
                    .save(recipeOutput);
        }
        if(hammer != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hammer)
                    .pattern("AAA")
                    .pattern("ASA")
                    .pattern(" S ")
                    .define('A', item)
                    .define('S', Items.STICK)
                    .unlockedBy("has_block_opal", has(item.get()))
                    .save(recipeOutput);
        }
    }
}
