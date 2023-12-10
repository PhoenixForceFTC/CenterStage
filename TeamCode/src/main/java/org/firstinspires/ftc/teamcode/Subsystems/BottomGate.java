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
    private TeleOpMode teleOp;
    //public ClawSensor sensor;

    private boolean canAutoClose = false;
    private ButtonToggle clawClosed;

    public BottomGate(OpMode opMode) {
        this.opMode = opMode;

        gate = opMode.hardwareMap.get(Servo.class, "GATE1");
        //grip.setDirection(Servo.Direction.REVERSE);
        //sensor = new ClawSensor(opMode.hardwareMap);

        clawClosed = new ButtonToggle();
    }

    public void controlClaw() {
        clawClosed.update(opMode.gamepad2.dpad_down);

        /*if (sensor.conePresent()) {
            if (canAutoClose) {
                clawClosed.setActive(true);
                canAutoClose = false;
            }
        } else {
            canAutoClose = true;
        }*/

        setClawClosed(clawClosed.isActive());

        //opMode.telemetry.addData("Claw sensor distance", sensor.getDistance());
        opMode.telemetry.addData("gate open", !isClosed());
        opMode.telemetry.addData("gate auto close", canAutoClose);
        //opMode.telemetry.addData("Is cone detected", sensor.conePresent());

    }

    public boolean isClosed() {
        return clawClosed.isActive();
    }

    public void setClawClosed(boolean closed) {
        if (closed) {
            gate.setPosition(closedPos);
        } else if(teleOp.reached() && teleOp.returnDrop()==0){
            gate.setPosition(openPos);
        }
    }
}
