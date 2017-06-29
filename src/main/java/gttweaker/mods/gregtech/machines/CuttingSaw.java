package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
import static gttweaker.util.ArrayHelper.itemOrNull;

import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the Saw recipes.
 *
 * @author DreamMasterXXL
 * @author bculkin2442
 */
@ZenClass("mods.gregtech.CuttingSaw")
@ModOnly(MOD_ID)
public class CuttingSaw {
    /**
     * Adds a Cutting Saw recipe.
     *
     * @param output1       recipe output1
     * @param output2       recipe output2
     * @param input         primary input
     * @param lubricant     primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input, ILiquidStack lubricant, int durationTicks, int euPerTick) {
        if (lubricant == null) {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Cutting Saw recipe for " + input, input, output1, output2, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                    RA.addCutterRecipe(input1, i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt());          	
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sCutterRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1});
                    if(tRecipe!=null)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sCutterRecipes, tRecipe));
                }
            });
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Cutting Saw recipe for " + input, input, lubricant, output1, output2, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                	FluidStack input3 = i.nextFluid();          	
                    RA.addCutterRecipe(input1, input3, i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt());
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sCutterRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1});
                    if(tRecipe!=null)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sCutterRecipes, tRecipe));
                }
            });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, ILiquidStack lubricant, int durationTicks, int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("canner requires at least 1 output");
        } else {
            addRecipe(output[0], itemOrNull(output, 1), input, lubricant, durationTicks, euPerTick);
        }
    }
}
