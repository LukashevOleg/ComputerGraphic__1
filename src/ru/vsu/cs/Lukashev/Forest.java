package ru.vsu.cs.Lukashev;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Forest
{
    private final double xLeft;
    private final double xRight;
    private final double yUpp;
    private final double yDown;
    private final int nTree;
    private List<Tree> treeList=new ArrayList<>();


    public Forest(double xLeft, double xRight, double yUpp, double yDown, int nTree)
    {
        this.xLeft = xLeft;
        this.xRight = xRight;
        this.yUpp = yUpp;
        this.yDown = yDown;
        this.nTree = nTree;
//        createTreeList();

        double xStep=(xRight-xLeft)/nTree;
        double yStep=(yDown-yUpp)/3;
        int count=30;
        int countTreeOnCol=(int)((yDown-yUpp)*count);
        double xTree=xLeft;
        double yTree=yUpp;
        int t=0;
        for(int i=0;i<nTree;i+=countTreeOnCol)
        {
            for(int k=0;k<countTreeOnCol;k++)
            {
                xTree+=Math.random()*xStep;
                yTree+=Math.random()*yStep;
                if(yTree>yDown-0.07)
                {
                    yTree=yDown-0.008-(Math.random()*10)/(yDown-yUpp);
                }
                treeList.add(t, new Tree(xTree, yTree, Color.decode("#3b280c"), Color.decode("#2fc23d")));
                t++;
            }
            xTree+=xStep;
            yTree=yUpp;
        }
    }


//    public void createTreeList()
//    {
//        int xStep=(xRight-xLeft)/nTree;
//        int yStep=(yDown-yUpp)/3;
//        int countTreeOnCol=(yDown-yUpp)/30;
//        int xTree=xLeft;
//        int yTree=yUpp;
//        int t=0;
//        for(int i=0;i<nTree;i+=countTreeOnCol)
//        {
//            for(int k=0;k<countTreeOnCol;k++)
//            {
//                xTree+=Math.random()*xStep;
//                yTree+=Math.random()*yStep;
//                if(yTree>yDown-80)
//                {
//                    yTree=yDown-80-(int)(Math.random()*10);
//                }
//                treeList.add(t, new Tree(xTree, yTree, Color.decode("#3b280c"), Color.decode("#2fc23d")));
//                t++;
//            }
//            xTree+=xStep;
//            yTree=yUpp;
//        }
//    }


    public void paint(Graphics2D g, int width, int height)
    {
        for (Tree value : treeList) {
            value.pain(g, width, height);
        }
    }



    protected class Tree {
        private final double xTree;
        private final double yTree;
        private final Color colorTrunk;
        private final Color colorFoliage;
        int nPoint = 10;
        double radius = 0.02;
        double[] xArrTree;
        double[] yArrTree;

        Area foliage = new Area();

        public Tree(double xTree, double yTree, Color colorTrunk, Color colorFoliage) {
            this.xTree = xTree;
            this.yTree = yTree;
            this.colorTrunk = colorTrunk;
            this.colorFoliage = colorFoliage;
            xArrTree = xArrTree(nPoint);
            yArrTree = yArrTree(nPoint);



        }


        public void pain(Graphics2D g, int width, int height) {
            /**
             * ствол
             */
            g.setColor(colorTrunk);
            g.fillRect((int) (xTree * width), (int) (yTree * height), 15 * width / 1000, 80 * height / 1000);

            /**
             * крона
             */
            drawFoliage(g, width, height);
//            foliage=null;

        }


        public int[] intFromDouble(double[] arrD, int count) {
            int[] arrI = new int[arrD.length];
            for (int i = 0; i < arrD.length; i++) {
                arrI[i] = (int) (arrD[i] * count);
            }
            return arrI;
        }

        public void drawFoliage(Graphics2D g, int width, int height) {
            g.setColor(Color.decode("#0a4a11"));
            int[] xArrTreeI = intFromDouble(xArrTree, width);
            int[] yArrTreeI = intFromDouble(yArrTree, height);
            for (int i = 0; i < nPoint; i++) {
                foliage.add(new Area(new Ellipse2D.Double(xArrTreeI[i], yArrTreeI[i], radius * width, radius * width)));
            }
            for (int i = 0; i < nPoint; i++) {
                g.setColor(colorFoliage);
                g.fillOval(xArrTreeI[i], yArrTreeI[i], (int) (radius * width), (int) (radius * width));
                g.setColor(Color.decode("#0a4a11"));
                g.draw(foliage);
            }
            for (int i = 0; i < nPoint; i++) {
                g.setColor(colorFoliage);
                g.fillOval((int) (xTree * width), (int) (yTree * height) + i * height / 1000, (int) (radius * width), (int) (radius * width));
            }
            foliage.reset();
        }


        public double[] xArrTree(int nPoint) {
            double[] xArrTree = new double[nPoint + 1];
//            xArrTree[0]=xTree;
            xArrTree[0] = xTree - 0.008;
            for (int i = 1; i < nPoint; i++) {
                if (i <= nPoint / 2) {
                    xArrTree[i] = xArrTree[0] + (Math.random() * 18) * 0.001 - 0.0025;
                } else {
                    xArrTree[i] = xArrTree[0] + (Math.random() * 18) * 0.001;
                }
            }
            xArrTree[nPoint] = xTree + 0.004;
            return xArrTree;
        }

        public double[] yArrTree(int nPoint) {
            double[] yArrTree = new double[nPoint + 1];
            yArrTree[0] = yTree + 0.025;
            for (int i = 1; i < nPoint; i++) {
                if (i <= nPoint / 2) {
                    yArrTree[i] = yArrTree[i - 1] - 0.009;
                } else {
                    yArrTree[i] = yArrTree[i - 1] + 0.009;
                }
            }
            yArrTree[nPoint] = yTree - 0.05;
            return yArrTree;
        }


//    public double[] yArrTree(int nPoint)
//    {
//        double[] yArrTree=new double[nPoint+1];
//        yArrTree[0]=yTree + 25;
//        for(int i=1;i<nPoint;i++)
//        {
//            if(i<=nPoint/2)
//            {
//                yArrTree[i]=yArrTree[i-1]-6;
//            }
//            else
//            {
//                yArrTree[i]=yArrTree[i-1]+6;
//            }
//        }
//        yArrTree[nPoint]=yTree-5;
//        return yArrTree;
//    }
    }
}
