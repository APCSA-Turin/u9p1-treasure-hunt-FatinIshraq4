package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size=size; //Sets size
        grid = new Sprite[size][size]; //Sets the rows and columns for grid
        for (int i=0; i<grid.length; i++) { //Uses a for loop to iterate through grid and make every item an empty Dot object
            for (int x=0; x<grid[0].length; x++) {
                grid[i][x] = new Dot(Math.abs(i - size + 1), x);
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}

    public Sprite getSprite(int x, int y){
        return grid[size - y - 1][x];
    }

    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[Math.abs(size - s.getY() - 1)][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        int prevX = s.getX();
        int prevY = s.getY();
        if (direction.equals("s")){
            prevY++;
        }else if (direction.equals("a")){
            prevX++;
        }else if (direction.equals("d")){
            prevX--;
        }else if (direction.equals("w")){
            prevY--;
        }
        placeSprite(new Dot(prevX, prevY)); 
        placeSprite(s);
    }


    public void display() { //print out the current grid to the screen 
        for (Sprite[] row : grid){
            for (Sprite sprite : row) {
                if (sprite instanceof Dot) {
                    System.out.print("⬜");
                } else if (sprite instanceof Player) {
                    System.out.print("🦄");
                } else if (sprite instanceof Trophy) {
                    System.out.print("🏆");
                } else if (sprite instanceof Treasure) {
                    System.out.print("🌈");
                } else if (sprite instanceof Enemy) {
                    System.out.print("🦂");
                } else if (sprite instanceof Lose) {
                    System.out.print("💀");
                } else if (sprite instanceof Win) {
                    System.out.print("🎉");
                }
    }
    System.out.println();
}
}
    
    public void gameover(){ //use this method to display a loss
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) { 
                if (!(grid[i][j] instanceof Player)) {
                    grid[i][j] = new Lose(i, j);
                }
            }
        }
    }

    public void win(){ //use this method to display a win 
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) { 
                if (!(grid[i][j] instanceof Player)) {
                    grid[i][j] = new Win(i, j);
                }
            }
        }
        
    }


}