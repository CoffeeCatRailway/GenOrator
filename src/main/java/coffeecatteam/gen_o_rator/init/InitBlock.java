package coffeecatteam.gen_o_rator.init;

import coffeecatteam.gen_o_rator.Reference;
import coffeecatteam.gen_o_rator.objects.blocks.BlockCable;
import coffeecatteam.gen_o_rator.objects.blocks.BlockCoalGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCable;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCoalGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitBlock {

    /* Generators */
    public static final Block COAL_GENERATOR = new BlockCoalGenerator("coal_generator");

    // Cables
    public static final Block CABLE_IRON = new BlockCable("iron", "blockCable", 500, 50, 50);
    public static final Block CABLE_SILVER = new BlockCable("silver", "blockCable", 1000, 50, 100);
    public static final Block CABLE_ALUMINIUM = new BlockCable("aluminium", "blockCable", 1500, 100, 150);
    public static final Block CABLE_COPPER = new BlockCable("copper", "blockCable", 2000, 200, 200);

	public static void init() {
        register(COAL_GENERATOR);
        register(CABLE_IRON, CABLE_SILVER, CABLE_ALUMINIUM, CABLE_COPPER);
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
}
