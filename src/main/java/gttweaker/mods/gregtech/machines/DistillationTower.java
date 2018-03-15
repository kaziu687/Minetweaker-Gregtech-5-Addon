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
 * Provides access to the Distillation Tower recipes.
 *
 * @author DreamMasterXXL
 * @author Blood Asp
 */
@ZenClass("mods.gregtech.DistillationTower")
@ModOnly(MOD_ID)
public class DistillationTower {
    /**
     * Adds an Distillation Tower recipe.
     *
     * @param fluidInput    Fluid Input
     * @param fluidOutput   Up to 6 Fluid Outputs
     * @param itemOutput    Item output Slot
     * @param durationTicks duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack[] fluidOutput, IItemStack itemOutput, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        if (fluidOutput.length < 1) {
            MineTweakerAPI.logError("Distillation Twower must have at least 1 Fluid output");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Distillation Tower recipe for " + fluidInput.getDisplayName(), fluidInput, fluidOutput, itemOutput, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	FluidStack input1 = i.nextFluid();
                    RA.addDistillationTowerRecipe(input1, i.nextFluidArr(), i.nextItem(), i.nextInt(), i.nextInt());       	
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sDistillationRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input1}, null, new ItemStack[]{});
                    if(tRecipe!=null)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sDistillationRecipes, tRecipe));
                }
            });
        }
    }
}