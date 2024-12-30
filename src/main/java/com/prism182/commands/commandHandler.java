package com.prism182.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class commandHandler extends ListenerAdapter {
    
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
            
            Guild guild = event.getGuild();
            Channel channel = event.getChannel();
            Member member = event.getMember();
            OptionMapping campaignName = event.getOption("name");
            OptionMapping roleRequired = event.getOption("req-role");
            OptionMapping categoryRequired = event.getOption("req-category");
            
            assert campaignName != null;
            String categoryName = campaignName.getAsString();
            
            if (!(channel.getId().equals("1323309898804498603") || channel.getId().equals("1323309779313102848")) || !Objects.requireNonNull(guild).getId().equals("1323305942363672689")) {
                event.getHook().sendMessage("This is not the right place nor time for this command to be used").setEphemeral(true).queue();
                return;
            }
            
            if (campaignName.getAsString() == null || roleRequired.getAsString() == null || categoryRequired.getAsString() == null) {
                event.getHook().sendMessage("Please ensure all fields are filled").setEphemeral(true).queue();
                return;
            }
            if (roleRequired.getAsBoolean()) {
                
                guild.createRole().setName(categoryName).queue(role -> {
                    
                    guild.addRoleToMember(member, role).queue();
                    
                    if (categoryRequired.getAsBoolean()) {
                        
                        guild.createCategory(categoryName).queue(category -> {
                                    
                                    category.getManager()
                                            .putPermissionOverride(role, EnumSet.of(Permission.VIEW_CHANNEL), null)
                                            .putPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                                            .queue();
                                    
                                    category.createTextChannel("general").queue();
                                    category.createTextChannel("downtime").queue();
                                    category.createTextChannel("scheduling").queue();
                                    category.createTextChannel("the-tale").queue();
                                    category.createTextChannel("link-collection").queue();
                                    category.createTextChannel("materials").queue();
                                    category.createVoiceChannel("the tavern").queue();
                                    
                                    event.getHook().sendMessage("Category, channels, and role created successfully!")
                                            .setEphemeral(false)
                                            .queue();
                                },
                                error -> {
                                    event.getHook().sendMessage("Failed to create the category and channels, please check permissions")
                                            .setEphemeral(false)
                                            .queue();
                                }
                        );
                    }
                },
                error -> {
                    event.getHook().sendMessage("Failed to create the role. Please check permissions")
                            .setEphemeral(false)
                            .queue();
                });
            }
            
            event.getHook().sendMessage("Created the campaign channels, category and role with name: "+ categoryName)
                    .setEphemeral(false)
                    .queue();
        }
    }
    
}
