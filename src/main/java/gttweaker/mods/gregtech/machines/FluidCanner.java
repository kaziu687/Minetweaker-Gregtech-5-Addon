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
 * Provides access to the Fluid Canner recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidCanner")
@ModOnly(MOD_ID)
public class FluidCanner {
    /**
     * Adds a Fluid Canner recipe.
     *
     * @param output      output Slot
     * @param input       input Slot
     * @param fluidOutput fluid Output Slot
     * @param fluidInput  fluid Input Slot
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidOutput, ILiquidStack fluidInput) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Fluid Canner recipe for " + input, input, output, fluidInput, fluidOutput) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	FluidStack input3 = i.nextFluid();
                RA.addFluidCannerRecipe(input1, i.nextItem(), input3, i.nextFluid());           	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sFluidCannerRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sFluidCannerRecipes, tRecipe));
            }
        });
    }
}