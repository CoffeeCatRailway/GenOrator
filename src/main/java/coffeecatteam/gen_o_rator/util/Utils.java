package coffeecatteam.gen_o_rator.util;

import coffeecatteam.gen_o_rator.Reference;
import coffeecatteam.gen_o_rator.objects.tileentity.base.TileBaseGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getFormatterLogger(Reference.NAME.replace("\\s+", ""));
        }
        return logger;
    }

    public static int getEnergyReading(TileBaseGenerator generator, int pixels) {
        if (generator.getField(0) == 0)
            return -1;
        return generator.getField(0) * pixels / generator.getField(1);
    }

    public static int getBurnTime(TileBaseGenerator generator, int pixels) {
        if (generator.getField(2) == generator.getField(3))
            return -1;
        return generator.getField(2) * pixels / 100;
    }
}
