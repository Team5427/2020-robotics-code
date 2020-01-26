
// Put methods for controlling this subsystem
// here. Call these from Commands.
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.util.Color;

import javax.lang.model.util.ElementScanner6;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

//Documentation
//http://www.revrobotics.com/content/sw/color-sensor-v3/sdk/docs/javadoc/com/revrobotics/ColorSensorV3.html

public class ColorSensor extends SubsystemBase {

    public static char color;

    double rBlueTarget = Constants.kBlueTarget.red;
    double gBlueTarget = Constants.kBlueTarget.green;
    double bBlueTarget = Constants.kBlueTarget.blue;

    double rRedTarget = Constants.kRedTarget.red;
    double gRedTarget = Constants.kRedTarget.green;      
    double bRedTarget = Constants.kRedTarget.blue;

    double rGreenTarget = Constants.kGreenTarget.red;
    double gGreenTarget = Constants.kGreenTarget.green;
    double bGreenTarget = Constants.kGreenTarget.blue;

    double rYellowTarget = Constants.kYellowTarget.red;
    double gYellowTarget = Constants.kYellowTarget.green;
    double bYellowTarget = Constants.kYellowTarget.blue;

    SpeedController colorMotor;

    ColorSensorV3 colorSensor;

    ColorMatch colorMatch;

    boolean red, green, blue, yellow = false;
    
    int rawRed, rawGreen, rawBlue = 0;
    double colorRed, colorGreen, colorBlue = 0;

    public ColorSensor(SpeedController colorMotor, ColorSensorV3 colorSensor, ColorMatch colorMatch) {
        this.colorMotor = colorMotor;
        this.colorSensor = colorSensor;
        this.colorMatch = colorMatch;
        // this.colorSensor.configureColorSensor();

    }

