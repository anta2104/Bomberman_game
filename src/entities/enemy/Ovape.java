package bombermanN5.src.entities.enemy;

import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.graphic.Sprite;
import javafx.scene.image.Image;
public class Ovape extends Enemy {
    public Ovape(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (isAlive()) {
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (checkBoundsWall() || checkBoundsBomb() || checkBoundsEnemy(this) || getY() % Sprite.SCALED_SIZE == 0) {
                    if (getY() % Sprite.SCALED_SIZE != 0) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomDirection();
                }
            } else {
                this.x += this.getSpeedX();
                if (checkBoundsWall() || checkBoundsBomb() || checkBoundsEnemy(this) || getX() % Sprite.SCALED_SIZE == 0) {
                    if (getX() % Sprite.SCALED_SIZE != 0) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomDirection();
                }
            }
        } else {
            this.img = Sprite.ovape_dead.getFxImage();
        }

        if (isAlive()) {
            if (this.getSpeedX() > 0) {
                this.img = Sprite.movingSprite(Sprite.ovape_right1, Sprite.ovape_right2, Sprite.ovape_right3
                        , this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedX() < 0) {
                this.img = Sprite.movingSprite(Sprite.ovape_left1, Sprite.ovape_left2, Sprite.ovape_left3
                        , this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedY() > 0) {
                this.img = Sprite.movingSprite(Sprite.ovape_right1, Sprite.ovape_right2, Sprite.ovape_right3
                        , this.y, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.img = Sprite.movingSprite(Sprite.ovape_left1, Sprite.ovape_left2, Sprite.ovape_left3
                        , this.y, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.img = Sprite.ovape_dead.getFxImage();
        }
    }
}
