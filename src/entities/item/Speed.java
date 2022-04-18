package bombermanN5.src.entities.item;

import javafx.scene.image.Image;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.graphic.Sprite;
import bombermanN5.src.sound.Sound;

public class Speed extends Item {
    public Speed(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (checkBoundsBomber()) {
            Sound.play("item");
            this.isVisible = false;
            EntityArr.bomberman.setSpeed(EntityArr.bomberman.getSpeed() + Sprite.SCALED_SIZE / 32);
        }
    }
}
