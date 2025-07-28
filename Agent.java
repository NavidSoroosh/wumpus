package wumpus;
import java.util.* ;

public class Agent{


public class Pos{
	int X;
	int Y;
	
	Pos(int newX, int newY){
		X = newX;
		Y = newY;
	}
}

    public enum Action{
	FOREWARD,TURN_L,TURN_R,SHOOT
    };
    
    public enum Orientation{
	N,E,S,W
    };
    
    public int [][] percepts;
    public boolean [][] known;
    public double [][]probaPit;
    int posX,posY;
    Orientation orientation;

    
    public Agent(){
	percepts=new int [World.SIZE][World.SIZE];
	probaPit=new double[World.SIZE][World.SIZE];
	known=new boolean[World.SIZE][World.SIZE];
	resetPercepts();
	resetProba();
	computeProba();
    }

    public void resetPercepts(){
	for(int i=0;i<World.SIZE;i++){
	    for(int j=0;j<World.SIZE;j++){
		percepts[i][j]=0;
		known[i][j]=false;
	    }
	}
    }

    public void resetProba(){
	for(int i=0;i<World.SIZE;i++){
	    for(int j=0;j<World.SIZE;j++){
		probaPit[i][j]=World.PROBA_PIT;
	    }
	}
    }
    public void setPosition(int x, int y,Orientation o){
	posX=0;posY=0;orientation=o;
    }

    public Action act(){
	return Action.FOREWARD;
    }

    public void computeProba(){
    	int posXToTest = posX - 1;
    	int posYToTest = posY;

		probaPit[posX][posY] = 0;
		int cellValue = percepts[posX][posY];
		System.out.println("cellValue" + cellValue);
		if (
				cellValue == World.CELL_EMPTY
				|| cellValue == World.CELL_SMELL
				|| cellValue == World.CELL_EMPTY + World.CELL_SMELL
				|| cellValue == World.CELL_WUMPUS
				|| cellValue == World.CELL_GOLD
				|| cellValue == World.CELL_SMELL + World.CELL_GOLD
		) {
        	for (int i=0;i<4;i++) {

    			//System.out.println(posXToTest + "," + posYToTest);
        		if (existCell(posXToTest, posYToTest)) {
        			//System.out.println(posXToTest + "," + posYToTest + "Y");
            		probaPit[posXToTest][posYToTest] = 0;
        		}

            	switch (i) {
            	case 0:
            		//System.out.println(percepts[posX][posY]);
            		posXToTest = posX + 1;
                	break;
            	case 1:
            		posXToTest = posX;
            		posYToTest = posY - 1;
                	break;
            	case 2:
            		posYToTest = posY + 1;
                	break;
                default:
                	break;
            	}
        	}
		}
    	else {
    		List<Pos> bordure;
        	for (int i=0;i<4;i++) {

    			//System.out.println(posXToTest + "," + posYToTest);
        		if (existCell(posXToTest, posYToTest) && bordure.contains(new Pos(posXToTest, posYToTest))) {
        			bordure.add(arg0)
        		}

            	switch (i) {
            	case 0:
            		//System.out.println(percepts[posX][posY]);
            		posXToTest = posX + 1;
                	break;
            	case 1:
            		posXToTest = posX;
            		posYToTest = posY - 1;
                	break;
            	case 2:
            		posYToTest = posY + 1;
                	break;
                default:
                	break;
            	}
        	}
    	}
    }
/*
 * 1) faire une liste de toutes les instances
 * 2) calculer la probabilité d'une instance
 * 3) faire la somme des probabilités de toutes les instances
 */
    private void updateCellProba() {
    }
    
    private double arrondir(double value, int n) {
	double r = (Math.round(value * Math.pow(10, n))) / (Math.pow(10, n));
	return r;
    }

    public boolean existCell(int i, int j){
	return (i>=0 && j>=0 && i<World.SIZE && j<World.SIZE);
    }
}
