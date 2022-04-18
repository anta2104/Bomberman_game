package bombermanN5.src.entities.bomb;

import javafx.scene.image.Image;
import bombermanN5.src.graphic.Sprite;

public class FlameH extends Flame {
    public FlameH(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        super.update();
        this.animate += Sprite.DEFAULT_SIZE / 10;
        this.setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                , Sprite.explosion_horizontal2, animate, Sprite.DEFAULT_SIZE).getFxImage());
    }
}
