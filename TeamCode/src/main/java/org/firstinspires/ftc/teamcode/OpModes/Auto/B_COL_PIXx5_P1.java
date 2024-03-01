package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Vision;

@Config
@Autonomous(group="!CompOpModes")
public class B_COL_PIXx5_P1 extends AutoOpMode {
    private Vision vision;

    //--- adb connect 192.168.43.1:5555

    private Vision.IDENTIFIED_SPIKE_MARK_LOCATION spikeLocation;

    //------------------------------------------------------------
    //--- Field Positions ---
    //------------------------------------------------------------

    //--- Collecting Side, align left side of the tile
    public static Position START = new Position(-39, 63, 270);
    public static Position CENTERPOS = new Position(-36, 59, 270);
    public static Position CENTERPOS_L = new Position(-36, 59, 292);

    public static Position CENTERPOS_R = new Position(-36, 59, 248);
    public static Position STACK1 = new Position(-36, 59, 210);
    public static Position STACK1STRAFE = new Position(-40, 59, 230);
    public static Position STACKRETURN = new Position(-36, 59, 203);
    public static Position INTERMEDIATE = new Position(20, 59, 180);
    public static Position PARK_ARENA_CENTER = new Position(44, 15, 180);
    public static Position PARK_BACKDROP_CENTER = new Position(44, 37, 180);
    public static Position PARK_ARENA_WALL = new Position(44, 60, 180);

    //--- Drop positions (LEFT)
    public static Position RIGHT_BACKDROP = new Position(4, 30, 180);

    public static Position RIGHT_BACKDROP_LEFT = new Position(50, 31, 180);
    //Position to drop the white pixel slightly left of the drop pos
    public static Position RIGHT_BACKDROP_CLOSE = new Position(48, 25, 180);

    public static Position RIGHT_BACKDROP_LEFT_CLOSE = new Position(48, 25, 180);


    //--- Drop positions (MIDDLE)
    public static Position MIDDLE_BACKDROP = new Position(42, 31, 180);

    public static Position MIDDLE_BACKDROP_LEFT_CLOSE = new Position(50, 40, 180);
    public static Position MIDDLE_BACKDROP_LEFT = new Position(47, 39, 180);
    public static Position MIDDLE_BACKDROP_CLOSE = new Position(51, 31, 180);



    //--- Drop positions (RIGHT)
    public static Position LEFT_BACKDROP = new Position(47, 43, 180);

    public static Position LEFT_BACKDROP_RIGHT = new Position(50, 30, 180);
    public static Position LEFT_BACKDROP_CLOSE = new Position(50, 39, 180);

    public static Position LEFT_BACKDROP_RIGHT_CLOSE = new Position(50, 39, 180);

    //--- Shared position to drop the pixel on the backdrop
    public static Position BACKDROP_CENTER = new Position(44, 37, 180);
    public static Position BACKDROP_CENTER_CLOSE = new Position(53, 37, 180);
    public static Position COLLECT_SCAFFOLDING_CENTER = new Position(-36, 61, 180);
    
