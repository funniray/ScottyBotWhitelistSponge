package pro.kdray.scottybotwhitelist;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainCommand implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource source, CommandContext arguments) throws CommandException {
        source.sendMessage(this.getUsage(source));
        return CommandResult.success();
    }

    public List<String> getSuggestions(CommandSource source, String arguments, @Nullable Location<World> targetPosition) throws CommandException {
        return new ArrayList<>();
    }

    public boolean testPermission(CommandSource source) {
        return source.hasPermission("whitelister.whitelist");
    }

    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.of(Text.of("Main command for ScottybotWhitelist"));
    }

    public Optional<Text> getHelp(CommandSource source) {
        return Optional.of(Text.of("There's not much needed here..."));
    }

    public Text getUsage(CommandSource source) {
        return Text.of("/whitelister <reload | list>");
    }
}
