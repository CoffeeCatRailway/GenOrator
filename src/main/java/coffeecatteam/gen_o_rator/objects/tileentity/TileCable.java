package coffeecatteam.gen_o_rator.objects.tileentity;

import coffeecatteam.gen_o_rator.objects.tileentity.base.TileEnergyBase;
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
