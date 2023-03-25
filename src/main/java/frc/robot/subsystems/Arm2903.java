package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
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
  public DigitalInput limitRotateBottom;
  public DigitalInput limitRotateTop;

  public Arm2903() {
    motorArmExtend = new CANSparkMax(RobotMap.motorArmExtend, MotorType.kBrushless);
    motorArmRotate = new CANSparkMax(RobotMap.motorArmRotate, MotorType.kBrushless);

    limitRotateBottom = new DigitalInput(0);
    limitRotateTop = new DigitalInput(1);
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
    String message = speed + "";

    if((limitRotateBottomOpen() && limitRotateTopOpen()) || Math.abs(speed) < 0.05) {
      if(Math.abs(speed) < 0.05) motorArmRotate.stopMotor(); else motorArmRotate.set(speed);
      System.out.println(message);
      return;
    }

    if(limitRotateBottomOpen() && Math.signum(speed) == Math.signum(speedUp)) {
      motorArmRotate.set(speed);
      System.out.println(message);
    }

    if(limitRotateTopOpen() && Math.signum(speed) == -Math.signum(speedUp)) {
      motorArmRotate.set(speed);
      System.out.println(message);
    }
  }

  public void rotateArmDegrees(double degrees) {
    // setPosition(degrees / 360 * TICKS_PER_REVOLUTIONS * ARM_ROTATE_GEAR_RATIO);
  }

  public boolean limitRotateBottomOpen() {
    return limitRotateBottom.get();
  }

  public boolean limitRotateTopOpen() {
    return limitRotateTop.get();
  }
}