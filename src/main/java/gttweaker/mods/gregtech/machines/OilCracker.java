package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
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
 * Provides access to the OilCracker recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.OilCracker")
@ModOnly(MOD_ID)
public class OilCracker {
    /**
     * Adds a Pyrolyse Oven recipe.
     *
     * @param fluidOutput   recipe Fluid output
     * @param fluidInput    recipe Fluid input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding OilCracker recipe for " + fluidOutput, fluidInput, fluidOutput, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	FluidStack input3 = i.nextFluid();           	
                RA.addCrackingRecipe(input3, i.nextFluid(), i.nextInt(), i.nextInt());
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sCrakingRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sCrakingRecipes, tRecipe));
            }
        });
    }
}

