package ru.vsu.cs.Lukashev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel
{

    private boolean night;
    private boolean drive;
//    private Timer timer;
//    ActionListener timerListener;


    private Flashlight flashlight;
    private Car car;
    private Forest underForest;
    private Forest downForest;
    private final Sun sun;

    public MainPanel(boolean n, boolean drive)
    {
        this.night = n;
        this.drive = drive;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                night = !night;
                repaint();
            }
        });


        car= new Car(0.32,0.35, Color.decode("#dec5da"), Color.decode("#1c1c1c"));
        System.out.println("car");
        flashlight=new Flashlight(0.2,0.11);
        System.out.println("flashlight");
//        flashlight=new Flashlight(200,110, 200, 10, getWidth(), getHeight());
        underForest=new Forest(0,2,0.1,0.3, 150);
        System.out.println("underForest");
        downForest=new Forest(0,2,0.65,1, 150);
        System.out.println("downForest");
        sun=new Sun(0.2, 0.05);



//
//        flashlight=new Flashlight(200,110, 200*getHeight(), 10*getWidth());
//        underForest=new Forest(0,2500*getWidth(),100*getHeight(),300*getHeight(), 150);
//        downForest=new Forest(0,2500*getWidth(),650*getHeight(),850*getHeight(), 200);

    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        Graphics2D g=(Graphics2D) graphics;

        /**
         * заполняем фон
         * потом расставим объекты
         */
        background(g, getWidth(), getHeight());

        /**
         * машинка
         */
        car.paint(g, getWidth(), getHeight());

        /**
         * лес перед и после дороги
         */
//        underForest=new Forest(0,2500*getWidth(),100*getHeight(),300*getHeight(), 150);
//        downForest=new Forest(0,2500*getWidth(),650*getHeight(),850*getHeight(), 200);
        underForest.paint(g, getWidth(), getHeight());
        downForest.paint(g, getWidth(), getHeight());

        drawNight(g);// ночь

        /**
         * фонарь
         */

        flashlight.paint(g, night, getWidth(), getHeight());

//        g.setColor(Color.BLACK);
//        g.setFont(new Font("courier", Font.BOLD|Font.ITALIC, 15 ));
//        g.drawString("BMW", 400,368);
//        drive();


        sun.paint(g, getWidth(), getHeight());

        System.out.println("that's all");


    }




//    public void drive()
//    {
//        if(drive)
//        {
//            timerListener= new ActionListener()
//            {
//                @Override
//                public void actionPerformed(ActionEvent e)
//                {
//                    flashlight.step();
//                    repaint();
//                }
//            };
//            timer =new Timer(5, timerListener);
//            timer.start();
//        }
//    }

    public void drawNight(Graphics2D g)
    {
        if(night)
        {
            g.setColor(new Color(0,0,0,.5f));
            g.fillRect(0,0, 2500,2000 );

        }
        else
        {
            g.setColor(new Color(0,0,0,0f));
            g.fillRect(0,0, 1200,1000 );

        }
    }

    public void background(Graphics2D g, int width, int height)
    {
        g.setColor(Color.decode("#84ccf5"));
        g.fillRect(0,0, width, (int)(0.1*height)); // небо
        g.setColor(Color.decode("#1a700c"));
        g.fillRect(0,(int)(0.100*height), width,(int)(0.200*height)); // травка
        g.setColor(Color.decode("#404040"));
        g.fillRect(0,(int)(0.300*height),width,(int)(0.350*height)); // дорога
        g.setColor(Color.WHITE);
        g.fillRect(0,(int)(0.458*height),width,(int)(0.008*height));
        g.fillRect(0,(int)(0.474*height),width,(int)(0.008*height));
        g.setColor(Color.decode("#1a700c"));
        g.fillRect(0,(int)(0.650*height),width,(int)(0.600*height)); // травка снизу
    }
}
