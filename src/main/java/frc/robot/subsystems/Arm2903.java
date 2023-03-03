package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class Arm2903 {
  // `1q
  public static final double ARM_EXTEND_GEAR_RATIO = 6.85;
  public static final double ARM_ROTATE_GEAR_RATIO = 60;
  public static final int TICKS_PER_REVOLUTIONS = 42;
  public static final int GEAR_DIAMETER = 2;
  public static final int distanceToTravelToTop = 10000;
  public static final double downSpeed = 1;
  public double bottomEncoder;

  public CANSparkMax motorArmExtend;
  public CANSparkMax motorArmRotate;

  public Arm2903() {
    motorArmExtend = new CANSparkMax(RobotMap.motorArmExtend, MotorType.kBrushless);
    motorArmRotate = new CANSparkMax(RobotMap.motorArmRotate, MotorType.kBrushless);
    GiveJessicaBlanket.initSmartMotion(motorArmExtend, 0, 0, 0, 0, 0, 0, 0);
    GiveJessicaBlanket.initSmartMotion(motorArmRotate, 0, 0, 0, 0, 0, 0, 0);
  }

  public void findBottomEncoder() {
    motorArmRotate.set(downSpeed);

    while(!getBottomLimit()) {
      // Wait for arm to go down
    }

    motorArmRotate.set(0);
    bottomEncoder = motorArmRotate.getEncoder().getPosition();
  }

  public void extendArm(double speed) {
    motorArmExtend.set(speed);
  }

  public void extendArmInches(int inches) {
    GiveJessicaBlanket.setPosition(motorArmExtend, inches / Math.PI * GEAR_DIAMETER * TICKS_PER_REVOLUTIONS * ARM_EXTEND_GEAR_RATIO);
  }

  public void rotateArm(double speed) {
    motorArmRotate.set(speed);
  }

  public void rotateArmDegrees(int degrees) {
    GiveJessicaBlanket.setPosition(motorArmRotate, degrees / 360 * TICKS_PER_REVOLUTIONS * ARM_ROTATE_GEAR_RATIO);
  }

  public boolean getBottomLimit() {
    return false;
  }
}
