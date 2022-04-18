package bombermanN5.src.entities;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

import bombermanN5.src.BombermanGame;
import bombermanN5.src.entities.bomb.Bomb;
import bombermanN5.src.graphic.Sprite;
import bombermanN5.src.sound.Sound;


public class Bomber extends Entity {
    private int ammo = 1;
    private int range = 1;
    private int speed = Sprite.SCALED_SIZE / 16;
    private int speedX = 0;
    private int speedY = 0;
    private boolean isAlive = true;
    public String direction = "STOP";

    public List<Bomb> bombs = new ArrayList<>();

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (isAlive()) {
            if (statusDie()) {
                killBomber();
            }
            if (statusPortal()) {
                BombermanGame.level++;
                Map.createMapByLevel(BombermanGame.level);
            }
            moveBomber(this.direction);
        }
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    public boolean isAlive() {
        return this.isAlive;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmo() {
        return this.ammo;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getRange() {
        return this.range;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    //di chuyen bomber muot ma
    public void moveBomber(String direction) {
        switch (direction) {
            case "UP":
                setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, this.getY(), 80).getFxImage());
                this.speedY = -this.speed;
                this.speedX = 0;
                break;
            case "DOWN":
                setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, this.getY(), 80).getFxImage());
                this.speedY = this.speed;
                this.speedX = 0;
                break;
            case "LEFT":
                setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, this.getX(), 80).getFxImage());
                this.speedX = -this.speed;
                this.speedY = 0;
                break;
            case "RIGHT":
                setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, this.getX(), 80).getFxImage());
                this.speedX = this.speed;
                this.speedY = 0;
                break;
            case "STOP":
                this.speedX = 0;
                this.speedY = 0;
                break;
            default:
                break;
        }
        this.x += this.speedX;
        this.y += this.speedY;
        if (checkBoundsBrick() || checkBoundsBomb() || checkBoundsWall() ) {
            if (this.x % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
            } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
            }
            if (this.y % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
            } else if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
            } 
        }
    }

    public void removeBomb(Bomb b) {
        this.bombs.removeIf(bomb -> bomb.getX() == b.getX() && bomb.getY() == b.getY());
    }

    //cham vao enemies
    public boolean statusDie() {
        for (Entity e : EntityArr.enemies) {
            if (this.intersects(e)) {
                return true;
            }
        }
        return false;
    }

    //cham vao portal
    public boolean statusPortal() {
        for (Entity e : EntityArr.portals) {
            if (EntityArr.enemies.size() != 0) {
                break;
            }
            if (this.intersects(e)) {
                return true;
            }
        }
        return false;
    }

    //end screen
    public void killBomber() {
        Sound.play("die");
        Map.createMapByLevel(999);
    }

    //cho di qua bomb lan dau
    public boolean checkBoundsBomb() {
       for (Bomb e : EntityArr.bomberman.bombs) {
            double diffX = this.getX() - e.getX();
            double diffY = this.getY() - e.getY();
            if (!(diffX > -32 && diffX < 32 && diffY > -32 && diffY < 32)) {
                e.allowedToPass = false;
            }
            if (e.allowedToPass) return false;
            if (this.intersects(e)) return true;
        }
        return false; 
    }
}
