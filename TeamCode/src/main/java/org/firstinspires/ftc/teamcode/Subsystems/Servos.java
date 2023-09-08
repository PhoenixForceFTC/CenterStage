package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


public class Servos {
    private LinearOpMode currentOpMode;
    private Servo rollServo;
    public Servos (LinearOpMode opMode){
        currentOpMode = opMode;
        rollServo = opMode.hardwareMap.get(Servo.class, "S1");
    }

    public void GoToPosition (float position){
        rollServo.setPosition(position);
    }
}
