// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Teleop2903 extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  int leftY = 1;
  int rightX = 0;
  int lt = 2;
  int rt = 3;
  int buttonA = 1;
  int buttonX = 3;
  int buttonY = 4;
  int buttonB = 2;
  int buttonRB = 6;
  int buttonLB = 5;
  double error = 0.5;
  boolean shootPressedLocked = false;

  public Teleop2903() {

  }

  @Override
  public void initialize() {
    Robot.shoot2903.initPivot();
  }

  @Override
  public void execute() {
    // Robot.shoot2903.checkLimits();
    boolean indexerPressed = Robot.opJoy.getRawButton(buttonRB); // index brings balls in
    boolean intakePressed = Robot.opJoy.getRawButton(buttonRB); // wheels brings ball in
    boolean intakeRevPressed = Robot.opJoy.getRawButton(buttonLB);// wheels bring ball out
    boolean intakeInPressed = Robot.opJoy.getRawButton(buttonY); // it comes in
    boolean intakeOutPressed = Robot.opJoy.getRawButton(buttonB); // it goes out
    boolean shootPressed = Robot.opJoy.getRawButton(buttonX); // Shoots the balls
    boolean autoAimPressed = Robot.driveJoy.getRawButton(buttonB); // auto shoot/aim and drive
    // boolean indexRevPressed = Robot.opJoy.getRawButton(buttonA); //index brings
    // balls out
    double upPress = Robot.opJoy.getRawAxis(leftY); // pos shooter
    Robot.shoot2903.limits();
    Robot.limelight2903.getTA();
    Robot.limelight2903.getTV();
    Robot.limelight2903.getTX();
    Robot.limelight2903.getTY();

    // System.out.println("Shoot angle DEGREES: " + pivotDegrees);
    if (shootPressed) {
      if (!shootPressedLocked) {
        Robot.shoot2903.resetShoot();
        shootPressedLocked = true;
      }
      Robot.shoot2903.teleShoot(3000, 1);
    } else {
      shootPressedLocked = false;
      if (intakeRevPressed) {
        Robot.intake2903.indexer(-.50);
        Robot.shoot2903.shoot(0.5);
      } else {
        if (intakePressed) {
          Robot.intake2903.indexer(.50);
          Robot.shoot2903.shoot(-.5);
        } else {
          Robot.intake2903.indexer(0);
          Robot.shoot2903.shoot(0);
        }
      }
    }

    if (autoAimPressed) {
      if (Robot.limelight2903.getTV()) {
        if (Robot.limelight2903.getTX() > error) {
          Robot.drive2903.arcadeDrive(0, .07);
        } else if (Robot.limelight2903.getTX() < -error) {
          Robot.drive2903.arcadeDrive(0, -.07);
        } else {
          Robot.drive2903.arcadeDrive(0, 0);
        }
      }
    } else {
      double driveBackPower = Robot.driveJoy.getRawAxis(lt);
      double driveForwardPower = Robot.driveJoy.getRawAxis(rt);
      double turnPower = Robot.driveJoy.getRawAxis(rightX);
      Robot.drive2903.arcadeDrive(driveForwardPower - driveBackPower, turnPower);
    }

    // if (indexerPressed){
    // Robot.intake2903.indexer(.50);
    // Robot.shoot2903.shoot(-.10);
    // } else if (indexRevPressed) {
    // Robot.intake2903.indexer(-.50);
    // } else {
    // Robot.intake2903.indexer(0);
    // }

    if (intakeInPressed) {
      Robot.intake2903.intakeIn(0.75);
    } else if (intakeOutPressed) {
      Robot.intake2903.intakeOut(0.75);
    } else {
      Robot.intake2903.intakeIn(0);
    }

    if (intakePressed) {
      Robot.intake2903.intake(.75);
    } else if (intakeRevPressed) {
      Robot.intake2903.intakeRev(.75);
    } else {
      Robot.intake2903.intake(0);
    }

    double climbUp = Robot.opJoy.getRawAxis(rt);
    double climbDown = Robot.opJoy.getRawAxis(lt);
    // System.out.println("Climb Power: " + (climbUp - climbDown));
    Robot.climb2903.setPower(climbUp - climbDown);
    if (upPress < -.2) {
      Robot.shoot2903.setAngle(Robot.shoot2903.getTargetBoom() + 1);
    } else if (upPress > .2) {
      Robot.shoot2903.setAngle(Robot.shoot2903.getTargetBoom() - 1);
    }
  }

  @Override
  public void end(boolean interrupted) {
    Robot.drive2903.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
