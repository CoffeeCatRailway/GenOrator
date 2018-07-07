package coffeecatteam.genOrator.objects.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockBase extends Block {

    public BlockBase(String name, float hardness, float resistance, Material material, CreativeTabs tab) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(tab);
    }
    
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            String info = I18n.format(this.getUnlocalizedName() + ".info");
            tooltip.addAll(Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(info, 150));
        } else {
            tooltip.add(TextFormatting.YELLOW + I18n.format("item.show_info", "SHIFT"));
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
