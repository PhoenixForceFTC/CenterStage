package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.Auto.AutoOpMode;

@Config
@Autonomous(group="!CompOpModes")
public class RedRightBlueRightConeDeliver extends AutoOpMode {
    public static Position START = new Position(36, -63, 90);
    public static Position INIT_CONE_DEL = new Position(32 , -14.5, 224);
    public static Position INIT_CONE_DEL2 = new Position(32, -16, 224);
    public static Position INIT_CONE_DEL3 = new Position(32 , -14.5, 224);
    public static Position INIT_CONE_DEL4= new Position(32 , -14.5, 224);
    public static Position INIT_CONE_DEL_INT = new Position(-36, -55, 90);
    public static Position STRAFE_TO = new Position(-38.5, -10, 90);
    public static Position CONE_GRAB = new Position(52, -15.5, 176);
    public static Position CONE_GRAB_INT = new Position(40, -15.5, 176);
    public static Position CONE_GRAB_INT2 = new Position(40, -15.5, 176);
    public static Position CONE_GRAB2 = new Position(53, -15.5, 171);
    public static Position CONE_GRAB3 = new Position(54, -15.5, 169);
    public static Position CONE_GRAB4 = new Position(54, -15.5, 165);

    public static Position POS_1_PARK_INT = new Position(36, -12, 90);
    public static Position POS_1_PARK = new Position(12, -13.5, 90);

    public static Position POS_2_PARK = new Position(36, -15, 90);

    public static Position POS_3_INT = new Position(36, -12, 85);
    public static Position POS_3_PARK = new Position(59, -15  , 85);


    //DcMotorEx elbowArm;


    @Override
    public void runOpMode() {
        setup();
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_FOREST_PALETTE);
        switch (camera.getTagOfInterest()) {
            case 1:
                coneDrop();
                goTo(POS_1_PARK_INT);
                sleep(100);
                goTo(POS_1_PARK);
                break;
            case 2:
                coneDrop();
                goTo(POS_2_PARK);
                break;

            case 3:
                coneDrop();
                goTo(POS_3_INT);
                sleep(100);
                goTo(POS_3_PARK);



        }


    }
//       switch(camera.getTagOfInterest()) {
//            case 1:
//                goTo(POS_1_INT);
//                sleep(2 * 1000);
//                goTo(POS_1_PARK);
//                break;
//
//            case 2:
//                goTo(POS_2_PARK);
//                break;
//
//            case 3:
//                goTo(POS_3_INT);
//                sleep(2 * 1000);
//                goTo(POS_3_PARK);
//                break;
//        }*/


    public void coneDrop() {
        elbowArm.gotoPosition(0);
        claw.setClawClosed(true);
        setStartPosition(START);
        setSpeed(Speed.MEDIUM);
        goTo(INIT_CONE_DEL);
        setSpeed(Speed.FAST);
        elbowArm.dropOnMedium();
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        //  sleep(150);
        claw.setClawClosed(false);
        sleep(200);
        elbowArm.gotoPosition(0);
        goTo(CONE_GRAB_INT);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        setSpeed(Speed.FAST);
        sleep(100);

        //cone 1

        elbowArm.gotoPositionAuto(1);
        sleep(100);
        goTo(CONE_GRAB);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(0);
        sleep(200);
        setSpeed(Speed.FAST);
        elbowArm.dropOnMedium();
        goTo(INIT_CONE_DEL);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);
        sleep(100);
        //cone 2
        elbowArm.gotoPosition(0);
        goTo(CONE_GRAB_INT);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        //   setSpeed(Speed.SLOW);
        sleep(100);
        elbowArm.gotoPositionAuto(3);
        sleep(100);
        goTo(CONE_GRAB2);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(2);
        sleep(200);
        setSpeed(Speed.FAST);
        elbowArm.dropOnMedium();
        goTo(INIT_CONE_DEL2);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);
        sleep(100);
        //cone 3
        elbowArm.gotoPosition(0);
        goTo(CONE_GRAB_INT2);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        sleep(100);
        //  setSpeed(Speed.SLOW);

        elbowArm.gotoPositionAuto(5);
        sleep(100);
        goTo(CONE_GRAB3);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(4);
        sleep(200);
        setSpeed(Speed.FAST);
        elbowArm.dropOnMedium();
        goTo(INIT_CONE_DEL3);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);
        sleep(100);
        elbowArm.gotoPositionAuto(0);
        //cone 4
        elbowArm.gotoPosition(0);
        goTo(CONE_GRAB_INT2);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        sleep(100);
        //  setSpeed(Speed.SLOW);

        elbowArm.gotoPositionAuto(7);
        sleep(100);
        goTo(CONE_GRAB4);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(6);
        sleep(200);
        setSpeed(Speed.FAST);
        elbowArm.dropOnMedium();
        goTo(INIT_CONE_DEL4);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);
        sleep(100);
        elbowArm.gotoPosition(0);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }


    }
}

