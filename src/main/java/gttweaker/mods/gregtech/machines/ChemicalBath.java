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
 * Provides access to the Chemical Bath recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.ChemicalBath")
@ModOnly(MOD_ID)
public class ChemicalBath {
    /**
     * Adds a Chemical Bath recipe.
     *
     * @param output        outputs 1-3
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param chances       chance of 3 outputs
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("chemical bath requires at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Chemical Bath recipe for " + input, input, fluidInput, output[0],
                    itemOrNull(output, 1), itemOrNull(output, 2), chances, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                	FluidStack input3 = i.nextFluid();
                    RA.addChemicalBathRecipe(input1, input3, i.nextItem(), i.nextItem(), i.nextItem(), i.nextIntArr(), i.nextInt(), i.nextInt());    	
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sChemicalBathRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1});
                    if(tRecipe!=null)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sChemicalBathRecipes, tRecipe));
                }
            });
        }
    }
}