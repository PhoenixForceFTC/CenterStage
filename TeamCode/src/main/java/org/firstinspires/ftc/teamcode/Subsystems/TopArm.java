package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Config

public class TopArm {
    private LinearOpMode currentOpMode;
    private Servo arm;

    private final double dropPosition =0.3;
    private final double intakePosition = 0.66;
    private final double intermediate = 0.6;

    public TopArm(LinearOpMode opMode){

        currentOpMode = opMode;
        arm = opMode.hardwareMap.get(Servo.class, "ARM");

    }

    public void setPosition(double position){

        arm.setPosition(position);
    }
    public void goToDropPosition(){
        setPosition(dropPosition);
        currentOpMode.telemetry.addData("TopArm: Going to drop position", dropPosition);
    }
    public void goToIntakePosition(){
        setPosition(intakePosition);
        currentOpMode.telemetry.addData("TopArm: Going to intake position", intakePosition);
    }
    public void goToIntermediate(){
        setPosition(intermediate);
        currentOpMode.telemetry.addData("TopArm: Going to intake position", intermediate);
    }
}