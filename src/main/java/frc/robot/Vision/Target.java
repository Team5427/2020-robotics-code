package frc.robot.Vision;

import java.util.ArrayList;

import org.opencv.core.*;

public class Target {

    /**
     * The left and right pieces of retroreflective tape
     */
    /**
     * The average of both halves' centers
     */
    
  //if side diff or upright diff are too high, can use proportion to do this, then set a variable of too high to true, if its true then in robot while filtering dont include it and just continue

    Point[] points;

    private double topWidth;
    private Point center = new Point();
    private double widthRatio = 0;

    private Point topPoint = null;
    private Point bottomPoint = null;
    private Point leftPoint = null;
    private Point rightPoint = null;
    private double uprightDiff = 0;
    private double sideDiff = 0;
    private double proportion = 0;
    private double inverseProportion = 0;

    private Mat mat;
    
    public Target(Point[] pts, Mat mat) 
    {
        this.mat = mat;
        points = new Point[pts.length];
        for(int x = 0; x<pts.length;x++)
        {
            points[x] = pts[x];
        }

        Point topPoint = points[0];
        Point bottomPoint = points[0];
        Point rightPoint = points[0];
        Point leftPoint = points[0];
    
        for (Point p : points)
        {
            if(p.x < leftPoint.x)
                leftPoint = p;
            if(p.x > rightPoint.x)
                rightPoint = p;
            if(p.y > topPoint.y)
                topPoint = p;
            if(p.y < bottomPoint.y)
                bottomPoint = p;

          
        }

        center.y = (topPoint.y+bottomPoint.y)/2;
        center.x = (leftPoint.x + rightPoint.x)/2;

        uprightDiff  = Math.abs(topPoint.y-bottomPoint.y);
        sideDiff = Math.abs(leftPoint.x-rightPoint.x);

        proportion = uprightDiff/sideDiff;
        inverseProportion = sideDiff/inverseProportion;


        //TODO FILTER IF THERE ARE MULTIPLE TARGETS
    }
    public double getWidthRatio()
    {
        return widthRatio;
    }

    public double getUprightDiff()
    {
        return uprightDiff;
    }
    public double getSideDiff()
    {
        return sideDiff;
    }
    public Point getTopPoint()
    {
        return topPoint;
    }
    public Point getBottomPoint()
    {
        return bottomPoint;
    }
    public Point getRightPoint()
    {
        return rightPoint;
    }
    public Point getLeftPoint()
    {
        return leftPoint;
    }

    public double getDistanceFromCenter()
    {
        return center.x - (mat.width()/2);
    }
    public boolean isCentered()
    {
        return Math.abs(getDistanceFromCenter())<30;
    }
    public double getProportion()
    {
        return proportion;
    }
    public double getInverseProportion()
    {
        return proportion;
    }
   
    // public double getTapeDist()
    // {
    //     double diffX = right.topLeft.x - left.topLeft.x;
    //     double diffY = right.topLeft.y - left.topLeft.y;
    //     double pixDist = (right.topLeft.y != left.topLeft.y)? Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) : diffX;
    //     return pixDist;
    // }

    // public double getXOverZ() {
    //     return (center.x - GraphicsPanel.imageCenterX)/GraphicsPanel.focalLen;
    // }
    // public double getYOverZ() {
    //     return (center.y - GraphicsPanel.imageCenterY)/GraphicsPanel.focalLen;
    // }
    // public double getConstant3() {
    //     return getYOverZ()/getXOverZ();
    // }
    // public double getConstant4() {
    //     return 45; //inches, height of target - height of camera
    // }
    // public double solveForX() {
    //     return getConstant4()/getConstant3();
    // }
    // public double solveForZ() {
    //     return solveForX()/getXOverZ();
    // }
    // public double getHorAngle() {
    //     return Math.atan(solveForX());
    //}
    

}