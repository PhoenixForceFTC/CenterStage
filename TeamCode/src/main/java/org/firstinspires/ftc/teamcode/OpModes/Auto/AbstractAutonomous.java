package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.vision.PropDetector;

public abstract class AbstractAutonomous extends LinearOpMode {
    public PropDetector detector;
    public enum Case {
        LEFT, CENTER, RIGHT, UNDEFINED
    }
    public Side side;
    public enum Side {
        RED, BLUE
    }
}
