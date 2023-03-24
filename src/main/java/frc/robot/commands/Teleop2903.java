// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.UsingMap;
import frc.robot.subsystems.Claw2903.ClawPosition;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Teleop2903 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  @Override
  public void execute() {
    // Robot.navX.getYaw() and Robot.navX.getAngle() is rotation over X axis
    // Robot.navX.getPitch() is rotation over Y axis
    // ? is rotation over z axis
    // System.out.println(Robot.arm2903.motorArmRotate.getEncoder().getPosition());

    if(UsingMap.usingLimelight) {
      Robot.limelight2903.getArea();
      Robot.limelight2903.seesTarget();
      Robot.limelight2903.getXAxis();
      Robot.limelight2903.getYAxis();
    }

    if(UsingMap.usingDrive) {
      // double x = Robot.driveJoy.getX();
      // double y = -Robot.driveJoy.getY();

      // double deadband = 0.01;
      // if(Math.abs(x) < deadband) x = 0;
      // if(Math.abs(y) < deadband) y = 0;

      // double theta = Math.toDegrees(Math.atan(y / x));
      // if(theta < 0) theta += 180;
      // if(y < 0) theta += 180;
      // if(theta == -0) theta = 180;

      // System.out.println(theta + " " + Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
      // Robot.drive2903.headlessDrive(theta);

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
      Robot.claw2903.suck(Robot.opJoy.getRawAxis(2) - Robot.opJoy.getRawAxis(3));

      //System.out.println(Robot.claw2903.motorClawOpener.getEncoder().getPosition());
      
      if(Robot.opJoy.getRawButton(1)) Robot.claw2903.openClaw(ClawPosition.CUBE);
      if(Robot.opJoy.getRawButton(2)) Robot.claw2903.openClaw(ClawPosition.CONE);
      if(Robot.opJoy.getRawButton(4)) Robot.claw2903.openClaw(ClawPosition.CONE_SQUEEZE);
      if(
        !Robot.opJoy.getRawButton(1) &&
        !Robot.opJoy.getRawButton(2) &&
        !Robot.opJoy.getRawButton(4)
      ) Robot.claw2903.motorClawOpener.stopMotor();
    }

    if(UsingMap.usingArm) {
      double extendSpeed = Robot.opJoy.getRawAxis(4);
      Robot.arm2903.motorArmExtend.set(extendSpeed);
      if(Math.abs(extendSpeed) < 0.01) Robot.arm2903.motorArmExtend.stopMotor();

      System.out.println(Robot.arm2903.motorArmRotate.getEncoder().getPosition());
      Robot.arm2903.motorArmRotate.set(-Robot.opJoy.getY());
    }

    // AnalogInput input = new AnalogInput(0);
    // System.out.println(input.getValue());
    // input.close();
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