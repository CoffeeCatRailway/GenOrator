package coffeecatteam.gen_o_rator.init;

import coffeecatteam.gen_o_rator.GenOrator;
import coffeecatteam.gen_o_rator.Reference;
import coffeecatteam.gen_o_rator.objects.blocks.BlockCable;
import coffeecatteam.gen_o_rator.objects.blocks.BlockCoalGenerator;
import coffeecatteam.gen_o_rator.objects.blocks.BlockDriedUpLog;
import coffeecatteam.gen_o_rator.objects.blocks.base.BlockBase;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCable;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCoalGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitBlock {

    /* Generators */
    public static final Block COAL_GENERATOR = new BlockCoalGenerator("coal_generator");
    public static final Block COAL_GENERATOR_ACTIVE = new BlockCoalGenerator("coal_generator_active");

    /* Cables */
    public static final Block CABLE_IRON = new BlockCable("iron", "blockCable", 500, 50, 50);
    public static final Block CABLE_SILVER = new BlockCable("silver", "blockCable", 1000, 50, 100);
    public static final Block CABLE_ALUMINIUM = new BlockCable("aluminium", "blockCable", 1500, 100, 150);
    public static final Block CABLE_COPPER = new BlockCable("copper", "blockCable", 2000, 200, 200);

    /* Ores */
    public static final Block ORE_SILVER = new BlockBase("silver_ore", 2.0F, 4.0F, Material.ROCK, false, 1, GenOrator.TAB);
    public static final Block ORE_ALUMINIUM = new BlockBase("aluminium_ore", 2.0F, 4.0F, Material.ROCK, false, 1, GenOrator.TAB);
    public static final Block ORE_COPPER = new BlockBase("copper_ore", 2.0F, 4.0F, Material.ROCK, false, 1, GenOrator.TAB);

    /* Other */
    public static final Block DRIED_UP_LOG = new BlockDriedUpLog("dried_up_log", GenOrator.TAB);

	public static void init() {
        registerGenerator(COAL_GENERATOR, COAL_GENERATOR_ACTIVE);
        register(CABLE_IRON, CABLE_SILVER, CABLE_ALUMINIUM, CABLE_COPPER);

        register(ORE_SILVER, ORE_ALUMINIUM, ORE_COPPER);

        //register(DRIED_UP_LOG);
	}

	public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileCoalGenerator.class, Reference.MODID+":coal_generator");
        GameRegistry.registerTileEntity(TileCable.class, Reference.MODID+":cable");
    }

    private static void registerGenerator(Block idle, Block active) {
	    register(idle);
        ForgeRegistries.BLOCKS.register(active);
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
