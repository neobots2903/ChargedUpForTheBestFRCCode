// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.UsingMap;
import frc.robot.subsystems.Claw2903.ClawPosition;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Teleop2903 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  @Override
  public void execute() {

    if(UsingMap.usingLimelight) {
      Robot.limelight2903.getArea();
      Robot.limelight2903.seesTarget();
      Robot.limelight2903.getXAxis();
      Robot.limelight2903.getYAxis();
    }


    if(UsingMap.usingDrive) {
      Robot.drive2903.trapezoidalDrive(-Robot.driveJoy.getY(), -Robot.driveJoy.getX());

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
      double suckSpeed = 0;
      if(Robot.opJoy.getRawButton(5)) suckSpeed--;
      if(Robot.opJoy.getRawButton(6)) suckSpeed++;
      Robot.claw2903.suck(suckSpeed);

      // if(!Robot.claw2903.limitClawHoldingOpen() && suckSpeed < 0) Robot.claw2903.suck(0);

      // if(Robot.opJoy.getRawButton(1)) Robot.claw2903.openClaw(ClawPosition.CUBE);
      // if(Robot.opJoy.getRawButton(2)) Robot.claw2903.openClaw(ClawPosition.CONE);
      // if(Robot.opJoy.getRawButton(4)) Robot.claw2903.openClaw(ClawPosition.CONE_SQUEEZE);
      // if(
      //   !Robot.opJoy.getRawButton(1) &&
      //   !Robot.opJoy.getRawButton(2) &&
      //   !Robot.opJoy.getRawButton(4)
      // ) Robot.claw2903.motorClawOpener.stopMotor();

      double openSpeed = (Robot.opJoy.getRawAxis(2) - Robot.opJoy.getRawAxis(3)) / 5;
      Robot.claw2903.motorClawOpener.set(openSpeed);
    }

    if(UsingMap.usingArm) {
      double extendSpeed = Robot.opJoy.getRawAxis(4) / 4;
      Robot.arm2903.motorArmExtend.set(extendSpeed);
      if(Math.abs(extendSpeed) < 0.01) Robot.arm2903.motorArmExtend.stopMotor();
      //Robot.arm2903.rotateArm(-Robot.opJoy.getY() / 4);
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