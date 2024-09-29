public class Mario extends Entity{
      
   //Location of image file to be drawn for Mario
   public static final String PLAYER_IMAGE_FILE = "assets/mario.gif";
   //Dimensions of Mario
   public static final int PLAYER_WIDTH = 75;
   public static final int PLAYER_HEIGHT = 75;
   //Default speed that the Player's Mario moves (in pixels) each time the user moves it
   public static final int DEFAULT_MOVEMENT_SPEED = 7;
   //Starting hit points
   public static final int STARTING_HP = 3;
   public static final int MAX_HP = 5;

    
    //Current movement speed
    private int movementSpeed;
    //Remaining Hit Points (HP) -- indicates the number of "hits" (ie collisions
    //with Avoids) that Mario can take before the game is over
    private int hp;
    
    
    public Mario(){
        this(0, 0);        
    }
    
    public Mario(int x, int y){
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_IMAGE_FILE);  
        this.hp = STARTING_HP;
        this.movementSpeed = DEFAULT_MOVEMENT_SPEED;
    }
    
    
    //Retrieve and set Mario's current movement speed 
    public int getMovementSpeed(){
        return this.movementSpeed;
    }
    
    public void setMovementSpeed(int newSpeed){
        this.movementSpeed = newSpeed;
    }  
    
    
    //Retrieve Mario's current HP
    public int getHP(){
        return hp;
    }

    
    //Set Mario's HP to a specific value.
    //Returns an boolean indicating if Player still has HP remaining
    public boolean setHP(int newHP){
        this.hp = newHP;
        return (this.hp > 0);
    }
    
    //Set Mario's HP to a specific value.
    //Returns an boolean indicating if Mario still has HP remaining
    public boolean modifyHP(int delta){
        this.hp += delta;
        if (this.hp > MAX_HP) {
            this.hp = MAX_HP;
        }
        return (this.hp > 0);
    }    
    
}
