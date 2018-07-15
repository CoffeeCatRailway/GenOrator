package coffeecatteam.gen_o_rator.objects.blocks;

import coffeecatteam.gen_o_rator.GenOrator;
import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBase;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCable;
import coffeecatteam.gen_o_rator.util.aabb.Bounds;
import coffeecatteam.gen_o_rator.util.iinterface.IOreDict;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockCable extends BlockBase implements ITileEntityProvider, IOreDict {

    public static final PropertyBool BACK = PropertyBool.create("back");
    public static final PropertyBool FORWARD = PropertyBool.create("forward");
    public static final PropertyBool LEFT = PropertyBool.create("left");
    public static final PropertyBool RIGHT = PropertyBool.create("right");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");

    private String oreDict;
    private int capacity, maxReceive, maxExtract;

    public BlockCable(String name, String oreDict, int capacity, int maxReceive, int maxExtract) {
        super("cable_" + name, 0.1F, 1.0F, Material.ROCK, false, 1, GenOrator.TAB);
        this.oreDict = oreDict;
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.setDefaultState(this.blockState.getBaseState().withProperty(BACK, false).withProperty(FORWARD, false).withProperty(LEFT, false).withProperty(RIGHT, false).withProperty(UP, false).withProperty(DOWN, false));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int capacity = this.capacity;
        int maxReceive = this.maxReceive;
        int maxExtract = this.maxExtract;
        tooltip.add("Max energy storage: §6" + capacity + "§r");
        tooltip.add("Max energy receive: §6" + maxReceive + "§r");
        tooltip.add("Max energy extract: §6" + maxExtract + "§r");
    }

    @Override
    public String getOreDict() {
        return this.oreDict;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCable(this.capacity, this.maxReceive, this.maxExtract);
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
        boolean back = getNeighborState(EnumFacing.SOUTH, world, pos);
        boolean forward = getNeighborState(EnumFacing.NORTH, world, pos);
        boolean left = getNeighborState(EnumFacing.WEST, world, pos);
        boolean right = getNeighborState(EnumFacing.EAST, world, pos);
        boolean up = getNeighborState(EnumFacing.UP, world, pos);
        boolean down = getNeighborState(EnumFacing.DOWN, world, pos);

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
        boolean back = getNeighborState(EnumFacing.SOUTH, world, pos);
        boolean forward = getNeighborState(EnumFacing.NORTH, world, pos);
        boolean left = getNeighborState(EnumFacing.WEST, world, pos);
        boolean right = getNeighborState(EnumFacing.EAST, world, pos);
        boolean up = getNeighborState(EnumFacing.UP, world, pos);
        boolean down = getNeighborState(EnumFacing.DOWN, world, pos);

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

    private boolean getNeighborState(EnumFacing facing, IBlockAccess world, BlockPos pos) {
        boolean neighbor = false;
        switch (facing) {
            case SOUTH:
                neighbor = world.getBlockState(pos.south()).getBlock() instanceof BlockCable;
                break;
            case NORTH:
                neighbor = world.getBlockState(pos.north()).getBlock() instanceof BlockCable;
                break;
            case WEST:
                neighbor = world.getBlockState(pos.west()).getBlock() instanceof BlockCable;
                break;
            case EAST:
                neighbor = world.getBlockState(pos.east()).getBlock() instanceof BlockCable;
                break;
            case UP:
                neighbor = world.getBlockState(pos.up()).getBlock() instanceof BlockCable;
                break;
            case DOWN:
                neighbor = world.getBlockState(pos.down()).getBlock() instanceof BlockCable;
                break;
        }
        return neighbor;
    }
}
