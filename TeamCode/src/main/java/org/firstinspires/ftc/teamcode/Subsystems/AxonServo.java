package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;


public class AxonServo {
    private LinearOpMode currentOpMode;
    private CRServo rollServo;
    private CRServo rollServo2;
    public AxonServo(LinearOpMode opMode){
        currentOpMode = opMode;
        rollServo = opMode.hardwareMap.get(CRServo.class, "S1");
        rollServo2 = opMode.hardwareMap.get(CRServo.class, "S2");

    }

    public void goToPosition (float position){

        rollServo.setPower(position);
        rollServo.setPower(position);
    }
}
