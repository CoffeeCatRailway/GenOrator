package coffeecatteam.gen_o_rator.init;

import coffeecatteam.gen_o_rator.Reference;
import coffeecatteam.gen_o_rator.objects.blocks.BlockCable;
import coffeecatteam.gen_o_rator.objects.blocks.BlockCoalGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCable;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCoalGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitBlock {

    public static final Block COAL_GENERATOR_OFF = new BlockCoalGenerator("coal_generator_off");
    public static final Block COAL_GENERATOR_ON = new BlockCoalGenerator("coal_generator_on");
    public static final Block CABLE = new BlockCable("cable");

	public static void init() {
        register(COAL_GENERATOR_OFF, CABLE);

        ForgeRegistries.BLOCKS.register(COAL_GENERATOR_ON);
	}

	public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileCoalGenerator.class, Reference.MODID+":coal_generator");
        GameRegistry.registerTileEntity(TileCable.class, Reference.MODID+":cable");
    }

    private static void register(Block... blocks) {
        for (Block block : blocks)
            register(block);
    }

    private static void register(Block block) {
	    RegistrationHandler.Blocks.BLOCKS.add(block);
        ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
        RegistrationHandler.Items.ITEMS.add(itemBlock);
    }

	public static void registerItemBlock(Block block, ItemBlock itemblock) {
		ForgeRegistries.BLOCKS.register(block);
		itemblock.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(itemblock);
		
		if (itemblock instanceof ItemSlab)
			RegistrationHandler.Items.ITEMS.add(itemblock);
	}
}
