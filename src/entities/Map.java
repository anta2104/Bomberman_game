package bombermanN5.src.entities;

import bombermanN5.src.BombermanGame;
import bombermanN5.src.entities.blocks.Brick;
import bombermanN5.src.entities.blocks.Grass;
import bombermanN5.src.entities.blocks.Portal;
import bombermanN5.src.entities.blocks.Wall;
import bombermanN5.src.entities.enemy.*;
import bombermanN5.src.graphic.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Map {
    public static void createMapByLevel(int level) {
        EntityArr.clearArr();
        try {
            String path = "resources/levels/Level" + level + ".txt";
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line = buffReader.readLine().trim();
            String[] str = line.split(" ");
            BombermanGame.HEIGHT = Integer.parseInt(str[1]);
            BombermanGame.WIDTH = Integer.parseInt(str[2]);
            char [][] maps = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];

            for (int i = 0; i < BombermanGame.HEIGHT; ++i) {
                line = buffReader.readLine();
                for (int j = 0; j < BombermanGame.WIDTH; ++j) {
                    maps[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < BombermanGame.WIDTH; ++i) {
                for (int j = 0 ; j < BombermanGame.HEIGHT; ++j) {
                    Entity object1 = new Grass(i, j, Sprite.grass.getFxImage());
                    EntityArr.grasses.add(object1);
                    switch (maps[j][i]) {
                        case 'p':
                            EntityArr.bomberman = new Bomber(i, j, Sprite.player_right.getFxImage());
                            EntityArr.bombers.add(EntityArr.bomberman);
                            break;
                        case '#':
                            Entity object = new Wall(i, j, Sprite.wall.getFxImage());
                            EntityArr.walls.add(object);
                            break;
                        case '*':
                            Brick brick = new Brick(i, j, Sprite.brick.getFxImage());
                            EntityArr.bricks.add(brick);
                            break;
                        case 'x':
                            Portal portal = new Portal(i, j, Sprite.portal.getFxImage());
                            EntityArr.portals.add(portal);

                            Brick brick1 = new Brick(i, j, Sprite.brick.getFxImage());
                            EntityArr.bricks.add(brick1);
                            break;
                        case '1':
                            Balloom balloom = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                            EntityArr.enemies.add(balloom);
                            break;
                        case '2':
                            Oneal oneal = new Oneal(i, j, Sprite.oneal_left1.getFxImage());
                            EntityArr.enemies.add(oneal);
                            break;
                        case '3':
                            Ovape ovape = new Ovape(i, j, Sprite.ovape_left1.getFxImage());
                            EntityArr.enemies.add(ovape);
                            break;
                        case '4':
                            Doll doll = new Doll(i, j, Sprite.ovape_left1.getFxImage());
                            EntityArr.enemies.add(doll);
                            break;
                        case '5':
                            Minvo minvo = new Minvo(i, j, Sprite.minvo_left1.getFxImage());
                            EntityArr.enemies.add(minvo);
                            break;
                        case '6':
                            Kondoria kondoria = new Kondoria(i, j, Sprite.kondoria_left1.getFxImage());
                            EntityArr.enemies.add(kondoria);
                            break;
                        default:
                    }
                }
            }
            fileReader.close();
            buffReader.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }
}
