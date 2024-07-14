package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Vision;

@Config
@Autonomous(group="!CompOpModes")
public class R_MID_PIXx5_P1_CRI extends AutoOpMode {
    private Vision vision;

    //--- adb connect 192.168.43.1:5555

    private Vision.IDENTIFIED_SPIKE_MARK_LOCATION spikeLocation;
    //------------------------------------------------------------
    //--- Field Positions ---
    //------------------------------------------------------------

    //--- Collecting Side, align left side of the tile
    public static Position START = new Position(-36, -63, 360-270);
    public static Position CENTERPOS = new Position(-36, -58, 360-270);
    public static Position CENTERPOS_R = new Position(-36, -59, 360-292);//292
    public static Position CENTERPOS_L = new Position(-32, -59, 360-244); //248
    public static Position INTERMEDIATE = new Position(20, -59, 180);
    public static Position PARK_ARENA_CENTER = new Position(44, -13, 180);
    public static Position PARK_BACKDROP_CENTER = new Position(44, -37, 180);
    public static Position PARK_ARENA_WALL = new Position(44, -60, 180);


    //--- Drop positions (LEFT)
    public static Position LEFT_BACKDROP = new Position(45, -30, 180);

    public static Position LEFT_BACKDROP_MIDDLE = new Position(45, -36, 180);
    //Position to drop the white pixel slightly left of the drop pos
    public static Position LEFT_BACKDROP_MIDDLE_CLOSE = new Position(51, -36, 180);
    public static Position LEFT_BACKDROP_CLOSE = new Position(50, -30, 180);


    //--- Drop positions (MIDDLE)
    public static Position MIDDLE_BACKDROP = new Position(44, -36, 180); //
    public static Position MIDDLE_BACKDROP_LEFT = new Position(49, -42, 180); /// 39
    public static Position MIDDLE_BACKDROP_LEFT_CLOSE = new Position(50, -42, 180); /// 40
    public static Position MIDDLE_BACKDROP_CLOSE = new Position(49, -36, 180);



    //--- Drop positions (RIGHT)
    public static Position RIGHT_BACKDROP = new Position(45, -43, 180); /// x47

    public static Position RIGHT_BACKDROP_MIDDLE = new Position(45, -31, 180); ///x50
    public static Position RIGHT_BACKDROP_CLOSE = new Position(50, -43, 180);

    public static Position RIGHT_BACKDROP_MIDDLE_CLOSE = new Position(51, -31, 180);

    //--- Shared position to drop the pixel on the backdrop
    public static Position BACKDROP_CENTER = new Position(44, -37, 180);
    public static Position BACKDROP_CENTER_CLOSE = new Position(53, -37, 180);
    public static Position COLLECT_SCAFFOLDING_CENTER = new Position(-36, -60, 180); /// 61

    @Override
    public void runOpMode() {

        //--- Initialize vision
        vision = new Vision(this, Vision.START_POSITION.RED_COL);
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
                        LEFT_BACKDROP ,
                        LEFT_BACKDROP_MIDDLE,
                        LEFT_BACKDROP_MIDDLE_CLOSE,
                        LEFT_BACKDROP_CLOSE ,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
            case MIDDLE:
                SpikeMovementPaths(
                        CENTERPOS,
                        MIDDLE_BACKDROP ,
                        MIDDLE_BACKDROP_LEFT ,
                        MIDDLE_BACKDROP_LEFT_CLOSE,
                        MIDDLE_BACKDROP_CLOSE ,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
            case RIGHT:
                goTo(CENTERPOS);
                SpikeMovementPaths(
                        CENTERPOS_R,
                        RIGHT_BACKDROP ,
                        RIGHT_BACKDROP_MIDDLE,
                        RIGHT_BACKDROP_MIDDLE_CLOSE,
                        RIGHT_BACKDROP_CLOSE ,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
        }

//        //--- Park
        goTo(PARK_ARENA_CENTER);
        //goTo(PARK_ARENA_WALL);
        //goTo(PARK_BACKDROP_CENTER);

        sleep(5000);
    }

    private void SpikeMovementPaths(Position CenterPos,
                                    Position BackdropPos,
                                    Position BackdropPos2,
                                    Position BackdropPos2Close,
                                    Position BackdropClosePos,
                                    Position Collect,
                                    Position IntermediatePos
    )
    {
        //--- Move away from wall
        goTo(CenterPos);
        setSpeed(Speed.FAST);
        //--- Deliver Purple Pixel

        switch (spikeLocation) {
            case LEFT:
                snagger.goToPosition(1);
                break;
            case MIDDLE:
                snagger.goToPosition(2);
                break;
            case RIGHT:
                snagger.goToPosition(3);
                break;
        }


        sleep(1250);
        intake.returnPixel();
        sleep(225); // 225
        intake.stop();
        sleep(350);

        //--- Move intake back in
        snagger.goToPosition(0);
        sleep(1250);



        //--- Go under the scaffolding
        goTo(Collect);
        goTo(IntermediatePos);

        //--- Move the deployment up
        intake.stop();
        topGate.setGateClosed();
        drop.goToPosition(3); //--- Up

        //--- Deliver to the backboard
        goTo(BackdropPos);
        setSpeed(Speed.VERY_SLOW);
        goTo(BackdropClosePos); //-- yellow pixel
        sleep(500);
        topGate.setGateOpen();
        sleep(1250);
        topGate.setGateStopped();

        //--- Move away from backdrop
        setSpeed(Speed.FAST);
        goTo(BackdropPos);
        drop.goToPosition(0);
        intake.stop();
    }
}