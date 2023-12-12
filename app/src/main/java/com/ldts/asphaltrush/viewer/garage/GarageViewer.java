package com.ldts.asphaltrush.viewer.garage;

import com.ldts.asphaltrush.gui.GUI;
import com.ldts.asphaltrush.model.Image;
import com.ldts.asphaltrush.model.ImageFactory;
import com.ldts.asphaltrush.model.Position;
import com.ldts.asphaltrush.model.garage.Garage;
import com.ldts.asphaltrush.viewer.Viewer;

public class GarageViewer extends Viewer<Garage> {
    public GarageViewer(Garage model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui, ImageFactory imageFactory) {

        gui.drawImage(new Position(0,0), imageFactory.getImage("/background/garage"));

        Image carImage = imageFactory.getImage("/cars/player/garage/car" + getModel().getCurrentCar());
        gui.drawImage(new Position(130-carImage.getWidth()/2,150-carImage.getHeight()/2), carImage);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                if (index < getModel().getNumberEntries()) {
                    String entry = getModel().getEntry(index);
                    String color = getModel().isSelected(index) ? "#FFD700" : "#FFFFFF";
                    gui.drawText(new Position(5 + col * 2, 13 + row), entry, color);
                }
            }
        }
    }
}

