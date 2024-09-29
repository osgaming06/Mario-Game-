public class Goomba extends Entity implements Collectable, Scrollable {
    //Location of image file to be drawn for an Goomba
    public static final String AVOID_IMAGE_FILE = "assets/goomba.gif";
    //Dimensions of the Goomba    
    public static final int GOOMBA_WIDTH = 60;
    public static final int GOOMBA_HEIGHT = 60;
    //Speed that the avoid moves each time the game scrolls
    public static final int GOOMBA_SCROLL_SPEED = 5;
    
    public Goomba(){
        this(0, 0);        
    }
    
    public Goomba(int x, int y){
        super(x, y, GOOMBA_WIDTH, GOOMBA_HEIGHT, AVOID_IMAGE_FILE);  
    }
    
    
    public int getScrollSpeed(){
        return GOOMBA_SCROLL_SPEED;
    }
    
    //Move the Goomba left by the scroll speed
    public void scroll(){
        setX(getX() - GOOMBA_SCROLL_SPEED);
    }
    
    //Colliding with Goomba does not affect the player's score
    public int getPoints(){
        return 0;
    }
    
    //Colliding with Goomba Reduces players HP by 1. 
    public int getDamage(){
        return -1;
    }
}
