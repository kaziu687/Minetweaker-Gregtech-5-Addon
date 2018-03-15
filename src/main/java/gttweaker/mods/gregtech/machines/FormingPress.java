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
 * Provides access to the Forming Press recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FormingPress")
@ModOnly(MOD_ID)
public class FormingPress {
    /**
     * Adds a Forming Press recipe.
     *
     * @param output        recipe output
     * @param input1        Item input
     * @param input2        Press Form input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Forming Press recipe for " + output, input1, input2, output, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	ItemStack input2 = i.nextItem(); 
                RA.addFormingPressRecipe(input1, input2, i.nextItem(), i.nextInt(), i.nextInt());         	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sPressRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1,input2});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sPressRecipes, tRecipe));
            }
        });
    }
}
