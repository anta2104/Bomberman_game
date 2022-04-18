package bombermanN5.src.entities.item;

import javafx.scene.image.Image;
import bombermanN5.src.entities.Entity;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.entities.Bomber;

public abstract class Item extends Entity {
    protected boolean isVisible = false;

    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public abstract void update();

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible() {
        this.isVisible = true;
    }

    public boolean checkBoundsBomber() {
        for (Bomber bomber : EntityArr.bombers) {
            if (this.intersects(bomber)) {
                return true;
            }
        }
        return false;
    }
}
