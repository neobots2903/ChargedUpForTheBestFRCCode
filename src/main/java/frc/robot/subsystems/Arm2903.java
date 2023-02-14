package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class Arm2903 {
  public CANSparkMax motorArmMain;

  public Arm2903() {
    motorArmMain = new CANSparkMax(RobotMap.motorArmMain, MotorType.kBrushless);
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
}
