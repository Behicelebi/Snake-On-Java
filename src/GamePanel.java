import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener , KeyListener {
    int WIDTH;
    int HEIGHT;
    int tileSize = 25;
    boolean loop;
    Color snakeColor,foodColor,bgColor,outlineColor;

    public static class Tile{
        int x;
        int y;
        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    Tile snake;
    Tile food;
    Random random;
    Timer timer;
    int vX=0;
    int vY=0;
    boolean gameOver = false;
    ArrayList<Tile> snakeBody;

    GamePanel(int WIDTH, int HEIGHT){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.setPreferredSize(new Dimension(this.WIDTH,this.HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(this);
        snake = new Tile((int) (WIDTH / 2) / tileSize, (int) (HEIGHT / 2) / tileSize);
        food = new Tile(17, 17);
        random = new Random();
        FoodRand(gameOver);
        timer = new Timer(100,this);
        timer.start();
        snakeBody = new ArrayList<>();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //BACKGROUND
        g.setColor(bgColor);
        g.fillRect(0,0,WIDTH,HEIGHT);

        //GRID
        g.setColor(Color.gray);
        for(int i=0; i<WIDTH/tileSize; i++){
            g.drawLine(i*tileSize,0,i*tileSize,HEIGHT);
            g.drawLine(0,i*tileSize,WIDTH,i*tileSize);
        }

        //FOOD
        g.setColor(foodColor);
        g.fill3DRect(food.x*tileSize,food.y*tileSize,tileSize,tileSize,true);

        //SNAKE HEAD
        g.setColor(snakeColor);
        g.fill3DRect(snake.x*tileSize,snake.y*tileSize,tileSize,tileSize,true);

        //SNAKE BODY
        for(int i=0; i<snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x*tileSize,snakePart.y*tileSize,tileSize,tileSize,true);
        }

        //OUTLINE
        g.setColor(outlineColor);
        g.fillRect(25,25,18*25,25);
        g.fillRect(25,25,25,18*25);
        g.fillRect(25,18*25,18*25,25);
        g.fillRect(18*25,25,25,18*25);

        g.setFont(new Font("Arial",Font.PLAIN,16));
        g.drawString("SNAKE GAME",tileSize*8,20);
        g.drawString("Score: "+ snakeBody.size(),tileSize-16,20);
        if(gameOver){
            g.setFont(new Font("Arial",Font.PLAIN,25));
            g.setColor(Color.red);
            g.drawString("GAME OVER",tileSize*7,tileSize*10);
        }
    }

    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x==tile2.x && tile1.y==tile2.y;
    }

    public void FoodRand(boolean gameOver){
        if(gameOver){
            food.x = -1;
            food.y = -1;
        }
        else {
            food.x = random.nextInt(15) + 2;
            food.y = random.nextInt(15) + 2;
        }
    }

    public void move(){
        if(collision(snake,food)){
            snakeBody.add(new Tile(food.x,food.y));
            FoodRand(gameOver);
        }
        for(int i=snakeBody.size()-1; i>=0; i--){
            Tile snakePart = snakeBody.get(i);
            if (i==0){
                snakePart.x = snake.x;
                snakePart.y = snake.y;
            }
            else{
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        snake.x += vX;
        snake.y += vY;

        for (int i=0; i<snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            if(collision(snake,snakePart)){
                gameOver = true;
                FoodRand(gameOver);
            }
        }
        if(loop){
            if(snake.x<2){snake.x+=16;}
            else if(snake.x>17){snake.x-=16;}
            else if(snake.y<2){snake.y+=16;}
            else if(snake.y>17){snake.y-=16;}
        }
        else{
            if(snake.x<2 || snake.x>17 || snake.y<2 || snake.y>17){
                gameOver=true;
                FoodRand(gameOver);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            timer.stop();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP && vY != 1){
            vX=0;
            vY=-1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN && vY != -1){
            vX=0;
            vY=1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT && vX != -1){
            vX=1;
            vY=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT && vX != 1){
            vX=-1;
            vY=0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
