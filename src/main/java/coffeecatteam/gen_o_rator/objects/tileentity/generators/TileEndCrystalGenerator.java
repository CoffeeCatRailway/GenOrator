package coffeecatteam.gen_o_rator.objects.tileentity.generators;

import coffeecatteam.gen_o_rator.init.InitBlock;
import coffeecatteam.gen_o_rator.objects.tileentity.base.TileBaseGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TileEndCrystalGenerator extends TileBaseGenerator {

    public float innerRotation = 0.0f;

    public TileEndCrystalGenerator() {
        super(InitBlock.END_CRYSTAL_GENERATOR, 200000, 0, 100, 0);
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return stack.getItem() == Items.END_CRYSTAL;
    }

    @Override
    public void update() {
        boolean flag1 = false;

        if (this.world != null) {
            // Check if stored energy is greater then max capacity
            if (this.energyStorage.isFull()) {
                this.energyStorage.setEnergy(this.energyStorage.getCapacity());
            } else {
                ItemStack stack = this.inventory.get(0);

                // Check if stack is empty
                if (!stack.isEmpty())
                    this.burn = true;

                // Cooldown
                if (this.burn) {
                    flag1 = true;
                    this.cooldown++;

                    // Check if cooldown is done
                    if (this.cooldown >= this.maxCooldown) {
                        this.cooldown = 0;
                        stack.shrink(1);
                        this.burn = false;
                    }
                }

                // Add energy to storage if burning
                if (this.isBurning() && this.burn) {
                    this.energyStorage.addEnergy(40);
                    flag1 = true;
                }
            }

            // Output energy if stored energy is greater then 0
            if (this.energyStorage.getEnergyStored() > 0)
                this.outputEnergy();
        }

        if (flag1)
            this.markDirty();
    }
}
