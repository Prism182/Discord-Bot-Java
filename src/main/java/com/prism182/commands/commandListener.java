package com.prism182.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

public class commandListener extends ListenerAdapter {
    
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("create-campaign")) {
            createCampaign(event);
        } else if (event.getName().equals("ping")) {
            pingCommand(event);
        }
    }
    
    public static void pingCommand(SlashCommandInteraction event) {
        long Time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(false).flatMap(v -> event.getHook().editOriginalFormat(
                "Pong: %d ms", System.currentTimeMillis() - Time)).queue();
    }
    public static void createCampaign(SlashCommandInteractionEvent event) {
        if (event.getName().equals("create-campaign")) {
            
            event.deferReply().queue();
            
            OptionMapping campaignName = event.getOption("name");
            OptionMapping roleRequired = event.getOption("req-role");
            
            if (campaignName.getAsString() == null || roleRequired.getAsString() == null) {
                event.getHook().sendMessage("Please ensure all fields are filled").setEphemeral(true).queue();
                
                return;
            }else if (roleRequired.getAsBoolean()) {
                event.getHook().sendMessage("This would have made a role").setEphemeral(false).queue();
            }
            
            event.getHook().sendMessage("Created the campaign with name '"+ campaignName.getAsString() +"'").setEphemeral(false).queue();
        }
    }
    
}
