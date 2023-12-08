package com.ldts.asphaltrush.controller.game;

import com.ldts.asphaltrush.Game;
import com.ldts.asphaltrush.gui.GUI;
import com.ldts.asphaltrush.model.game.elements.powerup.PointMultiplierPowerUp;
import com.ldts.asphaltrush.model.game.elements.powerup.PowerUp;
import com.ldts.asphaltrush.model.game.street.Street;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PointsController extends GameController {
    private long lastUpdate;
    private static final long POINTS_UPDATE_INTERVAL = 250; // 500 milliseconds = 0.5 seconds

    public PointsController(Street street) {
        super(street);
        lastUpdate = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        PowerUp playerPowerUp = getModel().getPlayer().getPowerUp();
        double multiplier = getModel().getPlayer().getSpeed();
        if(playerPowerUp != null && playerPowerUp.getClass() == PointMultiplierPowerUp.class) multiplier*=10;

        getModel().getPoints().setMultiplier(multiplier);

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastUpdate;

        if (elapsedTime >= POINTS_UPDATE_INTERVAL) {
            getModel().getPoints().increasePoints(1);
            lastUpdate = currentTime;
        }
        System.out.println(getModel().getPoints().getPoints());
    }
}
