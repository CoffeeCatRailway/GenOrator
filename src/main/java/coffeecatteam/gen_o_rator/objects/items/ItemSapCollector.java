package coffeecatteam.gen_o_rator.objects.items;

import coffeecatteam.gen_o_rator.GenOrator;
import coffeecatteam.gen_o_rator.init.InitBlock;
import coffeecatteam.gen_o_rator.init.InitItem;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ItemSapCollector extends ItemBaseTool {

    public ItemSapCollector(String name) {
        super(name, GenOrator.TAB, 1, 1.5f, ToolMaterial.WOOD, Sets.newHashSet(Blocks.LOG, Blocks.LOG2));
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (block == Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK).getBlock()) {
                int amount = 1 + new Random().nextInt(1);
                player.addItemStackToInventory(new ItemStack(InitItem.TREE_SAP, amount));

//                EnumFacing axis = EnumFacing.getFacingFromVector(pos.getX(), pos.getY(), pos.getZ());
//                IBlockState newState = InitBlock.DRIED_UP_LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(axis.getAxis()));
//
//                this.setBlock(itemstack, player, world, pos, newState);
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.PASS;
        }
    }

    private void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state) {
        worldIn.playSound(player, pos, SoundEvents.BLOCK_WOOD_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote) {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }
}
