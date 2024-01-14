package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class Intake {
    private IntakeServo intakeServo;

    private BottomGate bottomGate;
    private LinearOpMode opMode;
    private Drop drop;
    private PixelCounter pixelCounter;
    private Lights lights;

    private TopGate topGate;

    public Intake(LinearOpMode opMode, Drop drop, TopGate topGate){
        this.opMode = opMode;
        intakeServo = new IntakeServo(this.opMode);
        this.drop = drop;
        this.topGate = topGate;
        bottomGate = new BottomGate(this.opMode,this.drop);
        pixelCounter = new PixelCounter(this.opMode);
        lights = new Lights(this.opMode);
    }

    public void eatPixel(){
        opMode.telemetry.addLine("Eating Pixel");
        topGate.setGateStopped();
        intakeServo.forward();
        bottomGate.setClawClosed(true);

    }
    public void returnPixel(){
        opMode.telemetry.addLine("Returning Pixel");
        topGate.setGateStopped();
        intakeServo.backward();
        bottomGate.setClawClosed(true);
    }
    public void returnPixelTeleOpMode(){
        opMode.telemetry.addLine("Returning Pixel");
        topGate.setGateStopped();
        intakeServo.backward2();
        bottomGate.setClawClosed(true);
    }
    public void transferPixel(){
        bottomGate.setClawClosed(false);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        drop.transfer();
                        opMode.telemetry.addLine("Transferring Pixel");
                        intakeServo.transfer();
                        topGate.setGateClosed();
                    }
                },
                100 // Delay in milliseconds
        );

    }
    public void stop(){
        opMode.telemetry.addLine("Intake is stopped");
        topGate.setGateStopped();
        intakeServo.stop();
        bottomGate.setClawClosed(true);
    }
    public void lights(){
        RevBlinkinLedDriver.BlinkinPattern color;
        if(pixelCounter.getDropPixels()==2){
            color = RevBlinkinLedDriver.BlinkinPattern.GREEN;
        }else if(pixelCounter.getDropPixels()==1){
            color = RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_FOREST_PALETTE;
        }else if(pixelCounter.getIntakePixels()==2){
            color = RevBlinkinLedDriver.BlinkinPattern.RED;
        }else if(pixelCounter.getIntakePixels()==1){
            color = RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED;
        }else{
            color = RevBlinkinLedDriver.BlinkinPattern.HOT_PINK;
        }
        lights.setPattern(color);
    }
}
