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
 * Provides access to the Blast Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.BlastFurnace")
@ModOnly(MOD_ID)
public class Blastfurnace {
    /**
     * Adds a Blast Furnace recipe.
     *
     * @param output        recipe output 1+2
     * @param fluidInput    primary fluidInput
     * @param input         recipes input  1+2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param heat          heat in Kelvin
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, ILiquidStack fluidInput, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Blast furnace recipe for " + output[0], input[0], itemOrNull(input, 1), fluidInput, output[0], itemOrNull(output, 1), durationTicks, euPerTick, heat) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                	ItemStack input2 = i.nextItem();
                	FluidStack input3 = i.nextFluid();
                    RA.addBlastRecipe(input1, input2, input3, null, i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt(), i.nextInt()); 	
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sBlastRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1,input2});
                    if(tRecipe!=null)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sBlastRecipes, tRecipe));
                }
            });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        addRecipe(output, null, input, durationTicks, euPerTick, heat);
    }
}