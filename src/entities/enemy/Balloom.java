package bombermanN5.src.entities.enemy;

import bombermanN5.src.graphic.Sprite;
import javafx.scene.image.Image;

public class Balloom extends Enemy {
    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (isAlive()) {
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (checkBoundsWall() || checkBoundsBomb() || checkBoundsBrick() || checkBoundsEnemy(this)
                        || getY() % Sprite.SCALED_SIZE == 0) {
                    if (getY() % Sprite.SCALED_SIZE != 0) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomDirection();
                }
            } else {
                this.x += this.getSpeedX();
                if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)
                        || getX() % Sprite.SCALED_SIZE == 0) {
                    if (getX() % Sprite.SCALED_SIZE != 0) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomDirection();
                }
            }
        }

        if (isAlive()) {
            if (this.getSpeedX() > 0) {
                this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3
                        , this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedX() < 0) {
                this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3
                        , this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedY() > 0) {
                this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3
                        , this.y, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3
                        , this.y, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3
                    , this.animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
    }
}
