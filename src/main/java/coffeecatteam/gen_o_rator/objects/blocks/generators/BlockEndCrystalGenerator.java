package coffeecatteam.gen_o_rator.objects.blocks.generators;

import coffeecatteam.gen_o_rator.GenOrator;
import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBaseGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.generators.TileEndCrystalGenerator;
import coffeecatteam.gen_o_rator.util.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEndCrystalGenerator extends BlockBaseGenerator {

    public BlockEndCrystalGenerator(String name) {
        super(name, Material.IRON);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEndCrystalGenerator();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(GenOrator.instance, GuiHandler.END_CRYSTAL_GENERATOR_GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof TileEndCrystalGenerator) {
                InventoryHelper.dropInventoryItems(world, pos, (TileEndCrystalGenerator) tileentity);
                world.updateComparatorOutputLevel(pos, this);
            }
        }
        super.breakBlock(world, pos, state);
    }
}
