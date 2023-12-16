package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(group="!CompOpModes")
public class TestAutoOpMode extends AutoOpMode {
    public static Position START = new Position(-40.5, -63.375, 90);
    public static Position SPIKE_POSITION_L = new Position(-48, -44.625, 90);
    public static Position SPIKE_POSITION_L_BACKUP = new Position(-48, -52, 90);
    public static Position LEFT_WALL = new Position(-55, -52, 90);
    public static Position COLLECTION_SIDE = new Position(-55, -12, 90);
    public static Position COLLECTION_SIDE_TURN =  new Position(-55, -12, 180);
    public static Position DROP_SIDE =  new Position(42, -12, 180);




    public static Position SPIKE_CENTER = new Position(-36, -36, 90);

    public static Position DROP_PARK_INTERMEDIATE = new Position(42, -60, 180);
    public static Position DROP_POSITION = new Position(42, -36, 180);
    public static Position PARK_POSITION = new Position(60, -60, 180);

    @Override
    public void runOpMode() {
        setup();
        switch (1) {   // camer detection value goes herre
            case 1:
                coneDrop();
                break;
            case 2:
                coneDrop();
                break;

            case 3:
                coneDrop();
        }
    }
    public void coneDrop() {
        boolean loopIsOver = false;
        drop.goToPosition(3);
        sleep(5000);
            topGate.setGateOpen();



            telemetry.update();
        sleep(15000);
        drop.goToBottom();
        sleep(5000);
        sleep(10000);

    }
}

