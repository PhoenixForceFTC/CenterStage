package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.Auto.RedScoringSideSubsystems.left;
import org.firstinspires.ftc.teamcode.OpModes.Auto.RedScoringSideSubsystems.middle;
import org.firstinspires.ftc.teamcode.OpModes.Auto.RedScoringSideSubsystems.right;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;

@Config
@Autonomous(group="!CompOpModes")
public class RedScoringSideFastL2 extends AutoOpMode {
    private Vision vision;

    //--- adb connect 192.168.43.1:5555

//    private left left;
//    private middle middle;
//    private right right;

    private Vision.IDENTIFIED_SPIKE_MARK_LOCATION spikeLocation;

    //------------------------------------------------------------
    //--- Field Positions ---
    //------------------------------------------------------------

    //--- Scoring Side, align right side of the tile
    public static Position START = new Position(14, -63, 90);

    //--- Deliver
    public static Position SPIKE_LEFT = new AutoOpMode.Position(10, -34, 180);

    //--- Drop positions (LEFT)
    public static Position LEFT__DROP_POSITION_TOUCH_BOARD_L = new Position(47, -30, 180);
    public static Position LEFT__DROP_POSITION_TOUCH_BOARD_L2 = new Position(50, -30, 180);

    public static Position LEFT__DROP_POSITION_TOUCH_BOARD_C = new Position(44, -37, 180);
    public static Position LEFT__DROP_POSITION_TOUCH_BOARD_C2 = new Position(50, -37, 180);

    //--- Collection positions
    public static AutoOpMode.Position RED_PILE_1_PREPOSITION = new AutoOpMode.Position(12, -12, 180);
    public static AutoOpMode.Position RED_PILE_1_POSITION = new AutoOpMode.Position(-17, -15, 185);

    //--- Parking
    public static Position PARK_POSITION_CENTER = new Position(44, -15, 180);

    public static Position PARK_POSITION_BOARD = new Position(44, -37, 180);

    public static Position PARK_POSITION_WALL = new Position(44, -60, 180);

    public static Position DROP_POSITION_TOUCH_BOARD_R = new Position(47, -44, 180);
    public static Position DROP_POSITION_TOUCH_BOARD_R2 = new Position(49, -44, 180);

    public static Position SPIKE_RIGHT = new Position(25, -44.625, 90);
    public static Position SPIKE_RIGHT_BACKUP = new Position(25, -52, 90);

    public static Position AFTER_SPIKE_POSITION = new Position(24, -52, 90);
    public static Position AFTER_SPIKE_TURN = new Position(24, -52, 180);

    public static Position DROP_START_INTERMEDIATE = new Position(44, -52, 180);
    public static Position DROP_PARK_INTERMEDIATE = new Position(44, -60, 180);
    public static Position DROP_POSITION = new Position(44, -36, 180);
    public static Position DROP_POSITION_TOUCH_BOARD = new Position(52, -36, 180);
    public static Position DROP_POSITION_BACKUP_BOARD = new Position(44, -36, 180);


    public static Position DROP_POSITION_L = new Position(44, -28, 180);
    //public static Position DROP_POSITION_TOUCH_BOARD_L = new Position(52, -28, 180);
    public static Position DROP_POSITION_BACKUP_BOARD_L = new Position(44, -28, 180);
    public static Position DROP_POSITION_R = new Position(44, -44, 180);






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
        spikeLocation = Vision.IDENTIFIED_SPIKE_MARK_LOCATION.LEFT; //--- Set default value to left
        //spikeLocation = vision.getPixelLocation();
        //vision.Stop();

        //--- Initialize
        setup();
        setStartPosition(START);

        //--- Drive based on spike position
        switch (spikeLocation) {
            case LEFT:
                //--- Drive to spike and eject pixel
                goTo(SPIKE_LEFT);
                intake.returnPixel();
                sleep(200);
                intake.stop();

                //--- Deliver pixel to backdrop
                drop.goToPosition(3); //--- Up
                goTo(LEFT__DROP_POSITION_TOUCH_BOARD_L);
                setSpeed(Speed.VERY_SLOW); //--- Slow down before moving back a little
                goTo(LEFT__DROP_POSITION_TOUCH_BOARD_L2);
                setSpeed(Speed.FAST);

                topGate.setGateOpen();
                sleep(1000);
                topGate.setGateStopped();

                //--- Collect pixels from the pile
                goTo(LEFT__DROP_POSITION_TOUCH_BOARD_L);
                intake.stop();
                drop.goToPosition(0);
                goTo(RED_PILE_1_PREPOSITION);
                goTo(RED_PILE_1_POSITION);

                snagger.goToPosition(3); //--- Almost full out
                intake.eatPixel();
                snagger.goToPosition(4, 0.25); //-- Full out at slow speed
                sleep(4000);
                intake.frontWheelReverse();
                snagger.goToPosition(0); //--- Retract
                sleep(3000);
                intake.stop();

                //--- Transfer pixels
                intake.transferPixel();

                //--- Move back to backdrop
                goTo(RED_PILE_1_POSITION);
                snagger.goToPosition(0);
                goTo(RED_PILE_1_PREPOSITION);
                snagger.goToPosition(0);
                sleep(1000);
                intake.stop();
                drop.goToPosition(4); //--- Up (higher)
                goTo(LEFT__DROP_POSITION_TOUCH_BOARD_C);
                setSpeed(Speed.VERY_SLOW); //--- Slow down before moving back a little
                goTo(LEFT__DROP_POSITION_TOUCH_BOARD_C2);
                setSpeed(Speed.FAST);

                //--- Drop the pixel
                topGate.setGateOpen();
                sleep(2000);
                topGate.setGateStopped();

                //--- Move away from board
                goTo(LEFT__DROP_POSITION_TOUCH_BOARD_C);
                drop.goToPosition(0); //--- Down

                //--- Park
                goTo(PARK_POSITION_CENTER);

                break;
            case MIDDLE:
                //middle.go();
                break;
            case RIGHT:
                //right.go();
                break;
        }

        sleep(5000);
    }


}

