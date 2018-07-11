package coffeecatteam.gen_o_rator.objects.tileentity;

import coffeecatteam.gen_o_rator.objects.tileentity.base.TileEnergyBase;
import coffeecatteam.gen_o_rator.util.enums.EnumCableType;
import net.minecraft.util.ITickable;

public class TileCable extends TileEnergyBase implements ITickable {

    public TileCable(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract, 0);
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
