package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the Brewing Machine recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Brewery")
@ModOnly(MOD_ID)
public class Brewery {
    /**
     * Adds a Brewing Machine recipe.
     *
     * @param output     primary fluid output
     * @param ingredient primary ingredient
     * @param input      primary fluid ingredient
     * @param hidden     hidden true or false
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack output, IIngredient ingredient, ILiquidStack input, boolean hidden) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Brewery recipe for " + output, ingredient, input, output, hidden) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	FluidStack input3 = i.nextFluid();
                RA.addBrewingRecipe(input1, input3.getFluid(), i.nextFluid().getFluid(), i.nextBool());    	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sBrewingRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sBrewingRecipes, tRecipe));
            }
        });
    }
}
