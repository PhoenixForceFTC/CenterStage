package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(group="!CompOpModes")
public class RedScoringSide extends AutoOpMode {
    public static Position START = new Position(60, 36, 90);
    public static Position SPIKE_POSITION = new Position(36, 32, 90);
    public static Position DROP_PARK_INTERMEDIATE = new Position(60, 48, 0);
    public static Position DROP_POSITION = new Position(36, 48, 0);
    public static Position PARK_POSITION = new Position(60, 60, 0);
    public static Position POS_1_PARK_INT = new Position(-36, -15, 85);
    public static Position POS_1_PARK = new Position(-65, -15, 90);
    public static Position POS_2_PARK = new Position(-36, -24, 92);
    public static Position POS_3_INT = new Position(-36, -8, 93);
    public static Position POS_3_PARK = new Position(-12, -12, 93);

    @Override
    public void runOpMode() {
        setup();
        switch (1) {   // camer detection value goes herre
            case 1:
                coneDrop();
                goTo(POS_1_PARK_INT);
                goTo(POS_1_PARK);
                break;
            case 2:
                coneDrop();
                goTo(POS_2_PARK);
                break;

            case 3:
                coneDrop();
                goTo(POS_3_INT);
                sleep(100);
                goTo(POS_3_PARK);
        }
    }
    public void coneDrop() {
        setStartPosition(START);
        goTo(SPIKE_POSITION);
        goTo(DROP_POSITION);
        goTo(DROP_PARK_INTERMEDIATE);
        goTo(PARK_POSITION);

    }
}

