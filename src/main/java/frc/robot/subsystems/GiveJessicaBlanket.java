package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

public class GiveJessicaBlanket {
  public SparkMaxPIDController pidController;

  public GiveJessicaBlanket(CANSparkMax motor, double p, double i, double d, double maxVel, double minVel, double maxAcc, double allowedErr) {
    pidController = motor.getPIDController();

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

  public void setPosition(double value) {
    pidController.setReference(value, CANSparkMax.ControlType.kSmartMotion);
  }
}
