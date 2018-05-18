package pro.kdray.scottybotwhitelist;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.permission.PermissionDescription;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.text.Text;
import pro.kdray.scottybotwhitelist.Command.ListCommand;
import pro.kdray.scottybotwhitelist.Command.ReloadCommand;

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

    public static void reload() {
        Config.reload();
        Utils.reloadUsers();
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        new Config(this.defaultConfig, this.logger, Sponge.getAssetManager().getAsset(this, "defaultConfig.conf").get());
        Config.reload();
        Utils.reloadUsers();
        game.getEventManager().registerListeners(this, new EventListener());

        PermissionDescription.Builder builder = game.getServiceManager().getRegistration(PermissionService.class).get()
                .getProvider().newDescriptionBuilder(this);
        builder.id("whitelister.whitelister")
                .description(Text.of("Needed to use /whitelist"))
                .assign(PermissionDescription.ROLE_STAFF, true)
                .assign(PermissionDescription.ROLE_USER, false)
                .register();
        builder.id("whitelister.whitelister.list")
                .description(Text.of("Needed to use /whitelist list"))
                .assign(PermissionDescription.ROLE_STAFF, true)
                .assign(PermissionDescription.ROLE_USER, false)
                .register();
        builder.id("whitelister.whitelister.reload")
                .description(Text.of("Needed to use /whitelist reload"))
                .assign(PermissionDescription.ROLE_STAFF, true)
                .assign(PermissionDescription.ROLE_USER, false)
                .register();

        CommandSpec reloadSpec = CommandSpec.builder()
                .description(Text.of("Reloads plugin"))
                .permission("whitelister.whitelister.reload")
                .executor(new ReloadCommand())
                .build();
        CommandSpec listSpec = CommandSpec.builder()
                .description(Text.of("Lists all players"))
                .permission("whitelister.whitelister.list")
                .executor(new ListCommand())
                .build();
        game.getCommandManager().register(this, CommandSpec.builder()
                        .description(Text.of("Main command for ScottybotWhitelist"))
                        .permission("whitelister.whitelister")
                        .child(reloadSpec, "reload")
                        .child(listSpec, "list")
                        .executor(new MainCommand())
                        .build(),
                "whitelister");
    }
}
