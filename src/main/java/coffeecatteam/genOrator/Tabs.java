package coffeecatteam.genOrator;

import coffeecatteam.genOrator.init.InitBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Tabs {

    public static final CreativeTabs BLOCKS = new TabBlocks("blocks");

    private static class TabBlocks extends CreativeTabs {

        public TabBlocks(String label) {
            super(label);
        }

        public ItemStack getTabIconItem() {
            return new ItemStack(InitBlock.CABLE);
        }
    }
}
