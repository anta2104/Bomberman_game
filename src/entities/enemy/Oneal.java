package bombermanN5.src.entities.enemy;

import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.graphic.Sprite;
import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (isAlive()) {
            int distanceX = Math.abs(getX() - EntityArr.bomberman.getX());
            int distanceY = Math.abs(getY() - EntityArr.bomberman.getY());
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (distanceX <= Sprite.SCALED_SIZE * 4 && distanceY <= Sprite.SCALED_SIZE * 4 &&
                        getX() % Sprite.SCALED_SIZE == 0) {
                    this.setSpeed(1);
                    this.chaseBomber();
                    if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)) {
                        this.y -= this.getSpeedY();
                        this.findWay();
                    }
                    this.randomSpeed();
                } else if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)
                        || getY() % Sprite.SCALED_SIZE == 0) {
                    if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomDirection();
                    this.randomSpeed();
                }
            } else {
                this.x += this.getSpeedX();
                if (distanceX <= Sprite.SCALED_SIZE * 4 && distanceY <= Sprite.SCALED_SIZE * 4 &&
                        getX() % Sprite.SCALED_SIZE == 0) {
                    this.setSpeed(1);
                    this.chaseBomber();
                    if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)) {
                        this.x -= this.getSpeedX();
                        this.findWay();
                    }
                    this.randomSpeed();
                } else if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)
                        || getX() % Sprite.SCALED_SIZE == 0) {
                    if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomDirection();
                    this.randomSpeed();
                }
            }
        } else {
            this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3
                    , this.animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
        if (isAlive()) {
            if (this.getSpeedX() > 0) {
                this.img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3
                        , this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedX() < 0){
                this.img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3
                        , this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedY() > 0) {
                this.img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3
                        , this.y, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3
                        , this.y, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.img = Sprite.oneal_dead.getFxImage();
        }
    }
}