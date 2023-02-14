package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class Arm2903 {
  // `1q
  public static final int distanceToTravelToTop = 10000;
  public static final double downSpeed = 1;
  public double bottomEncoder; 

  public CANSparkMax motorArmMain;

  public Arm2903() {
    motorArmMain = new CANSparkMax(RobotMap.motorArmMain, MotorType.kBrushless);
    
    motorArmMain.set(downSpeed);

    while(!getBottomLimit()) {
      // Wait for arm to go down
    }

    motorArmMain.set(0);
    bottomEncoder = motorArmMain.getEncoder().getPosition();
  }

  public void powerArm(double speed) {
    motorArmMain.set(speed);
  }

  public void powerArmSeconds(double speed, double seconds) {
    new Thread() {
      @Override
      public void run() {
        powerArm(speed);

        try {
          Thread.sleep((long) (seconds * 1000));
        } catch(InterruptedException exc) {}

        powerArm(0);
      }
    }.start();
  }

  public boolean getBottomLimit() {
    return true;
  }
}
