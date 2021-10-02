package ru.vsu.cs.Lukashev;

import java.awt.*;

public class Flashlight
{
    private double xFlash;
    private double yFlash;

    private double heightArea=0.2;
    private double widthArea=0.001;

    public Flashlight(double xFlash, double yFlash)
    {
        this.xFlash = xFlash;
        this.yFlash = yFlash;

    }

    public void paint(Graphics2D g, boolean night, int width, int height)
    {
        g.setColor(Color.decode("#b3b3b3"));
        g.fillRect((int)(xFlash*width),(int)(yFlash*height), (int)(widthArea*width), (int)(heightArea*height));

        g.setColor(Color.decode("#707070"));
        int[] xShadowFlash={
                (int)(xFlash*width)+ (int)(widthArea*width),
                (int)(xFlash*width)+ (int)(widthArea*width) *2-(int)(0.004*width),
                (int)(xFlash*width)+ (int)(widthArea*width) *2-(int)(0.004*width),
                (int)(xFlash*width)+ (int)(widthArea*width)};

        int[] yShadowFlash={
                (int)(yFlash*height),
                (int)(yFlash*height)-(int)(0.004*height),
                (int)(yFlash*height)+ (int)(heightArea*height) -(int)(0.004*height),
                (int)(yFlash*height)+ (int)(heightArea*height)};

        g.fillPolygon(xShadowFlash, yShadowFlash, 4);
        g.drawRect((int)(xFlash*width),(int)(yFlash*height), (int)(widthArea*width), (int)(heightArea*height));

        drawFlash(g, width, height);
        if(night)
        {
            drawLight(g, width, height );
        }
    }

    public void step()
    {
        xFlash+=1;
        if(xFlash>=1200)
        {
            xFlash=-10;
        }
        System.out.println(xFlash);
    }


    public void drawFlash(Graphics2D g, int width, int height)
    {
        g.setColor(Color.decode("#b3b3b3"));

        int[] xHorizontalFlash={
                (int)(xFlash*width)+ (int)(widthArea*width) *2-(int)(0.004*width),
                (int)(xFlash*width)+ (int)(widthArea*width) *2-(int)(0.015*width),
                (int)(xFlash*width)+(int)(0.041*width),
                (int)(xFlash*width)+(int)(0.03*width)};
        int[] yHorizontalFlash={
                (int)(yFlash*height)-(int)(0.004*height),
                (int)(yFlash*height)-(int)(0.004*height),
                (int)(yFlash*height)-(int)(0.02*height),
                (int)(yFlash*height)-(int)(0.02*height)};
        g.fillPolygon(xHorizontalFlash,yHorizontalFlash,4);

        g.setColor(Color.decode("#707070"));
        g.setStroke(new BasicStroke(1));
        g.drawPolygon(xHorizontalFlash,yHorizontalFlash,4);



    }

    public void drawLight(Graphics2D g, int width, int height)
    {
        g.setColor(new Color(0.93f, 0.97f, 0.44f,.4f));
        int[] xLight={
                (int)(xFlash*width)-(int)(0.04*width),
                (int)(xFlash*width)-(int)(0.03*width),
                (int)(xFlash*width)+(int)(0.08*width),
                (int)(xFlash*width)+(int)(0.12*width)};

        int[] yLight={
                (int)(yFlash*height)+(int)(0.02*height),
                (int)(yFlash*height)+(int)(0.02*height),
                (int)(yFlash*height)+(int)(0.3*height),
                (int)(yFlash*height)+(int)(0.3*height)};
        g.fillPolygon(xLight,yLight,4);
    }


}
