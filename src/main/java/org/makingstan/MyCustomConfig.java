package org.makingstan;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("mycustomplugin")
public interface MyCustomConfig extends Config
{
	@ConfigItem(
			keyName = "showText",
			name = "Show Text",
			description = "Tick this box to display text on the screen"
	)
	default boolean showText()
	{
		return true; // Default to showing the text; user can toggle off
	}

	@ConfigItem(
			keyName = "TextToSay",
			name = "Text on screen",
			description = ""
	)
	default String textmsg()
	{
		return "Hello";
	}

	@ConfigItem(
			keyName = "textsize",
			name = "Text Size",
			description = ""
	)
	default int size()
	{
		return 12;
	}

	@ConfigItem(
			keyName = "ColorText",
			name = "Color Text",
			description = ""
	)
	default Color colorText()
	{
		return Color.RED;
	}
}
