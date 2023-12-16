package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(group="!CompOpModes")
public class RedCollectionSide extends AutoOpMode {
    public static Position START = new Position(-40.5, -63.375, 90);
    public static Position SPIKE_POSITION_L = new Position(-49, -44.625, 90);
    public static Position SPIKE_POSITION_L_BACKUP = new Position(-49, -52, 90);
    public static Position LEFT_WALL = new Position(-58, -52, 90);
    public static Position COLLECTION_SIDE = new Position(-58, -12, 90);
    public static Position COLLECTION_SIDE_TURN =  new Position(-55, -12, 180);
    public static Position DROP_SIDE =  new Position(44, -12, 180);
    public static Position SPIKE_CENTER = new Position(-36, -36, 90);
    public static Position SPIKE_CENTER_BACKUP = new Position(-36, -44, 90);
    public static Position SPIKE_RIGHT = new Position(-36, -36, 45);
    public static Position SPIKE_RIGHT_BACKUP = new Position(-36, -44, 45);
    public static Position SPIKE_RIGHT_ADJUST_HEADING = new Position(-36, -44, 90);

    public static Position DROP_PARK_INTERMEDIATE = new Position(44, -16, 180);
    public static Position DROP_POSITION = new Position(44, -40, 180);
    public static Position DROP_POSITION_TOUCH_BOARD = new Position(52, -40, 180);
    public static Position DROP_POSITION_BACKUP_BOARD = new Position(44, -40, 180);


    public static Position PARK_POSITION = new Position(60, -16, 180);

    @Override
    public void runOpMode() {
        setup();
        setStartPosition(START);
        switch (3) {   // camer detection value goes herre
            case 1:
                goTo(SPIKE_POSITION_L);
                intake.returnPixel();
                sleep(2000);
                goTo(SPIKE_POSITION_L_BACKUP);
                coneDrop(1);

                break;
            case 2:
                goTo(SPIKE_CENTER);
                intake.returnPixel();
                sleep(2000);
                goTo(SPIKE_CENTER_BACKUP);
                coneDrop(2);
                break;
            case 3:
                goTo(SPIKE_RIGHT);
                intake.returnPixel();
                sleep(2000);
                goTo(SPIKE_RIGHT_BACKUP);
                goTo(SPIKE_RIGHT_ADJUST_HEADING);
                coneDrop(3);
        }
    }
    public void coneDrop(int casee) {
        intake.stop();
        goTo(LEFT_WALL);
        goTo(COLLECTION_SIDE);
        goTo(COLLECTION_SIDE_TURN);
        goTo(DROP_SIDE);
        goTo(DROP_POSITION);
        drop.goToPosition(3);
        setSpeed(Speed.SLOW);
        goTo(DROP_POSITION_TOUCH_BOARD);
        sleep(1000);
        topGate.setGateOpen();
        telemetry.update();
        sleep(1000);
        topGate.setGateStopped();
        goTo(DROP_POSITION_BACKUP_BOARD);
        setSpeed(Speed.MEDIUM);
        drop.goToBottom();
        sleep(1000);
        goTo(DROP_PARK_INTERMEDIATE);
        goTo(PARK_POSITION);
        sleep(10000);

    }
}

