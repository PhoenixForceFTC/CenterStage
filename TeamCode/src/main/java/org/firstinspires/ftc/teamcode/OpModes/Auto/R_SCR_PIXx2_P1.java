package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Vision;

@Config
@Autonomous(group="!CompOpModes")
public class R_SCR_PIXx2_P1 extends AutoOpMode {
    private Vision vision;

    //--- adb connect 192.168.43.1:5555

    private Vision.IDENTIFIED_SPIKE_MARK_LOCATION spikeLocation;

    //------------------------------------------------------------
    //--- Field Positions ---
    //------------------------------------------------------------

    //--- Scoring Side, align right side of the tile
    public static Position START = new Position(14, -63, 90);

    //--- Deliver
    public static Position LEFT_SPIKE = new Position(8, -35, 180);
    public static Position MIDDLE_SPIKE = new Position(22, -28, 180);
    public static Position RIGHT_SPIKE = new Position(27, -36, 180);
    
    //--- Collection positions under the scaffolding
    public static Position COLLECT_SCAFFOLDING_CENTER_TURN = new Position(-21, -17, 195);
    public static Position COLLECT_SCAFFOLDING_CENTER = new Position(-21, -17, 185);
    public static Position COLLECT_SCAFFOLDING_CENTER_INTERMEDIATE = new Position(-10, -15, 185);
    
    //--- Before and after going to collect under the scaffolding, we need to have an intermediate position
    public static Position LEFT_INTERMEDIATE = new Position(12, -12, 180);
    public static Position MIDDLE_INTERMEDIATE = new Position(24, -12, 180);
    public static Position RIGHT_INTERMEDIATE = new Position(32, -12, 180);

    //--- Parking
    public static Position PARK_ARENA_CENTER = new Position(44, -15, 180);
    public static Position PARK_BACKDROP_CENTER = new Position(44, -37, 180);
    public static Position PARK_ARENA_WALL = new Position(44, -60, 180);
    
    //--- Drop positions (LEFT)
    public static Position LEFT_BACKDROP = new Position(47, -30, 180);
    public static Position LEFT_BACKDROP_CLOSE = new Position(50, -30, 180);
    
    //--- Drop positions (MIDDLE)
    public static Position MIDDLE_BACKDROP = new Position(47, -37, 180);
    public static Position MIDDLE_BACKDROP_CLOSE = new Position(50, -37, 180);
    
    //--- Drop positions (RIGHT)
    public static Position RIGHT_BACKDROP = new Position(47, -43, 180);
    public static Position RIGHT_BACKDROP_CLOSE = new Position(50, -43, 180);
    
    //--- Shared position to drop the pixel on the backdrop 
    public static Position BACKDROP_CENTER = new Position(44, -37, 180);
    public static Position BACKDROP_CENTER_CLOSE = new Position(50, -37, 180);

    
    @Override
    public void runOpMode() {

        //--- Initialize vision
        vision = new Vision(this, Vision.START_POSITION.RED_SCORING);
        vision.initTfod();

        //--- Wait for Start
        while (!isStopRequested() && !opModeIsActive()) {
            //--- Run Vuforia Tensor Flow and keep watching for the identifier in the Signal Cone
            vision.runTfodTensorFlow();
            telemetry.addData("Vision identified Parking Location", vision.getPixelLocation());
            telemetry.update();
        }

        //--- Read the final position of the spike
        //spikeLocation = Vision.IDENTIFIED_SPIKE_MARK_LOCATION.RIGHT; //--- Set default value to left
        spikeLocation = vision.getPixelLocation();
        //vision.Stop();

        //--- Initialize
        setup();
        setStartPosition(START);

        //--- Initialize
        swinch.setClawClosed(true); //--- close the claw

        //--- Drive based on spike position
        switch (spikeLocation) {
            case LEFT:
                //LeftSpike();
                SpikeMovementPaths(LEFT_SPIKE, LEFT_BACKDROP, LEFT_BACKDROP_CLOSE, LEFT_INTERMEDIATE,
                        COLLECT_SCAFFOLDING_CENTER, COLLECT_SCAFFOLDING_CENTER_INTERMEDIATE, BACKDROP_CENTER, BACKDROP_CENTER_CLOSE,COLLECT_SCAFFOLDING_CENTER_TURN);
                break;
            case MIDDLE:
                SpikeMovementPaths(MIDDLE_SPIKE, MIDDLE_BACKDROP, MIDDLE_BACKDROP_CLOSE, MIDDLE_INTERMEDIATE,
                        COLLECT_SCAFFOLDING_CENTER, COLLECT_SCAFFOLDING_CENTER_INTERMEDIATE, BACKDROP_CENTER, BACKDROP_CENTER_CLOSE,COLLECT_SCAFFOLDING_CENTER_TURN);
                //CenterSpike();
                break;
            case RIGHT:
                SpikeMovementPaths(RIGHT_SPIKE, RIGHT_BACKDROP, RIGHT_BACKDROP_CLOSE, RIGHT_INTERMEDIATE,
                        COLLECT_SCAFFOLDING_CENTER, COLLECT_SCAFFOLDING_CENTER_INTERMEDIATE, BACKDROP_CENTER, BACKDROP_CENTER_CLOSE,COLLECT_SCAFFOLDING_CENTER_TURN);
                //RightSpike();
                break;
        }

        //--- Finalize
        swinch.setClawClosed(true); //--- close the claw

        //--- Park
     //   goTo(PARK_ARENA_CENTER);
        goTo(PARK_ARENA_WALL);
        //goTo(PARK_BACKDROP_CENTER);

        sleep(5000);
    }

    private void SpikeMovementPaths(Position SpikePos,
                           Position BackdropPos, Position BackdropClosePos,
                           Position IntermediatePos,
                           Position Collect, Position CollectIntermediate,
                           Position BackdropCenter, Position BackdropCenterClose,
                            Position CollectTurn)
    {
        //--- Drive to spike and eject pixel
        goTo(SpikePos);
        intake.returnPixel();
        sleep(250);
        intake.stop();

        //--- Drive to the backdrop
        drop.goToPosition(3); //--- Up
        goTo(BackdropPos);
        setSpeed(Speed.VERY_SLOW); //--- Slow down before moving back a little
        goTo(BackdropClosePos);
        setSpeed(Speed.FAST);

        //--- Deliver pixel
        topGate.setGateOpen();
        sleep(1000);
        topGate.setGateStopped();
        drop.goToPosition(0);
        intake.stop();

       ; //--- Down*/
    }

}

