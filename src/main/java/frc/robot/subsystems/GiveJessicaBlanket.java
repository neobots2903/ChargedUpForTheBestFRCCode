package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

public class GiveJessicaBlanket {
  public static void initSmartMotion(CANSparkMax motor, double p, double i, double d, double maxVel, double minVel, double maxAcc, double allowedErr) {
    SparkMaxPIDController pidController = motor.getPIDController();

    pidController.setP(p);
    pidController.setI(i);
    pidController.setD(d);
    pidController.setIZone(0);
    pidController.setFF(0);
    pidController.setOutputRange(-1, 1);

    pidController.setSmartMotionMaxVelocity(maxVel, 0);
    pidController.setSmartMotionMinOutputVelocity(minVel, 0);
    pidController.setSmartMotionMaxAccel(maxAcc, 0);
    pidController.setSmartMotionAllowedClosedLoopError(allowedErr, 0);
  }

  public static void setPosition(CANSparkMax motor, double value) {
    motor.getPIDController().setReference(value, CANSparkMax.ControlType.kSmartMotion);
  }

  public static boolean blanket() {
    boolean yes = true;
    return yes;
  }
}
