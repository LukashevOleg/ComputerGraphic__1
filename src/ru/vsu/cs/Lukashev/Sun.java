package ru.vsu.cs.Lukashev;

import java.awt.*;

public class Sun
{
    private double x,y;

    public Sun(double x, double y)
    {
        this.x = x;
        this.y = y;
    }


    public void paint(Graphics2D g, double width, double height) {

        int radiusS = (int) (0.02 * width);
        int radiusL = (int) (0.01 * width);
        int n = 9;
//        g.setColor(Color.BLACK);
//        g.fillOval(x,y, 50,50);
        g.setColor(Color.orange);
        g.fillOval((int) (x * height) - radiusS, (int) (y * width) - radiusS, radiusS*2, radiusS*2);

        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double a = da * i;
            double x1 = radiusS * Math.cos(a) + (int) (x * height);
            double y1 = radiusS * Math.sin(a) + (int) (y * width);
            double x2 = (radiusS + radiusL) * Math.cos(a) + (int) (x * height);
            double y2 = (radiusS + radiusL) * Math.sin(a) + (int) (y * width);

            g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

        }
    }
}
