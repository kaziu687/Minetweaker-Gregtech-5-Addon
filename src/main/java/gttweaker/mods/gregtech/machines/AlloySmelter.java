package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.mods.jei.JEI;
import minetweaker.mods.jei.JEIAddonPlugin;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provider access to the Alloy Smelter recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.AlloySmelter")
@ModOnly(MOD_ID)
public class AlloySmelter {
    /**
     * Adds an alloy smelter recipe.
     *
     * @param output        alloy smelter output
     * @param input1        primary input
     * @param input2        secondary input
     * @param durationTicks smelting time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding alloy smelter recipe for " + output, input1, input2, output, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	ItemStack input2 = i.nextItem();
                RA.addAlloySmelterRecipe(input1, input2, i.nextItem(), i.nextInt(), i.nextInt());
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sAlloySmelterRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1,input2});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sAlloySmelterRecipes, tRecipe));
//                JEIAddonPlugin.recipeRegistry.addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sAlloySmelterRecipes, GT_Recipe.GT_Recipe_Map.sAlloySmelterRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1,input2})));
            }
        });
    }
}
