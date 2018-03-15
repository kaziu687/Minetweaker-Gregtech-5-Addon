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
 * Provides access to the Fluid Extractor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidExtractor")
@ModOnly(MOD_ID)
public class FluidExtractor {
    /**
     * Adds a Fluid Extractor recipe.
     *
     * @param output        output Slot
     * @param input         input Slot
     * @param fluidOutput   fluidOutput Slot
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param chance        chance output slot
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidOutput, int durationTicks, int euPerTick, int chance) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Fluid Extractor recipe for " + input, input, output, fluidOutput, durationTicks, euPerTick, chance) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
                RA.addFluidExtractionRecipe(input1, i.nextItem(), i.nextFluid(), i.nextInt(), i.nextInt(), i.nextInt());          	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sFluidExtractionRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sFluidExtractionRecipes, tRecipe));
            }
        });
    }
}