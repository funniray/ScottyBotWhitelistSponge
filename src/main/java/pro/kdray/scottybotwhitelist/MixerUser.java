package pro.kdray.scottybotwhitelist;

import java.util.Date;

public class MixerUser {
    public String mcname;
    public String beamname;
    public String UserFriendlyDate;
    public Long toexpire;

    boolean isValid() {
        if (toexpire != null)
            return toexpire > new Date().getTime();
        return true;
    }
}
