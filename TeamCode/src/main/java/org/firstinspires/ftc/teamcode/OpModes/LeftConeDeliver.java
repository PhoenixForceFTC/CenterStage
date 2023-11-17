package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Config
@Autonomous(group="!CompOpModes")
public class LeftConeDeliver extends AutoOpMode {
    public static Position START = new Position(-37, -63, 90);

    public static Position POS_1_INT = new Position(-60, -59, 90);
    public static Position POS_1_PARK = new Position(-60, -24, 90);

    public static Position POS_2_PARK = new Position(-37, -24, 90);

    public static Position POS_3_INT = new Position(-12, -59, 90);
    public static Position POS_3_PARK = new Position(-12, -24, 90);

    @Override
    public void runOpMode() {
        setup();

        setStartPosition(START);

        switch(camera.getTagOfInterest()) {
            case 1:
                goTo(POS_1_INT);
                goTo(POS_1_PARK);
                break;

            case 2:
                goTo(POS_2_PARK);
                break;

            case 3:
                goTo(POS_3_INT);
                goTo(POS_3_PARK);
                break;
        }

        sleep(2 * 1000);
    }


}
