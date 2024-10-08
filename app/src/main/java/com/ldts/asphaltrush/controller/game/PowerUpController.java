package com.ldts.asphaltrush.controller.game;

import com.ldts.asphaltrush.Game;
import com.ldts.asphaltrush.gui.GUI;
import com.ldts.asphaltrush.model.Position;
import com.ldts.asphaltrush.model.game.elements.powerup.InvenciblePowerUp;
import com.ldts.asphaltrush.model.game.elements.powerup.PointMultiplierPowerUp;
import com.ldts.asphaltrush.model.game.elements.powerup.PowerUp;
import com.ldts.asphaltrush.model.game.street.Street;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerUpController extends GameController {

    private long lastMovement;
    private static final double POWER_UP_SPEED = DEFAULT_SPEED;
    private static final Random RNG = new Random();

    public PowerUpController(Street street) {
        super(street);
        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException {
        if (time - lastMovement > 100) {
            addNewPowerUps();
            for (PowerUp powerUp : getModel().getPowerUps()) {
                movePowerUp(powerUp, powerUp.getPosition());
            }
            this.lastMovement = time;
            checkAndRemovePowerUps();
        }
    }

    private void movePowerUp(PowerUp powerUp, Position position) {
        powerUp.setPosition(new Position(position.getX(), position.getY() + (int) (POWER_UP_SPEED*getModel().getPlayer().getSpeed())));
    }

    private void checkAndRemovePowerUps() {
        List<PowerUp> powerUpsToRemove = new ArrayList<>();

        for (PowerUp powerUp : getModel().getPowerUps()) {
            if (powerUp.getPosition().getY() >= getModel().getHeight()) {
                powerUpsToRemove.add(powerUp);
            }
        }

        getModel().getPowerUps().removeAll(powerUpsToRemove);
    }

    private void addNewPowerUps() {
        int r = RNG.nextInt(0, 5);
        int x = r-1+r * 28 + getModel().getLeftCurbWidth() + 14;
        int y = -50;
        PointMultiplierPowerUp pointMultiplierPowerUp = new PointMultiplierPowerUp(x,y);
        if(RNG.nextDouble(0,100) < 1 &&
                !getModel().isPowerUp(new Position(x, y), pointMultiplierPowerUp.getWidth(), pointMultiplierPowerUp.getHeight()) &&
                !getModel().isHole(new Position(x,y), pointMultiplierPowerUp.getWidth(), pointMultiplierPowerUp.getHeight()))
            getModel().getPowerUps().add(pointMultiplierPowerUp);

        r = RNG.nextInt(0, 5);
        x = r-1+r * 28 + getModel().getLeftCurbWidth() + 14;
        InvenciblePowerUp invenciblePowerUp = new InvenciblePowerUp(x,y);
        if(RNG.nextDouble(0,100) < 1 &&
                !getModel().isPowerUp(new Position(x, y), invenciblePowerUp.getWidth(), invenciblePowerUp.getHeight()) &&
                !getModel().isHole(new Position(x,y), invenciblePowerUp.getWidth(), invenciblePowerUp.getHeight()))
            getModel().getPowerUps().add(invenciblePowerUp);
    }
}
