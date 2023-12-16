package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(group="!CompOpModes")
public class RedScoringSide extends AutoOpMode {
    public static Position START = new Position(7.5, -63.375, 90);
    public static Position START_CENTER = new Position(12,-60,90);
    public static Position SPIKE_LEFT = new Position(12, -36, 135);
    public static Position SPIKE_LEFT_BACKUP = new Position(12, -44, 135);
    public static Position SPIKE_LEFT_ADJUST_HEADING = new Position(12, -44, 90);
    public static Position SPIKE_CENTER = new Position(12, -36, 90);
    public static Position SPIKE_CENTER_BACKUP = new Position(12, -44, 90);


    public static Position SPIKE_RIGHT = new Position(25, -44.625, 90);
    public static Position SPIKE_RIGHT_BACKUP = new Position(25, -52, 90);



    public static Position DROP_START_INTERMEDIATE = new Position(36, -60, 180);

    public static Position DROP_PARK_INTERMEDIATE = new Position(44, -60, 180);
    public static Position DROP_POSITION = new Position(44, -40, 180);
    public static Position DROP_POSITION_TOUCH_BOARD = new Position(52, -40, 180);
    public static Position DROP_POSITION_BACKUP_BOARD = new Position(44, -40, 180);


    public static Position PARK_POSITION = new Position(60, -60, 180);

    @Override
    public void runOpMode() {
        setup();
        setStartPosition(START);
        switch (3) {   // camer detection value goes herre
            case 1:
                goTo(SPIKE_LEFT);
                intake.returnPixel();
                sleep(2000);
                goTo(SPIKE_LEFT_BACKUP);
                goTo(SPIKE_LEFT_ADJUST_HEADING);
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
                coneDrop(3);
        }
    }
    public void coneDrop(int casee) {
        intake.stop();
        goTo(START_CENTER);
        goTo(DROP_START_INTERMEDIATE);
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

