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
 * Provides access to the Mixer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Mixer")
@ModOnly(MOD_ID)
public class Mixer {
    /**
     * Adds a Mixer recipe.
     *
     * @param output        recipe output
     * @param fluidOutput   primary fluidInput
     * @param input         input 1-4
     * @param fluidInput    primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, IIngredient[] input, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        if (input.length == 0) {
            MineTweakerAPI.logError("Mixer recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Mixer recipe for " + output, input[0], itemOrNull(input, 1),
                    itemOrNull(input, 2), itemOrNull(input, 3), fluidInput, fluidOutput, output, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                	ItemStack input2 = i.nextItem();
                	ItemStack input4 = i.nextItem();
                	ItemStack input5 = i.nextItem();
                	FluidStack input3 = i.nextFluid();         
                    RA.addMixerRecipe(input1, input2, input4, input5, input3, i.nextFluid(), i.nextItem(), i.nextInt(), i.nextInt());	
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sMixerRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1,input2,input4,input5});
                    if(tRecipe!=null&&!tRecipe.mHidden)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sMixerRecipes, tRecipe));
                }
            });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] input, int durationTicks, int euPerTick) {
        addRecipe(output, null, input, null, durationTicks, euPerTick);
    }
}
