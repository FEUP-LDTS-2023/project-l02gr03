package com.ldts.asphaltrush.controller.garage

import com.ldts.asphaltrush.Game
import com.ldts.asphaltrush.gui.GUI
import com.ldts.asphaltrush.model.garage.Garage
import spock.lang.Specification
import spock.lang.Subject;

class GarageControllerTest extends Specification {

    @Subject
    GarageController garageController
    Game game
    Garage garage

    def setup() {
        garage = new Garage(0)
        garageController = new GarageController(garage)
        game = new Game()
    }

    def "GarageController should respond correctly to user actions"() {
        when:
        garageController.step(game, GUI.ACTION.LEFT, System.currentTimeMillis())
        int movedLeftCar = garage.getCurrentCar()

        and:
        garageController.step(game, GUI.ACTION.RIGHT, System.currentTimeMillis())
        int movedRightCar = garage.getCurrentCar()

        then:
        movedLeftCar == 5
        movedRightCar == 0

        cleanup:
        game.getGameState().setState(null)
    }
}
