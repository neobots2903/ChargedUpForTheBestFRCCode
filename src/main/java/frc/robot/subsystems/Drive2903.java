// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Drive2903 {
  public static final int TICKS_PER_REV = 4096;
  public static final double WHEEL_CIRCUMFERENCE = Math.PI * 6; // in inches
  public static final double GEAR_RATIO = 10.714284;
  public CANSparkMax frontLeftMotor;
  public CANSparkMax frontRightMotor;
  public CANSparkMax backLeftMotor;
  public CANSparkMax backRightMotor;

  /** Creates a new ExampleSubsystem. */
  public Drive2903() {
    frontLeftMotor = new CANSparkMax(RobotMap.frontLeftMotor, MotorType.kBrushless);
    frontRightMotor = new CANSparkMax(RobotMap.frontRightMotor, MotorType.kBrushless);
    backLeftMotor = new CANSparkMax(RobotMap.backLeftMotor, MotorType.kBrushless);
    backRightMotor = new CANSparkMax(RobotMap.backRightMotor, MotorType.kBrushless);
  }

  public void tankDrive(double left, double right) {
    frontLeftMotor.set(left);
    frontRightMotor.set(-right);
    backLeftMotor.set(left);
    backRightMotor.set(-right);
  }

  public void arcadeDrive(double forward, double turn) {
    frontLeftMotor.set(forward + turn);
    frontRightMotor.set(-forward + turn);
    backLeftMotor.set(forward + turn);
    backRightMotor.set(-forward + turn);
  }

  // to go backwords give a negative speed
  public void distanceDrive(double distance, double speed) {
    double startPos = frontLeftMotor.getEncoder().getPosition();

    while (ticksToInches(frontLeftMotor.getEncoder().getPosition() - startPos) < Math.abs(distance)) {
      SmartDashboard.putNumber("distance (in)", ticksToInches(frontLeftMotor.getEncoder().getPosition() - startPos));
      arcadeDrive(speed, 0);
    }

    arcadeDrive(0, 0);
  }

  public static double ticksToInches(double rev) {
    double wheelRev = rev / GEAR_RATIO;
    double distance = wheelRev * WHEEL_CIRCUMFERENCE;
    return Math.abs(distance);
  }
}