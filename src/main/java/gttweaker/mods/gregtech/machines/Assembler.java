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
 * Provides access to the assembler recipes.
 *
 * @author Blood Asp
 * @author DreamMasterXXL
 * @author bculkin2442
 */
@ZenClass("mods.gregtech.Assembler")
@ModOnly(MOD_ID)
public class Assembler {
    /**
     * Adds an assemble recipe.
     *
     * @param output        recipe output
     * @param input1        primary input
     * @param input2        secondary input (optional, can be null)
     * @param fluidInput    primary fluidInput
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding assembler recipe with fluid Support for " + output, input1, input2, fluidInput, output, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	ItemStack input2 = i.nextItem();
            	FluidStack input3 = i.nextFluid();
                RA.addAssemblerRecipe(input1, input2, input3, i.nextItem(), i.nextInt(), i.nextInt());            	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sAssemblerRecipes.findRecipe(null, null, false, Long.MAX_VALUE, new FluidStack[]{input3}, null, new ItemStack[]{input1,input2});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sAssemblerRecipes, tRecipe));
            }
        });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding assembler recipe for " + output, input1, input2, output, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
            	ItemStack input1 = i.nextItem();
            	ItemStack input2 = i.nextItem();
                RA.addAssemblerRecipe(input1, input2, i.nextItem(), i.nextInt(), i.nextInt());            	
                GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sAssemblerRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1,input2});
                if(tRecipe!=null&&!tRecipe.mHidden)
                MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sAssemblerRecipes, tRecipe));
            }
        });
    }
}