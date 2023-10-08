package org.firstinspires.ftc.teamcode.util;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


public class MotorEncoder {
    private DcMotorEx leftLift;
    private LinearOpMode currentOpMode;


    /*boolean stateOfUp;
    boolean stateOfDown;*/

    private double power = 0.01;
    private int highPos =10;
    private int lowPos = 0;
    private Level where = Level.LOW;

    public MotorEncoder(LinearOpMode opMode){
        currentOpMode = opMode;
        leftLift = currentOpMode.hardwareMap.get(DcMotorEx.class, "TAPE");
        leftLift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    //manual teleop movement


    public void moveToTop(){
        leftLift.setPower(power);
        leftLift.setTargetPosition(highPos);

        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        where = Level.HIGH;
        currentOpMode.telemetry.addData("Power: ",leftLift.getPower());

    }

    public void moveToBottom(){
        leftLift.setTargetPosition(lowPos);
        leftLift.setPower(power);
        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        where = Level.LOW;
        while(leftLift.isBusy()){}

    }



    /*public void moveToTop(boolean upPressed, boolean downPressed){
        if (upPressed && !stateOfUp) {
            slide.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            slide.setTargetPosition(1300);
            stateOfUp = true;
            stateOfDown = false;
            slide.setPower(1);
            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while(slide.isBusy()){}
        }

        if (downPressed && !stateOfDown) {
            slide.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            slide.setTargetPosition(0);
            stateOfDown = true;
            stateOfUp = false;
            slide.setPower(1);
            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while(slide.isBusy()){}
        }
    }*/

    public enum Level {
        LOW,
        HIGH,
        NOWHERE
    }


}
