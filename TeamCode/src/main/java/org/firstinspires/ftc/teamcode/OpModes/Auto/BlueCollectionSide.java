package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Vision;

@Config
@Autonomous(group="!CompOpModes")
public class BlueCollectionSide extends AutoOpMode {
    private Vision vision;
    private Vision.IDENTIFIED_SPIKE_MARK_LOCATION spikeLocation;
    public static Position START = new Position(-39, 63.375, 270);
    public static Position SPIKE_POSITION_R = new Position(-47.5, 41.625, 270);
    public static Position SPIKE_POSITION_R_BACKUP = new Position(-47.5, 52, 270);
    public static Position LEFT_WALL = new Position(-58, 52, 270);
    public static Position LEFT_WALL_COLLECTION_INTERMEDIATE = new Position(-58, 12, 270);
    public static Position COLLECTION_SIDE = new Position(-54, 12, 270);
    public static Position COLLECTION_SIDE_TURN =  new Position(-54, 12, 180);
    public static Position DROP_SIDE =  new Position(44, 12, 180);
    public static Position SPIKE_CENTER = new Position(-41, 34, 270);
    public static Position SPIKE_CENTER_BACKUP = new Position(-39, 44, 270);
    public static Position SPIKE_LEFT = new Position(-39, 36, 270);
    public static Position SPIKE_LEFT_TURN = new Position(-39,36, 345);
    public static Position SPIKE_LEFT_FORWARD = new Position(-34, 36, 345);

    public static Position SPIKE_LEFT_BACKUP = new Position(-39, 44, 345);
    public static Position SPIKE_LEFT_ADJUST_HEADING = new Position(-39, 44, 270);




    public static Position DROP_PARK_INTERMEDIATE = new Position(46, 16, 180);
    public static Position DROP_POSITION = new Position(44, 36, 180);
    public static Position DROP_POSITION_TOUCH_BOARD = new Position(54, 36, 180);
    public static Position DROP_POSITION_BACKUP_BOARD = new Position(46, 36, 180);



    public static Position DROP_POSITION_L = new Position(44, 42, 180);
    public static Position DROP_POSITION_TOUCH_BOARD_L = new Position(54, 42, 180);
    public static Position DROP_POSITION_BACKUP_BOARD_L = new Position(46, 42, 180);

    public static Position DROP_POSITION_R = new Position(44, 32, 180);
    public static Position DROP_POSITION_TOUCH_BOARD_R = new Position(54, 32, 180);
    public static Position DROP_POSITION_BACKUP_BOARD_R= new Position(46, 32, 180);


    public static Position PARK_POSITION = new Position(60, 16, 180);

    @Override
    public void runOpMode() {
        vision = new Vision(this, Vision.START_POSITION.BLUE_COL);
        vision.initTfod();

        while (!isStopRequested() && !opModeIsActive()) {

            //Run Vuforia Tensor Flow and keep watching for the identifier in the Signal Cone.
            vision.runTfodTensorFlow();
            telemetry.addData("Vision identified Parking Location", vision.getPixelLocation());
            telemetry.update();
        }
        spikeLocation = vision.getPixelLocation();
        //vision.Stop();
        setSpeed(Speed.MEDIUM);
        setup();
        setStartPosition(START);
        switch (spikeLocation) {   // camer detection value goes herre
            case RIGHT:
                goTo(SPIKE_POSITION_R);
                intake.returnPixel();
                sleep(200);
                goTo(SPIKE_POSITION_R_BACKUP);
                coneDrop(3);

                break;
            case MIDDLE:
                goTo(SPIKE_CENTER);
                intake.returnPixel();
                sleep(200);
                goTo(SPIKE_CENTER_BACKUP);
                coneDrop(2);
                break;
            case LEFT:
                goTo(SPIKE_LEFT);
                goTo(SPIKE_LEFT_TURN);
                goTo(SPIKE_LEFT_FORWARD);
                intake.returnPixel();
                sleep(200);
                goTo(SPIKE_LEFT_TURN);
                goTo(SPIKE_LEFT_BACKUP);
                goTo(SPIKE_LEFT_ADJUST_HEADING);
                coneDrop(1);
        }
    }
    public void coneDrop(int casee) {
        setSpeed(Speed.FAST);
        intake.stop();
        goTo(LEFT_WALL);
        goTo(LEFT_WALL_COLLECTION_INTERMEDIATE);
        goTo(COLLECTION_SIDE);
        goTo(COLLECTION_SIDE_TURN);
        goTo(DROP_SIDE);
        setSpeed(Speed.MEDIUM);
        if(casee==3){
            goTo(DROP_POSITION_R);
            drop.goToPosition(3);
            setSpeed(Speed.SLOW);
            goTo(DROP_POSITION_TOUCH_BOARD_R);
            sleep(500);
            topGate.setGateOpen();
            telemetry.update();
            sleep(1000);
            topGate.setGateStopped();
            goTo(DROP_POSITION_BACKUP_BOARD_R);
        }
        if(casee==2){
            goTo(DROP_POSITION);
            drop.goToPosition(3);
            setSpeed(Speed.SLOW);
            goTo(DROP_POSITION_TOUCH_BOARD);
            sleep(500);
            topGate.setGateOpen();
            telemetry.update();
            sleep(1000);
            topGate.setGateStopped();
            goTo(DROP_POSITION_BACKUP_BOARD);
        }
        if(casee==1){
            goTo(DROP_POSITION_L);
            drop.goToPosition(3);
            setSpeed(Speed.SLOW);
            goTo(DROP_POSITION_TOUCH_BOARD_L);
            sleep(500);
            topGate.setGateOpen();
            telemetry.update();
            sleep(1000);
            topGate.setGateStopped();
            goTo(DROP_POSITION_BACKUP_BOARD_L);
        }
        setSpeed(Speed.MEDIUM);
        drop.goToBottom();
        sleep(1000);
        goTo(DROP_PARK_INTERMEDIATE);
        sleep(10000);
    }
}
