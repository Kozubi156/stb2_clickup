package pl.akademiaqa.properties;

import static java.lang.System.getProperty;

import java.util.ResourceBundle;

public class ClickUpProperties {

    private static final String TOKEN = "clickup.token";
    private static final String TEAM_ID = "clickup.team.id";

    public static String getToken() {
        if (getProperty(TOKEN).isEmpty() || getProperty(TOKEN).startsWith("YOUR")) {
            return System.getProperty("TOKEN");
        } else {
            return getProperties(TOKEN);
        }
    }

    public static String getTeamId() {
        if (getProperty(TEAM_ID).isEmpty() || getProperty(TEAM_ID).startsWith("YOUR")) {
            return System.getProperty("TOKEN");
        } else {
            return getProperties(TEAM_ID);
        }
    }

    private static String getProperties(String key) {
        return ResourceBundle.getBundle("clickup").getString(key);
    }
}
