package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.RobotMap;

public class Drive2903 {
  public static final int TICKS_PER_REV = 4096;
  public static final double WHEEL_CIRCUMFERENCE = Math.PI * 6;// In inches
  public static final double GEAR_RATIO = 10.714284;
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

    motorDriveFrontRight.setInverted(true);
    motorDriveBackRight.setInverted(true);

    left = new MotorControllerGroup(motorDriveFrontLeft, motorDriveBackLeft);
    right = new MotorControllerGroup(motorDriveFrontRight, motorDriveBackRight);
    diffDrive = new DifferentialDrive(left, right);
    diffDrive.setDeadband(0.05);

    turnOnBrakes(false);
  }

  public void turnOnBrakes(boolean breaksOn) {
    motorDriveFrontLeft.setIdleMode(breaksOn ? IdleMode.kBrake : IdleMode.kCoast);
    motorDriveFrontRight.setIdleMode(breaksOn ? IdleMode.kBrake : IdleMode.kCoast);
    motorDriveBackLeft.setIdleMode(breaksOn ? IdleMode.kBrake : IdleMode.kCoast);
    motorDriveBackRight.setIdleMode(breaksOn ? IdleMode.kBrake : IdleMode.kCoast);
  }

  public void arcadeDriveSeconds(double forward, double turn, double seconds) {
    double startTime = System.currentTimeMillis();

    while(System.currentTimeMillis() <= startTime + seconds * 1000) {
      diffDrive.arcadeDrive(forward, turn, false);
    }

    diffDrive.stopMotor();
  }

  // To go backwords give a negative speed
  public void arcadeDriveDistance(double forward, double turn, double distance) {
    double startPos = motorDriveFrontLeft.getEncoder().getPosition();

    while(ticksToInches(motorDriveFrontLeft.getEncoder().getPosition() - startPos) < Math.abs(distance)) {
      diffDrive.arcadeDrive(forward, turn);
    }

    diffDrive.stopMotor();
  }

  public static double ticksToInches(double rev) {
    double wheelRev = rev / GEAR_RATIO;
    double distance = wheelRev * WHEEL_CIRCUMFERENCE;
    return Math.abs(distance);
  }
}
