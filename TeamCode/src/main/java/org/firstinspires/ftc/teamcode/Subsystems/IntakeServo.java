package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;


public class IntakeServo {
    private OpMode currentOpMode;
    private CRServo cRServo1;
    private CRServo cRServo2;
    private CRServo cRServo3;


    public IntakeServo(OpMode opMode){
        currentOpMode = opMode;
        cRServo1 = opMode.hardwareMap.get(CRServo.class, "CRS1");
        cRServo2 = opMode.hardwareMap.get(CRServo.class, "CRS2");
        cRServo3 = opMode.hardwareMap.get(CRServo.class, "CRS3");

    }

    public void setPower(double position){

        cRServo1.setPower(-position);
        cRServo2.setPower(position);
        cRServo3.setPower(position);
        currentOpMode.telemetry.addData("Intake Servo Power:",position);
    }
    public void forward(){setPower(1);}
    public void backward(){setPower(-0.4);}
    public void backward2(){setPower(-1);}
    public void stop(){setPower(0);}
}