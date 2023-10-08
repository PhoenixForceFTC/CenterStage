package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.NewLift;

@Config
@Autonomous(group="!CompOpModes")
public class HighJunctBlueRightRedRight extends AutoOpMode {
    public static Position START = new Position(-36, -63, 90);
   //cone deliver positions
   private NewLift newLift;

    public static Position INIT_CONE_DEL = new Position(-34, -14, 307);
    public static Position HighJunctDeliver = new Position(-43, -11, 37);
    public static Position HighJunctDeliver2 = new Position(-43, -11, 37);
    public static Position HighJunctDeliver3 = new Position(-43, -11, 37);
    public static Position HighJunctDeliver4 = new Position(-43, -11, 37);
    public static Position HighJunctDeliver5 = new Position(-43, -11, 37);
    //
    public static Position INIT_CONE_DEL2 = new Position(-34.5, -12, 306);
    public static Position INIT_CONE_DEL3 = new Position(-34, -12, 306);
    public static Position INIT_CONE_DEL4 = new Position(-35.5, -11, 318);
    public static Position INIT_CONE_DEL5 = new Position(-40, -12.5, 0);
    public static Position INIT_CONE_DEL_INT = new Position(-36, -55, 90);
    public static Position STRAFE_TO = new Position(-38.5, -10, 90);
    //cone grab positions
    public static Position CONE_GRAB = new Position(-55, -17, 0);
    public static Position CONE_GRAB2 = new Position(-55.5, -17,   2);
    public static Position CONE_GRAB3 = new Position(-56.25, -17.5, 3);
    public static Position CONE_GRAB4 = new Position(-57, -17.5 , 6);;
    public static Position CONE_GRAB5 = new Position(-52, -12, 0);
    public static Position CONE_GRAB_INT = new Position(-43, -15.5, 0);
    public static Position CONE_GRAB_INT2 = new Position(-43, -15.5, 6);
    //park positions
    public static Position POS_1_PARK_INT = new Position(-36, -15, 90);
    public static Position POS_1_PARK = new Position(-65, -15, 93);

    public static Position POS_2_PARK = new Position(-36, -24, 92);

    public static Position POS_3_INT = new Position(-36, -12, 93);
    public static Position POS_3_PARK = new Position(-12, -12, 93);


    //DcMotorEx elbowArm;


    @Override
    public void runOpMode() {
        setup();
        newLift = new NewLift(this);
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.FIRE_LARGE);
        switch (camera.getTagOfInterest()) {
            case 1:
                coneDrop();
                //goTo(POS_1_PARK);
                break;
            case 2:
                coneDrop();
               // goTo(POS_2_PARK);
                break;

            case 3:
                coneDrop();
              //  goTo(POS_3_INT);
                sleep(100);
            //   goTo(POS_3_PARK);



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
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        setSpeed(Speed.FAST);


        // go to intital high junct position

        //cone 1
        goTo(CONE_GRAB_INT);
        elbowArm.gotoPositionAuto(1);
        sleep(100);
        goTo(CONE_GRAB);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(0);
        sleep(200);
        setSpeed(Speed.FAST);
        goTo(HighJunctDeliver);
        //deliver cone #1
        elbowArm.gotoPosition(3);
        sleep(300);
        newLift.goToPosition(1);
        while (!newLift.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }        sleep(100);
        claw.setClawClosed(false);
        newLift.goToPosition(0);

       /* while (!newLift.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }        sleep(100);*/
        sleep(500);
        elbowArm.gotoPosition(0);
        /*while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }*/

        //cone 2
        goTo(CONE_GRAB_INT);
        elbowArm.gotoPositionAuto(3);
        sleep(100);
        goTo(CONE_GRAB2);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(2);
        sleep(200);
        goTo(HighJunctDeliver);
        elbowArm.gotoPosition(3);
        sleep(300);
        newLift.goToPosition(1);
        while (!newLift.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }        sleep(100);
        claw.setClawClosed(false);
        newLift.goToPosition(0);
        while (!newLift.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }        sleep(100);
        sleep(300);
       /* while (!newLift.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }        sleep(100);*/
        //cone 3
        elbowArm.gotoPosition(0);
        goTo(CONE_GRAB_INT);
        elbowArm.gotoPositionAuto(4);
        sleep(100);
        goTo(CONE_GRAB3);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(3);
        sleep(200);
        setSpeed(Speed.FAST);
        goTo(HighJunctDeliver);
        //deliver cone #1
        elbowArm.gotoPosition(3);
        sleep(300);
        newLift.goToPosition(1);
        while (!newLift.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }        sleep(100);
        claw.setClawClosed(false);
        newLift.goToPosition(0);
       /* while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }*/







        /*
        sleep(100);
        elbowArm.dropOnMedium();
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);
        sleep(100);
        //cone 2
        elbowArm.gotoPosition(0);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
     //   setSpeed(Speed.SLOW);
        goTo(CONE_GRAB_INT);
        elbowArm.gotoPositionAuto(3);
        sleep(100);
        goTo(CONE_GRAB2);
        sleep(100);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(2);
        sleep(200);
        setSpeed(Speed.FAST);
        goTo(INIT_CONE_DEL2);
        sleep(100);
        elbowArm.dropOnMedium();
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);
        sleep(100);
        //cone 3
        elbowArm.gotoPosition(0);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
      //  setSpeed(Speed.SLOW);
        goTo(CONE_GRAB_INT2);
        elbowArm.gotoPositionAuto(5);
        sleep(100);
        goTo(CONE_GRAB3);
        sleep(100);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(4);
        sleep(200);
        setSpeed(Speed.FAST);
        goTo(INIT_CONE_DEL3);
        sleep(100);
        elbowArm.dropOnMedium();
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);
        sleep(100);
        elbowArm.gotoPositionAuto(0);*/
        /*//cone 4
        elbowArm.gotoPosition(0);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        //  setSpeed(Speed.SLOW);
        goTo(CONE_GRAB_INT2);
        elbowArm.gotoPositionAuto(7);
        sleep(100);
        goTo(CONE_GRAB4);
        sleep(100);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(6);
        sleep(200);
        setSpeed(Speed.FAST);
        goTo(INIT_CONE_DEL4);
        sleep(100);
        elbowArm.dropOnMedium();
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);*/


      /*  sleep(100);
        elbowArm.gotoPositionAuto(3);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        sleep(200);
        goTo(CONE_GRAB2);
        sleep(100);
      //  elbowArm.gotoPositionAuto(3);
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        sleep(500);
        claw.setClawClosed(true);
        sleep(100);
        elbowArm.gotoPositionAuto(1);
        goTo(INIT_CONE_DEL);
        elbowArm.dropOnMedium();
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }
        sleep(100);
        claw.setClawClosed(false);
        sleep(100);
        elbowArm.gotoPositionAuto(0);
        sleep(500);*/


    }
}

