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

import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the Autoclave recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Autoclave")
@ModOnly(MOD_ID)
public class Autoclave {
    /**
     * Adds an Autoclave recipe.
     *
     * @param output        primary output
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param chances       chances
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidInput, int chances, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Autoclave recipe for " + output, input, fluidInput, output, chances, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	FluidStack input3 = i.nextFluid();
                RA.addAutoclaveRecipe(input1, input3, i.nextItem(), i.nextInt(), i.nextInt(), i.nextInt());       	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sAutoclaveRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1});
                if(tRecipe!=null)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sAutoclaveRecipes, tRecipe));
            }
        });
    }
}
