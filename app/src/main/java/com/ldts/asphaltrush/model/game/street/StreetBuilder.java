package com.ldts.asphaltrush.model.game.street;

import com.ldts.asphaltrush.model.Image;
import com.ldts.asphaltrush.model.ImageFactory;
import com.ldts.asphaltrush.model.game.elements.*;
import com.ldts.asphaltrush.model.game.elements.obstacleCar.ObstacleCarBuilder;
import java.util.ArrayList;
import java.util.LinkedList;


public class StreetBuilder {


    private int playerType;
    private ImageFactory imageFactory;

    public StreetBuilder(int playerType, ImageFactory imageFactory ){
        this.playerType = playerType;
        this.imageFactory = imageFactory;
    }

    public Street createStreet() {
        Street street = new Street(new ObstacleCarBuilder(imageFactory));

        street.setPlayer(createPlayer());
        street.setLines(createLines());
        street.setHoles(new ArrayList<>());
        street.setObstacleCars(new ArrayList<>());
        street.setPowerUps(new ArrayList<>());
        return street;
    }


    private Player createPlayer() {
        Image playerImage = imageFactory.getImage("/cars/player/car" + playerType);
        return new Player(121,235-playerImage.getHeight() ,playerImage.getWidth(), playerImage.getHeight(), playerType);
    }

    private LinkedList<Line> createLines() {
        LinkedList<Line> lines = new LinkedList<>();

        for (int y=0; y<240 ; y+=33){
            lines.addLast(new Line(87, y));
        }
        return lines;
    }

}