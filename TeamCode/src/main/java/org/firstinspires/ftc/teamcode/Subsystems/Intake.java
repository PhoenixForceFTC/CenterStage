package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class Intake {
    private IntakeServo intakeServo;
    private BottomGate bottomGate;
    private OpMode opMode;
    private Drop drop;

    private TopGate topGate;

    public Intake(OpMode opMode,Drop drop,TopGate topGate){
        this.opMode = opMode;
        intakeServo = new IntakeServo(this.opMode);
        this.drop = drop;
        this.topGate = topGate;
        bottomGate = new BottomGate(this.opMode,this.drop);
    };
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
    public void transferPixel(){
        opMode.telemetry.addLine("Transferring Pixel");
        intakeServo.forward();
        bottomGate.setClawClosed(false);
        topGate.setGateClosed();

    }
    public void stop(){
        opMode.telemetry.addLine("Intake is stopped");
        topGate.setGateStopped();
        intakeServo.stop();
        bottomGate.setClawClosed(true);
    }

}
