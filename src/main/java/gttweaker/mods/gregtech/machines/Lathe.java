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
import static gttweaker.util.ArrayHelper.itemOrNull;

import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Access point for Lathe recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.Lathe")
@ModOnly(MOD_ID)
public class Lathe {
    /**
     * Adds a lathe recipe with a single output.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param durationTicks crafting duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks, int euPerTick) {
        addRecipe(new IItemStack[]{output, null}, input, durationTicks, euPerTick);
    }

    /**
     * Adds a lathe recipe with 1 or 2 outputs.
     *
     * @param outputs       array with 1-2 outputs
     * @param input         recipe input
     * @param durationTicks crafting duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int durationTicks, int euPerTick) {
        if (outputs.length == 0) {
            MineTweakerAPI.logError("Lathe recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding lathe recipe for " + outputs[0], input, outputs[0], itemOrNull(outputs, 1), durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                    RA.addLatheRecipe(input1, i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt());           	
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sLatheRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1});
                    if(tRecipe!=null&&!tRecipe.mHidden)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sLatheRecipes, tRecipe));
                }
            });
        }
    }
}
