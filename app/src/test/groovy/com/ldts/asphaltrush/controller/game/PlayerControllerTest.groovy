package com.ldts.asphaltrush.controller.game

import com.ldts.asphaltrush.model.ImageFactory
import com.ldts.asphaltrush.model.game.elements.powerup.InvenciblePowerUp
import com.ldts.asphaltrush.model.game.elements.powerup.PowerUp
import com.ldts.asphaltrush.model.game.street.StreetBuilder
import spock.lang.Specification
import spock.lang.Subject
import com.ldts.asphaltrush.gui.GUI
import com.ldts.asphaltrush.Game
import com.ldts.asphaltrush.model.game.street.Street
import com.ldts.asphaltrush.model.Position

class PlayerControllerTest extends Specification {

    @Subject
    StreetBuilder streetBuilder
    Street street
    PlayerController playerController

    def setup() {
        streetBuilder = new StreetBuilder(1, new ImageFactory())
        street = streetBuilder.createStreet()
        playerController = new PlayerController(street)
    }

    def "movePlayerLeft should move the player to the left"() {
        given:
        int x = playerController.getModel().getPlayer().getPosition().getX()
        int y = playerController.getModel().getPlayer().getPosition().getY()

        when:
        playerController.movePlayerLeft()

        then:
        playerController.getModel().getPlayer().getPosition() == new Position(x - 28, y)

        cleanup:
        streetBuilder = null
        street = null
        playerController = null
    }

    def "movePlayerRight should move the player to the right"() {
        given:
        int x = playerController.getModel().getPlayer().getPosition().getX()
        int y = playerController.getModel().getPlayer().getPosition().getY()

        when:
        playerController.movePlayerRight()

        then:
        playerController.getModel().getPlayer().getPosition() == new Position(x + 28, y)

        cleanup:
        streetBuilder = null
        street = null
        playerController = null
    }

    def "increasePlayerSpeed should increase the player speed"() {
        when:
        double speed = playerController.getModel().getPlayer().getSpeed()
        playerController.increasePlayerSpeed()

        then:
        playerController.getModel().getPlayer().getSpeed() == speed+0.2

        cleanup:
        streetBuilder = null
        street = null
        playerController = null
    }

    def "decreasePlayerSpeed should decrease the player speed"() {
        when:
        double speed = playerController.getModel().getPlayer().getSpeed()
        playerController.increasePlayerSpeed()
        playerController.decreasePlayerSpeed()

        then:
        playerController.getModel().getPlayer().getSpeed() == speed

        cleanup:
        streetBuilder = null
        street = null
        playerController = null
    }

    def "step should handle power-up and increase player speed accordingly"() {
        given:
        PowerUp powerUp = new InvenciblePowerUp(1,1)
        playerController.getModel().getPlayer().addPowerUp(powerUp)
        Game game = new Game()
        game.backgroundMusic.initializeSounds() >> {}

        when:
        playerController.step(game, GUI.ACTION.NONE, 150)

        then:
        playerController.getModel().getPlayer().getPowerUp() == powerUp
        playerController.getModel().getPlayer().getPowerUpTime() == 4.9
        playerController.getModel().getPlayer().getSpeed() == 1.005

        cleanup:
        streetBuilder = null
        street = null
        playerController = null
        game.backgroundMusic.backgroundMusicMainMenu.close()
        game.backgroundMusic.backgroundMusicGameOverMenu.close()
        game.backgroundMusic.currentBackgroundMusic.close()
        game.gui.close()

    }
}


