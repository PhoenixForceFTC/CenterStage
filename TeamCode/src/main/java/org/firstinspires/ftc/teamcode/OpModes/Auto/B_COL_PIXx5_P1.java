package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Vision;

@Config
@Autonomous(group="!CompOpModes")
public class B_COL_PIXx5_P1 extends AutoOpMode {
    private Vision vision;

    //--- adb connect 192.168.43.1:5555

    private Vision.IDENTIFIED_SPIKE_MARK_LOCATION spikeLocation;

    //------------------------------------------------------------
    //--- Field Positions ---
    //------------------------------------------------------------

    //--- Collecting Side, align left side of the tile
    public static Position START = new Position(-36, 63, 270);
    public static Position CENTERPOS = new Position(-36, 59, 270);
    public static Position CENTERPOS_L = new Position(-36, 59, 292);

    public static Position CENTERPOS_R = new Position(-36, 59, 248);
    public static Position STACK1 = new Position(-36, 59, 210);
    public static Position STACK1STRAFE = new Position(-40, 59, 230);
    public static Position INTERMEDIATE = new Position(20, 59, 180);
    public static Position PARK_ARENA_CENTER = new Position(44, 15, 180);
    public static Position PARK_BACKDROP_CENTER = new Position(44, 37, 180);
    public static Position PARK_ARENA_WALL = new Position(44, 60, 180);

    //--- Drop positions (LEFT)
    public static Position RIGHT_BACKDROP = new Position(47, 30, 180);
    public static Position RIGHT_BACKDROP_CLOSE = new Position(50, 30, 180);

    //--- Drop positions (MIDDLE)
    public static Position MIDDLE_BACKDROP = new Position(47, 37, 180);
    public static Position MIDDLE_BACKDROP_CLOSE = new Position(53, 37, 180);

    //--- Drop positions (RIGHT)
    public static Position LEFT_BACKDROP = new Position(47, 43, 180);
    public static Position LEFT_BACKDROP_CLOSE = new Position(53, 43, 180);

    //--- Shared position to drop the pixel on the backdrop
    public static Position BACKDROP_CENTER = new Position(44, 37, 180);
    public static Position BACKDROP_CENTER_CLOSE = new Position(53, 37, 180);
    public static Position COLLECT_SCAFFOLDING_CENTER = new Position(-36, 60, 180);
    
    @Override
    public void runOpMode() {

        //--- Initialize vision
        vision = new Vision(this, Vision.START_POSITION.BLUE_LEFT);
        vision.initTfod();

        //--- Wait for Start
        while (!isStopRequested() && !opModeIsActive()) {
            //--- Run Vuforia Tensor Flow and keep watching for the identifier in the Signal Cone
            vision.runTfodTensorFlow();
            telemetry.addData("Vision identified Parking Location", vision.getPixelLocation());
            telemetry.update();
        }

        //--- Read the final position of the spike
       // spikeLocation = Vision.IDENTIFIED_SPIKE_MARK_LOCATION.LEFT; //--- Set default value to left
        spikeLocation = vision.getPixelLocation();
        //vision.Stop();

        //--- Initialize
        setup();
        setStartPosition(START);

        //--- Drive based on spike position
        switch (spikeLocation) {
            case LEFT:
                //LeftSpike();
                goTo(CENTERPOS);
                SpikeMovementPaths(
                        CENTERPOS_L,
                        STACK1,
                        STACK1STRAFE,
                        LEFT_BACKDROP ,
                        LEFT_BACKDROP_CLOSE ,
                        BACKDROP_CENTER,
                        BACKDROP_CENTER_CLOSE,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
            case MIDDLE:
                SpikeMovementPaths(
                        CENTERPOS,
                        STACK1,
                        STACK1STRAFE,
                        MIDDLE_BACKDROP ,
                        MIDDLE_BACKDROP_CLOSE ,
                        BACKDROP_CENTER, BACKDROP_CENTER_CLOSE,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
            case RIGHT:
                goTo(CENTERPOS);
                SpikeMovementPaths(
                        CENTERPOS_R,
                        STACK1,
                        STACK1STRAFE,
                        RIGHT_BACKDROP ,
                        RIGHT_BACKDROP_CLOSE ,
                        BACKDROP_CENTER,
                        BACKDROP_CENTER_CLOSE,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
        }

//        //--- Park
//        goTo(PARK_ARENA_CENTER);
//        //goTo(PARK_ARENA_WALL);
//        //goTo(PARK_BACKDROP_CENTER);
        
        sleep(5000);
    }

    private void SpikeMovementPaths(Position CenterPos,
                                    Position Stack,
                                    Position StackStrafe,
                                    Position BackdropPos,
                                    Position BackdropClosePos,
                                    Position BackdropCenter,
                                    Position BackdropCenterClose,
                                    Position Collect,
                                    Position IntermediatePos
                                    )
    {
     goTo(CenterPos);
        switch (spikeLocation) {
            case LEFT:
                snagger.goToPosition(1);
                sleep(2000);
                intake.returnPixel();
                sleep(200);
                intake.stop();
                sleep(300);
                snagger.goToPosition(0);
                sleep(2000);
                break;
            case MIDDLE:
                snagger.goToPosition(2);
                sleep(2000);
                intake.returnPixel();
                sleep(200);
                intake.stop();
                sleep(300);
                snagger.goToPosition(0);
                sleep(2000);
                break;
            case RIGHT:
                snagger.goToPosition(3);
                sleep(2000);
                intake.returnPixel();
                sleep(200);
                intake.stop();
                sleep(300);
                snagger.goToPosition(0);
                sleep(2000);
                break;
        }
        goTo(Stack);
        snagger.goToPosition(4);
        sleep(2000);
        goTo(StackStrafe);
        snagger.goToPosition(5);
        intake.eatPixel();
        sleep(1000);
        intake.eatPixel();
        intake.stop();
        swinch.setClawClosed(true);
        snagger.goToPosition(0);
        sleep(700);
        intake.transferPixel();
        sleep(1000);


        goTo(Collect);
        goTo(IntermediatePos);
        intake.stop();
        drop.goToPosition(3); //--- Up
        goTo(BackdropPos);
        setSpeed(Speed.VERY_SLOW); //--- Slow down before moving back a little
        goTo(BackdropClosePos);
        setSpeed(Speed.FAST);
        topGate.setGateOpen();
        sleep(1000);
        topGate.setGateStopped();
        goTo(BackdropPos);
        drop.goToPosition(0);
        intake.stop();
    }

}

