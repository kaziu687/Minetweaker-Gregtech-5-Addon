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
 * Provides access to the Chemical Reactor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.ChemicalReactor")
@ModOnly(MOD_ID)
public class ChemicalReactor {
    /**
     * Adds a Chemical Reactor recipe.
     *
     * @param output        recipe output
     * @param fluidOutput   primary fluidInput
     * @param input1        primary input
     * @param input2        secondary input
     * @param fluidInput    primary fluidInput
     * @param durationTicks reaction time, in ticks
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, IIngredient input1, IIngredient input2, ILiquidStack fluidInput, int durationTicks) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Chemical Reactor recipe for " + output, input1, input2, fluidInput, fluidOutput, output, durationTicks) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	ItemStack input2 = i.nextItem();
            	FluidStack input3 = i.nextFluid();
                RA.addChemicalRecipe(input1, input2, input3, i.nextFluid(), i.nextItem(), i.nextInt());      	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sChemicalRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1,input2});
                if(tRecipe!=null)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sChemicalRecipes, tRecipe));
            }
        });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks) {
        addRecipe(output, null, input1, input2, null, durationTicks);
    }
}
