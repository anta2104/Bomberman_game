package bombermanN5.src.entities.item;

import javafx.scene.image.Image;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.sound.Sound;

public class Ammo extends Item {
    public Ammo(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (checkBoundsBomber()) {
            Sound.play("item");
            this.isVisible = false;
            EntityArr.bombers.forEach(b -> b.setAmmo(b.getAmmo() + 1));
        }
    }
}
