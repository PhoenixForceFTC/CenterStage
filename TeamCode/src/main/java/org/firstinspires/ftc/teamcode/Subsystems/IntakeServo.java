package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;


public class IntakeServo {
    private LinearOpMode currentOpMode;
    private CRServo cRServo1;
    private CRServo cRServo2;
    private CRServo cRServo3;


    public IntakeServo(LinearOpMode opMode){
        currentOpMode = opMode;
        cRServo1 = opMode.hardwareMap.get(CRServo.class, "CRS1");
        cRServo2 = opMode.hardwareMap.get(CRServo.class, "CRS2");
        cRServo3 = opMode.hardwareMap.get(CRServo.class, "CRS3");

    }

    public void setPower(double position){

        cRServo1.setPower(-position);
        cRServo2.setPower(position);
        cRServo3.setPower(position);
    }
    public void forward(){setPower(1);}
    public void backward(){setPower(-0.4);}
    public void stop(){setPower(0);}
}