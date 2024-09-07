package addons.boze.antibookban;

import dev.boze.api.addon.module.ToggleableModule;

public class AntiBookBanModule extends ToggleableModule {
    public static AntiBookBanModule INSTANCE = new AntiBookBanModule();

    protected AntiBookBanModule() {
        super("AntiBookBan", "Prevents you from getting book banned, but can cause inventory desync");
    }
}
