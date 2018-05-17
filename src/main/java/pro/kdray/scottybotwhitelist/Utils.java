package pro.kdray.scottybotwhitelist;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.net.HttpHeaders.USER_AGENT;

public class Utils {

    static Map<Integer, MixerUser> users = new HashMap();

    public static String getHTTP(String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static boolean verify(String name, boolean shouldCheck) {

        for (Map.Entry<Integer, MixerUser> user : users.entrySet()) {
            if (user.getValue().mcname.equalsIgnoreCase(name)) {
                if (user.getValue().isValid()) {
                    return true;
                } else {
                    users.remove(user.getKey());
                }
            }
        }

        if (shouldCheck) {
            reloadUsers();
            return verify(name, false);
        }

        return false;
    }

    public static boolean verify(String name) {
        return verify(name, true);
    }

    public static void reloadUsers() {
        String users;
        try {
            users = getHTTP("https://api.scottybot.net/api/whitelist?channame=" + Config.id);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Type listType = new TypeToken<Map<Integer, MixerUser>>() {
        }.getType();

        Utils.users = new Gson().fromJson(users, listType);
    }
}
