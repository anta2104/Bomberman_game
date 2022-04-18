package bombermanN5.src.entities.enemy;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.entities.bomb.Bomb;
import bombermanN5.src.graphic.Sprite;
import bombermanN5.src.entities.Entity;
import javafx.scene.image.Image;

import java.util.Random;

public class Kondoria extends Enemy {

    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
    }

    public boolean checkBomb() {
        for (Bomb b : EntityArr.bomberman.bombs) {
            int disX = Math.abs(getX() - b.getX());
            int disY = Math.abs(getY() - b.getY());
            if (disY <= 4 || disX <= 4) return true;
        }
        return false;
    }

    public void avoidBomb() {
        for (Bomb b : EntityArr.bomberman.bombs) {
            int disX = Math.abs(getX() - b.getX());
            int disY = Math.abs(getY() - b.getY());
            if (b.getX() == 0 && b.getY() == 0) {
                this.setSpeed(1);
            }
            if (disY <= 4 || disX <= 4) {
                this.setSpeed(2);
                if (b.getY() == getY()) {
                    if (getX() > b.getX()) {
                        direction(0);
                    } else  {
                        direction(1);
                    }
                } else if (b.getX() == getX()) {
                    if (getY() > b.getY()) {
                        direction(2);
                    } else if (getY() < b.getY()) {
                        direction(3);
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        if (isAlive()) {
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (checkBoundsWall() || checkBoundsBomb() || checkBoundsEnemy(this)
                        || getY() % Sprite.SCALED_SIZE == 0) {
                    if (getY() % Sprite.SCALED_SIZE != 0) {
                        this.y -= this.getSpeedY();
                    }
                    if (checkBomb()) {
                        avoidBomb();
                    } else {
                        this.setSpeed(1);
                        this.randomDirection();
                    }
                }
            } else {
                this.x += this.getSpeedX();
                if (checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)
                        || getX() % Sprite.SCALED_SIZE == 0) {
                    if (getX() % Sprite.SCALED_SIZE != 0) {
                        this.x -= this.getSpeedX();
                    }
                    if (checkBomb()) {
                        avoidBomb();
                    } else {
                        this.setSpeed(1);
                        this.randomDirection();
                    }
                }
            }
        } else {
            this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3
                    , this.animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
        if (isAlive()) {
            if (this.getSpeedX() > 0) {
                this.img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2
                        , Sprite.kondoria_right3, this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedX() < 0){
                this.img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2
                        , Sprite.kondoria_left3, this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedY() > 0) {
                this.img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2
                        , Sprite.kondoria_right3, this.y, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2
                        , Sprite.kondoria_left3, this.y, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.img = Sprite.kondoria_dead.getFxImage();
        }
    }
}