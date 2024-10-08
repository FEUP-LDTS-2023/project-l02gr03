package com.ldts.asphaltrush.viewer.menu;

import com.ldts.asphaltrush.gui.GUI;
import com.ldts.asphaltrush.model.Image;
import com.ldts.asphaltrush.model.ImageFactory;
import com.ldts.asphaltrush.model.Position;
import com.ldts.asphaltrush.model.menu.Menu;
import com.ldts.asphaltrush.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui, ImageFactory imageFactory) {
        // Draw the background
        gui.drawImage(new Position(0, 0), imageFactory.getImage("/background/menu"));
        // Draw the main Title
        gui.drawImage(new Position(40, 15), imageFactory.getImage("/title/menu"));

        // Draw all the different options
        for (int i = 0; i < getModel().getNumberEntries(); i++){
            // Consider if the entry is selected or not
            String selected = getModel().isSelected(i) ? "selected/" : "";
            Image entryImage = imageFactory.getImage("/text/" + selected + getModel().getEntry(i));
            gui.drawImage(new Position(130, 100 + i * 20), entryImage, 'c', 't');

        }

    }
}
