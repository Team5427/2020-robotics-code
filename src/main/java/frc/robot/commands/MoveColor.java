package frc.robot.commands;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveColor extends CommandBase
{
    private double speed;

    public MoveColor(double speed)
    {
        this.speed = speed;
    }

    @Override
    public void initialize() {
        RobotContainer.getColorSensor().run(speed);
    }

    @Override
    public void execute() {
        RobotContainer.getColorSensor().run(speed);
    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.COLOR_BUTTON);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getColorSensor().stop();
    }

}