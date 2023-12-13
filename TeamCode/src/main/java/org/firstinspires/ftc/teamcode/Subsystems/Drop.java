package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


public class Drop {
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;
    private LinearOpMode opMode;
    private TopArm arm;
    int LIFT_POSITIONS[] = {100,200,600,1200,1800};
    int liftPosition = 0;

    boolean dPadPressed = false;
    private boolean runManually = false;

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
        runManually = false;
        arm = new TopArm(opMode);
    }

    public void move(double magnitude){
        if (magnitude > 0.2 || magnitude < -0.2) {
            leftLift.setTargetPosition(leftLift.getCurrentPosition()+(int)(100*magnitude));
            rightLift.setTargetPosition(rightLift.getCurrentPosition()+(int)(100*magnitude));
            leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            opMode.telemetry.addLine("Drop is in manual modde");
            leftLift.setPower(magnitude);
            rightLift.setPower(magnitude);
            /*stateOfDown = false;
            stateOfUp = false;*/
            runManually = true;

        }
        else
        {
            if (runManually) {
                leftLift.setTargetPosition(leftLift.getCurrentPosition());
                rightLift.setTargetPosition(rightLift.getCurrentPosition());
                if (rightLift.getCurrentPosition() > 10) {
                    leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                    rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                    leftLift.setPower(1.0);
                    rightLift.setPower(1.0);
                } else {
                    leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    leftLift.setPower(0);
                    rightLift.setPower(0);
                }
            }
        }
        opMode.telemetry.addData("Drop manual slide power", magnitude);
        opMode.telemetry.addData("Drop manual encoder pos", leftLift.getCurrentPosition());
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
            runManually = false;
            goToBottom();
        }
        if (opMode.gamepad2.x) {
            runManually = false;
            liftPosition= 2;
            goToPosition(liftPosition);

        }
        if (opMode.gamepad2.b) {
            runManually = false;
            liftPosition=3;
            goToPosition(liftPosition);

        }
        if (opMode.gamepad2.y) {
            runManually = false;
            liftPosition=4;
            goToPosition(liftPosition);
        }

    }

    public void goToBottom() {
        if(getTicks()>100) {
            liftPosition = 1;
            goToPosition(liftPosition);
            opMode.sleep(1000);
        }
            liftPosition = 0;
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
        } else if (pos == 1){
            arm.goToIntermediate();

        }
        else {
            arm.goToDropPosition();
        }
    }
    public void waitUntilMoved(){
        while(reachedTarget() == false) {
            opMode.sleep(10);
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
    public double getTicks(){return leftLift.getCurrentPosition();}
    public void stop() {
        liftPosition=0;
    }
}