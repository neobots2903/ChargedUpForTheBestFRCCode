package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class Arm2903 {
  // `1q
  public static final int distanceToTravelToTop = 10000;
  public static final double downSpeed = 1;
  public double bottomEncoder;

  public CANSparkMax motorArmExtend;
  public CANSparkMax motorArmRotate;

  public Arm2903() {
    motorArmExtend = new CANSparkMax(RobotMap.motorArmExtend, MotorType.kBrushless);
    motorArmRotate = new CANSparkMax(RobotMap.motorArmRotate, MotorType.kBrushless);
    
    motorArmRotate.set(downSpeed);

    while(!getBottomLimit()) {
      // Wait for arm to go down
    }

    motorArmRotate.set(0);
    bottomEncoder = motorArmRotate.getEncoder().getPosition();
    
    new Thread() {
      @Override
      public void run() {
        if(getBottomLimit()) {
          extendArm(0);
        }
      }
    }.start();
  }

  public void extendArm(double speed) {
    motorArmExtend.set(speed);
  }

  public void extendArmSeconds(double speed, double seconds) {
    new Thread() {
      @Override
      public void run() {
        extendArm(speed);

        try {
          Thread.sleep((long) (seconds * 1000));
        } catch(InterruptedException exc) {}

        extendArm(0);
      }
    }.start();

    try {
      Thread.sleep((long) (seconds * 1000));
    } catch(InterruptedException exc) {}

    extendArm(0);
  }

  public void rotateArm(double speed) {
    motorArmRotate.set(speed);
  }

  public void rotateArmSeconds(double speed, double seconds) {
    new Thread() {
      @Override
      public void run() {
        rotateArm(speed);

        try {
          Thread.sleep((long) (seconds * 1000));
        } catch(InterruptedException exc) {}

        rotateArm(0);
      }
    }.start();

    try {
      Thread.sleep((long) (seconds * 1000));
    } catch(InterruptedException exc) {}

    rotateArm(0);
  }

  public boolean getBottomLimit() {
    return true;
  }
}
