package coffeecatteam.gen_o_rator.objects.tileentity.base;

import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBaseGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileBaseGenerator extends TileEnergyBase implements IInventory, ITickable {

    private int extraFieldCount;

    protected int maxCooldown = 100;
    protected int cooldown = maxCooldown;

    public boolean burn;

    protected NonNullList<ItemStack> inventory;
    protected BlockBaseGenerator generator;

    public TileBaseGenerator(BlockBaseGenerator generator, int capacity, int maxReceive, int maxExtract, int energy) {
        this(generator, capacity, maxReceive, maxExtract, energy, 0);
    }

    public TileBaseGenerator(BlockBaseGenerator generator, int capacity, int maxReceive, int maxExtract, int energy, int extraFieldCount) {
        this(generator, capacity, maxReceive, maxExtract, energy, extraFieldCount, 1);
    }

    public TileBaseGenerator(BlockBaseGenerator generator, int capacity, int maxReceive, int maxExtract, int energy, int extraFieldCount, int inventorySize) {
        super(capacity, maxReceive, maxExtract, energy);
        inventory = NonNullList.<ItemStack>withSize(inventorySize, ItemStack.EMPTY);
        this.generator = generator;
        this.extraFieldCount = extraFieldCount;
    }

    @Override
    public String getName() {
        return this.generator.getUnlocalizedName().replace("_active", "") + ".name";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation(getName());
    }

    @Override
    public int getSizeInventory() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.inventory)
            if (!stack.isEmpty())
                return false;
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= getSizeInventory())
            return ItemStack.EMPTY;
        return inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (getStackInSlot(index) != ItemStack.EMPTY) {
            ItemStack stack;
            if (getStackInSlot(index).getCount() <= count) {
                stack = getStackInSlot(index);
                setInventorySlotContents(index, ItemStack.EMPTY);
                markDirty();
                return stack;
            } else {
                stack = getStackInSlot(index).splitStack(count);
                if (getStackInSlot(index).getCount() <= 0)
                    setInventorySlotContents(index, ItemStack.EMPTY);
                else
                    setInventorySlotContents(index, getStackInSlot(index));
                markDirty();
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);
        setInventorySlotContents(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= getSizeInventory())
            return;
        if (stack != ItemStack.EMPTY && stack.getCount() > getInventoryStackLimit())
            stack.setCount(getInventoryStackLimit());
        if (stack != ItemStack.EMPTY && stack.getCount() == 0)
            stack = ItemStack.EMPTY;
        inventory.set(index, stack);
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(getPos()) == this && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
    }

    @Override
    public void clear() {
        for (int i = 0; i < getSizeInventory(); i++)
            setInventorySlotContents(i, ItemStack.EMPTY);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != ItemStack.EMPTY) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        compound.setTag("Items", list);
        compound.setInteger("cooldown", this.cooldown);
        compound.setBoolean("burn", this.burn);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        NBTTagList list = compound.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            setInventorySlotContents(slot, new ItemStack(stackTag));
        }
        this.cooldown = compound.getInteger("cooldown");
        this.burn = compound.getBoolean("burn");
        super.readFromNBT(compound);
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return this.energyStorage.getEnergyStored();
            case 1:
                return this.energyStorage.getMaxEnergyStored();
            case 2:
                return this.cooldown;
            case 3:
                return this.maxCooldown;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.energyStorage.setEnergy(value);
                break;
            case 1:
                this.energyStorage.setCapacity(value);
                break;
            case 2:
                this.cooldown = value;
                break;
            case 3:
                this.maxCooldown = value;
                break;
        }
        this.markDirty();
    }

    @Override
    public int getFieldCount() {
        return 4 + this.extraFieldCount;
    }

    public boolean isBurning() {
        return this.cooldown > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory) {
        return inventory.getField(0) > 0;
    }
}
