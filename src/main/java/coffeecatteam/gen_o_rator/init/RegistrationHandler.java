package coffeecatteam.gen_o_rator.init;

import coffeecatteam.gen_o_rator.Reference;
import coffeecatteam.gen_o_rator.util.iinterface.IOreDict;
import coffeecatteam.gen_o_rator.util.iinterface.SubModels;
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
            BLOCKS.forEach(block -> Items.registerItemModel(Item.getItemFromBlock(block)));
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
            ITEMS.forEach(Items::registerItemModel);
        }

        public static void registerItemModel(final Item item) {
            if (item instanceof SubModels) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(((SubModels) item).getModel(), "inventory"));
            } else {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
            }
        }
    }

    public static void init() {
        InitBlock.init();
        InitBlock.registerTileEntities();
        InitItem.init();
    }
}
