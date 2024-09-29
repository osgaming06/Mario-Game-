import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LopezGame extends ScrollingGame {
    protected static final String INTRO1_SPLASH_FILE = "assets/flying.png"; 
    protected static final String INTRO2_SPLASH_FILE = "assets/controls.png";
    protected static final String INTRO3_SPLASH_FILE = "assets/rules.gif"; 
    protected static final String BACKGROUND_IMAGE_FILE = "assets/background.jpeg"; 
    protected int splashScreenCount = 0;
    protected int level = 1;
    protected static final int NEW_SCORE_TO_WIN = 5000;
    

    

    public LopezGame(){
        super();
    }

    public LopezGame(int gameWidth, int gameHeight){
        super(gameWidth, gameHeight);
    }

    protected void pregame(){
        super.pregame();
        super.setTitleText("SUPER FLYING BROS GAME!");
        this.setBackgroundImage(BACKGROUND_IMAGE_FILE);
        setSplashImage(INTRO1_SPLASH_FILE);
    }

    protected void updateGame(){
        super.updateGame();
        setTitleText("HP: " + player.getHP() + " Score: " + score + " Level:" + level);
    }

    protected void scrollEntities(){
        super.scrollEntities();
    }

    protected void gcOutOfWindowEntities(){
        super.gcOutOfWindowEntities();
    }

    protected void handlePlayerCollision(Collectable collidedWith){
        super.handlePlayerCollision(collidedWith);
        if (score % 500 == 0 && score != 0) {
            setGameSpeed(getGameSpeed() + SPEED_CHANGE); // increases speed
            level++; 
        }
    }

    protected void spawnEntities(){
    // Spawns Coin
    if (rand.nextInt(100) < 50) {
        boolean collided = false;
        Coin newCoin = new Coin(getWindowWidth(), rand.nextInt(getWindowHeight()-Coin.COIN_HEIGHT - player.getHeight()));
        for(int i = 0; i < displayList.size(); i++){
            if(newCoin.isCollidingWith(displayList.get(i))){
                collided = true;
            } 
        }
        if(!collided){
            displayList.add(newCoin);
        }
    }

    //Spawns Bullet Bill
    if (rand.nextInt(100) < 10) {
        boolean collided = false;
        BulletBill newBulletBill = new BulletBill(getWindowWidth(), rand.nextInt(getWindowHeight()-BulletBill.BULLETBILL_HEIGHT-player.getHeight()));
        for(int i = 0; i < displayList.size(); i++){
            if(newBulletBill.isCollidingWith(displayList.get(i))){
                collided = true;
            }
        }
        if(!collided){
            displayList.add(newBulletBill);
        }
    }

    // Spawns Shroom
    if (rand.nextInt(100) < 10) {
        boolean collided = false;
        Shroom newShroom = new Shroom(getWindowWidth(), getWindowHeight()-Shroom.COIN_HEIGHT-player.getHeight());
        for(int i = 0; i < displayList.size(); i++){
            if(newShroom.isCollidingWith(displayList.get(i))){
                collided = true;
            } 
        }
        if(!collided){
            displayList.add(newShroom);
        }  
    }

    // Spawns Koopa
    if (rand.nextInt(100) < 50) {
        boolean collided = false;
        Koopa newKoopa = new Koopa(getWindowWidth(), rand.nextInt(getWindowHeight()-Koopa.KOOPA_HEIGHT-player.getHeight()));
        for(int i = 0; i < displayList.size(); i++){
            if(newKoopa.isCollidingWith(displayList.get(i))){
                collided = true;
            } 
        }
        if(!collided){
            displayList.add(newKoopa);
        }  
    }  

    // Spawn Goomba
    if (rand.nextInt(100) < 20) {
        boolean collided = false;
        Goomba newGoomba = new Goomba(getWindowWidth(), getWindowHeight()-Goomba.GOOMBA_HEIGHT-player.getHeight());
        for(int i = 0; i < displayList.size(); i++){
            if(newGoomba.isCollidingWith(displayList.get(i))){
                collided = true;
            } 
        }
        if(!collided){
            displayList.add(newGoomba);
        }  
    }    
    }

    protected void postgame(){
        if(this.determineIfGameIsOver() && score == NEW_SCORE_TO_WIN)
            this.setTitleText("GAME OVER: You Win!");
        else if(this.determineIfGameIsOver() && player.getHP() <= 0)
            super.setTitleText("GAME OVER: You Lose!");
    }
    protected boolean determineIfGameIsOver(){

        if(score == NEW_SCORE_TO_WIN)
            return true;
        else if(player.getHP() <= 0)
            return true;
        
        return false;
       
    }

    protected void reactToKey(int key){
        
        setDebugText("Key Pressed!: " + KeyEvent.getKeyText(key) + ",  DisplayList size: " + displayList.size());
        
        //if a splash screen is active, only react to the "advance splash" key... nothing else!
        if (getSplashImage() != null){
            if(splashScreenCount == 0){
                super.setSplashImage(INTRO2_SPLASH_FILE);
                splashScreenCount++;
            }
    
            else if (key == ADVANCE_SPLASH_KEY && splashScreenCount == 1){
                super.setSplashImage(INTRO3_SPLASH_FILE);
                splashScreenCount++;
            }
            else{
                if(key == ADVANCE_SPLASH_KEY && splashScreenCount > 1){
                    super.setSplashImage(null);
                }

            }
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
            if (key == DOWN_KEY && player.getY() + player.getMovementSpeed() + player.getHeight() < getWindowHeight()-player.getHeight()) {
                player.setY(player.getY() + player.getMovementSpeed());
            }
            if(key == KEY_PAUSE_GAME){
            isPaused = true;
            }
            if(key == SPEED_UP_KEY && getGameSpeed()+ SPEED_CHANGE <= MAX_GAME_SPEED){
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

    
}
    
