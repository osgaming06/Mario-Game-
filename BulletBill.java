public class BulletBill extends Entity implements Collectable, Scrollable {
    //Location of image file to be drawn for a Bullet Bill
    public static final String BULLETBILL_IMAGE_FILE = "assets/bulletbill.png";
    //Dimensions of the Avoid    
    public static final int BULLETBILL_WIDTH = 80;
    public static final int BULLETBILL_HEIGHT = 80;
    //Speed that the avoid moves each time the game scrolls
    public static final int BULLETBILL_SCROLL_SPEED = 10;
    
    public BulletBill(){
        this(0, 0);        
    }
    
    public BulletBill(int x, int y){
        super(x, y, BULLETBILL_WIDTH, BULLETBILL_HEIGHT, BULLETBILL_IMAGE_FILE);  
    }
    
    
    public int getScrollSpeed(){
        return BULLETBILL_SCROLL_SPEED;
    }
    
    //Move the Bullet Bill left by the scroll speed
    public void scroll(){
        setX(getX() - BULLETBILL_SCROLL_SPEED);
    }
    
    //Colliding with Bullet Bill does not affect the player's score
    public int getPoints(){
        return 0;
    }
    
    //Colliding with Bullet Bill Reduces players HP entirely 
    public int getDamage(){
        return -99999999;
    }
    
}
