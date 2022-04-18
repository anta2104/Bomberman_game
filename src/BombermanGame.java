package bombermanN5.src;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import bombermanN5.src.entities.Entity;
import bombermanN5.src.entities.EntityArr;
import bombermanN5.src.entities.Map;
import bombermanN5.src.entities.blocks.Brick;
import bombermanN5.src.entities.bomb.Bomb;
import bombermanN5.src.entities.bomb.Flame;
import bombermanN5.src.entities.enemy.Enemy;
import bombermanN5.src.graphic.Sprite;
import bombermanN5.src.sound.Sound;

import java.util.Timer;
import java.util.TimerTask;

public class BombermanGame extends Application {

    public static int WIDTH = 20;
    public static int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;

    public static int level = 1;

    public static boolean gameOver = false;

    public static void main(String[] args) {
        Sound.play("bgm");
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        Map.createMapByLevel(1);
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                render();
                update();
            }
        };

        timer.start();
        scene.setOnKeyPressed(event -> {
            String keyPress = event.getCode().toString();
            switch (keyPress) {
                case "SPACE":
                    Sound.play("bomb_set");
                    Bomb bomb = new Bomb(EntityArr.bomberman.getX() / Sprite.SCALED_SIZE
                            , EntityArr.bomberman.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                    boolean duplicate = false;
                    for (Bomb b : EntityArr.bomberman.bombs) {
                        if (b.getX() == bomb.getX() && b.getY() == bomb.getY()) {
                            duplicate = true;
                            break;
                        }
                    }

                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            bomb.setImg(Sprite.bomb_exploded.getFxImage());
                            bomb.setExploded(true);
                            bomb.addFlame();
                        }
                    };
                    TimerTask timerTask1 = new TimerTask() {
                        @Override
                        public void run() {
                            EntityArr.bricks.removeIf(Brick::isBroken);
                            EntityArr.bomberman.removeBomb(bomb);
                            EntityArr.removeEnemy();
                        }
                    };
                    if (!duplicate && EntityArr.bomberman.getAmmo() >= EntityArr.bomberman.bombs.size() + 1) {
                        EntityArr.bomberman.bombs.add(bomb);
                        Timer timerEx = new Timer();
                        timerEx.schedule(timerTask, 2000);
                        Timer timerRev = new Timer();
                        timerRev.schedule(timerTask1, 3000L);
                    }
                    break;
                case "R":
                    Map.createMapByLevel(1);
                    break;
                default:
                    EntityArr.bomberman.setDirection(keyPress);
            }
        });
        
        scene.setOnKeyReleased(event -> {
            String keyRelease = event.getCode().toString();
            if (!keyRelease.equals("SPACE")) {
                EntityArr.bomberman.setDirection("STOP");
            }
        });

    }
    public void update() {
        EntityArr.bomberman.update();
        EntityArr.enemies.forEach(Enemy::update);
        EntityArr.bomberman.bombs.forEach(Bomb::update);
        EntityArr.bricks.forEach(Brick::update);
        // update flame
        EntityArr.bomberman.bombs.forEach(g -> g.getUp().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getDown().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getLeft().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getRight().forEach(Flame::update));
        // Update item
        EntityArr.items.forEach(g -> {
            if (g.isVisible()) g.update();
        });
        EntityArr.portals.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityArr.grasses.forEach(g -> g.render(gc));
        EntityArr.items.forEach(g -> {
            if (g.isVisible()) {
                g.render(gc);
            }
        });
        EntityArr.walls.forEach(g -> g.render(gc));
        EntityArr.portals.forEach(g -> g.render(gc));
        EntityArr.bricks.forEach(g -> g.render(gc));
        EntityArr.enemies.forEach(g -> {
           if (g.isAlive()) g.render(gc);
        });
        EntityArr.bomberman.bombs.forEach(g -> g.render(gc));
        EntityArr.bombers.forEach(g -> g.render(gc));
        EntityArr.bomberman.bombs.forEach(g -> g.flames.forEach(g1 -> g1.render(gc)));
    }

}
