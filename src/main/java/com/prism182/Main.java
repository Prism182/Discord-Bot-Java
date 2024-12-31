package com.prism182;

import com.prism182.commands.commandHandler;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_BOT_TOKEN");
        
        if (token == null || token.isEmpty()) {
            System.out.println("error: DISCORD_BOT_TOKEN environment variable is not set!");
            return;
        }
        
        JDA bot = JDABuilder.createDefault(token)
                .setStatus(OnlineStatus.OFFLINE)
                .addEventListeners(new commandHandler())
//                .setActivity(Activity.playing("DND"))
                .build().awaitReady();
        
        Guild guild = bot.getGuildById("1323305942363672689");
        
        if (guild != null) {
            guild.upsertCommand("create-campaign", "Create a new campaign!")
                    .addOption(OptionType.STRING, "name", "The name of the campaign", true)
                    .addOption(OptionType.BOOLEAN, "req-role", "Whether a role is required to be made by the bot for the campaign", true)
                    .addOption(OptionType.BOOLEAN, "req-category", "Whether the category and channels are required to be made by the bot for the campaign", true)
                    .queue();
            
            guild.upsertCommand("end-campaign", "Create a new campaign!")
                    .addOption(OptionType.STRING, "name", "The name of the campaign", true)
                    .queue();
            
            guild.upsertCommand("ping", "Pings the bot")
                    .queue();
        }
        
    }
}