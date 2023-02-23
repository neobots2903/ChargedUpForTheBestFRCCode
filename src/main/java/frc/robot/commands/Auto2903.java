package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Auto2903 extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public boolean running = true;
  public double error = 0.5;
  public long startTime = 0;

  @Override
  public void initialize() {
    running = true;
    startTime = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    // Robot.drive2903.arcadeDriveDistance(30, 0, -.2);

    while(running) {
      Robot.drive2903.arcadeDriveSeconds(0, 0.1, 1);
      Robot.arm2903.rotateArmSeconds(0.25, 0.5);
      Robot.claw2903.suck(false);
      Robot.pause(1);
      Robot.drive2903.arcadeDriveSeconds(-0.1, 0, 1);
    }

    cancel();
  }

  @Override
  public void end(boolean interrupted) {
    running = false;
    Robot.drive2903.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return !running;
  }
}
