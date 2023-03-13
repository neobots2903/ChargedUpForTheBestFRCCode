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
  public static boolean qxwdcefgyuikop8 = true;

  @Override
  public void execute() {
    if(qxwdcefgyuikop8) {
      qxwdcefgyuikop8 = false;

      Robot.arm2903.rotateArmDegrees(45D);
    }

    Robot.telemacatrate();

    if(UsingMap.usingLimelight) {
      Robot.limelight2903.getArea();
      Robot.limelight2903.seesTarget();
      Robot.limelight2903.getXAxis();
      Robot.limelight2903.getYAxis();
    }

    if(UsingMap.usingDrive) {
      Robot.drive2903.trapezoidalArcadeDrive(-Robot.driveJoy.getY(), -Robot.driveJoy.getX());

      /* Logitech Dual Action
       * A: 2
       * B: 3
       * 
       * Controller()
       * A: 1
       * B: 2
       */
      if(Robot.driveJoy.getRawButton(1)) {
        System.out.println("Coast mode");
        Robot.drive2903.turnOnBrakes(false);
      }

      if(Robot.driveJoy.getRawButton(2)) {
        System.out.println("Brakes mode");
        Robot.drive2903.turnOnBrakes(true);
      }
    }
    
    if(UsingMap.usingClaw) {
      double power = 0;
      double speed = 0.3;

      if(Robot.opJoy.getRawButton(JoystickMap.buttonRB)) {
        power = -speed;
      }

      if(Robot.opJoy.getRawButton(JoystickMap.buttonLB)) {
        power = speed;
      }

      Robot.claw2903.motorClawOpener.set(power);
    }

    if(UsingMap.usingArm) {
      Robot.arm2903.motorArmRotate.set(Robot.opJoy.getY());
      Robot.arm2903.motorArmExtend.set(Robot.opJoy.getRawAxis(4) * 0.5);
    }
  }

  @Override
  public void end(boolean interrupted) {
    Robot.drive2903.diffDrive.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}