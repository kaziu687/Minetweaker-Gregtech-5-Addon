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

import gregtech.api.enums.ItemList;
import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the Electrolyzer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Electrolyzer")
@ModOnly(MOD_ID)
public class Electrolyzer {
    /**
     * Adds a Electrolyzer recipe.
     *
     * @param outputs       output 1-6
     * @param fluidOutput   primary fluid output
     * @param input         primary input
     * @param cells         Cell input
     * @param fluidInput    primary fluid input
     * @param chances       chance 1-6
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient input, IIngredient cells, ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Electrolyzer must have at least 1 output");
        } else if (outputs.length != chances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Electrolyzer recipe with Liquid support for " + input, input, cells, fluidInput, fluidOutput, outputs[0],
                    itemOrNull(outputs, 1), itemOrNull(outputs, 2), itemOrNull(outputs, 3), itemOrNull(outputs, 4), itemOrNull(outputs, 5), chances, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                	ItemStack input2 = i.nextItem();
                	FluidStack input3 = i.nextFluid();       
                    RA.addElectrolyzerRecipe(input1, input2, input3, i.nextFluid(), i.nextItem(), i.nextItem(),
                            i.nextItem(), i.nextItem(), i.nextItem(), i.nextItem(), i.nextIntArr(), i.nextInt(), i.nextInt());   	
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1,input2});
                    if(tRecipe!=null&&!tRecipe.mHidden)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes, tRecipe));
                }
            });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int cells, int durationTicks, int euPerTick) {
        if (outputs.length == 0) {
            MineTweakerAPI.logError("Electrolyzer recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding electrolyzer recipe with input " + input, input, cells, outputs[0],
                    itemOrNull(outputs, 1), itemOrNull(outputs, 2), itemOrNull(outputs, 3), itemOrNull(outputs, 4), itemOrNull(outputs, 5), durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                	int input2 = i.nextInt();
                    RA.addElectrolyzerRecipe(input1, input2, i.nextItem(), i.nextItem(), i.nextItem(),
                            i.nextItem(), i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt());         	
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1,ItemList.Cell_Empty.get(input2, new Object[]{})});
                    if(tRecipe!=null&&!tRecipe.mHidden)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes, tRecipe));
                }
            });
        }
    }
}