    public void getColor() {
        rawRed = colorSensor.getRed();
        rawGreen = colorSensor.getGreen();
        rawBlue = colorSensor.getBlue();
        int total = rawRed + rawGreen + rawBlue;

        colorRed = rawRed/(double)total;
        colorGreen = rawGreen/(double)total;
        colorBlue = rawBlue/(double)total;

        if(Math.abs(colorRed - rRedTarget) <= Constants.COLOR_THRESHOLD && Math.abs(colorGreen - gRedTarget) <= Constants.COLOR_THRESHOLD && Math.abs(colorBlue - bRedTarget) <= Constants.COLOR_THRESHOLD)
        {
          red = true;
          green = false;
          blue = false;
          yellow = false;
          System.out.println("red");
        }
        else if(Math.abs(colorRed - rBlueTarget) <= Constants.COLOR_THRESHOLD && Math.abs(colorGreen - gBlueTarget) <= Constants.COLOR_THRESHOLD && Math.abs(colorBlue - bBlueTarget) <= Constants.COLOR_THRESHOLD)
        {
          blue = true;
          green = false;
          red = false;
          yellow = false;
          System.out.println("blue");
        }
        else if(Math.abs(colorRed - rGreenTarget) <= Constants.COLOR_THRESHOLD && Math.abs(colorGreen - gGreenTarget) <= Constants.COLOR_THRESHOLD && Math.abs(colorBlue - bGreenTarget) <= Constants.COLOR_THRESHOLD)
        {
          green = true;
          blue = false;
          red = false;
          yellow = false;
          System.out.println("green");

        }
        else if(Math.abs(colorRed - rYellowTarget) <= Constants.COLOR_THRESHOLD && Math.abs(colorGreen - gYellowTarget) <= Constants.COLOR_THRESHOLD && Math.abs(colorBlue - bYellowTarget) <= Constants.COLOR_THRESHOLD)
        {
          yellow = true;
          green = false;
          blue = false;
          red = false;
          System.out.println("yellow");

        }
        else 
        {
          red = blue = green = yellow = false;
          System.out.println("none");
        }
        
        // if (match.color.blue <= (bBlueTarget + Constants.COLOR_THRESHOLD) && match.color.blue >= (bBlueTarget - Constants.COLOR_THRESHOLD) && match.color.red <= (rBlueTarget + Constants.COLOR_THRESHOLD) && match.color.red >= (rBlueTarget - Constants.COLOR_THRESHOLD) && match.color.green <= (gBlueTarget + Constants.COLOR_THRESHOLD) && match.color.green >= (gBlueTarget -Constants.COLOR_THRESHOLD)) {
        //     colorString = "Blue";
        //     blue = true;
        //   } else if  (match.color.blue <= bRedTarget + Constants.COLOR_THRESHOLD && match.color.blue >= bRedTarget - Constants.COLOR_THRESHOLD && match.color.red <= rRedTarget + Constants.COLOR_THRESHOLD && match.color.red >= rRedTarget - Constants.COLOR_THRESHOLD && match.color.green <= gRedTarget + Constants.COLOR_THRESHOLD && match.color.green >= gRedTarget - Constants.COLOR_THRESHOLD) {
        //     colorString = "Red";
        //     red = true;
        //   } else if (match.color.blue <= bGreenTarget + Constants.COLOR_THRESHOLD && match.color.blue >= bGreenTarget -Constants.COLOR_THRESHOLD && match.color.red <= rGreenTarget +Constants.COLOR_THRESHOLD && match.color.red >= rGreenTarget - Constants.COLOR_THRESHOLD && match.color.green <= gGreenTarget + Constants.COLOR_THRESHOLD && match.color.green >= gGreenTarget - Constants.COLOR_THRESHOLD) {
        //     colorString = "Green";
        //     green = true;
        //   } else if (match.color.blue <= bYellowTarget + Constants.COLOR_THRESHOLD && match.color.blue >= bYellowTarget - Constants.COLOR_THRESHOLD && match.color.red <= rYellowTarget + Constants.COLOR_THRESHOLD && match.color.red >= rYellowTarget - Constants.COLOR_THRESHOLD && match.color.green <= gYellowTarget + Constants.COLOR_THRESHOLD && match.color.green >= gYellowTarget - Constants.COLOR_THRESHOLD) {
        //     colorString = "Yellow";
        //     yellow = true;
        //   } else {
        //     colorString = "Unknown";
        //     blue = red = green = yellow = false;
        //   }
          

        // SmartDashboard.putNumber("Red", color.red);
        // SmartDashboard.putNumber("Green", color.green);
        // SmartDashboard.putNumber("Blue", color.blue);
        SmartDashboard.putBoolean("Red", red);
        SmartDashboard.putBoolean("Yellow", yellow);
        SmartDashboard.putBoolean("Green", green);
        SmartDashboard.putBoolean("Blue", blue);


        SmartDashboard.putNumber("R", colorRed);
        SmartDashboard.putNumber("G", colorGreen);
        SmartDashboard.putNumber("B", colorBlue);
        

        // double k = 1 - Math.max(Math.max(color.red, color.green), color.blue);
        // double c = (1 - color.red - k) / (1 - k);
        // double m = (1 - color.green - k ) / (1 - k);
        // double y = (1 - color.blue - k) / (1- k);

        // String colorwheel = "null";

        // if(c > 0.95 && c < 1){
        //     if(y > 0.95 && y < 1){
        //         colorwheel = "green";
        //     }
        //     else{
        //         colorwheel = "blue";
        //     }
        // }
        // if(y > 0.95 && y < 1){
        //     if(m > 0.95 && m < 1){
        //         colorwheel = "red";
        //     }
        //     else{
        //         colorwheel = "yellow";
        //     }
        // }
        // SmartDashboard.putString("colorwheel",colorwheel);
        // SmartDashboard.putNumber("k", k);
        // SmartDashboard.putNumber("c", c);
        // SmartDashboard.putNumber("y", y);
        // SmartDashboard.putNumber("m", m);


    }

    public void getProximity()
    {
        int proximity = colorSensor.getProximity();
        SmartDashboard.putNumber("Proximity", proximity);
    }

    @Override
    public void periodic()
    {}
    
}
