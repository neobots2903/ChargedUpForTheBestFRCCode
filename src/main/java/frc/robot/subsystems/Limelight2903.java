package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight2903 {
    public NetworkTable table;
    public NetworkTableEntry tx;
    public NetworkTableEntry ty;
    public NetworkTableEntry ta;
    public NetworkTableEntry tv;

    public Limelight2903(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx"); //x axis 
        ty = table.getEntry("ty"); //y axis 
        ta = table.getEntry("ta"); //area
        tv = table.getEntry("tv"); //see target or not  
    }

    public double getTX() {
        double x = tx.getDouble(0.0);
        SmartDashboard.putNumber("LimelightX", x);
        return x;
    }

    public double getTY() {
        double y = ty.getDouble(0.0);
        SmartDashboard.putNumber("LimelightY", y);
        return y; 
    }

    public double getTA() {
        double area = ta.getDouble(0.0);
        SmartDashboard.putNumber("LimelightArea", area); 
        return area; 
    }

    public boolean getTV() {
        boolean valid = tv.getDouble(0) == 1.0;
        SmartDashboard.putBoolean("LimelightV", valid);
        return valid;
    }

    // public void setLED(boolean on){
    //     table.getEntry("ledMode"); 
    // }
}

