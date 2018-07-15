package coffeecatteam.gen_o_rator.proxy;

import coffeecatteam.gen_o_rator.objects.tileentity.generators.TileEndCrystalGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.render.EndCrystalGeneratorRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyClient extends ProxyCommon {

	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

        ClientRegistry.bindTileEntitySpecialRenderer(TileEndCrystalGenerator.class, new EndCrystalGeneratorRenderer());
	}

	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
