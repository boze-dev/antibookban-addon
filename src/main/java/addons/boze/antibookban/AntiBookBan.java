package addons.boze.antibookban;

import com.google.gson.JsonObject;
import dev.boze.api.addon.Addon;
import dev.boze.api.addon.module.ToggleableModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AntiBookBan extends Addon {

    public static final String ID = "antibookban";
    public static final String NAME = "AntiBookban";
    public static final String DESCRIPTION = "An addon that prevents you from getting book banned";
    public static final String VERSION = "1.0.0";

    public static final AntiBookBan INSTANCE = new AntiBookBan();

    public static final Logger LOG = LogManager.getLogger();

    public AntiBookBan() {
        super(ID, NAME, DESCRIPTION, VERSION);
    }

    @Override
    public boolean initialize() {
        LOG.info("Initializing " + name);

        modules.add(AntiBookBanModule.INSTANCE);

        LOG.info("Successfully initialized " + name);

        return super.initialize();
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();

        JsonObject modulesObject = new JsonObject();

        for (ToggleableModule module : modules) {
            modulesObject.add(module.getName(), module.toJson());
        }

        object.add("modules", modulesObject);

        return object;
    }

    @Override
    public Addon fromJson(JsonObject jsonObject) {
        if (!jsonObject.has("modules")) {
            return this;
        }

        JsonObject modulesObject = jsonObject.getAsJsonObject("modules");

        for (ToggleableModule module : modules) {
            if (modulesObject.has(module.getName())) {
                module.fromJson(modulesObject.getAsJsonObject(module.getName()));
            }
        }

        return this;
    }
}
