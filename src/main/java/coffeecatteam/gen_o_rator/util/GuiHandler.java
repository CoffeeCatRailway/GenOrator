package coffeecatteam.gen_o_rator.util;

import coffeecatteam.gen_o_rator.gui.GuiCoalGenerator;
import coffeecatteam.gen_o_rator.gui.GuiEndCrystalGenerator;
import coffeecatteam.gen_o_rator.gui.container.ContainerCoalGenerator;
import coffeecatteam.gen_o_rator.gui.container.ContainerEndCrystalGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.generators.TileCoalGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.generators.TileEndCrystalGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int COAL_GENERATOR_GUI_ID = 1;
    public static final int END_CRYSTAL_GENERATOR_GUI_ID = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

        switch (ID) {
            case COAL_GENERATOR_GUI_ID:
                return new ContainerCoalGenerator(player.inventory, (TileCoalGenerator) te);
            case END_CRYSTAL_GENERATOR_GUI_ID:
                return new ContainerEndCrystalGenerator(player.inventory, (TileEndCrystalGenerator) te);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

        switch (ID) {
            case COAL_GENERATOR_GUI_ID:
                return new GuiCoalGenerator(player.inventory, (TileCoalGenerator) te);
            case END_CRYSTAL_GENERATOR_GUI_ID:
                return new GuiEndCrystalGenerator(player.inventory, (TileEndCrystalGenerator) te);
            default:
                return null;
        }
    }
}
