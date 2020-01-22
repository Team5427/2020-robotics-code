
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
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;

//Documentation
//http://www.revrobotics.com/content/sw/color-sensor-v3/sdk/docs/javadoc/com/revrobotics/ColorSensorV3.html


public class ColorSensor extends SubsystemBase
{
    public static char color;
    
    SpeedController colorMotor;

    ColorSensorV3 colorSensor;
    

    
    public ColorSensor(SpeedController colorMotor, ColorSensorV3 colorSensor)
    {
        this.colorMotor = colorMotor;
        this.colorSensor = colorSensor;
       // this.colorSensor.configureColorSensor();

    }

    public void getColor()
    {
        Color color = colorSensor.getColor();
        SmartDashboard.putNumber("R", color.red);
        SmartDashboard.putNumber("G", color.green);
        SmartDashboard.putNumber("B", color.blue);
        

        double k = 1 - Math.max(Math.max(color.red, color.green), color.blue);
        double c = (1 - color.red - k) / (1 - k);
        double m = (1 - color.green - k ) / (1 - k);
        double y = (1 - color.blue - k) / (1- k);

        String colorwheel = "null";

        if(c > 0.95 && c < 1){
            if(y > 0.95 && y < 1){
                colorwheel = "green";
            }
            else{
                colorwheel = "blue";
            }
        }
        if(y > 0.95 && y < 1){
            if(m > 0.95 && m < 1){
                colorwheel = "red";
            }
            else{
                colorwheel = "yellow";
            }
        }
        SmartDashboard.putString("colorwheel",colorwheel);
        SmartDashboard.putNumber("k", k);
        SmartDashboard.putNumber("c", c);
        SmartDashboard.putNumber("y", y);
        SmartDashboard.putNumber("m", m);


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
