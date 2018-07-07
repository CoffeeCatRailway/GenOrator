package coffeecatteam.genOrator.objects.tileentity;

import coffeecatteam.genOrator.objects.tileentity.base.TileEnergyBase;
import net.minecraft.util.ITickable;

public class TileCable extends TileEnergyBase implements ITickable {

    public TileCable() {
        super(1000, 50, 50, 0);
    }

    @Override
    public void update() {
        if(this.world != null) {
            this.receiveEnergy();
            this.outputEnergy();
            this.markDirty();
        }
    }
}
