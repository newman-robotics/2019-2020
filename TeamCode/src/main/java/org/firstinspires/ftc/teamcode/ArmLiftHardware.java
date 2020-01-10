package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

class ArmLiftHardware extends BotHardware {
    DcMotor armLifter;
    Servo leftGrabber;
    Servo rightGrabber;
    Servo foundationGrabberLeft;
    Servo foundationGrabberRight;

    private static final long TIME_NEED = 250;

    static final double ORIGIN = -0.05; // based on Galvin's measurement ;)

    void initGrabbers(HardwareMap hardwareMap){
        // The lifter of the arm
        armLifter = hardwareMap.dcMotor.get("ArmLifter");

        // The grabbers of the arm
        leftGrabber = hardwareMap.servo.get("GrabberLeft");
        rightGrabber = hardwareMap.servo.get("GrabberRight");

        // The foundation grabber
        foundationGrabberLeft = hardwareMap.servo.get("FoundationGrabberLeft");
        foundationGrabberRight = hardwareMap.servo.get("FoundationGrabberRight");

        armLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void openGrabber() {

    }

    public void closeGrabber() {

    }

    public enum GrabberSide {
        LEFT,
        RIGHT
    }

    private double accumulate(double original, double accumulation) {
        double attempt = original + accumulation;
        return Math.min(1.0, Math.max(attempt, 0.0));
    }

    public void operateGrabber(GrabberSide side, double amount) {
        if (side == GrabberSide.LEFT) {
            leftGrabber.setPosition(accumulate(leftGrabber.getPosition(), amount));
        } else if (side == GrabberSide.RIGHT) {
            rightGrabber.setPosition(accumulate(rightGrabber.getPosition(), amount));
        }
    }
}
