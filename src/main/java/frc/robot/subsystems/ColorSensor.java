
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

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

//Documentation
//http://www.revrobotics.com/content/sw/color-sensor-v3/sdk/docs/javadoc/com/revrobotics/ColorSensorV3.html

public class ColorSensor extends SubsystemBase {

    public static char color;

    SpeedController colorMotor;

    ColorSensorV3 colorSensor;

    ColorMatch colorMatch;

    boolean red, green, blue, yellow = false;

    public ColorSensor(SpeedController colorMotor, ColorSensorV3 colorSensor, ColorMatch colorMatch) {
        this.colorMotor = colorMotor;
        this.colorSensor = colorSensor;
        this.colorMatch = colorMatch;
        // this.colorSensor.configureColorSensor();

    }

    public void getColor() {
        Color color = colorSensor.getColor();
        String colorString = "";
        final ColorMatchResult match = colorMatch.matchClosestColor(color);
        double rBlueTarget =Constants.kBlueTarget.red;
        double gBlueTarget =Constants.kBlueTarget.green;
        double bBlueTarget =Constants.kBlueTarget.blue;

        double rRedTarget  =Constants.kRedTarget.red;
        double gRedTarget =Constants.kRedTarget.green;
        double bRedTarget =Constants.kRedTarget.blue;

        double rGreenTarget=Constants.kGreenTarget.red;
        double gGreenTarget=Constants.kGreenTarget.green;
        double bGreenTarget=Constants.kGreenTarget.blue;

        double rYellowTarget=Constants.kYellowTarget.red;
        double gYellowTarget=Constants.kYellowTarget.green;
        double bYellowTarget=Constants.kYellowTarget.blue;

        
        if (match.color.blue <= (bBlueTarget +.05) && match.color.blue >= (bBlueTarget -.05) && match.color.red <= (rBlueTarget +.05) && match.color.red >= (rBlueTarget -.05) && match.color.green <= (gBlueTarget +.05) && match.color.green >= (gBlueTarget -.05)) {
            colorString = "Blue";
            blue = true;
          } else if  (match.color.blue <= bRedTarget +.05 && match.color.blue >= bRedTarget -.05 && match.color.red <= rRedTarget +.05 && match.color.red >= rRedTarget -.05 && match.color.green <= gRedTarget +.05 && match.color.green >= gRedTarget -.05) {
            colorString = "Red";
            red = true;
          } else if (match.color.blue <= bGreenTarget +.05 && match.color.blue >= bGreenTarget -.05 && match.color.red <= rGreenTarget +.05 && match.color.red >= rGreenTarget -.05 && match.color.green <= gGreenTarget +.05 && match.color.green >= gGreenTarget -.05) {
            colorString = "Green";
            green = true;
          } else if (match.color.blue <= bYellowTarget +.05 && match.color.blue >= bYellowTarget -.05 && match.color.red <= rYellowTarget +.05 && match.color.red >= rYellowTarget -.05 && match.color.green <= gYellowTarget +.05 && match.color.green >= gYellowTarget -.05) {
            colorString = "Yellow";
            yellow = true;
          } else {
            colorString = "Unknown";
            blue = red = green = yellow = false;
          }
          

        // SmartDashboard.putNumber("Red", color.red);
        // SmartDashboard.putNumber("Green", color.green);
        // SmartDashboard.putNumber("Blue", color.blue);
        SmartDashboard.putBoolean("Red", red);
        SmartDashboard.putBoolean("Yellow", yellow);
        SmartDashboard.putBoolean("Green", green);
        SmartDashboard.putBoolean("Blue", blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);


        SmartDashboard.putNumber("R", color.red);
        SmartDashboard.putNumber("G", color.green);
        SmartDashboard.putNumber("B", color.blue);
        

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
