package listeners;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MessageListener extends ListenerAdapter {
    private List<TextChannel> textChannelList = new ArrayList<>();

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if(event.getAuthor().isBot()) return;

        if(event.getMessage().getContentDisplay().startsWith("!Post")) {
            if(!textChannelList.contains(event.getChannel())) {
                textChannelList.add(event.getChannel());
                event.getChannel().sendMessage("Channel hinzugefügt!").queue();
            }
        }

        if (textChannelList.contains(event.getChannel())) {
            for (TextChannel textChannel : textChannelList) {
                if (textChannel != event.getMessage().getChannel()) {
                    textChannel.sendMessage(event.getAuthor().getName() + "@" + event.getGuild().getName() + ": "
                            + event.getMessage().getContentDisplay()).queue();
                }
            }
        }
    }
}
