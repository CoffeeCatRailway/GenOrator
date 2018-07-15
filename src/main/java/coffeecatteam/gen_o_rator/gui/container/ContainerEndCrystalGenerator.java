package coffeecatteam.gen_o_rator.gui.container;

import coffeecatteam.gen_o_rator.gui.slot.SlotCoalGenerator;
import coffeecatteam.gen_o_rator.gui.slot.SlotEndCrystalGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.base.TileBaseGenerator;
import net.minecraft.inventory.IInventory;

public class ContainerEndCrystalGenerator extends ContainerBaseGenerator {

    public ContainerEndCrystalGenerator(IInventory player, TileBaseGenerator generator) {
        super(player, generator);
        addSlotToContainer(new SlotEndCrystalGenerator(generator, 0, 62, 35));
    }
}
