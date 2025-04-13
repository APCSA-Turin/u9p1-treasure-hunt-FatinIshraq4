package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size=size;
        grid = new Grid(size);
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean continu = true;
        while (continu) {
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop

            if (player.getWin()==true) {grid.win(); grid.display(); System.out.println("YOU WIN"); break;} 
            else if (player.getLives()==0) {grid.gameover(); grid.display(); System.out.println("GAME OVER"); break;}

            grid.display();
            System.out.println("Player: " + player.getCoords());
            System.out.println("Player: " + player.getRowCol(size));
            System.out.println("Treasure collected: " + player.getTreasureCount());
            System.out.println("Lives Remaining: " + player.getLives());
            System.out.print("Enter a direction (w,a,s,d) or 'q' to exit: ");
            input=scanner.nextLine();
            if (input.equals("q")) {continu=false;}
            else if (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d")) {

                // Calculate where the player is trying to move
                int newX = player.getX();
                int newY = player.getY();
    
                switch (input) {
                    case "w": newY += 1; break;
                    case "s": newY -= 1; break;
                    case "a": newX -= 1; break;
                    case "d": newX += 1; break;
                }
    
                // Check boundaries (optional)
                if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
                    Sprite target = grid.getSprite(newX, newY);
                    player.interact(size, input, treasures.length, target); // <-- this updates lives & treasure
                    player.move(input);
                    grid.placeSprite(player, input);
                }
            }
        }      
    }

    public void initialize(){

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        Player player = new Player(0, 0);
this.player = player;
Trophy trophy = new Trophy(0, 9);
this.trophy = trophy;
Treasure treasure1 = new Treasure(2, 2);
Treasure treasure2 = new Treasure(9, 9);
treasures = new Treasure[2];
treasures[0] = treasure1;
treasures[1] = treasure2;
Enemy enemy1 = new Enemy(2, 8);
Enemy enemy2 = new Enemy(3, 2);
Enemy enemy3 = new Enemy(5, 7);
enemies = new Enemy[3];
enemies[0] = enemy1;
enemies[1] = enemy2;
enemies[2] = enemy3;
grid.placeSprite(player);
grid.placeSprite(trophy);
grid.placeSprite(treasure1);
grid.placeSprite(treasure2);
grid.placeSprite(enemy1);
grid.placeSprite(enemy2);
grid.placeSprite(enemy3);
   
    }

    public static void main(String[] args) {
        Game g = new Game(10);

    }
}