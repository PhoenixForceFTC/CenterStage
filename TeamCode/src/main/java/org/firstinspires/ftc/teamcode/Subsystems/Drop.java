package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


public class Drop {
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;
    private LinearOpMode opMode;
    private TopArm arm;
    int LIFT_POSITIONS[] = {0,600,1200,1800};
    int liftPosition = 0;

    boolean dPadPressed = false;

    public Drop(LinearOpMode opMode){
        this.opMode = opMode;
        leftLift = this.opMode.hardwareMap.get(DcMotorEx.class, "LD");
        rightLift = this.opMode.hardwareMap.get(DcMotorEx.class, "RD");
        leftLift.setDirection(DcMotor.Direction.REVERSE);
        rightLift.setDirection(DcMotor.Direction.FORWARD);
        leftLift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightLift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftLift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightLift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        arm = new TopArm(opMode);
    }
    public void liftSleep(){

        while (leftLift.isBusy()) {
            opMode.sleep(100);
        }
    }
    public void controlLift(boolean canLift) {
        if (opMode.gamepad2.right_bumper) {
            if (liftPosition < LIFT_POSITIONS.length - 1 && !dPadPressed && canLift) {
                liftPosition++;
            }

            dPadPressed = true;
        } else if (opMode.gamepad2.right_trigger > .2) {
            if (liftPosition > 0 && !dPadPressed) {
                liftPosition--;
            }

            dPadPressed = true;
        } else {
            dPadPressed = false;
        }

        goToPosition(liftPosition);
    }
    public void controlLift2() {
        if (opMode.gamepad2.a) {
            liftPosition = 0;
        }
        if (opMode.gamepad2.x) {
            liftPosition= 1;
        }
        if (opMode.gamepad2.b) {
            liftPosition=2;
        }
        if (opMode.gamepad2.y) {
            liftPosition=3;
        }
        goToPosition(liftPosition);
    }


    public void goToPosition(int pos) {
        leftLift.setTargetPosition(LIFT_POSITIONS[pos]);
        rightLift.setTargetPosition(LIFT_POSITIONS[pos]);
        leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        boolean setToBottom = LIFT_POSITIONS[pos] == 0;

        if (setToBottom) {
            leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        } else {
            leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        if (rightLift.getCurrentPosition() > 10 || !setToBottom) {
            leftLift.setPower(1.0);
            rightLift.setPower(1.0);
        } else {
            leftLift.setPower(0);
            rightLift.setPower(0);
        }
        if (pos == 0) {
            arm.goToIntakePosition();
        } else {
            arm.goToDropPosition();
        }
    }

    public boolean reachedTarget()
    {
        int diff = Math.abs(leftLift.getCurrentPosition()-leftLift.getTargetPosition());
        if(diff<5)
        {
            return true;

        }
        else
        {
            return false;
        }
    }

    public void moveToTop(){
        //setPower(1);
        liftPosition = 1;
    }

    public void moveToBottom(){
        //setPower(0);
        liftPosition = 0;
    }
    public int getPos(){
        return liftPosition;
    }
    public void stop() {
        liftPosition=0;
    }
}