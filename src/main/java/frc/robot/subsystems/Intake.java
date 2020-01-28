package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private SpeedController intake;

    //add a proximity sensor, and parameter

    public Intake(SpeedController intake) {
    this.intake = intake;
}
    public void moveIntake(double Intake){
        intake.set(Intake);
    }
    public void stop()
    {
       intake.stopMotor();
    }

    //add method to get proximity input (true or false).
}