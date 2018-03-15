package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
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
 * Provides access to the Fluid Solidifier recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidSolidifier")
@ModOnly(MOD_ID)
public class FluidSolidifier {
    /**
     * Adds a Fluid Solidifier recipe.
     *
     * @param output        output Slot
     * @param mold          mold Slot
     * @param fluidInput    fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack mold, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Fluid Solidifier recipe for " + output, mold, fluidInput, output, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	FluidStack input3 = i.nextFluid();   
                RA.addFluidSolidifierRecipe(input1, input3, i.nextItem(), i.nextInt(), i.nextInt());      	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sFluidSolidficationRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sFluidSolidficationRecipes, tRecipe));
            }
        });
    }
}