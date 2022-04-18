package bombermanN5.src.entities.enemy;


import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.graphic.Sprite;
import javafx.scene.image.Image;

public class Minvo extends Enemy {
    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (isAlive()) {
            int distanceX = Math.abs(getX() - EntityArr.bomberman.getX());
            int distanceY = Math.abs(getY() - EntityArr.bomberman.getY());
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if ((distanceX <= Sprite.SCALED_SIZE * 5 && distanceY <= Sprite.SCALED_SIZE * 5) &&
                        getX() % Sprite.SCALED_SIZE == 0) {
                    this.setSpeed(2);
                    this.chaseBomber();
                    if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)) {
                        this.y -= this.getSpeedY();
                        this.findWay();
                    }
                } else if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)
                        || getY() % Sprite.SCALED_SIZE == 0) {
                    this.setSpeed(1);
                    if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomDirection();
                }
            } else {
                this.x += this.getSpeedX();
                if ((distanceX <= Sprite.SCALED_SIZE * 5 && distanceY <= Sprite.SCALED_SIZE * 5) &&
                        getX() % Sprite.SCALED_SIZE == 0) {
                    this.setSpeed(2);
                    this.chaseBomber();
                    if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)) {
                        this.x -= this.getSpeedX();
                        this.findWay();
                    }
                } else if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)
                        || getX() % Sprite.SCALED_SIZE == 0) {
                     this.setSpeed(1);
                    if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() || checkBoundsEnemy(this)) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomDirection();
                }
            }
        } else {
            this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3
                    , this.animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
        if (isAlive()) {
            if (this.getSpeedX() > 0) {
                this.img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2
                        , Sprite.minvo_right3, this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedX() < 0){
                this.img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2
                        , Sprite.minvo_left3, this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else if (this.getSpeedY() > 0) {
                this.img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2
                        , Sprite.minvo_right3, this.y, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.img = this.img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2
                        , Sprite.minvo_left3, this.y, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.img = Sprite.minvo_dead.getFxImage();
        }
    }
}
