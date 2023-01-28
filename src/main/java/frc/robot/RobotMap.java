
package frc.robot;

public class RobotMap {
    
    //                                                              SparkMax motors       Power distrubition
     public static final int frontLeftMotor = 1; //0
     public static final int frontRightMotor = 3; //1
     public static final int backLeftMotor = 2; //2
     public static final int backRightMotor = 4; //3
     public static final int lowerShoot =  5; //6
     public static final int upperShoot = 6; //7
     public static final int upperIntake = 8; //8
     public static final int indexer = 9; //10
    
    // TalonSRX motors 
    public static final int pivot = 10; //4
    public static final int pivotf = 11; //5
    public static final int otherIntake = 12; //9

    // Digital IO
    public static final int pivotLimitLower = 2;
    // public static final int otherIntakeOut = 4;
    // public static final int otherIntakeIn = 3;
    public static final int pivotLimitUpper = 3;

    // Controllers
    public static final int driveJoy = 0;
    public static final int opJoy = 1;

    // Analog Io
    public static final int intakePot = 0;
}