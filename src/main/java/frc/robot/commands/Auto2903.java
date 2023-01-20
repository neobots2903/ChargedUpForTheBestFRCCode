package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Auto2903 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    boolean running = true; 
    double error = 0.5; 
    long startTime = 0;
  public Auto2903() {
  }
  @Override
  public void initialize() {
    running = true;
    Robot.shoot2903.initPivot();
    startTime = System.currentTimeMillis();

  }

  @Override
  public void execute() {
    Robot.drive2903.distanceDrive(30,-.2);
    Robot.shoot2903.setAngle(-20);
  
    // while(running){
    //   if (Robot.limelight2903.getTV()){ 
    //     if (Robot.limelight2903.getTX() > error){
    //       Robot.drive2903.arcadeDrive(0, .07);
    //     }
    //     else if(Robot.limelight2903.getTX() < -error){
    //         Robot.drive2903.arcadeDrive(0, -.07);
    //     }
    //     else {
    //       Robot.drive2903.arcadeDrive(0, 0);
    //       running = false; 
    //     }
    //   } else {
    //     Robot.drive2903.arcadeDrive(0, -.10);
    //     if (startTime + 14000 < System.currentTimeMillis()){
    //       running = false;
    //       cancel();
    //       return;
    //     }
    //   }
    // }
    Robot.shoot2903.shootForTime(4000, 2500, 1);
    Robot.drive2903.distanceDrive(30,-.2);
    cancel(); 
  }
  @Override
  public void end(boolean interrupted) {
      running = false; 
    Robot.drive2903.arcadeDrive(0,0);
  }
  @Override
  public boolean isFinished() {
    return !running;
  }
}
