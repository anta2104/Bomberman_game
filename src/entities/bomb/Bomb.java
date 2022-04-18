package bombermanN5.src.entities.bomb;

import javafx.scene.image.Image;
import bombermanN5.src.entities.Entity;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.graphic.Sprite;
import bombermanN5.src.sound.Sound;

import java.util.*;

public class Bomb extends Entity {
    private int flameLength;

    private final List<Flame> fLeft = new ArrayList<>();
    private final List<Flame> fRight = new ArrayList<>();
    private final List<Flame> fUp = new ArrayList<>();
    private final List<Flame> fDown = new ArrayList<>();

    public List<Flame> flames = new ArrayList<>();

    private boolean isExploded = false;

    public int timerEx = 0;

    public boolean allowedToPass = true;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        animate += Sprite.DEFAULT_SIZE / 10;
        flameLength = EntityArr.bomberman.getRange();
        if (this.isExploded()) {
            this.setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2,
                                animate, Sprite.DEFAULT_SIZE).getFxImage());
            if (this.timerEx == 1) {
                this.timerEx++;
                this.addFlame();
                Sound.play("bomb");
            }
        } else {
            if (this.timerEx == 0) {
                this.timerEx++;
                setTimeExploded();
            }
            this.setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1,
                                Sprite.bomb_2, animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
    }

    //bomb no 4 huong
    public void addFlame() {
        Flame flame;
        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameV(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE + 1 + i
                    , Sprite.explosion_vertical.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fDown.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameV(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE - 1 - i
                    , Sprite.explosion_vertical.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fUp.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameH(getX() / Sprite.SCALED_SIZE + i + 1, getY() / Sprite.SCALED_SIZE
                    , Sprite.explosion_horizontal.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fRight.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameH(getX() / Sprite.SCALED_SIZE - i - 1, getY() / Sprite.SCALED_SIZE
                    , Sprite.explosion_horizontal.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fLeft.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public List<Flame> getLeft() {
        return fLeft;
    }

    public List<Flame> getRight() {
        return fRight;
    }

    public List<Flame> getUp() {
        return fUp;
    }

    public List<Flame> getDown() {
        return fDown;
    }

    //dat thoi gian no
    public void setTimeExploded() {
        Bomb bomb = this;
        TimerTask bombEx = new TimerTask() {
            @Override
            public void run() {
                bomb.setExploded(true);
            }
        };
        if (!this.isExploded()) {
            Timer timerEx = new Timer();
            timerEx.schedule(bombEx, 2000);
        }
        TimerTask removeFlame = new TimerTask() {
            @Override
            public void run() {
                EntityArr.removeBrick();
                EntityArr.removeBomb();
                EntityArr.removeEnemy();
            }
        };
        Timer timer = new Timer();
        timer.schedule(removeFlame, 2500L);
    }
}
