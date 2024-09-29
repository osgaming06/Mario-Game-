import java.awt.*;
import java.awt.event.*;
import java.util.*;

//The basic ScrollingGame, featuring Avoids, Gets, and SpecialGets
//Players must reach a score threshold to win
//If player runs out of HP (via too many Avoid collisions) they lose
public class ScrollingGame extends GameEngine {
    
 
    
    //Starting Player coordinates
    protected static final int STARTING_PLAYER_X = 0;
    protected static final int STARTING_PLAYER_Y = 100;
    
    //Score needed to win the game
    protected static final int SCORE_TO_WIN = 300;
    
    //Maximum that the game speed can be increased to
    //(a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
    protected static final int MAX_GAME_SPEED = 300;
    //Interval that the speed changes when pressing speed up/down keys
    protected static final int SPEED_CHANGE = 20;    
    
    protected static final String INTRO_SPLASH_FILE = "assets/splash.gif";        
    //Key pressed to advance past the splash screen
    public static final int ADVANCE_SPLASH_KEY = KeyEvent.VK_ENTER;
    
    //Interval that Entities get spawned in the game window
    //ie: once every how many ticks does the game attempt to spawn new Entities
    protected static final int SPAWN_INTERVAL = 45;
    
    
    //A Random object for all your random number generation needs!
    protected static final Random rand = new Random();
    
            
    
    //Player's current score
    protected int score;
    
    //Stores a reference to game's Player object for quick reference
    //(This Player will also be in the displayList)
    protected Player player;
    
    
    
    
    
    public ScrollingGame(){
        super();
    }
    
    public ScrollingGame(int gameWidth, int gameHeight){
        super(gameWidth, gameHeight);
    }
    
    
    //Performs all of the initialization operations that need to be done before the game starts
    protected void pregame(){
        this.setBackgroundColor(Color.BLACK);
        player = new Player(STARTING_PLAYER_X, STARTING_PLAYER_Y);
        displayList.add(player); 
        score = 0;
        setSplashImage(INTRO_SPLASH_FILE);
    }
    
    
    //Called on each game tick
    protected void updateGame(){
        if(!isPaused){
            //scroll all scrollable Entities on the game board
            scrollEntities();   

            //Spawn new entities only at a certain interval
            if (super.getTicksElapsed() % SPAWN_INTERVAL == 0){            
                spawnEntities();
            }
            gcOutOfWindowEntities();

            for(int i = 0; i < checkCollision(player).size(); i++ ){
                if(checkCollision(player).get(i) instanceof Collectable){
                    handlePlayerCollision((Collectable)checkCollision(player).get(i));
                }
            }

            //Update the title text on the top of the window
            setTitleText("HP: " + player.getHP() + ", Score: " + score);  
        }
    }
    
    
    //Scroll all scrollable entities per their respective scroll speeds
    protected void scrollEntities(){
        for (int i = 0; i < displayList.size(); i++){
            Entity entity = displayList.get(i);

            // Check if the entity is scrollable
            if (entity instanceof Scrollable) {
                // Cast to Scrollable and scroll
                Scrollable scrollableEntity = (Scrollable) entity;
                scrollableEntity.scroll();
            }
           
        }
    }
    
    
    //Handles "garbage collection" of the displayList
    //Removes entities from the displayList that have scrolled offscreen
    //(i.e. will no longer need to be drawn in the game window).
    protected void gcOutOfWindowEntities(){
       for(int i = 0; i < displayList.size(); i++){
        if(displayList.get(i).getX() < 0-displayList.get(i).getWidth()){
            displayList.remove(i);
        }
       }
       
    }
    
    
    //Called whenever it has been determined that the Player collided with a collectable
    protected void handlePlayerCollision(Collectable collidedWith){
        score += collidedWith.getPoints();
        player.modifyHP(collidedWith.getDamage());
        displayList.remove((Entity)collidedWith);
       
    }
    
    
    //Spawn new Entities on the right edge of the game window
    protected void spawnEntities(){
        // Spawn regular Gets
    if (rand.nextInt(100) < 50) {
        boolean collided = false;
        Get newGet = new Get(getWindowWidth(), rand.nextInt(getWindowHeight()-Get.GET_HEIGHT));
        for(int i = 0; i < displayList.size(); i++){
            if(newGet.isCollidingWith(displayList.get(i))){
                collided = true;
            } 
        }
        if(!collided){
            displayList.add(newGet);
        }  
    }

    // Spawn SpecialGets
    if (rand.nextInt(100) < 30) {
        boolean collided = false;
        SpecialGet newSpecialGet = new SpecialGet(getWindowWidth(), rand.nextInt(getWindowHeight()-SpecialGet.GET_HEIGHT));
        for(int i = 0; i < displayList.size(); i++){
            if(newSpecialGet.isCollidingWith(displayList.get(i))){
                collided = true;
            } 
        }
        if(!collided){
            displayList.add(newSpecialGet);
        }  
    }

    // Spawn Avoids
    if (rand.nextInt(100) < 50) {
        boolean collided = false;
        Avoid newAvoid = new Avoid(getWindowWidth(), rand.nextInt(getWindowHeight()-Avoid.AVOID_HEIGHT));
        for(int i = 0; i < displayList.size(); i++){
            if(newAvoid.isCollidingWith(displayList.get(i))){
                collided = true;
            } 
        }
        if(!collided){
            displayList.add(newAvoid);
        }  
    }    
    }
    
    
    //Called once the game is over, performs any end-of-game operations
    protected void postgame(){
        if(determineIfGameIsOver() && score == SCORE_TO_WIN)
            super.setTitleText("GAME OVER: You Win!");
        else if(determineIfGameIsOver() && player.getHP() == 0)
            super.setTitleText("GAME OVER: You lose!");
    }
    
    
    //Determines if the game is over or not
    //Game can be over due to either a win or lose state
    protected boolean determineIfGameIsOver(){

        if(score == SCORE_TO_WIN)
            return true;
        else if(player.getHP() == 0)
            return true;
        
        return false;
       
    }
    
    
    
