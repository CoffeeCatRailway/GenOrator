package coffeecatteam.gen_o_rator;

import coffeecatteam.gen_o_rator.init.InitBlock;
import coffeecatteam.gen_o_rator.proxy.ProxyCommon;
import coffeecatteam.gen_o_rator.util.GuiHandler;
import coffeecatteam.gen_o_rator.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class GenOrator {

    public static final CreativeTabs TAB = new CreativeTabs("go_tab") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(InitBlock.COAL_GENERATOR);
        }
    };

    public static Logger logger = Utils.getLogger();

    @Mod.Instance
    public static GenOrator instance;

    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
    private static ProxyCommon proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        logger.info("Pre Initialize");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        logger.info("Initialize");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        logger.info("Post Initialize");
    }
}
