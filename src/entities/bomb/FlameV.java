package bombermanN5.src.entities.bomb;

import javafx.scene.image.Image;
import bombermanN5.src.graphic.Sprite;

public class FlameV extends Flame {
    public FlameV(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        super.update();
        this.animate += Sprite.DEFAULT_SIZE / 10;
        this.setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                , Sprite.explosion_vertical2, animate, Sprite.DEFAULT_SIZE).getFxImage());
    }
}
