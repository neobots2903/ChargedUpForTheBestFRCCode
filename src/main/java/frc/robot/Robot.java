package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import frc.robot.commands.Auto2903;
import frc.robot.commands.Teleop2903;
import frc.robot.subsystems.Climb2903;
import frc.robot.subsystems.Drive2903;
import frc.robot.subsystems.Intake2903;
import frc.robot.subsystems.Limelight2903;
import frc.robot.subsystems.Shoot2903;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    private RobotContainer m_robotContainer;
    private static Teleop2903 teleop;
    public static Limelight2903 limelight2903;
    public static Drive2903 drive2903;
    public static Joystick driveJoy;
    public static Joystick opJoy;
    public static Shoot2903 shoot2903;
    public static Intake2903 intake2903; 
    public static Climb2903 climb2903; 
    public static UsbCamera camera; 

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        driveJoy = new Joystick(RobotMap.driveJoy);
        opJoy = new Joystick(RobotMap.opJoy);
        intake2903 = new Intake2903(); 
        climb2903 = new Climb2903();
        limelight2903 = new Limelight2903();
        shoot2903 = new Shoot2903(); 
        drive2903 = new Drive2903();
        teleop = new Teleop2903();
        m_robotContainer = new RobotContainer();
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
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        if(m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        if(m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
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
