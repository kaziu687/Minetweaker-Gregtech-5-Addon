package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the Amplifabricator recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Amplifabricator")
@ModOnly(MOD_ID)
public class Amplifabricator {
    /**
     * Adds a Amplifabricator recipe.
     *
     * @param input    primary Input
     * @param duration reaction time, in ticks
     */
    @ZenMethod
    public static void addRecipe(IIngredient input, int duration, int amount) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Amplifabricator recipe for " + input, input, duration, amount) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
                RA.addAmplifier(input1, i.nextInt(), i.nextInt());
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sAmplifiers.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1});
                if(tRecipe!=null)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sAmplifiers, tRecipe));
            }
        });
    }
}