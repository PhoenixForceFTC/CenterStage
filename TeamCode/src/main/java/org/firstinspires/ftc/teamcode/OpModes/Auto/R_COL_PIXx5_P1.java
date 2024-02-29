package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Vision;
import org.firstinspires.ftc.teamcode.trajectorysequence.sequencesegment.TurnSegment;

@Config
@Autonomous(group="!CompOpModes")
public class R_COL_PIXx5_P1 extends AutoOpMode {
    private Vision vision;

    //--- adb connect 192.168.43.1:5555

    private Vision.IDENTIFIED_SPIKE_MARK_LOCATION spikeLocation;

    //------------------------------------------------------------
    //--- Field Positions ---
    //------------------------------------------------------------

    //--- Collecting Side, align left side of the tile
    public static Position START = new Position(-38, -63, 90);

    //--- Deliver
    public static Position LEFT_SPIKE_DEPOSIT = new Position(0, 0, 25);
    public static Position TURN_INTAKE_PIXEL = new Position(0, 0, 55);
    public static Position RIGHT_SPIKE_TURN_ANGLE = new Position(0,0,-30);
    public static Position MIDDLE_SPIKE = new Position(22, -28, 180);
    public static Position RIGHT_SPIKE_DEPOSIT = new Position(-38, -32, 180);
    
    //--- Collection positions under the scaffolding
    public static Position COLLECT_SCAFFOLDING_CENTER = new Position(-17, -15, 185);
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
        spikeLocation = Vision.IDENTIFIED_SPIKE_MARK_LOCATION.LEFT; //--- Set default value to left
        //spikeLocation = vision.getPixelLocation();
        //vision.Stop();

        //--- Initialize
        setup();
        setStartPosition(START);

        //--- Drive based on spike position
        switch (spikeLocation) {
            case LEFT:
                //LeftSpike();
                SpikeMovementPaths(LEFT_SPIKE_DEPOSIT, LEFT_BACKDROP, LEFT_BACKDROP_CLOSE, LEFT_INTERMEDIATE,
                        COLLECT_SCAFFOLDING_CENTER, COLLECT_SCAFFOLDING_CENTER_INTERMEDIATE, BACKDROP_CENTER, BACKDROP_CENTER_CLOSE);
                break;
            case MIDDLE:
                SpikeMovementPaths(MIDDLE_SPIKE, MIDDLE_BACKDROP, MIDDLE_BACKDROP_CLOSE, MIDDLE_INTERMEDIATE,
                        COLLECT_SCAFFOLDING_CENTER, COLLECT_SCAFFOLDING_CENTER_INTERMEDIATE, BACKDROP_CENTER, BACKDROP_CENTER_CLOSE);
                //CenterSpike();
                break;
            case RIGHT:
                SpikeMovementPaths(RIGHT_SPIKE_DEPOSIT, RIGHT_BACKDROP, RIGHT_BACKDROP_CLOSE, RIGHT_INTERMEDIATE,
                        COLLECT_SCAFFOLDING_CENTER, COLLECT_SCAFFOLDING_CENTER_INTERMEDIATE, BACKDROP_CENTER, BACKDROP_CENTER_CLOSE);
                //RightSpike();
                break;
        }

//        //--- Park
//        goTo(PARK_ARENA_CENTER);
//        //goTo(PARK_ARENA_WALL);
//        //goTo(PARK_BACKDROP_CENTER);
        
        sleep(5000);
    }

    private void SpikeMovementPaths(Position SpikePos, 
                           Position BackdropPos, Position BackdropClosePos, 
                           Position IntermediatePos, 
                           Position Collect, Position CollectIntermediate,
                           Position BackdropCenter, Position BackdropCenterClose)


    {
        turn(LEFT_SPIKE_DEPOSIT.HEADING);
        snagger.goToPosition(5);
        sleep(1000);
        intake.returnPixel();
        sleep(200);
        intake.stop();
        snagger.goToPosition(0);
        turn(TURN_INTAKE_PIXEL.HEADING);
        snagger.goToPosition(4);
        intake.eatPixel();
         //-- Full out at slow speed
         //--- Reverse front wheel to not trap a 3rd pixel

        sleep(3000);
       //intake.stop();



//        //--- Drive to spike and eject pixel
//        goTo(SpikePos);
//        intake.returnPixel();
//        sleep(200);
//        intake.stop();
//
//        //--- Drive to the backdrop
//        drop.goToPosition(3); //--- Up
//        goTo(BackdropPos);
//        setSpeed(Speed.VERY_SLOW); //--- Slow down before moving back a little
//        goTo(BackdropClosePos);
//        setSpeed(Speed.FAST);
//
//        //--- Deliver pixel
//        topGate.setGateOpen();
//        sleep(1000);
//        topGate.setGateStopped();
//        goTo(BackdropPos);
//
//        //--- Drive under the scaffolding via the intermediate position (to avoid hitting purple pixel)
//        //intake.stop();
//        drop.goToPosition(0);
//        goTo(IntermediatePos);
//        goTo(CollectIntermediate); //--- Move to intermediate point near collection
//        setSpeed(Speed.MEDIUM);
//        goTo(Collect);
//
//        //--- Send out grabber to collect pixels
//        snagger.goToPosition(3); //--- Collector almost full out
//        intake.eatPixel();
//        snagger.goToPosition(4, 0.25); //-- Full out at slow speed
//        sleep(4000);
//        intake.frontWheelReverse(); //--- Reverse front wheel to not trap a 3rd pixel
//        snagger.goToPosition(0); //--- Retract
//        //sleep(3000);
//        //intake.stop();
//
//        //--- Move back to backdrop
//        goTo(Collect); //--- Move to collect spot to correct for movement from grabber
//        snagger.goToPosition(0);
//        goTo(IntermediatePos);
//        snagger.goToPosition(0); //--- Retract grabber as it comes out when we move backwards
//
//        //--- Transfer pixels
//        intake.transferPixel();
//        sleep(2000); //--- Extra sleep to allow transfer to finish (TODO: make this based on sensors)
//        intake.stop();
//        drop.goToPosition(4); //--- Up (higher so we don't know pixels off)
//        goTo(BackdropCenter);
//        setSpeed(Speed.VERY_SLOW); //--- Slow down before moving back a little
//        goTo(BackdropCenterClose);
//        setSpeed(Speed.FAST);
//
//        //--- Drop the pixel
//        topGate.setGateOpen();
//        sleep(2000);
//        topGate.setGateStopped();
//
//        //--- Move away from board
//        goTo(BACKDROP_CENTER);
//        drop.goToPosition(0); //--- Down
    }

}

