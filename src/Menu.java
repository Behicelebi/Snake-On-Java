import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener{
    int WIDTH;
    int HEIGHT;
    JCheckBox checkBox = new JCheckBox("Loop");
    JButton snakeColor=new JButton("Color"),foodColor=new JButton("Color"),bgColor=new JButton("Color"),outlineColor=new JButton("Color");
    Color snake=Color.green,food=Color.red,bg=Color.black,outline=Color.white;
    boolean loop;

    Menu(int WIDTH, int HEIGHT){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.setPreferredSize(new Dimension(this.WIDTH,this.HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setLayout(null);
        checkBox.setBounds(30,300,80,30);
        checkBox.setFont(new Font("Arial",Font.PLAIN,15));
        checkBox.setFocusable(false);
        checkBox.setBackground(Color.BLACK);
        checkBox.setForeground(Color.WHITE);
        snakeColor.setBounds(140,335,80,20);
        snakeColor.setFocusable(false);
        snakeColor.addActionListener(this);
        snakeColor.setForeground(snake);
        foodColor.setBounds(140,365,80,20);
        foodColor.setFocusable(false);
        foodColor.addActionListener(this);
        foodColor.setForeground(food);
        bgColor.setBounds(140,395,80,20);
        bgColor.setFocusable(false);
        bgColor.addActionListener(this);
        bgColor.setForeground(bg);
        outlineColor.setBounds(140,425,80,20);
        outlineColor.setFocusable(false);
        outlineColor.addActionListener(this);
        outlineColor.setForeground(outline);
        this.add(checkBox);
        this.add(snakeColor);
        this.add(foodColor);
        this.add(bgColor);
        this.add(outlineColor);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,40));
        g.drawString("SNAKE GAME",130,50);
        g.setFont(new Font("Arial",Font.PLAIN,15));
        g.drawString("Snake Color: ",30,350);
        g.drawString("Food Color: ",30,380);
        g.drawString("BG Color: ",30,410);
        g.drawString("Outline Color: ",30,440);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==snakeColor){
            new JColorChooser();
            Color color = JColorChooser.showDialog(null,"Pick a Color for the Snake",snake);
            snake = color;
            snakeColor.setForeground(color);
        }
        else if(e.getSource()==foodColor){
            new JColorChooser();
            Color color = JColorChooser.showDialog(null,"Pick a Color for the Food",food);
            food = color;
            foodColor.setForeground(color);
        }
        else if(e.getSource()==bgColor){
            new JColorChooser();
            Color color = JColorChooser.showDialog(null,"Pick a Color for the Background",bg);
            bg = color;
            bgColor.setForeground(color);
        }
        else if(e.getSource()==outlineColor){
            new JColorChooser();
            Color color = JColorChooser.showDialog(null,"Pick a Color for the Food",outline);
            outline = color;
            outlineColor.setForeground(color);
        }
    }
}
