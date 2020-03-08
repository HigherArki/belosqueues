package belosqueues.game;

import belosqueues.game.equipments.Equipment;
import belosqueues.game.equipments.helm.Helm;
import belosqueues.game.player.Player;
import belosqueues.game.player.PlayerFactory;
import belosqueues.game.enemies.Enemy;
import belosqueues.game.enemies.EnemyFactory;
import belosqueues.game.stages.Arena;
import belosqueues.game.stages.Stage;
import belosqueues.game.stages.Tutorial;

public class GameEngine {

    public static final int WIDTH = 12;
    public static final int HEIGHT = 16;

    private Stage tutorial;
    private Player player;
    private Enemy enemy;
    private Arena arena;


    public GameEngine() {
    }

    public void gameFlow(){

        while(!player.getPos().equals(enemy.getPos())) {
            moveEnemy();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(player.getPos().getCol());
        System.out.println(enemy.getPos().getCol());
        System.out.println(player.getPos().getRow());
        System.out.println(enemy.getPos().getRow());

        if (player.getPos().equals(enemy.getPos())){
            System.out.println("Battle");
            arena.startBattle();
        }
    }


    public void moveEnemy(){

        int[] distance = new int[4];

        int difCol = player.getPos().getCol() - enemy.getPos().getCol();
        int difRow = player.getPos().getRow() - enemy.getPos().getRow();

        //move up
        distance[0] = (int) Math.hypot(difCol, difRow - 1);

        //move down
        distance[1] = (int) Math.hypot(difCol, difRow + 1);

        //move left
        distance[2] = (int) Math.hypot(difCol - 1, difRow);

        //move right
        distance[3] = (int) Math.hypot(difCol + 1, difRow);;

        int min = (int) Math.hypot(difCol, difRow);

        for (int i = 1; i <distance.length ; i++) {
            if (distance[i] > min){
                min = distance[i];
            }
        }
        if(min == distance[0]) {
            enemy.moveUp();
            return;
        }
        if(min == distance[1]) {
            enemy.moveDown();
            return;
        }
        if(min == distance[2]) {
            enemy.moveLeft();
            return;
        }
        if(min == distance[3]) {
            enemy.moveRight();
        }
    }

    public void tutorialInit(){
        tutorial = new Tutorial();
    }

}