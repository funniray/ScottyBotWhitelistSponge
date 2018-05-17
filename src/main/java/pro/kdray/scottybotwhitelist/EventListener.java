package pro.kdray.scottybotwhitelist;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class EventListener {

    @Listener
    public void onLogin(ClientConnectionEvent.Auth e) {

        if (!Utils.verify(e.getProfile().getName().get())) {
            e.setMessage(Text.of(TextColors.RED, Config.message.replace("%name%", e.getProfile().getName().get())));
            e.setCancelled(true);
        }

    }
}
