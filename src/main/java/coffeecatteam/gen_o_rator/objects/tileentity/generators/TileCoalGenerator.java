package coffeecatteam.gen_o_rator.objects.tileentity.generators;

import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBaseGenerator;
import coffeecatteam.gen_o_rator.objects.blocks.generators.BlockCoalGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.base.TileBaseGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileCoalGenerator extends TileBaseGenerator {

    public TileCoalGenerator(BlockBaseGenerator generator) {
        super(generator,100000, 0, 50, 0);
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return GameRegistry.getFuelValue(stack) > 0;
    }

    @Override
    public void update() {
        boolean flag = (this.isBurning() && this.burn);
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
                if (burn) {
                    flag1 = true;
                    this.cooldown--;

                    // Check if cooldown is done
                    if (this.cooldown <= 0) {
                        this.cooldown = maxCooldown;
                        stack.shrink(1);
                        this.burn = false;
                    }
                }

                // Add energy to storage if burning
                if (isBurning() && this.burn) {
                    this.energyStorage.addEnergy(getEnergyFromCoal(stack) / 10);
                    flag1 = true;
                }
            }

            // Output energy if stored energy is greater then 0
            if (this.energyStorage.getEnergyStored() > 0)
                this.outputEnergy();

            if (flag != (this.isBurning() && this.burn)) {
                flag1 = true;
                BlockCoalGenerator.setState((this.isBurning() && this.burn), this.world, this.pos);
            }
        }

        if (flag1)
            this.markDirty();
    }

    /*
     * DOESN'T WORK YET!
     */
    private int getEnergyFromCoal(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK)) {
                return 2000;
            } else if (item == Items.COAL) {
                return 200;
            } else {
                return 100;
            }
        }
    }
}
