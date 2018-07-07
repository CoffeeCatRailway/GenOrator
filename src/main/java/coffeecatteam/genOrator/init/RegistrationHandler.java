package coffeecatteam.genOrator.init;

import coffeecatteam.genOrator.Reference;
import coffeecatteam.genOrator.util.IOreDict;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public class RegistrationHandler {

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    public static class Blocks {
        public static final Set<Block> BLOCKS = new HashSet<>();

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> reg = event.getRegistry();

            for (final Block block : BLOCKS) {
                reg.register(block);
            }
        }

        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event) {
            for (Block block : BLOCKS)
                registerBlockModel(block);
        }

        private static void registerBlockModel(final Block block) {
            final String registryName = block.getRegistryName().toString();
            final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, location);
        }
    }

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    public static class Items {
        public static final Set<Item> ITEMS = new HashSet<>();

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            for (Item item : ITEMS) {
                event.getRegistry().register(item);
                if (item instanceof IOreDict)
                    OreDictionary.registerOre(((IOreDict) item).getOreDict(), item);
            }
        }

        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event) {
            for (Item item : ITEMS)
                registerItemModel(item);
        }

        private static void registerItemModel(final Item item) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
        }
    }

    public static void init() {
        InitBlock.init();
        InitBlock.registerTileEntities();
        InitItem.init();
    }
}
