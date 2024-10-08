package com.ldts.asphaltrush.viewer.game;

import com.ldts.asphaltrush.gui.GUI;
import com.ldts.asphaltrush.model.ImageFactory;
import com.ldts.asphaltrush.model.game.elements.powerup.PowerUp;

public class PowerUpViewer implements ElementViewer<PowerUp> {

    @Override
    public void draw(PowerUp element, GUI gui, ImageFactory imageFactory) {
        // Draw the PowerUps considering their type
        gui.drawImage(element.getPosition(), imageFactory.getImage("/elements/"+element.getType()), 'c', 't');
    }
}
