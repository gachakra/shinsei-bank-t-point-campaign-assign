package dev.notonza.shinsei.config;

import com.google.inject.Singleton;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
@Singleton
public class SlackConfiguration implements YamlConfiguration {

    private String channelId;
    private String botToken;

    public String channelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String botToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public String toString() {
        return "SlackConfiguration{" +
            "channelId='" + channelId + '\'' +
            ", botToken='" + botToken + '\'' +
            '}';
    }
}
