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
 * Provides access to the Fusion Reactor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FusionReactor")
@ModOnly(MOD_ID)
public class FusionReactor {
    /**
     * Adds a Mixer recipe.
     *
     * @param fluidOutput   primary fluid Output
     * @param fluidInput1   primary fluid Input
     * @param fluidInput2   secondary fluid Input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param startEU       Starting at ... EU
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput1, ILiquidStack fluidInput2, int durationTicks, int euPerTick, int startEU) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Fusion Reactor recipe for " + fluidOutput, fluidInput1, fluidInput2, fluidOutput, durationTicks, euPerTick, startEU) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	FluidStack input1 = i.nextFluid();
            	FluidStack input3 = i.nextFluid();
                RA.addFusionReactorRecipe(input1, input3, i.nextFluid(), i.nextInt(), i.nextInt(), i.nextInt());           	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sFusionRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input1,input3}, null, new ItemStack[]{});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sFusionRecipes, tRecipe));
            }
        });
    }
}
