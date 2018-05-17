package pro.kdray.scottybotwhitelist;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.nio.file.Path;

@Plugin(
        id = "scottybotwhitelist",
        name = "Scottybotwhitelist",
        description = "ScottyBot Whitelister for Sponge",
        url = "https://www.kdray.pro",
        authors = {
                "Funniray"
        }
)
public class Scottybotwhitelist {

    @Inject
    private Logger logger;

    @Inject
    private Game game;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path defaultConfig;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        new Config(this.defaultConfig, this.logger, Sponge.getAssetManager().getAsset(this, "defaultConfig.conf").get());
        Config.reload();
        Utils.reloadUsers();
        game.getEventManager().registerListeners(this, new EventListener());
    }
}
