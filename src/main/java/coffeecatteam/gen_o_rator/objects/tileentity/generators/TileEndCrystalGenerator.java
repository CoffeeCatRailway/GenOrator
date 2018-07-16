package coffeecatteam.gen_o_rator.objects.tileentity.generators;

import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBaseGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.base.TileBaseGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEndCrystalGenerator extends TileBaseGenerator {

    public TileEndCrystalGenerator(BlockBaseGenerator generator) {
        super(generator, 200000, 0, 100, 0);
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
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("energyStored", this.energyStorage.getEnergyStored());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.energyStorage.setEnergy(compound.getInteger("energyStored"));
        super.readFromNBT(compound);
    }

    @Override
    public void update() {
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
                if (burn) {
                    this.cooldown--;

                    // Check if cooldown is done
                    if (this.cooldown <= 0) {
                        this.cooldown = maxCooldown;
                        stack.shrink(1);
                        this.burn = false;
                    }
                }

                // Add energy to storage if burning
                if (isBurning() && this.burn)
                    this.energyStorage.addEnergy(40);
            }

            // Output energy if stored energy is greater then 0
            if (this.energyStorage.getEnergyStored() > 0)
                this.outputEnergy();

            this.markDirty();
        }
    }
}