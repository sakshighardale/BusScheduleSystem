import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel<Image>  extends JPanel implements ActionListener{
    final int Panel_Width=700;
    final int Panel_height=700;
    java.awt.Image enemy;
    java.awt.Image backgroundImage;
    Timer timer;
    int xVelocity=2;
    int yVelocity=4;
    int x=0;
    int y=0;
    MyPanel()
    {
        setPreferredSize(new Dimension(Panel_Width,Panel_height));
        enemy=new ImageIcon("pmpml_Bus.png").getImage();
        backgroundImage=new ImageIcon("com/TimePass/space.jpg").getImage();
        timer=new Timer(10,this);

        timer.start();


    }
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2D=(Graphics2D)g;
        g2D.drawImage(backgroundImage,0,0,null);

        g2D.drawImage(enemy,x,y, null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(x>=Panel_Width-enemy.getWidth(null) ||x<0)   {
            xVelocity=xVelocity* -1;
        }
        x=x+xVelocity;
        if(y>=Panel_height-enemy.getHeight(null) ||y<0)   {
            yVelocity=yVelocity* -1;
        }
        y=y+yVelocity;
        repaint();

    }

    public void main(String[] args) {
        new MyPanel<>();
    }

}
