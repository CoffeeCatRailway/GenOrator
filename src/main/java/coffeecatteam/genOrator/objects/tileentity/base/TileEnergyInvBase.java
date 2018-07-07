package coffeecatteam.genOrator.objects.tileentity.base;

import net.minecraft.inventory.IInventory;

public abstract class TileEnergyInvBase extends TileEnergyBase implements IInventory {

    private int extraFieldCount;

    public TileEnergyInvBase(int capacity, int maxReceive, int maxExtract, int energy) {
        this(capacity, maxReceive, maxExtract, energy, 0);
    }

    public TileEnergyInvBase(int capacity, int maxReceive, int maxExtract, int energy, int extraFieldCount) {
        super(capacity, maxReceive, maxExtract, energy);
        this.extraFieldCount = extraFieldCount;
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return this.energyStorage.getEnergyStored();
            case 1:
                return this.energyStorage.getMaxEnergyStored();
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.energyStorage.setEnergy(value);
                break;
            case 1:
                this.energyStorage.setCapacity(value);
                break;
        }
        this.markDirty();
    }

    @Override
    public int getFieldCount() {
        return 2 + this.extraFieldCount;
    }
}
