package main;

import listeners.MessageListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import util.SECRETS;

import javax.security.auth.login.LoginException;

public class Main {

    private static JDABuilder builder;

    public static void main(String[] args) {
        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(SECRETS.token);
        builder.setAutoReconnect(true);

        builder.setStatus(OnlineStatus.ONLINE);

        addListeners();

        try {
            JDA jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private static void addListeners() {
        builder.addEventListeners(new MessageListener());
    }
}