    @Override
    public void runOpMode() {

        //--- Initialize vision
        vision = new Vision(this, Vision.START_POSITION.BLUE_COL);
        vision.initTfod();

        //--- Wait for Start
        while (!isStopRequested() && !opModeIsActive()) {
            //--- Run Vuforia Tensor Flow and keep watching for the identifier in the Signal Cone
            vision.runTfodTensorFlow();
            telemetry.addData("Vision identified Parking Location", vision.getPixelLocation());
            telemetry.update();
        }

        //--- Read the final position of the spike
       // spikeLocation = Vision.IDENTIFIED_SPIKE_MARK_LOCATION.LEFT; //--- Set default value to left
        spikeLocation = vision.getPixelLocation();
        //vision.Stop();

        //--- Initialize
        setup();
        setStartPosition(START);

        //--- Drive based on spike position
        switch (spikeLocation) {
            case LEFT:
                //LeftSpike();
                goTo(CENTERPOS);
                SpikeMovementPaths(
                        CENTERPOS_L,
                        STACK1,
                        STACK1STRAFE,
                        STACKRETURN,
                        LEFT_BACKDROP ,
                        LEFT_BACKDROP_RIGHT,
                        LEFT_BACKDROP_RIGHT_CLOSE,
                        LEFT_BACKDROP_CLOSE ,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
            case MIDDLE:
                SpikeMovementPaths(
                        CENTERPOS,
                        STACK1,
                        STACK1STRAFE,
                        STACKRETURN,
                        MIDDLE_BACKDROP ,
                        MIDDLE_BACKDROP_LEFT ,
                        MIDDLE_BACKDROP_LEFT_CLOSE,
                        MIDDLE_BACKDROP_CLOSE ,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
            case RIGHT:
                goTo(CENTERPOS);
                SpikeMovementPaths(
                        CENTERPOS_R,
                        STACK1,
                        STACK1STRAFE,
                        STACKRETURN,
                        RIGHT_BACKDROP ,
                        RIGHT_BACKDROP_LEFT,
                        RIGHT_BACKDROP_LEFT_CLOSE,
                        RIGHT_BACKDROP_CLOSE ,
                        COLLECT_SCAFFOLDING_CENTER,
                        INTERMEDIATE);
                break;
        }

//        //--- Park
//        goTo(PARK_ARENA_CENTER);
//        //goTo(PARK_ARENA_WALL);
//        //goTo(PARK_BACKDROP_CENTER);
        
        sleep(5000);
    }

    private void SpikeMovementPaths(Position CenterPos,
                                    Position Stack,
                                    Position StackStrafe,
                                    Position Stackreturn,
                                    Position BackdropPos,
                                    Position BackdropPos2,
                                    Position BackdropPos2Close,
                                    Position BackdropClosePos,
                                    Position Collect,
                                    Position IntermediatePos
                                    )
    {
                //--- Move away from wall
                goTo(CenterPos);

                //--- Deliver Purple Pixel
                snagger.goToPosition(2);
                sleep(1250);
                intake.returnPixel();
                sleep(200);
                intake.stop();
                sleep(300);

                //--- Move intake back in
                snagger.goToPosition(0);
                sleep(1250);

                //--- Align with the stack
                goTo(Stack);
                snagger.goToPosition(4);
                sleep(500);
                goTo(StackStrafe);  //--- Sweep the stack

                //   snagger.goToPosition(5);
                sleep(750);
                goTo(Stackreturn);  //--- Pulls back, realigns with stack
                snagger.goToPosition(5);
                //swinch.setClawClosed(true);
                intake.eatPixel();
                sleep(1000);

                //--- Kick it back out if we have extra pixel
                intake.frontWheelReverse();
                sleep(200);

                //--- Transfer Pixel
                snagger.goToPosition(6);
                sleep(1250);
                intake.transferPixel();
                sleep(3000);

                //--- Go under the scaffolding
                goTo(Collect);
                goTo(IntermediatePos);

                //--- Move the deployment up
                intake.stop();
                drop.goToPosition(3); //--- Up

                //--- Deliver to the backboard
                goTo(BackdropPos);
                setSpeed(Speed.MEDIUM);
               // goTo(BackdropClosePos);

                //--- Deliver white pixel
                goTo(BackdropPos2);
                setSpeed(Speed.VERY_SLOW);
                goTo(BackdropPos2Close); //--- Approach backboard
                topGate.setGateOpen();
                sleep(720);
                topGate.setGateStopped();
                sleep(500);

                //--- Deliver the yellow pixel
                setSpeed(Speed.FAST);
                goTo(BackdropClosePos); //-- yellow pixel
                sleep(500);
                topGate.setGateOpen();
                sleep(750);
                topGate.setGateStopped();

                //--- Move away from backdrop
                goTo(BackdropPos);
                drop.goToPosition(0);
                intake.stop();
        }
    }