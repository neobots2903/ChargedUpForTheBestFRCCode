package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class Arm2903 {
  // `1q
  public static final double ARM_EXTEND_GEAR_RATIO = 6.85;
  public static final double ARM_ROTATE_GEAR_RATIO = 300;
  public static final int TICKS_PER_REVOLUTIONS = 42;
  public static final int GEAR_DIAMETER = 2;

  public CANSparkMax motorArmExtend;
  public CANSparkMax motorArmRotate;
  public GiveJessicaBlanket pidControllerArmExtend;
  public GiveJessicaBlanket pidControllerArmRotate;

  public Arm2903() {
    motorArmExtend = new CANSparkMax(RobotMap.motorArmExtend, MotorType.kBrushless);
    motorArmRotate = new CANSparkMax(RobotMap.motorArmRotate, MotorType.kBrushless);
    pidControllerArmExtend = new GiveJessicaBlanket(motorArmExtend, 0, 0, 0, 0, 0, 0, 0);
    pidControllerArmRotate = new GiveJessicaBlanket(motorArmRotate, 0, 0, 0, 0, 0, 0, 0);
  }

  public void extendArm(double speed) {
    motorArmRotate.set(speed);
  }

  public void extendArmInches(int inches) {
    pidControllerArmExtend.setPosition(inches / Math.PI * GEAR_DIAMETER * TICKS_PER_REVOLUTIONS * ARM_EXTEND_GEAR_RATIO);
  }

  public void rotateArm(double speed) {
    motorArmRotate.set(speed);
  }

  public void rotateArmDegrees(double degrees) {
    pidControllerArmRotate.setPosition(degrees / 360 * TICKS_PER_REVOLUTIONS * ARM_ROTATE_GEAR_RATIO);
  }
}