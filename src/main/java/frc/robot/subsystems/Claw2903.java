package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class Claw2903 {
    // 1000 millis in a second
    public static final long clawSuckerSuckInLengthMillis = 1000;
    public static final long clawSuckerSuckOutLengthMillis = 1000;

    // Encoder distance needed to travel from limit switch detected  to go to cube/cone mode
    public static final int clawDistanceFromConeMode = 2000;// Guess - find real value
    public static final int clawDistanceFromCubeMode = 1000;// Guess - find real value

    // Encoder distance needed to flip claw 180 degrees (Pi radians)
    public static final int clawDistanceToFlip = 1000;// Guess - find real value

    public static final int clawOpenerOpenPower = 1;
    public static final int clawFlipUpPower = 1;
    public static final int suckingSpeed = 1;

    public static boolean clawIsFlipping = false;
    public static boolean clawIsOpening = false;
    public static boolean sucked = false;

    public CANSparkMax motorClawOpener;
    public CANSparkMax motorClawSucker;
    public CANSparkMax motorClawTwist;

    public Claw2903() {
        motorClawOpener = new CANSparkMax(RobotMap.motorClawOpener, MotorType.kBrushless);
        motorClawSucker = new CANSparkMax(RobotMap.motorClawSucker, MotorType.kBrushless);
        motorClawTwist = new CANSparkMax(RobotMap.motorClawTwist, MotorType.kBrushless);
    }

    public void flipClaw(boolean rightSideUp) {
        if(clawIsFlipping) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                clawIsFlipping = true;
                // Flip claw till upsidedown

                // Power claw to open
                motorClawTwist.set(-clawFlipUpPower);

                // While limit switch not pressed
                while(!motorClawFlipperLimit()) {
                    // Wait for claw to flip upsidedown
                }

                motorClawTwist.set(0);

                if(rightSideUp) {
                    double motorClawFlipperEncoderDefualtPosition = motorClawTwist.getEncoder().getPosition();
                    motorClawTwist.set(clawOpenerOpenPower);

                    while(motorClawTwist.getEncoder().getPosition() != motorClawFlipperEncoderDefualtPosition + clawDistanceToFlip) {
                        // Wait for claw to flip rightside up
                    }

                    motorClawOpener.set(0);
                }

                clawIsFlipping = false;
            }
        }.start();
    }

    // If inCubeMode then open claw to pick up cubes else close claw to pick up cubes
    public void cubeMode(boolean inCubeMode) {
        if(clawIsOpening) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                clawIsOpening = true;
                // Open claw till encoder pressed
 
                // Power claw to open
                motorClawOpener.set(-clawOpenerOpenPower);

                // While limit switch not pressed
                while(!motorClawOpenerLimit()) {
                    // Wait for claw to open
                }

                motorClawOpener.set(0);
                double motorClawOpenerEncoderDefualtPosition = motorClawOpener.getEncoder().getPosition();
                motorClawOpener.set(clawOpenerOpenPower);

                while(motorClawOpener.getEncoder().getPosition() != motorClawOpenerEncoderDefualtPosition + (inCubeMode ? clawDistanceFromCubeMode : clawDistanceFromConeMode)) {
                    // Wait for claw to close the right amount
                }

                motorClawOpener.set(0);
                clawIsOpening = false;
            }
        }.start();
    }

    public boolean motorClawOpenerLimit() {
        return true;// Fix
    }

    public boolean motorClawFlipperLimit() {
        return true;// Fix
    }

    // If suckIn then power suckers sucking in for clawSuckerSuckInLengthMillis to suck in else power suckers sucking out for power suckers for clawSuckerSuckOutLengthMillis
    public void suck(boolean suckIn) {
        new Thread() {
            @Override
            public void run() {
                motorClawSucker.set(suckIn ? suckingSpeed : -suckingSpeed);

                try {
                    Thread.sleep(suckIn ? clawSuckerSuckInLengthMillis : clawSuckerSuckOutLengthMillis);
                } catch(InterruptedException exc) {}

                motorClawSucker.set(0);
                sucked = sucked ? false : true;
            }
        }.start();
    }
}
