package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite  {
    private int treasureCount;
    private int numLives;
    private boolean win = false; //Start with win set to false

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount=0; //initialize variables
        numLives=2;
        
    }


    //Getter methdods
    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

  
    @Override
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size); //Display row col with Player: in front
    }

    @Override
    public String getCoords() {
        return "Player:" + super.getCoords(); //Display coords with Player: in front
    }

    //move method should override parent class, sprite
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
        if (direction.equals("w")) { //check the direction based on wasd
            setY(getY()+1); 
        } //change y value if w or s is pressed
        else if (direction.equals("s")) {
            setY(getY()-1);
        }
        else if (direction.equals("a")) {
            setX(getX()-1);
        } //change x value if a or d is pressed
        else if (direction.equals("d")) {
            setX(getX()+1);
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
    if (obj instanceof Treasure && !(obj instanceof Trophy)) {treasureCount++;} //Increase number of treasures as long as its not a trophy
    else if (obj instanceof Enemy) {numLives--;} //Take a life if touching enemy
    else if (obj instanceof Trophy && numTreasures == treasureCount) {win=true;} //Win condition is only met after all treasures are collected
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w")) { //Check direction then check if its within the grid
                if (super.getY() + 1 > size - 1) {
                    return false;
                }
            } else if (direction.equals("a")) {
                if (super.getX() - 1 < 0) {
                    return false;
                }
            } else if (direction.equals("s")) {
                if (super.getY() - 1 < 0) {
                    return false;
                }
            } else if (direction.equals("d")) {
                if (super.getX() + 1 > size - 1) {
                    return false;
                }
            }
            return true; //return true if none of the conditions are met
    }


   
}



