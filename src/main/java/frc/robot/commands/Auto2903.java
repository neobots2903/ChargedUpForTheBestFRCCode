package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.GiveJessicaBlanket;
import frc.robot.subsystems.Limelight2903;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Auto2903 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  public boolean running = true;
  public double error = 0.5;
  public long startTime = 0;

  @Override
  public void initialize() {
    running = true;
    startTime = System.currentTimeMillis();

    Robot.arm2903.findBottomEncoder();
  }

  @Override
  public void execute() {
    GiveJessicaBlanket.blanket();

    Robot.limelight2903.setPipeline(Limelight2903.pipelineType.APRIL_TAG);
    
    while(Math.abs(Robot.limelight2903.getXAxis()) > 10) {
      Robot.drive2903.arcadeDrive(0, Math.signum(Robot.limelight2903.getXAxis()) * 0.25);
    }
    
    Robot.drive2903.arcadeDrive(0.25, 0);

    while(Robot.limelight2903.getArea() < 25) {
      // Wait for robot to get close to april tag
    }

    Robot.drive2903.arcadeDrive(0, 0);
    Robot.arm2903.rotateArmDegrees(-90);
    Robot.arm2903.extendArmInches(69);

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
