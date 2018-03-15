package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import ic2.core.ref.BlockName;
import ic2.core.ref.ItemName;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
import static gttweaker.util.ArrayHelper.itemOrNull;

import gregtech.api.enums.ItemList;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.jei.JEIGregtechRecipe;

/**
 * Provides access to the Implosion Compressor recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.ImplosionCompressor")
@ModOnly(MOD_ID)
public class ImplosionCompressor {
    /**
     * Adds an implosion compressor recipe with a single output.
     *
     * @param output recipe output
     * @param input  primary input
     * @param tnt    amount of TNT needed
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int tnt) {
        addRecipe(new IItemStack[]{output, null}, input, tnt);
    }

    /**
     * Adds an implosion compressor recipe with one or two outputs.
     *
     * @param output array with 1-2 outputs
     * @param input  primary input
     * @param tnt    amount of TNT needed
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, int tnt) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Implosion compressor recipe requires at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Implosion compressor recipe for " + output[0], input, tnt, output[0], itemOrNull(output, 1)) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                	ItemStack input1 = i.nextItem();
                	int input2 = i.nextInt();           	
                    RA.addImplosionRecipe(input1, input2, i.nextItem(), i.nextItem());
                    for(ItemStack tStack : new ItemStack[]{ItemList.Block_Powderbarrel.get(input2*2, new Object[0]),GT_ModHandler.getIC2Item(ItemName.dynamite, input2*4),new ItemStack(Blocks.TNT,input2/2),GT_ModHandler.getIC2Item(BlockName.te, input2/4)}){
                    GT_Recipe tRecipe = GT_Recipe.GT_Recipe_Map.sImplosionRecipes.findRecipe(null, null, false, Long.MAX_VALUE, null, null, new ItemStack[]{input1,tStack});
                    if(tRecipe!=null&&!tRecipe.mHidden)
                    MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new JEIGregtechRecipe(GT_Recipe.GT_Recipe_Map.sImplosionRecipes, tRecipe));
                    }
                }
            });
        }
    }
}
