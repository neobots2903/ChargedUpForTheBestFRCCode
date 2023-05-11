package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

public class Claw2903 {
    public CANSparkMax motorClawOpener;
    public TalonSRX motorClawSucker;// Negative power makes it suck in
    public DigitalInput limitClawHolding;
    
    public Claw2903() {
        motorClawOpener = new CANSparkMax(RobotMap.motorClawOpener, MotorType.kBrushless);
        motorClawSucker = new TalonSRX(RobotMap.motorClawSucker);
        limitClawHolding = new DigitalInput(2);
        motorClawOpener.setIdleMode(IdleMode.kBrake);
    }

    public void suck(double speed) {
        motorClawSucker.set(TalonSRXControlMode.PercentOutput, speed);
    }

    // Positive power to motorClawOpener opens it
    public void openClaw(ClawPosition clawPosition) {
        double distance = clawPosition.position - motorClawOpener.getEncoder().getPosition();
        
        while(Math.abs(distance) > 0.25) {
            distance = clawPosition.position - motorClawOpener.getEncoder().getPosition();
            double speed = speedFrom(Math.abs(distance)) * Math.signum(distance) / 3;
            motorClawOpener.set(speed);
        }

        motorClawOpener.stopMotor();
    }

    public static double speedFrom(double distance) {
        return 1 - (1 / (distance + 1));
    }

    public boolean limitClawHoldingOpen() {
        return limitClawHolding.get();
    }

    public static enum ClawPosition {
        CUBE(0),
        CONE(-10),
        CONE_SQUEEZE(-16),
        EXTENDED(0),
        UNEXTENDED(-80);

        public double position;

        private ClawPosition(double position) {
            this.position = position;
        }
    }
}