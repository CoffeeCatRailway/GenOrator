package coffeecatteam.genOrator.init;

import net.minecraft.item.Item;

public class InitItem {

    

	public static void init() {

	}

    private static void register(Item... items) {
        for (Item item : items)
            register(item);
    }

	private static void register(Item item) {
        RegistrationHandler.Items.ITEMS.add(item);
    }
}
