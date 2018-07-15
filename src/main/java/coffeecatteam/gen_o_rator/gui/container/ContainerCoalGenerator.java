package coffeecatteam.gen_o_rator.gui.container;

import coffeecatteam.gen_o_rator.gui.slot.SlotCoalGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.base.TileBaseGenerator;
import net.minecraft.inventory.IInventory;

public class ContainerCoalGenerator extends ContainerBaseGenerator {

    public ContainerCoalGenerator(IInventory player, TileBaseGenerator generator) {
        super(player, generator);
        addSlotToContainer(new SlotCoalGenerator(generator, 0, 52, 45));
    }
}
