package coffeecatteam.gen_o_rator.objects.items;

import coffeecatteam.gen_o_rator.util.iinterface.IOreDict;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IOreDict {

    private String oredict;

    public ItemBase(String name, String oredict, CreativeTabs tab) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        this.oredict = oredict;
    }

    @Override
    public String getOreDict() {
        return this.oredict;
    }
}
