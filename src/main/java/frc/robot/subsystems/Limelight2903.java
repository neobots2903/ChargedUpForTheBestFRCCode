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

    public Limelight2903() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");// X axis
        ty = table.getEntry("ty");// Y axis
        ta = table.getEntry("ta");// Area
        tv = table.getEntry("tv");// See target or not   
    }

    public static enum pipelineType {
        APRIL_TAG(0),
        REFLECTIVE_TAPE(1);

        public int value;

        private pipelineType(int value) {
            this.value = value;
        }
    }

    public void setPipeline(pipelineType type) {
        table.getEntry("pipeline").setNumber(type.value); 
    }

    public double getXAxis() { 
        double x = tx.getDouble(0.0);
        SmartDashboard.putNumber("LimelightX", x);
        return x;
    }

    public double getYAxis() {
        double y = ty.getDouble(0.0);
        SmartDashboard.putNumber("LimelightY", y);
        return y; 
    }

    public double getArea() {
        double area = ta.getDouble(0.0);
        SmartDashboard.putNumber("LimelightArea", area); 
        return area; 
    }

    public boolean seesTarget() {
        boolean valid = tv.getDouble(0) == 1.0;
        SmartDashboard.putBoolean("LimelightV", valid);
        return valid;
    }
}