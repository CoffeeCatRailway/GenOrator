package coffeecatteam.gen_o_rator.objects.tileentity.base;

import net.minecraftforge.energy.EnergyStorage;

public class CGEnergyStorage extends EnergyStorage {

    public CGEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public int getMaxReceive() {
        return this.maxReceive;
    }

    public int getMaxExtract() {
        return this.maxExtract;
    }

    public boolean isFull() {
        return this.energy >= this.capacity;
    }

    public int getCapacity() {
        return this.getMaxEnergyStored();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setEnergy(int newEnergy) {
        this.energy = newEnergy;
    }

    public void addEnergy(int energy) {
        this.energy += energy;
    }
}
