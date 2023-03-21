package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;

public class Claw2903 {
    public CANSparkMax motorClawOpener;
    
    public Claw2903() {
        motorClawOpener = new CANSparkMax(RobotMap.motorClawOpener, MotorType.kBrushless);
    }
}