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
 * Provides access to the Vacuum Freezer recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.VacuumFreezer")
@ModOnly(MOD_ID)
public class VacuumFreezer {
    /**
     * Adds a vacuum freezer recipe.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param durationTicks freezing duration, in ticks
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding vacuum freezer recipe for " + output, input, output, durationTicks) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();         	
                RA.addVacuumFreezerRecipe(input1, i.nextItem(), i.nextInt());
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sVacuumRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sVacuumRecipes, tRecipe));
            }
        });
    }
}
