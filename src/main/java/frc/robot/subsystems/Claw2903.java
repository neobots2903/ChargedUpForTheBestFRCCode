package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotMap;

public class Claw2903 {
    public CANSparkMax motorClawOpener;

    public Claw2903() {
        motorClawOpener = new CANSparkMax(RobotMap.motorClawOpener, MotorType.kBrushless);

        new Thread() {
            @Override
            public void run() {
                if(!clawActuatorInRange()) {
                    motorClawOpener.set(0);
                }
            }
        }.start();
    }

    public boolean clawActuatorInRange() {
        // double EXTENDED_ENCODER_POSITION = 100;
        // double position = motorClawOpener.getEncoder().getPosition();
        // return position > startPosition && position < startPosition + EXTENDED_ENCODER_POSITION;

        double a = 1;
        return clawActuatorDistance() <= a || clawActuatorDistance() >= 12 - a;
    }

    public double clawActuatorDistance() {
        return 6.0;
        // return new Encoder(0, 0, false, Encoder.EncodingType.k1X).getDistance();
    }
}