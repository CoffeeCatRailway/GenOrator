package coffeecatteam.gen_o_rator.gui.container;

import coffeecatteam.gen_o_rator.objects.tileentity.base.TileBaseGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBaseGenerator extends Container {

    private final IInventory player;
    private final TileBaseGenerator generator;

    public ContainerBaseGenerator(IInventory player, TileBaseGenerator generator) {
        this.player = player;
        this.generator = generator;

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int k = 0; k < 9; ++k)
            this.addSlotToContainer(new Slot(player, k, 8 + k * 18, 142));
    }

    public IInventory getPlayer() {
        return player;
    }

    public TileBaseGenerator getGenerator() {
        return generator;
    }

    private boolean performMerge(int slotIndex, ItemStack stack) {
        int size = generator.getSizeInventory();

        if (slotIndex < size)
            return mergeItemStack(stack, size, size + 26, true);
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
