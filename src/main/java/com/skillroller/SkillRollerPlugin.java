package com.skillroller;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Skill Roller"
)
public class SkillRollerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private SkillRollerConfig config;

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Skill Roller says " + config.greeting(), null);
		}
	}

	@Provides
    SkillRollerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillRollerConfig.class);
	}
}
