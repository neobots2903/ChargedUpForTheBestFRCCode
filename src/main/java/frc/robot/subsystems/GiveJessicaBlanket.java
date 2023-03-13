package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

public class GiveJessicaBlanket {
  public CANSparkMax motor;
  public SparkMaxPIDController pidController;

  public GiveJessicaBlanket(CANSparkMax motor, double p, double i, double d, double maxVel, double minVel, double maxAcc, double allowedErr) {
    this.motor = motor;
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

  public void setPosition(double position) {
    //pidController.setReference(value, CANSparkMax.ControlType.kSmartMotion);
    new Thread() {
      public void run() {
        while(true) {
          double distance = Math.abs(position - motor.getEncoder().getPosition());
          if(distance < 10) return;
          double speed = distance / 10;
          if(speed > 1) speed = 1;
          //motor.set(position > motor.getEncoder().getPosition() ? -speed : speed);
          System.out.println("Distance: " + distance + " Speed" + (position > motor.getEncoder().getPosition() ? -speed : speed));
        }
      }
    }.start();
  }
}
