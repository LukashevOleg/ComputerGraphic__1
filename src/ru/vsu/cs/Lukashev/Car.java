package ru.vsu.cs.Lukashev;

import javax.swing.*;
import java.awt.*;

public class Car
{
    private double xCar;
    private double yCar;
    private final Color carColor;
    private final Color borderColor;


    public Car(double xCar, double yCar, Color carColor, Color borderColor)
    {
        this.xCar = xCar;
        this.yCar = yCar;
        this.carColor = carColor;
        this.borderColor = borderColor;
    }

    public void paint(Graphics2D g, int width, int height)
    {
//        xCar=xCar*width;
//
//        yCar=yCar*height;



        /**
         * тело машины
         */
        g.setColor(carColor);
        g.fillRect((int)(xCar*width)+(int)(0.012*width), (int)(yCar*height), (int)(0.280*width),(int)(0.048*height) );
        g.fillRect((int)(xCar*width), (int)(yCar*height) +(int)(0.010*height), (int)(0.040*width), (int)(0.038*height));

        /**
         * контур машины
         */
        drawBorder(g, width, height);

        /**
         * фара
         */
        drawHeadlight(g, Color.orange, (int)(xCar*width), (int)(yCar*height),(int)(0.025*width));

        /**
         * колеса
         */
        Wheel frontWheel = new Wheel((int)(xCar*width) + (int)(0.062*width), (int)(yCar*height) + (int)(0.020*height), (int)(0.040*width), Color.BLACK);
        Wheel backWheel = new Wheel((int)(xCar*width) + (int)(0.222*width), (int)(yCar*height) + (int)(0.020*height), (int)(0.040*width), Color.BLACK);
        frontWheel.paint(g,width, height);
        backWheel.paint(g,width, height);


        /**
         * дверь
         */
        Door door= new Door((int)(xCar*width)+(int)(0.1*width),(int)(yCar*height), borderColor);
        door.paint(g, width, height);

        /**
         * лобовое стекло
         */
        drawWindscreen(g, width, height);


        /**
         *  надпись
         */
        g.setColor(Color.BLACK);
        g.setFont(new Font("courier", Font.BOLD|Font.ITALIC, (int)(0.015*height) ));
        g.drawString("BMW", (int)(xCar*width)+(int)(0.02*width),(int)(yCar*height)+(int)(0.03*height));



    }

    public void drawWindscreen(Graphics2D g, int width, int height)
    {
        g.setColor(Color.decode("#a3a3a3"));
        int[] xWindscreen={(int)(xCar*width)+(int)(0.100*width), (int)(xCar*width)+(int)(0.130*width), (int)(xCar*width)+(int)(0.130*width)};
        int[] yWindscreen={(int)(yCar*height),(int)(yCar*height) -(int)(0.020*height), (int)(yCar*height) };
        g.fillPolygon(xWindscreen,yWindscreen,3);
    }

    public void drawBorder(Graphics2D g, int width, int height)
    {
        g.setColor(borderColor);  //yDoor+(int)(0.040*width)
        g.setStroke(new BasicStroke(2));
        g.drawOval((int)(xCar*width),(int)(yCar*height),(int)(0.025*height),(int)(0.025*height));
        g.drawLine((int)(xCar*width)+(int)(0.012*width),(int)(yCar*height), (int)(xCar*width)+(int)(0.292*width),(int)(yCar*height)); // верх
        g.drawLine((int)(xCar*width), (int)(yCar*height) +(int)(0.015*height), (int)(xCar*width), (int)(yCar*height) +(int)(0.048*height)); //радиатор
        g.drawLine((int)(xCar*width)+(int)(0.100*width), (int)(yCar*height),(int)(xCar*width)+(int)(0.130*width) , (int)(yCar*height) -(int)(0.020*height) );//лобовое
        g.drawLine((int)(xCar*width)+(int)(0.130*width), (int)(yCar*height) -(int)(0.020*height), (int)(xCar*width)+(int)(0.130*width),(int)(yCar*height)); // лобовое вниз
        g.drawLine((int)(xCar*width), (int)(yCar*height) +(int)(0.048*height), (int)(xCar*width)+(int)(0.062*width), (int)(yCar*height) +(int)(0.048*height)); // до переднего колеса
        g.drawLine((int)(xCar*width)+(int)(0.095*width), (int)(yCar*height) +(int)(0.048*height), (int)(xCar*width)+(int)(0.222*width), (int)(yCar*height) +(int)(0.048*height)); //между колесами
        g.drawLine((int)(xCar*width)+(int)(0.265*width), (int)(yCar*height) +(int)(0.048*height),(int)(xCar*width)+(int)(0.292*width), (int)(yCar*height) +(int)(0.048*height));// после колес
        g.drawLine((int)(xCar*width)+(int)(0.292*width), (int)(yCar*height) +(int)(0.048*height), (int)(xCar*width)+(int)(0.292*width), (int)(yCar*height));//багажник
    }

