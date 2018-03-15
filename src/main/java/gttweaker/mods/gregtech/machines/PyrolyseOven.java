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

import gregtech.api.enums.ItemList;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the PyrolyseOven recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PyrolyseOven")
@ModOnly(MOD_ID)
public class PyrolyseOven {
    /**
     * Adds a Pyrolyse Oven recipe.
     *
     * @param output        recipe output
     * @param fluidOutput   recipe Fluid output
     * @param circuit       circuit preset nr.
     * @param input         recipe input
     * @param fluidInput    recipe Fluid input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, int circuit, IIngredient input, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Pyrolyse Oven recipe for " + output, input, fluidInput, circuit, output, fluidOutput, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	FluidStack input3 = i.nextFluid();
            	int input2 = i.nextInt();           	
                RA.addPyrolyseRecipe(input1, input3, input2, i.nextItem(), i.nextFluid(), i.nextInt(), i.nextInt());
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sPyrolyseRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1,ItemList.Circuit_Integrated.getWithDamage(0,input2, new Object[]{})});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sPyrolyseRecipes, tRecipe));
            }
        });
    }
}

