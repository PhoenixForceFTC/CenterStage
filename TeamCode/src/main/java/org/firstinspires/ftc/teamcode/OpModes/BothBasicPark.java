package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(group="!CompOpModes")
public class BothBasicPark extends AutoOpMode {
    public static Position START = new Position(-36, -63, 90);

    public static Position POS_1_INT = new Position(-63, -61, 90); // 3 inches towards wall to avoid ground junction
    public static Position POS_1_PARK = new Position(-63, -24, 90);

    public static Position POS_2_PARK = new Position(-36, -24, 90);

    public static Position POS_3_INT = new Position(-12, -61, 90);
    public static Position POS_3_PARK = new Position(-12, -24, 90);

    @Override
    public void runOpMode() {
        setup();

        setStartPosition(START);

        switch(camera.getTagOfInterest()) {
            case 1:
                goTo(POS_1_INT);
                sleep(2 * 1000);
                goTo(POS_1_PARK);
                break;

            case 2:
                goTo(POS_2_PARK);
                break;

            case 3:
                goTo(POS_3_INT);
                sleep(2 * 1000);
                goTo(POS_3_PARK);
                break;
        }

        sleep(2 * 1000);
    }
}
