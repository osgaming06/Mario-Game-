//Gets are entities that the player wants to collide with, as they increase
//their score.
public class Coin extends Entity implements Collectable, Scrollable {
    
    //Location of image file to be drawn for a Coin
    public static final String COIN_IMAGE_FILE = "assets/coin.png";
    //Dimensions of the Coin  
    public static final int COIN_WIDTH = 50;
    public static final int COIN_HEIGHT = 50;
    //Speed that the Coin moves (in pixels) each time the game scrolls
    public static final int COIN_SCROLL_SPEED = 5;
    //Amount of points received when player collides with a Get
    public static final int COIN_POINT_VALUE = 20;
    
    
    public Coin(){
        this(0, 0);        
    }
    
    public Coin(int x, int y){
        super(x, y, COIN_WIDTH, COIN_HEIGHT, COIN_IMAGE_FILE);  
    }
    
    public Coin(int x, int y, String imageFileName){
        super(x, y, COIN_WIDTH, COIN_HEIGHT, imageFileName);
    }
    
    public int getScrollSpeed(){
        return COIN_SCROLL_SPEED;
    }
    
    //Move the Get left by its scroll speed
    public void scroll(){
        setX(getX() - COIN_SCROLL_SPEED);
    }
    
    //Colliding with a Get increases the player's score by the specified amount
    public int getPoints(){
        return COIN_POINT_VALUE;
    }
    
    //Colliding with a Get does not affect the player's HP
    public int getDamage(){
        return 0;
    }
    
}
