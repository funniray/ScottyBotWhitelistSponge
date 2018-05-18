package pro.kdray.scottybotwhitelist.Command;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import pro.kdray.scottybotwhitelist.Scottybotwhitelist;

public class ReloadCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        Scottybotwhitelist.reload();
        src.sendMessage(Text.of("Reloaded plugin!"));
        return CommandResult.success();
    }
}
