package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.RobotMap;

public class Drive2903 {
  public static final long SLEEP_TIME_MILLIS = 5;
  public static final double AMOUNT = 0.01;
  public static final double DRIVE_REDUCTION = 8.45;
  public static final double DRIVE_WHEEL_SIZE_INCHES = 6;
  public static final double TICKS_PER_REVOLUTION = 42;
  public double targetForwardSpeed = 0;
  public double targetRotateSpeed = 0;
  public double forwardSpeed = 0;
  public double rotateSpeed = 0;
  public CANSparkMax motorDriveFrontLeft;
  public CANSparkMax motorDriveFrontRight;
  public CANSparkMax motorDriveBackLeft;
  public CANSparkMax motorDriveBackRight;
  public MotorControllerGroup left;
  public MotorControllerGroup right;
  public DifferentialDrive diffDrive;

  public Drive2903() {
    motorDriveFrontLeft = new CANSparkMax(RobotMap.motorDriveFrontLeft, MotorType.kBrushless);
    motorDriveFrontRight = new CANSparkMax(RobotMap.motorDriveFrontRight, MotorType.kBrushless);
    motorDriveBackLeft = new CANSparkMax(RobotMap.motorDriveBackLeft, MotorType.kBrushless);
    motorDriveBackRight = new CANSparkMax(RobotMap.motorDriveBackRight, MotorType.kBrushless);
    left = new MotorControllerGroup(motorDriveFrontLeft, motorDriveBackLeft);
    right = new MotorControllerGroup(motorDriveFrontRight, motorDriveBackRight);

    diffDrive = new DifferentialDrive(left, right);
    diffDrive.setDeadband(0.05);

    motorDriveBackRight.setInverted(true);
    
    turnOnBrakes(true);

    new Thread() {
      @Override
      public void run() {
        while(true) {
          if(forwardSpeed < targetForwardSpeed) forwardSpeed += AMOUNT;
          if(forwardSpeed > targetForwardSpeed) forwardSpeed -= AMOUNT;
          if(rotateSpeed < targetRotateSpeed) rotateSpeed += AMOUNT;
          if(rotateSpeed > targetRotateSpeed) rotateSpeed -= AMOUNT;

          diffDrive.arcadeDrive(forwardSpeed, rotateSpeed);

          try {
            Thread.sleep(SLEEP_TIME_MILLIS);
          } catch(InterruptedException exc) {}
        }
      }
    }.start();
  }

  public void turnOnBrakes(boolean breaksOn) {
    motorDriveFrontLeft.setIdleMode(breaksOn ? IdleMode.kBrake : IdleMode.kCoast);
    motorDriveFrontRight.setIdleMode(breaksOn ? IdleMode.kBrake : IdleMode.kCoast);
    motorDriveBackLeft.setIdleMode(breaksOn ? IdleMode.kBrake : IdleMode.kCoast);
    motorDriveBackRight.setIdleMode(breaksOn ? IdleMode.kBrake : IdleMode.kCoast);
  }

  public void trapezoidalDrive(double forwardSpeed, double rotateSpeed) {
    targetForwardSpeed = forwardSpeed;
    targetRotateSpeed = rotateSpeed;
  }
}
