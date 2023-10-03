package pl.akademiaqa.properties;

import java.util.ResourceBundle;

public class ClickUpProperties {

    private static final String TOKEN = "clickup.token";
    private static final String TEAM_ID = "clickup.team.id";

    public static String getToken() {
        return getProperties(TOKEN);
    }

    public static String getTeamId() {
        return getProperties(TEAM_ID);
    }

    private static String getProperties(String key) {
        return ResourceBundle.getBundle("clickup").getString(key);
    }
}
