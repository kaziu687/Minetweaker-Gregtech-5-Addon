package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the Unpacker recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Unpacker")
@ModOnly(MOD_ID)
public class Unpacker {
    /**
     * Adds a Unpacker recipe.
     *
     * @param output1       recipe output Slot 1
     * @param output2       recipe output Slot 2
     * @param input         recipe Input Slot
     * @param durationTicks duration time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Unpacker recipe for " + input, input, output1, output2, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();         	
                RA.addUnboxingRecipe(input1, i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt());
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sUnboxinatorRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sUnboxinatorRecipes, tRecipe));
            }
        });
    }
}
