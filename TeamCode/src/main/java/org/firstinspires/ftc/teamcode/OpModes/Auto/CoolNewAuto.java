package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.Auto.AutoOpMode;

@Config
@Autonomous(group="!CompOpModes")
public class CoolNewAuto extends AutoOpMode {
    public static Position START = new Position(-36, -63, 90);

    public static Position INIT_CONE_DEL = new Position(-33, -14.5, 302);
    public static Position INIT_CONE_DEL_INT = new Position(-39, -25, 95);
    public static Position INIT_CONE_DEL_INT2 = new Position(-39, -11, 95);
    public static Position INIT_CONE_DEL2 = new Position(-40, -12.5, 0);
    public static Position INIT_CONE_DEL3 = new Position(-40, -12.5, 0);
    public static Position INIT_CONE_DEL4 = new Position(-40, -12.5, 0);
    public static Position INIT_CONE_DEL5 = new Position(-40, -12.5, 0);


    public static Position STRAFE_TO = new Position(-38.5, -10, 90);
    public static Position CONE_GRAB = new Position(-52, -12, 0);
    public static Position CONE_GRAB2 = new Position(-54.5, -12, 0);
    public static Position CONE_GRAB3 = new Position(-52, -12, 0);
    public static Position CONE_GRAB4 = new Position(-52, -12, 0);
    public static Position CONE_GRAB5 = new Position(-52, -12, 0);
    public static Position POS_1_PARK_INT = new Position(-36, -15, 90);
    public static Position POS_1_PARK = new Position(-65, -15, 93);

    public static Position POS_2_PARK = new Position(-36, -24, 92);

    public static Position POS_3_INT = new Position(-36, -12, 93);
    public static Position POS_3_PARK = new Position(-12, -12, 93);


    //DcMotorEx elbowArm;


    @Override
    public void runOpMode() {
        setup();
        coneDrop();
        /*switch (camera.getTagOfInterest()) {
            case 1:

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
                goTo(POS_3_PARK);*/






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

        claw.setClawClosed(true);
        setStartPosition(START);
       elbowArm.gotoPosition(0);


//        elbowArm.dropOnMedium();
//        while (!elbowArm.reachedTarget()) {
//            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
//            telemetry.update();
//        }
//        sleep(150);
//        claw.setClawClosed(false);
//        sleep(200);
//        elbowArm.gotoPositionAuto(2);
//        while (!elbowArm.reachedTarget()) {
//            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
//            telemetry.update();
//            ;  // this will auto close the
//        }

        goTo(INIT_CONE_DEL_INT);//drive to intermediary position
        sleep(100); //sleep
        goTo(INIT_CONE_DEL_INT2);
       goTo(INIT_CONE_DEL);//turn to cone deliver position
        sleep(100);
       elbowArm.dropOnMedium();//elbow arm function to deliver the cone
        while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());

        }
        //elbowArm.gotoPositionAuto(2);
        sleep(100);

        sleep(100);
     //   claw.setClawClosed(true);

     //   elbowArm.gotoPositionAuto(0);

      //  goTo(INIT_CONE_DEL);
      //  elbowArm.dropOnMedium();
       /* while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }*/
      //  sleep(100);
       // claw.setClawClosed(false);
      //  sleep(100);
      //  elbowArm.gotoPositionAuto(3);
      /*  while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }
        sleep(200);*/
       /* goTo(CONE_GRAB2);
        sleep(100);*/
      //  elbowArm.gotoPositionAuto(3);
       /* while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
            ;  // this will auto close the
        }*/

       // claw.setClawClosed(true);
      /*  sleep(100);
     //   elbowArm.gotoPositionAuto(1);
        goTo(INIT_CONE_DEL);
        sleep(100);
        goTo(CONE_GRAB3);
        sleep(100);
        goTo(INIT_CONE_DEL);
        sleep(100);
        goTo(CONE_GRAB4);
        sleep(100);
        goTo(INIT_CONE_DEL);
        sleep(100);
        goTo(CONE_GRAB5);
        sleep(100);
        goTo(INIT_CONE_DEL);
        sleep(100);*/
      //  elbowArm.dropOnMedium();
      /*  while (!elbowArm.reachedTarget()) {
            telemetry.addData("elbow arm position:", elbowArm.getElbowPosition());
            telemetry.update();
        }*/



    }
}