    //Reacts to a single key press on the keyboard
    protected void reactToKey(int key){
        
        setDebugText("Key Pressed!: " + KeyEvent.getKeyText(key) + ",  DisplayList size: " + displayList.size());
        
        //if a splash screen is active, only react to the "advance splash" key... nothing else!
        if (getSplashImage() != null){
            if (key == ADVANCE_SPLASH_KEY)
                super.setSplashImage(null);

            return;
        }
        if(!isPaused){
            if (key == RIGHT_KEY && player.getX() + player.getMovementSpeed() + player.getWidth() < getWindowWidth()) {
                player.setX(player.getX() + player.getMovementSpeed());
            } else if (key == LEFT_KEY && player.getX() - player.getMovementSpeed() > 0) {
                player.setX(player.getX() - player.getMovementSpeed());
            }
            if (key == UP_KEY && player.getY() - player.getMovementSpeed() > 0) {
                player.setY(player.getY() - player.getMovementSpeed());
            }
            if (key == DOWN_KEY && player.getY() + player.getMovementSpeed() + player.getHeight() < getWindowHeight()) {
                player.setY(player.getY() + player.getMovementSpeed());
            }
            if(key == KEY_PAUSE_GAME){
            isPaused = true;
            }
            if(key == SPEED_UP_KEY && getGameSpeed() + SPEED_CHANGE <= MAX_GAME_SPEED){
                setGameSpeed(getGameSpeed() + SPEED_CHANGE);
            }
            if(key == SPEED_DOWN_KEY && getGameSpeed() - SPEED_CHANGE >= 0){
                setGameSpeed(getGameSpeed() - SPEED_CHANGE);
            }
        }
        else{
            if(key == KEY_PAUSE_GAME){
            isPaused = false;
            }
    }
    }
    
    
    
    //Handles reacting to a single mouse click in the game window
    //Won't be used in Milestone #2... you could use it in Creative Game though!
    protected MouseEvent reactToMouseClick(MouseEvent click){
        if (click != null){ //ensure a mouse click occurred
            int clickX = click.getX();
            int clickY = click.getY();
            setDebugText("Click at: " + clickX + ", " + clickY);
        }
        return click;//returns the mouse event for any child classes overriding this method
    }
    
    
    
    
}
