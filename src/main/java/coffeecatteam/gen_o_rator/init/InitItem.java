package coffeecatteam.gen_o_rator.init;

import coffeecatteam.gen_o_rator.GenOrator;
import coffeecatteam.gen_o_rator.objects.items.ItemBase;
import coffeecatteam.gen_o_rator.objects.items.ItemCraftingComponent;
import coffeecatteam.gen_o_rator.objects.items.ItemSapCollector;
import net.minecraft.item.Item;

public class InitItem {

    /* Ingot */
    public static final Item INGOT_SILVER = new ItemBase("ingot_silver", "ingotSilver", GenOrator.TAB);
    public static final Item INGOT_ALUMINIUM = new ItemBase("ingot_aluminium", "ingotAluminium", GenOrator.TAB);
    public static final Item INGOT_COPPER = new ItemBase("ingot_copper", "ingotCopper", GenOrator.TAB);

    /* Wire */
    public static final Item WIRE_IRON = new ItemBase("wire_iron", "wireIron", GenOrator.TAB);
    public static final Item WIRE_SILVER = new ItemBase("wire_silver", "wireSilver", GenOrator.TAB);
    public static final Item WIRE_ALUMINIUM = new ItemBase("wire_aluminium", "wireAluminium", GenOrator.TAB);
    public static final Item WIRE_COPPER = new ItemBase("wire_copper", "wireCopper", GenOrator.TAB);

    /* Crafting Components */
    public static final Item RF_CONVERTER = new ItemCraftingComponent("rf_converter");
    public static final Item MOTOR = new ItemCraftingComponent("motor");
    public static final Item ENERGY_CONNECTOR = new ItemCraftingComponent("energy_connector");

    public static final Item BLANK_CIRCUIT = new ItemCraftingComponent("blank_circuit");
    public static final Item BASIC_CIRCUIT = new ItemCraftingComponent("basic_circuit");
    public static final Item ADVANCED_CIRCUIT = new ItemCraftingComponent("advanced_circuit");

    /* Other */
    public static final Item TREE_SAP = new ItemBase("tree_sap", "", GenOrator.TAB);
    public static final Item RUBBER = new ItemBase("rubber", "itemRubber", GenOrator.TAB);
    public static final Item PLASTIC = new ItemBase("plastic", "itemPlastic", GenOrator.TAB);

    public static final Item SAP_COLLECTOR = new ItemSapCollector("sap_collector");

    public static void init() {
        /* Ingot */
        register(INGOT_SILVER, INGOT_ALUMINIUM, INGOT_COPPER);

        /* Wire */
        register(WIRE_IRON, WIRE_SILVER, WIRE_ALUMINIUM, WIRE_COPPER);

        /* Crafting Components */
        register(RF_CONVERTER, MOTOR, ENERGY_CONNECTOR);
        register(TREE_SAP, RUBBER, PLASTIC);
        register(BLANK_CIRCUIT, BASIC_CIRCUIT, ADVANCED_CIRCUIT);

        /* Other */
        register(SAP_COLLECTOR);
    }

    private static void register(Item... items) {
        for (Item item : items)
            register(item);
    }

	private static void register(Item item) {
        RegistrationHandler.Items.ITEMS.add(item);
    }
}