    public void drawHeadlight(Graphics2D g, Color color, int xCar, int yCar, int radius)
    {
        g.setColor(color);
        g.fillOval(xCar, yCar, radius, radius);
    }




    private static class Door
    {
        private final int xDoor;
        private final int yDoor;
        private final Color borderColor;

        public Door(int xDoor, int yDoor, Color borderColor)
        {
            this.xDoor = xDoor;
            this.yDoor = yDoor;
            this.borderColor = borderColor;
        }

        public void paint(Graphics2D g, int width, int height)
        {
            g.setColor(borderColor);
            g.setStroke(new BasicStroke(2));

            /**
             * передняя дверь
             */
            g.drawLine(xDoor,yDoor, xDoor, yDoor+(int)(0.033*height));
            g.drawLine(xDoor+(int)(0.080*width),yDoor,xDoor+(int)(0.080*width), yDoor+(int)(0.048*height));

            /**
             * ручка
             */
            g.drawLine(xDoor+(int)(0.059*width), yDoor+(int)(0.015*height), xDoor+(int)(0.065*width), yDoor+(int)(0.014*height));

            /**
             * задняя двень
             */
            g.drawLine(xDoor+(int)(0.140*width), yDoor,xDoor+(int)(0.140*width), yDoor+(int)(0.025*height));

            /**
             * ручка
             */
            g.drawLine(xDoor+(int)(0.125*width), yDoor+(int)(0.015*height), xDoor+(int)(0.131*width), yDoor+(int)(0.014*height));

        }
    }

    private static class Wheel
    {
        private final int xWheel;
        private final int yWheel;
        private final int diameterWheel;
        private final Color color;



        public Wheel(int xWheel, int yWheel, int diameterWheell, Color color)
        {
            this.xWheel = xWheel;
            this.yWheel = yWheel;
            this.diameterWheel = diameterWheell;
            this.color = color;
        }

        public void paint(Graphics2D g, int width, int height)
        {
            g.setColor(color);
            g.fillOval(xWheel, yWheel, diameterWheel, diameterWheel);

            /**
             * диск
             */
            int diameterInnerDisk=(int)(0.007*width), diameterOuterDisk=(int)(0.025*width);
            Disk disk = new Disk(diameterInnerDisk, diameterOuterDisk, Color.decode("#af33e8"),
                    (int) (xWheel + diameterWheel * 0.5 - diameterInnerDisk * 0.5),
                    (int) (yWheel + diameterWheel * 0.5 - diameterInnerDisk * 0.5),
                    (int) (xWheel + diameterWheel * 0.5 - diameterOuterDisk * 0.5),
                    (int) (yWheel + diameterWheel * 0.5 - diameterOuterDisk * 0.5));

            disk.paint(g, width, height);
        }






        private static class Disk
        {
            private final int diameterInnerDisk; // внутренний радиус
            private final int diameterOuterDisk; // внешний радиус
            private final Color color;
            private final int xInnerDisk;
            private final int yInnerDisk;
            private final int xOuterDisk;
            private final int yOuterDisk;

            public Disk(int diameterInnerDisk, int diameterOuterDisk, Color color, int xInnerDisk,
                        int yInnerDisk, int xOuterDisk, int yOuterDisk)
            {
                this.diameterInnerDisk = diameterInnerDisk;
                this.diameterOuterDisk = diameterOuterDisk;
                this.color = color;
                this.xInnerDisk = xInnerDisk;
                this.yInnerDisk = yInnerDisk;
                this.xOuterDisk = xOuterDisk;
                this.yOuterDisk = yOuterDisk;
            }

            public void paint(Graphics2D g, int width, int height)
            {
                g.setColor(color);
                g.fillOval(xInnerDisk, yInnerDisk, diameterInnerDisk, diameterInnerDisk);
                g.drawOval(xOuterDisk, yOuterDisk, diameterOuterDisk, diameterOuterDisk);
//                drawSpokes(g, 6);
            }




//            public void drawSpokes(Graphics2D g, int n)
//            {
//                double da = 2*Math.PI/n;
//                for(int i=0; i<n; i++) {
//                    double a = da*i;
//                    double xIn =xInnerDisk + diameterInnerDisk*0.5;
//                    double yIn = yInnerDisk + diameterInnerDisk*0.5;
//                    double xOut = (diameterOuterDisk*0.5-diameterInnerDisk*0.5)*Math.cos(a)
//                            + xInnerDisk + diameterInnerDisk*0.5;
//                    double yOut = (diameterOuterDisk*0.5-diameterInnerDisk*0.5)*Math.sin(a)
//                            + yInnerDisk + diameterInnerDisk*0.5;
//
//                    g.drawLine((int)xIn, (int)yIn, (int)xOut, (int) yOut);
//
//                }
//            }
        }


    }
}
