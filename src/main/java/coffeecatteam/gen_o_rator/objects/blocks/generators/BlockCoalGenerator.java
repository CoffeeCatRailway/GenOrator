package coffeecatteam.gen_o_rator.objects.blocks.generators;

import coffeecatteam.gen_o_rator.GenOrator;
import coffeecatteam.gen_o_rator.init.InitBlock;
import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBaseGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.generators.TileCoalGenerator;
import coffeecatteam.gen_o_rator.util.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCoalGenerator extends BlockBaseGenerator {

    public BlockCoalGenerator(String name) {
        super(name, Material.IRON);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCoalGenerator();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(GenOrator.instance, GuiHandler.COAL_GENERATOR_GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof TileCoalGenerator) {
                InventoryHelper.dropInventoryItems(world, pos, (TileCoalGenerator) tileentity);
                world.updateComparatorOutputLevel(pos, this);
            }
        }
        super.breakBlock(world, pos, state);
    }

    public static void setState(boolean active, World world, BlockPos pos) {
        TileEntity tileentity = world.getTileEntity(pos);
        IBlockState iblockstate = world.getBlockState(pos);
        keepInventory = true;

        if (active) {
            world.setBlockState(pos, InitBlock.COAL_GENERATOR_ACTIVE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            world.setBlockState(pos, InitBlock.COAL_GENERATOR_ACTIVE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        } else {
            world.setBlockState(pos, InitBlock.COAL_GENERATOR.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            world.setBlockState(pos, InitBlock.COAL_GENERATOR.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
        }
    }
}
