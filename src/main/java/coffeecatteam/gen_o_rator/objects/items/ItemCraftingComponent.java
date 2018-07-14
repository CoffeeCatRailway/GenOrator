package coffeecatteam.gen_o_rator.objects.items;

import coffeecatteam.gen_o_rator.GenOrator;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCraftingComponent extends ItemBase {

    public ItemCraftingComponent(String name) {
        super(name, "craftingComponent", GenOrator.TAB);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Used for crafting items.");
    }
}
