package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;

public class Claw2903 {
    public CANSparkMax motorClawOpener;
    public TalonSRX motorClawSucker;
    
    public Claw2903() {
        //motorClawOpener = new CANSparkMax(RobotMap.motorClawOpener, MotorType.kBrushless);
        motorClawSucker = new TalonSRX(RobotMap.motorClawSucker);
    }

    public void suck(double speed) {System.out.println(speed);
        //motorClawSucker.set(TalonSRXControlMode.PercentOutput, speed);
    }
}