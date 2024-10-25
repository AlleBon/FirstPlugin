package org.makingstan;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Flashing text"
)
public class ExamplePlugin extends Plugin
{

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private MyCustomOverlay overlay;

	@Override
	protected void startUp() throws Exception
	{
		overlay.startScheduler();
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlay.stopScheduler();
		overlayManager.remove(overlay);
	}

	@Provides
	MyCustomConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MyCustomConfig.class);
	}
}
