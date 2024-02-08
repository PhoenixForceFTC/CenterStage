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
    public static AutoOpMode.Position SPIKE_LEFT = new AutoOpMode.Position(10, -34, 180);

    //--- Drop positions
    public static AutoOpMode.Position DROP_POSITION_TOUCH_BOARD_L = new AutoOpMode.Position(48, -30, 180);
    public static AutoOpMode.Position DROP_POSITION_TOUCH_BOARD_L2 = new AutoOpMode.Position(49, -30, 180);

    //--- Collection positions
    public static AutoOpMode.Position RED_PILE_1_PREPOSITION = new AutoOpMode.Position(12, -12, 180);
    public static AutoOpMode.Position RED_PILE_1_POSITION = new AutoOpMode.Position(-10, -16, 185);


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
    public static Position DROP_POSITION_TOUCH_BOARD_R = new Position(52, -44, 180);
    public static Position DROP_POSITION_BACKUP_BOARD_R = new Position(44, -44, 180);

    public static Position PARK_POSITION = new Position(60, -60, 180);







    @Override
    public void runOpMode() {
        //--- Initialize positions
//        left = new left(this);
//        middle = new middle(this);
//        right = new right(this);

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
//                goTo(SPIKE_LEFT);
//                intake.returnPixel();
//                sleep(200);
//                intake.stop();

                //--- Deliver pixel
//                drop.goToPosition(3);
//                goTo(DROP_POSITION_TOUCH_BOARD_L);
//                setSpeed(Speed.SLOW); //--- Slow down before moving back a little
//                goTo(DROP_POSITION_TOUCH_BOARD_L2);
//                setSpeed(Speed.FAST);
//                topGate.setGateOpen();
//                sleep(1000);
//                topGate.setGateStopped();

                //--- Collect pixels from the pile
//                intake.stop();
//                drop.goToPosition(0);
//                goTo(RED_PILE_1_PREPOSITION);
//                goTo(RED_PILE_1_POSITION);
                snagger.goToPosition(3); //--- Almost full out
                intake.eatPixel();
                snagger.goToPosition(4, 0.25); //-- Full out at slow speed
                sleep(2000);
                snagger.goToPosition(3,0.25); //-- Slight Retract to pull tbe top two pixels off the stack
                sleep(2000);
                snagger.goToPosition(4, 0.25); //-- Full out at slow speed
                sleep(2000);
                intake.frontWheelReverse();
                sleep(2000);
                //sleep(5000);
                snagger.goToPosition(0); //--- Retract
                sleep(3000);
                intake.stop();

//
//
//                telemetry.update();
//                int counter = 0;
//                intake.eatPixel();
//                while (counter < 40 && intake.getIntakePixels() < 2) {
//
//                    sleep(100);
//                    counter++;
//                    intake.pixelCounterTelemetry();
//                    telemetry.update();
//                }
//                intake.frontWheelReverse();
//                snagger.goToPosition(0);
//
//                sleep(1000);
//
//                intake.transferPixel();
//                sleep(2000);

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

//    public void coneDrop(int casee) {
//        intake.stop();
//        goTo(AFTER_SPIKE_POSITION);
//        goTo(AFTER_SPIKE_TURN);
//        goTo(DROP_START_INTERMEDIATE);
//
//
//
//        if(casee==1){
//            goTo(DROP_POSITION_L);
//            drop.goToPosition(3);
//            setSpeed(Speed.SLOW);
//            goTo(DROP_POSITION_TOUCH_BOARD_L);
//            sleep(1000);
//            topGate.setGateOpen();
//            telemetry.update();
//            sleep(1000);
//            topGate.setGateStopped();
//            goTo(DROP_POSITION_BACKUP_BOARD_L);
//        }
//        if(casee==2){
//            goTo(DROP_POSITION);
//            drop.goToPosition(3);
//            setSpeed(Speed.SLOW);
//            goTo(DROP_POSITION_TOUCH_BOARD);
//            sleep(1000);
//            topGate.setGateOpen();
//            telemetry.update();
//            sleep(1000);
//            topGate.setGateStopped();
//            goTo(DROP_POSITION_BACKUP_BOARD);
//        }
//        if(casee==3){
//            goTo(DROP_POSITION_R);
//            drop.goToPosition(3);
//            setSpeed(Speed.SLOW);
//            goTo(DROP_POSITION_TOUCH_BOARD_R);
//            sleep(1000);
//            topGate.setGateOpen();
//            telemetry.update();
//            sleep(1000);
//            topGate.setGateStopped();
//            goTo(DROP_POSITION_BACKUP_BOARD_R);
//        }
//
//
//
//
//        setSpeed(Speed.MEDIUM);
//        drop.goToBottom();
//        sleep(1000);
//        goTo(DROP_PARK_INTERMEDIATE);
//        goTo(PARK_POSITION);
//        sleep(10000);
//
//    }
}

