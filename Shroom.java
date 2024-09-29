//A SpecialGet is a rare kind of Get that spawns more infrequently than the regular Get
//When consumed, SpecialGets restores the Player's HP in addition to awarding points
//Otherwise, behaves the same as a regular Get
public class Shroom extends Coin{
    
    //Location of image file to be drawn for a SpecialGet
    public static final String SHROOM_IMAGE_FILE = "assets/shroom.png";
    
    public Shroom(){
        this(0, 0);        
    }
    
    public Shroom(int x, int y){
        super(x, y, SHROOM_IMAGE_FILE);  
    }
    
    public int getDamage(){
        return 1;
    }
}
