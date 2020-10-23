package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;

public class Motor extends WPI_VictorSPX
{
    private boolean reverse;
    private double bias;

    public Motor(int deviceNumber, boolean reverse, double bias) 
    {
        super(deviceNumber);
        this.reverse = reverse;
        this.bias = bias;
        super.setInverted(reverse);
    }

    @Override
    public void set(double speed) 
    {
        double biasedSpeed = speed * bias;
        super.set(biasedSpeed);
    }

}