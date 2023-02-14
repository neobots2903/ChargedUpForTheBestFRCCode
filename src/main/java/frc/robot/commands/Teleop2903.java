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

  @Override
  public void execute() {
    Robot.limelight2903.getTA();
    Robot.limelight2903.getTV();
    Robot.limelight2903.getTX();
    Robot.limelight2903.getTY();

    Robot.telemacatrate();
    Robot.drive2903.arcadeDrive(-Robot.driveJoy.getY(), -Robot.driveJoy.getX(), true);
    
    if(!Robot.claw2903.clawIsOpening) {
      if(Robot.driveJoy.getRawButton(buttonRB)) {
        Robot.claw2903.cubeMode(true);
      }

      if(Robot.driveJoy.getRawButton(buttonLB)) {
        Robot.claw2903.cubeMode(false);
      }
    }

    if(Robot.driveJoy.getRawButton(buttonA)) {
      Robot.claw2903.suck(Robot.claw2903.sucked ? false : true);
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