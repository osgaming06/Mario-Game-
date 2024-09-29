//Avoids are entities the player needs to avoid colliding with.
//If a player collides with an avoid, it reduces the players Hit Points (HP).
public class Koopa extends Entity implements Collectable, Scrollable {
    
    //Location of image file to be drawn for a Koopa
    public static final String KOOPA_IMAGE_FILE = "assets/koopa.gif";
    //Dimensions of the Koopa
    public static final int KOOPA_WIDTH = 60;
    public static final int KOOPA_HEIGHT = 60;
    //Speed that the avoid moves each time the game scrolls
    public static final int KOOPA_SCROLL_SPEED = 5;
    
    public Koopa(){
        this(0, 0);        
    }
    
    public Koopa(int x, int y){
        super(x, y, KOOPA_WIDTH, KOOPA_HEIGHT, KOOPA_IMAGE_FILE);  
    }
    
    
    public int getScrollSpeed(){
        return KOOPA_SCROLL_SPEED;
    }
    
    //Move the avoid left by the scroll speed
    public void scroll(){
        setX(getX() - KOOPA_SCROLL_SPEED);
    }
    
    //Colliding with an Avoid does not affect the player's score
    public int getPoints(){
        return 0;
       //implement me!
    }
    
    //Colliding with an Avoid Reduces players HP by 2
    public int getDamage(){
        return -2;
    }
    
}
