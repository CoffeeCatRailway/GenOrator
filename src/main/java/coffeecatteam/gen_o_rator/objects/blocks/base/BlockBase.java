package coffeecatteam.gen_o_rator.objects.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockBase extends Block {

    public BlockBase(String name, float hardness, float resistance, Material material, boolean isWooden, int harvestLevel, CreativeTabs tab) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(hardness);
        setResistance(resistance);
        setHarvestLevel(isWooden ? "axe" : "pickaxe", harvestLevel);
        if (isWooden)
            setSoundType(SoundType.WOOD);
        setCreativeTab(tab);
    }
}
