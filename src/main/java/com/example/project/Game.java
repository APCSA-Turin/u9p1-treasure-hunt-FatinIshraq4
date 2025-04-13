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
        while (continu) { //The game continues while the variable continu is true 
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop

            //Check if the win variable is true or if the player is out of lives and then give the end screen 
            if (player.getWin()==true) {grid.win(); grid.display(); System.out.println("YOU WIN"); break;} 
            else if (player.getLives()==0) {grid.gameover(); grid.display(); System.out.println("GAME OVER"); break;}

            grid.display();
            //Show the player info
            System.out.println("Player: " + player.getCoords());
            System.out.println("Player: " + player.getRowCol(size));
            System.out.println("Treasure collected: " + player.getTreasureCount());
            System.out.println("Lives Remaining: " + player.getLives());
            System.out.print("Enter a direction (w,a,s,d) or 'q' to exit: ");
            input=scanner.nextLine();
            if (input.equals("q")) {continu=false;} //End the loop if q is pressed
            //If any of the directional keys wasd is pressed then move the sprite
            else if (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d")) {
                // Check boundaries
                if (player.isValid(size, input)) {
                    // Get the target coordinates based on input
                    int targetX = player.getX();
                    int targetY = player.getY();
                    if (input.equals("a")) {
                        targetX--;
                    } else if (input.equals("d")) {
                        targetX++;
                    } else if (input.equals("w")) {
                        targetY++;
                    } else if (input.equals("s")) {
                        targetY--;
                    }
                
                    Sprite target = grid.getSprite(targetX, targetY);
                
                    // Only allow moving onto Trophy square if all treasures are collected                
                    if (!(target instanceof Trophy && player.getTreasureCount() < treasures.length)) {
                        player.interact(size, input, treasures.length, target);
                        player.move(input);
                        grid.placeSprite(player, input);
                    }
                }
                
            }
        }      
    }

    public void initialize(){
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid

        //Make the Player
        Player player = new Player(0, 0);
        this.player = player;

        //Make the Trophy
        Trophy trophy = new Trophy(9, 0);
        this.trophy = trophy;

        //Make the Treasures
        Treasure treasure1 = new Treasure(1, 7);
        Treasure treasure2 = new Treasure(7, 3);
        treasures = new Treasure[2];
        treasures[0] = treasure1;
        treasures[1] = treasure2;

        //Make the Enemies
        Enemy enemy1 = new Enemy(4, 4);
        Enemy enemy2 = new Enemy(6, 8);
        Enemy enemy3 = new Enemy(8, 1);
        enemies = new Enemy[3];
        enemies[0] = enemy1;
        enemies[1] = enemy2;
        enemies[2] = enemy3;

        //Place the sprites
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        grid.placeSprite(treasure1);
        grid.placeSprite(treasure2);
        grid.placeSprite(enemy1);
        grid.placeSprite(enemy2);
        grid.placeSprite(enemy3);
   
    }


    //Test
    public static void main(String[] args) {
        Game g = new Game(10);
    }
}