package pro.kdray.scottybotwhitelist.Command;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import pro.kdray.scottybotwhitelist.Utils;

public class ListCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        src.sendMessage(Utils.getUserList());
        return CommandResult.success();
    }
}
