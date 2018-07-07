package coffeecatteam.genOrator.gui.container;

import coffeecatteam.genOrator.gui.slot.SlotCoalGenerator;
import coffeecatteam.genOrator.objects.tileentity.TileCoalGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCoalGenerator extends Container {

    private final TileCoalGenerator generator;

    public ContainerCoalGenerator(IInventory player, TileCoalGenerator generator) {
        this.generator = generator;
        addSlotToContainer(new SlotCoalGenerator(generator, 0, 52, 45));

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int k = 0; k < 9; ++k)
            this.addSlotToContainer(new Slot(player, k, 8 + k * 18, 142));
    }

    private boolean performMerge(int slotIndex, ItemStack stack) {
        int size = generator.getSizeInventory();

        if (slotIndex < size) {
            return mergeItemStack(stack, size, size + 26, true);
        }
        return mergeItemStack(stack, 0, size, false);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            stack = stackInSlot.copy();

            if (!performMerge(index, stackInSlot)) {
                return ItemStack.EMPTY;
            }
            slot.onSlotChange(stackInSlot, stack);

            if (stackInSlot.getCount() <= 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.putStack(stackInSlot);
            }
            if (stackInSlot.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stackInSlot);
        }
        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
