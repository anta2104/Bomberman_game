package bombermanN5.src.entities.enemy;

import bombermanN5.src.entities.Entity;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.graphic.Sprite;
import javafx.scene.image.Image;

import java.util.Random;

public class Enemy extends Entity{
    private int speed = 1;
    private int speedX = this.speed;
    private int speedY = 0;
    private boolean isAlive = true;
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int UP = 3;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.animate += Sprite.DEFAULT_SIZE / 10;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    protected void direction(int num) {
        switch (num) {
            case RIGHT:
                this.speedX = this.getSpeed();
                this.speedY = 0;
                break;
            case LEFT:
                this.speedX = this.getSpeed() * -1;
                this.speedY = 0;
                break;
            case DOWN:
                this.speedY = this.getSpeed();
                this.speedX = 0;
                break;
            case UP:
                this.speedY = this.getSpeed() * -1;
                this.speedX = 0;
                break;
        }
    }

    public void randomDirection() {
        Random rd = new Random();
        int n = rd.nextInt(4);
        direction(n);
    }

    public int sameRow() {
        if (getX() > EntityArr.bomberman.getX()) {
            return LEFT;
        }
        else if (getX() < EntityArr.bomberman.getX()) {
            return RIGHT;
        }
        return -1;
    }

    public int sameColumn() {
        if (getY() > EntityArr.bomberman.getY()) {
            return UP;
        }
        else if (getY() < EntityArr.bomberman.getY()) {
            return DOWN;
        }
        return -1;
    }

    public void randomSpeed() {
        Random random = new Random();
        int num = random.nextInt(10);
        if (num <= 5) {
            this.setSpeed(1);
        } else {
            this.setSpeed(2);
        }
    }

    public void chaseBomber() {
        if (getY() == EntityArr.bomberman.getY()) {
            direction(sameRow());
        } else if (getX() == EntityArr.bomberman.getX()) {
            direction(sameColumn());
        }
    }

    public void findWay() {
        Enemy fake = new Enemy(this.getX() , this.getY(), img);
        fake.x = fake.x + 5;
        if (checkbound(fake)) {
            direction(0);
        } else {
            fake.x = fake.x - 10;
            if (checkbound(fake)) {
                direction(1);
            } else {
                fake.y = fake.y - 5;
                if (checkbound(fake)) {
                    direction(2);
                } else direction(3);
            }
        }
    }

    public boolean checkBoundsEnemy(Enemy x) {
        for(Entity e : EntityArr.enemies) {
            if (e != x) {
                if (this.intersects(e)) return true;
            }
        }
        return false;

    }
}
