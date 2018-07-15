package coffeecatteam.gen_o_rator.objects.tileentity;

import coffeecatteam.gen_o_rator.objects.tileentity.base.TileEnergyBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileCable extends TileEnergyBase implements ITickable {

    public TileCable(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract, 0);
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
        if(this.world != null) {
            this.outputEnergy();
            this.receiveEnergy();
            this.markDirty();
        }
    }
}
