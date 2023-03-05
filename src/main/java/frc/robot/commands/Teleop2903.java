// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.JoystickMap;
import frc.robot.Robot;
import frc.robot.UsingMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Teleop2903 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  @Override
  public void execute() {
    Robot.telemacatrate();

    if(UsingMap.usingLimelight) {
      Robot.limelight2903.getArea();
      Robot.limelight2903.seesTarget();
      Robot.limelight2903.getXAxis();
      Robot.limelight2903.getYAxis();
    }

    if(UsingMap.usingDrive) {
      Robot.drive2903.arcadeDrive(-Robot.driveJoy.getY(), -Robot.driveJoy.getX(), true);

      if(Robot.driveJoy.getRawButton(JoystickMap.buttonA)) {
        Robot.drive2903.turnOnBrakes(false);
      }

      if(Robot.driveJoy.getRawButton(JoystickMap.buttonB)) {
        Robot.drive2903.turnOnBrakes(true);
      }
    }
    
    if(UsingMap.usingClaw) {
      double power = 0;

      if(Robot.opJoy.getRawButton(JoystickMap.buttonRB)) {
        power = -0.1;
      }

      if(Robot.opJoy.getRawButton(JoystickMap.buttonLB)) {
        power = 0.1;
      }

      Robot.claw2903.motorClawOpener.set(power);
    }

    if(UsingMap.usingArm) {
      Robot.arm2903.motorArmRotate.set(Robot.opJoy.getY() / 10);
      Robot.arm2903.motorArmExtend.set(Robot.opJoy.getRawAxis(4) / 2);
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