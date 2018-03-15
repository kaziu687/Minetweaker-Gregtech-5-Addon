package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the Sifter recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Sifter")
@ModOnly(MOD_ID)
public class Sifter {
    /**
     * Adds a Sifter recipe.
     *
     * @param outputs       1-9 outputs
     * @param input         primary input
     * @param outChances    chances of 1-9 output
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int[] outChances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Sifter must have at least 1 output");
        } else if (outputs.length != outChances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Sifter recipe for " + input, input, outputs, outChances, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();           	
                    RA.addSifterRecipe(input1, i.nextItemArr(), i.nextIntArr(), i.nextInt(), i.nextInt());
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sSifterRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1});
                    if(tRecipe!=null&&!tRecipe.mHidden)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sSifterRecipes, tRecipe));
                }
            });
        }
    }
}