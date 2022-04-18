package bombermanN5.src.entities;

import bombermanN5.src.entities.blocks.Brick;
import bombermanN5.src.entities.blocks.Portal;
import bombermanN5.src.entities.bomb.Bomb;
import bombermanN5.src.entities.enemy.Enemy;
import bombermanN5.src.entities.item.Item;
import bombermanN5.src.graphic.Sprite;
import bombermanN5.src.sound.Sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityArr {
    public static List<Bomber> bombers = new ArrayList<>();
    public static List<Entity> grasses = new ArrayList<>();
    public static List<Entity> walls = new ArrayList<>();
    public static List<Brick> bricks = new ArrayList<>();
    public static List<Portal> portals = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    public static void removeEnemy() {
        enemies.removeIf(enemy -> !enemy.isAlive());
    }

    public static void removeBrick() {
        bricks.removeIf(Brick::isBroken);
    }

    public static void removeBomb() {
        bomberman.bombs.removeIf(Bomb::isExploded);
    }

    public static void clearArr() {
        bombers.clear();
        grasses.clear();
        bricks.clear();
        walls.clear();
        items.clear();
        portals.clear();
        enemies.clear();
    }
}
