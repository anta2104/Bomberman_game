package bombermanN5.src.entities.blocks;

import java.util.Random;
import javafx.scene.image.Image;
import bombermanN5.src.entities.Entity;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.entities.item.Ammo;
import bombermanN5.src.entities.item.Range;
import bombermanN5.src.entities.item.Item;
import bombermanN5.src.entities.item.Speed;
import bombermanN5.src.graphic.Sprite;


public class Brick extends Entity {

    private boolean isBroken = false;
    private final Item item;

    public Brick (int x, int y, Image img) {
        super(x, y, img);
        item = randomItem();
        if (item != null) {
            EntityArr.items.add(item);
        }
    }

    @Override
    public void update() {
        if (isBroken) {
            if (item != null) {
                item.setVisible();
            }
            this.animate += Sprite.DEFAULT_SIZE / 10;
            this.setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                    , Sprite.brick_exploded2, animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
    }

    //tao ra item nang cap
    private Item randomItem() {
        Random random = new Random();
        int rng = random.nextInt(10);
        switch (rng) {
            case 1:
                    return new Ammo(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                        , Sprite.powerup_bombs.getFxImage());
            case 2:
                    return new Range(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                        , Sprite.powerup_flames.getFxImage());
            case 3:
                    return new Speed(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                        , Sprite.powerup_speed.getFxImage());
            default:
                    return null;
        }
    }

    public void setBroken(boolean broken) {
        this.isBroken = broken;
    }

    public boolean isBroken() {
        return this.isBroken;
    }
} 

