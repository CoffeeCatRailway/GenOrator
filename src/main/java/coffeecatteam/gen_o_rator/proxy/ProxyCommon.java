package coffeecatteam.gen_o_rator.proxy;

import coffeecatteam.gen_o_rator.init.RegistrationHandler;
import coffeecatteam.gen_o_rator.util.Smelting;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyCommon {

	public void preInit(FMLPreInitializationEvent event) {
        RegistrationHandler.init();
	}

	public void init(FMLInitializationEvent event) {
        Smelting.register();
	}

	public void postInit(FMLPostInitializationEvent event) {
	}
}
