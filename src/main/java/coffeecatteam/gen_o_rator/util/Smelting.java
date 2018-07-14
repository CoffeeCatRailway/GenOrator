package coffeecatteam.gen_o_rator.util;

import coffeecatteam.gen_o_rator.init.InitBlock;
import coffeecatteam.gen_o_rator.init.InitItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smelting {

	public static void register() {
	    /*
	        ITEMS
	     */
		GameRegistry.addSmelting(InitItem.TREE_SAP, new ItemStack(InitItem.RUBBER, 1), 15);

	    /*
	        BLOCKS
	     */
        GameRegistry.addSmelting(InitBlock.ORE_SILVER, new ItemStack(InitItem.INGOT_SILVER, 1), 15);
        GameRegistry.addSmelting(InitBlock.ORE_ALUMINIUM, new ItemStack(InitItem.INGOT_ALUMINIUM, 1), 15);
        GameRegistry.addSmelting(InitBlock.ORE_COPPER, new ItemStack(InitItem.INGOT_COPPER, 1), 15);
	}
}
