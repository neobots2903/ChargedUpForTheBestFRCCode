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
  public static final double speedUp = -1;
  public CANSparkMax motorArmExtend;
  public CANSparkMax motorArmRotate;

  public Arm2903() {
    motorArmExtend = new CANSparkMax(RobotMap.motorArmExtend, MotorType.kBrushless);
    motorArmRotate = new CANSparkMax(RobotMap.motorArmRotate, MotorType.kBrushless);
    
    new Thread() {
      @Override
      public void run() {
        while(true) {
          if(limitRotateBottom() || limitRotateTop()) {
            motorArmRotate.stopMotor();
          }
        }
      }
    }.start();
  }

  public void extendArm(double speed) {
    System.out.println(speed);
    motorArmRotate.set(speed);
  }

  public void extendArmTicks(double ticks) {
    
  }

  public void extendArmInches(int inches) {
    extendArmTicks(inches / Math.PI * GEAR_DIAMETER * TICKS_PER_REVOLUTIONS * ARM_EXTEND_GEAR_RATIO);
  }

  public void rotateArm(double speed) {
    if(!limitRotateBottom() && !limitRotateTop()) {
      motorArmRotate.set(speed);
      return;
    }

    if(limitRotateBottom()) {
      if(Math.signum(speed) == Math.signum(speedUp)) {
        motorArmRotate.set(speed);
      }
    }

    if(limitRotateTop()) {
      if(Math.signum(speed) == -Math.signum(speedUp)) {
        motorArmRotate.set(speed);
      }
    }
  }

  public void rotateArmDegrees(double degrees) {
    // setPosition(degrees / 360 * TICKS_PER_REVOLUTIONS * ARM_ROTATE_GEAR_RATIO);
  }

  public boolean limitRotateBottom() {
    return false;
  }

  public boolean limitRotateTop() {
    return false;
  }
}