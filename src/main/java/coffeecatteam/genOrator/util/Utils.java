package coffeecatteam.genOrator.util;

import coffeecatteam.genOrator.Reference;
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

    public static int getEnergyReading(int pixels, int currentEnergy, int maxEnergy) {
        if (currentEnergy == 0)
            return -1;
        return currentEnergy * pixels / maxEnergy;
    }
}
