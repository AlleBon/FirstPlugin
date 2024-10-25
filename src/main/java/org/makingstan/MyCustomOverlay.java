package org.makingstan;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyCustomOverlay extends Overlay
{
    private int x = 100;
    private int y = 100;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Inject
    private MyCustomConfig config;

    @Inject
    MyCustomOverlay(MyCustomConfig config)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    // Call this to start the scheduler
    public void startScheduler()
    {
        // Stop any existing scheduler first
        stopScheduler();

        // Initialize and start the new scheduler
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::updatePosition, 0, 2, TimeUnit.SECONDS);
    }

    // Call this to stop the scheduler
    public void stopScheduler()
    {
        if (scheduler != null && !scheduler.isShutdown())
        {
            scheduler.shutdown();
            scheduler = null;  // Set to null to indicate the scheduler is no longer active
        }
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        // Only render text if the "Show Text" checkbox is ticked
        if (config.showText())
        {
            graphics.setColor(new Color(config.colorText().getRed(), config.colorText().getGreen(), config.colorText().getBlue(), config.textAlpha()));
            graphics.setFont(new Font(graphics.getFont().getFontName(), Font.PLAIN, config.size()));
            graphics.drawString(config.textmsg(), x, y);
        }
        return null;
    }

    private void updatePosition()
    {
        // Randomly change x and y coordinates within a specified range
        x = 100 + (int) (Math.random() * 200);
        y = 100 + (int) (Math.random() * 200);
    }
}
