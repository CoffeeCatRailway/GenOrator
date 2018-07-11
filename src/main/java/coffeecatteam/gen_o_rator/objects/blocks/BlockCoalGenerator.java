package coffeecatteam.gen_o_rator.objects.blocks;

import coffeecatteam.gen_o_rator.GenOrator;
import coffeecatteam.gen_o_rator.init.InitBlock;
import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBaseFacingContainer;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCoalGenerator;
import coffeecatteam.gen_o_rator.util.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCoalGenerator extends BlockBaseFacingContainer {

    public BlockCoalGenerator(String name) {
        super(name, Material.IRON);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCoalGenerator();
    }

    @Override
    public boolean onBlockActivatedAb(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(GenOrator.instance, GuiHandler.COAL_GENERATOR_GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlockAb(World world, BlockPos pos, IBlockState state) {
        TileEntity tileentity = world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, (TileCoalGenerator) tileentity);
        world.updateComparatorOutputLevel(pos, this);
    }
}
