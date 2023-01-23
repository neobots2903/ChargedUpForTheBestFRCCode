package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot2903 {
    public final int TICKS_PER_REV = 4096; 
    public CANSparkMax upperShoot;
    public CANSparkMax lowerShoot; 
    public TalonSRX pivot; 
    public TalonSRX pivotf; 
    public DigitalInput pivotLimitLower; 
    public DigitalInput pivotLimitUpper;
    public boolean initialize = false; 
    public double setAngleBoom = 0;
    public long shootStartTele = 0; 

    public Shoot2903(){
        upperShoot = new CANSparkMax (RobotMap.upperShoot,MotorType.kBrushless);
        lowerShoot = new CANSparkMax (RobotMap.lowerShoot,MotorType.kBrushless);
        pivot = new TalonSRX(RobotMap.pivot);
        pivot.enableCurrentLimit(true);
        pivotf = new TalonSRX(RobotMap.pivotf);
        pivotf.enableCurrentLimit(true);
        pivotLimitLower = new DigitalInput(RobotMap.pivotLimitLower); 
        pivotLimitUpper = new DigitalInput(RobotMap.pivotLimitUpper);
        pivotf.follow(pivot);
        pivot.setInverted(false);
        pivotf.setInverted(InvertType.OpposeMaster);
    }

    public void shoot(double speed){
        upperShoot.set(-speed);
        lowerShoot.set(speed);
    }

    public void shootForTime(int millis,int startMillis, double speed ){
        long startTime = System.currentTimeMillis();
        long endTime = startTime + millis; 

        while(System.currentTimeMillis() < endTime){
            if (System.currentTimeMillis() > startTime + startMillis){
                Robot.intake2903.indexer(-.6); 
            }
            shoot(speed);  
        }

        Robot.intake2903.indexer(0); 
        shoot(0);  
    }

    public void teleShoot(int startMillis, double speed ){
        if (shootStartTele + startMillis < System.currentTimeMillis()){
            Robot.intake2903.indexer(-.6);
        }

        shoot(speed); 
    }

    public void resetShoot(){
        shootStartTele = System.currentTimeMillis();
    }

    public void limits(){
        SmartDashboard.putBoolean ("Limit switch up",!pivotLimitUpper.get());
        SmartDashboard.putBoolean ("Limit switch down",!pivotLimitLower.get());
    }

    public void initPivot(){
        // long maxTime = System.currentTimeMillis() + 3000;
        // while (!pivotLimit.get() && System.currentTimeMillis() < maxTime){
        //     pivot.set(ControlMode.PercentOutput, .25);
        // }
        // pivot.set(ControlMode.PercentOutput, 0);
        // if (!initialize) {
        pivot.setSelectedSensorPosition(0);
        //     initialize = true;
        // }
        setAngle(getAngle());
    }
    
    // // public void checkLimits(){
    //     if (pivotLimitUpper.get() || pivotLimitLower.get()){
    //         setAngle(getAngle());
    //     }
    // }
    public void setAngle(double deg){
        if ((!pivotLimitUpper.get() && deg > setAngleBoom) || (!pivotLimitLower.get() && deg < setAngleBoom)){
            return;
        }

        pivot.set(ControlMode.Position, deg / 360 * TICKS_PER_REV);
        setAngleBoom = deg;
        SmartDashboard.putNumber("Pivot angle ", deg);
    }

    public double getTargetBoom (){
        return setAngleBoom; 
    }

    public double getAngle(){
        return pivot.getSelectedSensorPosition(0) / TICKS_PER_REV * 360; 
    }
}
