package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(group="!CompOpModes")
public class RedCollectionSide extends AutoOpMode {
    public static Position START = new Position(-40.5, -63.375, 90);
    public static Position SPIKE_POSITION = new Position(-48, -44.625, 90);
    public static Position TURN_TEST_POSITION = new Position(-36, -36, 0);

    public static Position SPIKE_CENTER = new Position(-36, -36, 90);

    public static Position DROP_PARK_INTERMEDIATE = new Position(-48, -60, 0);
    public static Position DROP_POSITION = new Position(-48, -36, 0);
    public static Position PARK_POSITION = new Position(-60, -60, 0);

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
        setStartPosition(START);
        //goTo(SPIKE_POSITION);
        goTo(SPIKE_CENTER);
        goTo(TURN_TEST_POSITION);
        //goTo(DROP_POSITION);
        //goTo(DROP_PARK_INTERMEDIATE);
        //goTo(PARK_POSITION);

    }
}

