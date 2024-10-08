package com.ldts.asphaltrush.model.game.elements;

import com.ldts.asphaltrush.model.game.elements.powerup.PowerUp;

public class Player extends Element {

    private final int PowerUpDuration = 5;
    private int type;
    private double speed = 1;
    private double minSpeed = 1;
    private double maxSpeed = 10;
    private boolean crashed = false;
    private PowerUp powerUp;
    private double powerUpTime;


    public int getType(){
        return type;
    }
    public int getPowerUpDuration(){
        return PowerUpDuration;
    }
    public Player(int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        this.type = type;
    }

    public double getSpeed() {
        return speed;
    }

    public void increaseSpeed() {
        if(speed < maxSpeed) this.speed+=0.2;
    }

    public void decreaseSpeed() {
        speed = Math.max(minSpeed, speed-0.4);
    }

    public void increaseMinSpeed() {
        minSpeed += 0.005;
        speed = Math.max(speed, minSpeed);
    }

    public boolean getCrashed() {
        return this.crashed;
    }

    public void setCrashed() {
        this.crashed = true;
    }

    public PowerUp getPowerUp() {
        return this.powerUp;
    }

    public void addPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
        this.powerUpTime = PowerUpDuration;
    }

    public void removePowerUp() {
        this.powerUp = null;
        powerUpTime = 0;
    }

    public double getPowerUpTime() {
        return this.powerUpTime;
    }

    public void decreasePowerUpTime() {
        powerUpTime-=0.1;
    }
}
