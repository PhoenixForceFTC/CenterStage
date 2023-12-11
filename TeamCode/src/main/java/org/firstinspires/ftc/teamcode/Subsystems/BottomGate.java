package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.OpModes.TeleOpMode;

import org.firstinspires.ftc.teamcode.util.ButtonToggle;

public class BottomGate {
    private OpMode opMode;
    private final double closedPos = 1;
    private final double openPos = 0;
    private Servo gate;
    private Drop dropSlides;

    private TeleOpMode teleOp;
    //public ClawSensor sensor;

    private boolean canAutoClose = false;
    private ButtonToggle clawClosed;

    public BottomGate(OpMode opMode, Drop drop) {
        this.opMode = opMode;

        gate = opMode.hardwareMap.get(Servo.class, "GATE1");
        //grip.setDirection(Servo.Direction.REVERSE);
        //sensor = new ClawSensor(opMode.hardwareMap);

        clawClosed = new ButtonToggle();
    }

    public void controlClaw() {
        if (dropSlides.reachedTarget() && dropSlides.getPos()==0) {
            clawClosed.update(opMode.gamepad2.dpad_down);
        }


        setClawClosed(clawClosed.isActive());
        opMode.telemetry.addData("gate open", !isClosed());
        opMode.telemetry.addData("gate auto close", canAutoClose);

    }

    public boolean isClosed() {
        return clawClosed.isActive();
    }

    public void setClawClosed(boolean closed) {
        if(dropSlides.reachedTarget() && dropSlides.getPos()==0 && !closed){
            gate.setPosition(openPos);
        }
        else{
            gate.setPosition(closedPos);
        }

    }
}
