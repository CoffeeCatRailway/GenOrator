package coffeecatteam.gen_o_rator.gui.slot;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotCoalGenerator extends Slot {

    public SlotCoalGenerator(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        String name = I18n.format(stack.getUnlocalizedName() + ".name").toLowerCase();
        return (name.contains("coal") && TileEntityFurnace.isItemFuel(stack));
    }
}
