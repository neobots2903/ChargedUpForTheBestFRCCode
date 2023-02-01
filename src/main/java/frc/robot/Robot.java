package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Teleop2903;
import frc.robot.subsystems.Drive2903;
import frc.robot.subsystems.Limelight2903;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command autoCommand;
    public static Teleop2903 teleop;
    public static Limelight2903 limelight2903;
    public static Drive2903 drive2903;
    public static Joystick driveJoy;
    public static Joystick opJoy;
    public static UsbCamera camera; 

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings, and put our autonomous chooser on the dashboard.
        driveJoy = new Joystick(RobotMap.driveJoy);
        opJoy = new Joystick(RobotMap.opJoy);
        limelight2903 = new Limelight2903();
        drive2903 = new Drive2903();
        teleop = new Teleop2903();
        camera = CameraServer.startAutomaticCapture();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
    @Override
    public void autonomousInit() {
        teleop.cancel();

        if(autoCommand != null) {
            autoCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        if(autoCommand != null) {
            autoCommand.cancel();
        }

        teleop.schedule();
    }

    @Override
    public void teleopPeriodic() {}

    @Override
    public void testInit() {
      // Cancels all running commands at the start of test mode
      CommandScheduler.getInstance().cancelAll();
    }

    // This function is called periodically during test mode
    @Override
    public void testPeriodic() {}
}
