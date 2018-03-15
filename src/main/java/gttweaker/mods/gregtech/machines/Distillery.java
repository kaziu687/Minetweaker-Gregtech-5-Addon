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
 * Provides access to the Distillery recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Distillery")
@ModOnly(MOD_ID)
public class Distillery {
    /**
     * Adds a Distillery recipe.
     *
     * @param fluidOutput   Fluid output
     * @param circuit       Circuit
     * @param fluidInput    fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param hidden        hidden
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IItemStack circuit, ILiquidStack fluidInput, int durationTicks, int euPerTick, boolean hidden) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Distillery recipe for " + fluidOutput, circuit, fluidInput, fluidOutput, durationTicks, euPerTick, hidden) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	FluidStack input3 = i.nextFluid();
                RA.addDistilleryRecipe(input1, input3, i.nextFluid(), i.nextInt(), i.nextInt(), i.nextBool());          	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sDistilleryRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sDistilleryRecipes, tRecipe));
            }
        });
    }
}