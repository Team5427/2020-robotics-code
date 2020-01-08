
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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;

//Documentation
//http://www.revrobotics.com/content/sw/color-sensor-v3/sdk/docs/javadoc/com/revrobotics/ColorSensorV3.html

public class ControlPanel extends SubsystemBase
{
    SpeedController colorMotor;

    ColorSensorV3 colorSensor;

    public ControlPanel(SpeedController colorMotor, ColorSensorV3 colorSensor)
    {
        this.colorMotor = colorMotor;
        this.colorSensor = colorSensor;
        this.colorSensor.configureColorSensor(0,0,0);

    }

    public Color getColor()
    {
        return colorSensor.getColor();
    }

    public void run(double speed)
    {
        colorMotor.set(speed);
    }

    @Override
    public void periodic()
    {}
    
}
