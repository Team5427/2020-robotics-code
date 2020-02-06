
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
import edu.wpi.first.wpilibj.command.Subsystem;
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

public class ColorSensor extends Subsystem 
{

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

    boolean red, green, blue, yellow = false;
    
    int rawRed, rawGreen, rawBlue = 0;
    double colorRed, colorGreen, colorBlue = 0;

    public ColorSensor(SpeedController colorMotor, ColorSensorV3 colorSensor) {
        this.colorMotor = colorMotor;
        this.colorSensor = colorSensor;
    }

    public void run(double speed)
    {
      colorMotor.set(speed);
    }

    public void stop()
    {
      colorMotor.stopMotor();
    }

    public char getColor() 
    {
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
        
        SmartDashboard.putBoolean("Red", red);
        SmartDashboard.putBoolean("Yellow", yellow);
        SmartDashboard.putBoolean("Green", green);
        SmartDashboard.putBoolean("Blue", blue);


        SmartDashboard.putNumber("R", colorRed);
        SmartDashboard.putNumber("G", colorGreen);
        SmartDashboard.putNumber("B", colorBlue);

        if(red)
          return 'R';
        else if(green)
          return 'G';
        else if(yellow)
          return 'Y';
        else if(blue)
          return 'B';
        else 
          return '0';
    }

    public void getProximity()
    {
        int proximity = colorSensor.getProximity();
        SmartDashboard.putNumber("Proximity", proximity);
    }

    @Override
    public void periodic()
    {}

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub 
  }
    
}
