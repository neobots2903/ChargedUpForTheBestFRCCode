package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake2903 {
    public CANSparkMax upperIntake;
    public VictorSPX otherIntake;
    public CANSparkMax indexer;
    // DigitalInput otherIntakeOut;
    // DigitalInput otherIntakeIn;
    public AnalogInput intakePot;

    public Intake2903() {
        upperIntake = new CANSparkMax(RobotMap.upperIntake, MotorType.kBrushed);
        otherIntake = new VictorSPX(RobotMap.otherIntake);
        indexer = new CANSparkMax(RobotMap.indexer, MotorType.kBrushed);
        // otherIntakeOut = new DigitalInput(RobotMap.otherIntakeOut);
        // otherIntakeIn = new DigitalInput(RobotMap.otherIntakeIn);
        intakePot = new AnalogInput(RobotMap.intakePot);
    }

    public void intake(double speed) {
        upperIntake.set(-speed);
    }

    public void intakeRev(double speed) {
        upperIntake.set(speed);
    }

    public void intakeIn(double speed) {
        SmartDashboard.putNumber("intake pos", intakePot.getValue());

        if (intakePot.getValue() < 1600) {
            otherIntake.set(ControlMode.PercentOutput, speed);
        } else {
            otherIntake.set(ControlMode.PercentOutput, 0);
        }
    }

    public void intakeOut(double speed) {
        SmartDashboard.putNumber("intake pos", intakePot.getValue());
        if (intakePot.getValue() > 200) {
            otherIntake.set(ControlMode.PercentOutput, -speed);
        } else {
            otherIntake.set(ControlMode.PercentOutput, 0);
        }
    }

    public void indexer(double speed) {
        indexer.set(speed);
    }
}
