package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TimeDelay extends CommandBase {
  private double time;
  private double start;
  
  public TimeDelay(double seconds) {
    this.time = seconds;
  }

  @Override
    public void initialize() {
       this.start = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
      //if time difference is greater than set time, finish
      return (Timer.getFPGATimestamp() - start) > this.time;
    }

    //not needed methods, but required
    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}
}