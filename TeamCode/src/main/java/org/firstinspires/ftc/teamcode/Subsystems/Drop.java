package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


public class Drop {
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;
    private LinearOpMode opMode;
    private TopArm arm;
    int LIFT_POSITIONS[] = {0,100,600,1200,1800};
                           // 0 = Arm.Intake
                           // 1 = Arm.Intermediate
                           // 2+ = Arm.Drop
    int liftPosition = 0;

    boolean dPadPressed = false;
    private boolean prePressed = false;
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
        leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        runManually = false;
        arm = new TopArm(opMode);

    }

    public void move(double magnitude){
        if(!runManually){
            leftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightLift.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        }
        runManually=true;
        if (magnitude > 0.2 || magnitude < -0.2) {

            opMode.telemetry.addLine("Drop is in manual modde");
            leftLift.setPower(magnitude);
            rightLift.setPower(magnitude);
        }else{
            leftLift.setPower(0);
            rightLift.setPower(0);
        }
        if(opMode.gamepad2.right_trigger>0.4){
                    arm.goToIntermediate();
                } else{
                    arm.goToDropPosition();
                }
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
    public void resetEncoders(){
        leftLift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightLift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftLift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightLift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }
    public void controlLift2() {
        if(runManually){
            resetEncoders();
        }

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
        if(getTicks()>210) {
            liftPosition = 1;
            goToPosition(liftPosition);
            opMode.sleep(500);
        }
            liftPosition = 0;
            goToPosition(liftPosition);

    }


    public void goToPosition(int pos) {
        if (pos == 0) {

            arm.goToIntakePosition();
        } else if (pos == 1){
            arm.goToIntermediate();
            opMode.sleep(1000);

        }
        else {
            arm.goToDropPosition();
        }
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

        if (rightLift.getCurrentPosition() > 20 || !setToBottom) {
            leftLift.setPower(1.0);
            rightLift.setPower(1.0);
        } else {
            leftLift.setPower(0);
            rightLift.setPower(0);
        }

    }
    public void waitUntilMoved(){
        while(reachedTarget() == false) {
            opMode.sleep(100);
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
    public void resetPosition(){
        leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public int getPos(){
        return liftPosition;
    }
    public double getTicks(){return leftLift.getCurrentPosition();}
    public void stop() {
        goToBottom();
    }
}