package com.prism182;

import com.prism182.commands.commandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JDA bot = JDABuilder.createDefault("MTMyMzMzNDU3MjM0MzE2NTAwOA.GGrmOE.5HRoCfT9SFtxRqp-_N12JQpzyE8_H107qpafqM")
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(new commandListener())
//                .setActivity(Activity.playing("DND"))
                .build().awaitReady();
        
        Guild guild = bot.getGuildById("1323305942363672689");
        
        if (guild != null) {
            guild.upsertCommand("create-campaign", "Create a new campaign!")
                    .addOption(OptionType.STRING, "name", "The name of the campaign", true)
                    .addOption(OptionType.BOOLEAN, "req-role", "Whether a role is required to be made by the bot for the campaign", true)
                    .queue();
            
            guild.upsertCommand("ping", "Pings the bot")
                    .queue();
        }
        
    }
}