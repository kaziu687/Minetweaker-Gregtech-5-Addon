package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the extruder recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.Extruder")
@ModOnly(MOD_ID)
public class Extruder {
    /**
     * Adds an extruder recipe.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param shape         shape (set stack size to 0 to prevent the shape from being consumed)
     * @param durationTicks extruding time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, IItemStack shape, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding extruder recipe for " + output, input, shape, output, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	ItemStack input2 = i.nextItem(); 
                RA.addExtruderRecipe(input1, input2, i.nextItem(), i.nextInt(), i.nextInt());         	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sExtruderRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1,input2});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sExtruderRecipes, tRecipe));
            }
        });
    }
}
