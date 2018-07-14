package coffeecatteam.gen_o_rator.objects.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemTool;

import java.util.Set;

public class ItemBaseTool extends ItemTool {

    public ItemBaseTool(String name, CreativeTabs tab, float attackDamage, float attackSpeed, ToolMaterial material, Set<Block> effectiveBlocks) {
        super(attackDamage, attackSpeed, material, effectiveBlocks);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        maxStackSize = 1;
        setMaxDamage(material.getMaxUses());
    }
}
