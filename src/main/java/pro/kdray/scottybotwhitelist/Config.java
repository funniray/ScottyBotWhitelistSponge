package pro.kdray.scottybotwhitelist;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.asset.Asset;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {

    public static int id = -1;
    public static String message = "Please go to Mixer and run \"!whitelistme %name%\" in the appropriate chat.";
    public static boolean sublist = false;

    private static Path defaultConfig;
    private static Logger logger;
    private static Asset config;

    Config(Path defaultConfig, Logger logger, Asset config) {
        Config.defaultConfig = defaultConfig;
        Config.logger = logger;
        Config.config = config;
    }

    public static void reload() {
        ConfigurationLoader<CommentedConfigurationNode> loader;

        if (Files.notExists(defaultConfig)) {
            try {
                config.copyToFile(defaultConfig);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        loader = HoconConfigurationLoader.builder().setPath(defaultConfig).build();

        ConfigurationNode localConfig;

        if (loader != null) {
            try {
                localConfig = loader.load();
                id = localConfig.getNode("id").getInt();
                message = localConfig.getNode("message").getString();
                sublist = localConfig.getNode("sublist").getBoolean();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (id == -1) {
            logger.info("Config won't load :/");
        }
    }
}
