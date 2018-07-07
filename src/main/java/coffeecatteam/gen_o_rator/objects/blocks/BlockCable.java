package coffeecatteam.gen_o_rator.objects.blocks;

import coffeecatteam.gen_o_rator.GenOrator;
import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBase;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCable;
import coffeecatteam.gen_o_rator.util.aabb.Bounds;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCable extends BlockBase implements ITileEntityProvider {

    public static final PropertyBool BACK = PropertyBool.create("back");
    public static final PropertyBool FORWARD = PropertyBool.create("forward");
    public static final PropertyBool LEFT = PropertyBool.create("left");
    public static final PropertyBool RIGHT = PropertyBool.create("right");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");

    public BlockCable(String name) {
        super(name, 0.1F, 1.0F, Material.ROCK, GenOrator.BLOCKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BACK, false).withProperty(FORWARD, false).withProperty(LEFT, false).withProperty(RIGHT, false).withProperty(UP, false).withProperty(DOWN, false));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCable();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public boolean isSideSolid(IBlockState baseState, IBlockAccess world, BlockPos pos, EnumFacing side) {
        if (side == EnumFacing.UP) {
            return true;
        }
        return false;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        boolean back = world.getBlockState(pos.south()).getBlock() == this;
        boolean forward = world.getBlockState(pos.north()).getBlock() == this;
        boolean left = world.getBlockState(pos.west()).getBlock() == this;
        boolean right = world.getBlockState(pos.east()).getBlock() == this;
        boolean up = world.getBlockState(pos.up()).getBlock() == this;
        boolean down = world.getBlockState(pos.down()).getBlock() == this;
        return state.withProperty(BACK, back).withProperty(FORWARD, forward).withProperty(LEFT, left).withProperty(RIGHT, right).withProperty(UP, up).withProperty(DOWN, down);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BACK, FORWARD, LEFT, RIGHT, UP, DOWN);
    }

    private static final Bounds DOT = new Bounds(4, 4, 4, 12, 12, 12);
    private static final Bounds STAR = new Bounds(0, 0, 0, 16, 16, 16);

    private static final Bounds SINGLE_UP = new Bounds(4, 4, 4, 12, 16, 12);
    private static final Bounds SINGLE_DOWN = new Bounds(4, 0, 4, 12, 12, 12);
    private static final Bounds SINGLE_SIDE = new Bounds(4, 4, 4, 16, 12, 12);

    private static final Bounds DOUBLE_VERTICLE = new Bounds(4, 0, 4, 12, 16, 12);
    private static final Bounds DOUBLE_SIDE = new Bounds(0, 4, 4, 16, 12, 12);

    private static final Bounds CORNER_DOUBLE_TOP = new Bounds(4, 0, 4, 16, 12, 16);
    private static final Bounds CORNER_DOUBLE_BOTTOM = new Bounds(4, 4, 4, 16, 16, 16);

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        boolean back = world.getBlockState(pos.south()).getBlock() == this;
        boolean forward = world.getBlockState(pos.north()).getBlock() == this;
        boolean left = world.getBlockState(pos.west()).getBlock() == this;
        boolean right = world.getBlockState(pos.east()).getBlock() == this;
        boolean up = world.getBlockState(pos.up()).getBlock() == this;
        boolean down = world.getBlockState(pos.down()).getBlock() == this;
        AxisAlignedBB box = STAR.toAABB();

        /* Dot & Star */
        if (!back && !forward && !left && !right && !up && !down)
            box = DOT.toAABB();
        if (back && forward && left && right && up && down)
            box = STAR.toAABB();

        /* Single */
        if (!back && !forward && !left && !right && up && !down)
            box = SINGLE_UP.toAABB();
        if (!back && !forward && !left && !right && !up && down)
            box = SINGLE_DOWN.toAABB();
        if (back && !forward && !left && !right && !up && !down)
            box = SINGLE_SIDE.getRotation(EnumFacing.SOUTH);
        if (!back && forward && !left && !right && !up && !down)
            box = SINGLE_SIDE.getRotation(EnumFacing.NORTH);
        if (!back && !forward && left && !right && !up && !down)
            box = SINGLE_SIDE.getRotation(EnumFacing.WEST);
        if (!back && !forward && !left && right && !up && !down)
            box = SINGLE_SIDE.getRotation(EnumFacing.EAST);

        /* Double */
        if (!back && !forward && !left && !right && up && down)
            box = DOUBLE_VERTICLE.toAABB();
        if (back && forward && !left && !right && !up && !down)
            box = DOUBLE_SIDE.getRotation(EnumFacing.NORTH);
        if (!back && !forward && left && right && !up && !down)
            box = DOUBLE_SIDE.getRotation(EnumFacing.EAST);

        /* Corner Double */
        if (back && !forward && left && !right && !up && down)
            box = CORNER_DOUBLE_TOP.getRotation(EnumFacing.SOUTH);
        if (!back && forward && !left && right && !up && down)
            box = CORNER_DOUBLE_TOP.getRotation(EnumFacing.NORTH);
        if (!back && forward && left && !right && !up && down)
            box = CORNER_DOUBLE_TOP.getRotation(EnumFacing.WEST);
        if (back && !forward && !left && right && !up && down)
            box = CORNER_DOUBLE_TOP.getRotation(EnumFacing.EAST);

        if (back && !forward && left && !right && up && !down)
            box = CORNER_DOUBLE_BOTTOM.getRotation(EnumFacing.SOUTH);
        if (!back && forward && !left && right && up && !down)
            box = CORNER_DOUBLE_BOTTOM.getRotation(EnumFacing.NORTH);
        if (!back && forward && left && !right && up && !down)
            box = CORNER_DOUBLE_BOTTOM.getRotation(EnumFacing.WEST);
        if (back && !forward && !left && right && up && !down)
            box = CORNER_DOUBLE_BOTTOM.getRotation(EnumFacing.EAST);

        return box;
    }
}
