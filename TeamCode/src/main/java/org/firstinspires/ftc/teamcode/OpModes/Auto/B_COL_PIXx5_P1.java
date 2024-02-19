package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.sun.source.tree.ConditionalExpressionTree;

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
    public static Position STACK1 = new Position(-36, 59, 215);
    public static Position STACK1STRAFE = new Position(-36, 55, 215);
    
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
        spikeLocation = Vision.IDENTIFIED_SPIKE_MARK_LOCATION.RIGHT; //--- Set default value to left
        //spikeLocation = vision.getPixelLocation();
        //vision.Stop();

        //--- Initialize
        setup();
        setStartPosition(START);

        //--- Drive based on spike position
        switch (spikeLocation) {
            case LEFT:
                //LeftSpike();
                goTo(CENTERPOS);
                SpikeMovementPaths(CENTERPOS_L);
                break;
            case MIDDLE:
                SpikeMovementPaths(CENTERPOS);
                break;
            case RIGHT:
                goTo(CENTERPOS);
                SpikeMovementPaths(CENTERPOS_R);
                break;
        }

//        //--- Park
//        goTo(PARK_ARENA_CENTER);
//        //goTo(PARK_ARENA_WALL);
//        //goTo(PARK_BACKDROP_CENTER);
        
        sleep(5000);
    }

    private void SpikeMovementPaths(Position CenterPos)
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
        goTo(STACK1);
        snagger.goToPosition(4);
        sleep(2000);
        goTo(STACK1STRAFE);
        intake.eatPixel();
        sleep(1000);

    }

}

