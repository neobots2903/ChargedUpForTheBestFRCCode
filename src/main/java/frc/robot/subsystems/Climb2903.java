package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class Climb2903 {
    public CANSparkMax climb;

    public Climb2903() {
        climb = new CANSparkMax(RobotMap.climb, MotorType.kBrushed);
    }

    public void setPower(double speed) {
        climb.set(speed);
    }
}
