// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.JoystickMap;
import frc.robot.Robot;
import frc.robot.UsingMap;
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
    }
    
    if(UsingMap.usingClaw) {
      Robot.claw2903.powerClaw(Robot.driveJoy.getRawAxis(JoystickMap.lt) - Robot.opJoy.getRawAxis(JoystickMap.rt));

      // if(!Robot.claw2903.clawIsOpening) {
      //   if(Robot.opJoy.getRawButton(JoystickMap.buttonRB)) {
      //     Robot.claw2903.cubeMode(true);
      //   }

      //   if(Robot.opJoy.getRawButton(JoystickMap.buttonLB)) {
      //     Robot.claw2903.cubeMode(false);
      //   }
      // }

      // if(Robot.opJoy.getRawButton(JoystickMap.buttonA)) {
      //   Robot.claw2903.suck(Robot.claw2903.sucked ? false : true);
      // }
    }

    if(UsingMap.usingArm) {
      Robot.arm2903.rotateArm(Robot.opJoy.getRawAxis(JoystickMap.lt) - Robot.opJoy.getRawAxis(JoystickMap.rt));
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