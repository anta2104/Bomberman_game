package bombermanN5.src.entities.bomb;

import javafx.scene.image.Image;
import bombermanN5.src.BombermanGame;
import bombermanN5.src.graphic.Sprite;
import bombermanN5.src.sound.Sound;
import bombermanN5.src.entities.Bomber;
import bombermanN5.src.entities.Entity;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.entities.Map;
import bombermanN5.src.entities.blocks.Brick;
import bombermanN5.src.entities.enemy.Enemy;


import java.util.concurrent.TimeUnit;


public abstract class Flame extends Entity {
    private boolean isVisible = true;

    public Flame(int x, int y, Image img) {
       super(x, y, img);
    }

    @Override
    public void update() {
        checkEnemy();
        checkBomber();
        checkBomb();
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    protected boolean checkWall() {
        for (Entity w : EntityArr.walls) {
            if (this.getX() == w.getX() && this.getY() == w.getY()) {
                this.setVisible(false);
                return true;
            }
        }
        return false;
    }

    protected boolean checkBrick() {
        for (Brick b : EntityArr.bricks) {
            if (this.getX() == b.getX() && this.getY() == b.getY()) {
                this.setVisible(false);
                b.setBroken(true);
                return true;
            }
        }
        return false;
    }

    protected void checkEnemy() {
        for (Enemy e : EntityArr.enemies) {
            if (this.intersects(e)) {
                e.setAlive(false);
            }
        }
    }

    protected void checkBomber() {
        for (Bomber b : EntityArr.bombers) {
            if (this.intersects(b)) {
                b.killBomber();
            }
        }
    }

    protected void checkBomb() {
        for (Bomb v : EntityArr.bomberman.bombs) {
            if (this.intersects(v)) {
                v.setExploded(true);
            }
        }
    }
}
