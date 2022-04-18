package bombermanN5.src.entities;

import bombermanN5.src.graphic.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int animate;
    protected Image img;

    public Entity(int x, int y, Image img) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
        this.animate = Sprite.DEFAULT_SIZE;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img ,x , y);
    }

    public abstract void update();

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean intersects(Entity e) {
        //this is not recursion
        return e.getBoundary().intersects(this.getBoundary());
    } 

    public boolean checkBoundsBrick() {
        for (Entity e : EntityArr.bricks) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkBoundsBomb() {
        for (Entity e : EntityArr.bomberman.bombs) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkBoundsWall() {
        for (Entity e : EntityArr.walls) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkbound(Entity e) {
        return !e.checkBoundsBomb() && !e.checkBoundsBrick() && !e.checkBoundsWall();
    }
}
