package frc.robot;

import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.math.geometry.Rotation2d;


public class SwerveDrive {
    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule backLeft;
    private final SwerveModule backRight;

    private final Pigeon2 pigeon;

    public SwerveDrive() {
        // יצירת מודולים
        frontLeft = new SwerveModule(Constants.FRONT_LEFT_DRIVE_ID, Constants.FRONT_LEFT_TURN_ID);
        frontRight = new SwerveModule(Constants.FRONT_RIGHT_DRIVE_ID, Constants.FRONT_RIGHT_TURN_ID);
        backLeft = new SwerveModule(Constants.BACK_LEFT_DRIVE_ID, Constants.BACK_LEFT_TURN_ID);
        backRight = new SwerveModule(Constants.BACK_RIGHT_DRIVE_ID, Constants.BACK_RIGHT_TURN_ID);

        // אתחול ה-Pigeon2
        pigeon = new Pigeon2(Constants.PIGEON_ID);
        pigeon.setYaw(0); // איפוס ראשוני
    }

    public void drive(double fwd, double strafe, double rotate) {
        // זווית Yaw
        double yaw = pigeon.getYaw();
        Rotation2d rotation = Rotation2d.fromDegrees(yaw);

        // טרנספורמציה לפי שדה (Field-Oriented Control)
        double temp = fwd * Math.cos(rotation.getRadians()) + strafe * Math.sin(rotation.getRadians());
        strafe = -fwd * Math.sin(rotation.getRadians()) + strafe * Math.cos(rotation.getRadians());
        fwd = temp;

        // חישוב של מהירות וזווית לכל מודול (פשטות יחסית)
        double frontLeftSpeed = fwd + rotate;
        double frontLeftAngle = Math.atan2(fwd, strafe);

        double frontRightSpeed = fwd - rotate;
        double frontRightAngle = Math.atan2(fwd, strafe);

        double backLeftSpeed = fwd + rotate;
        double backLeftAngle = Math.atan2(fwd, strafe);

        double backRightSpeed = fwd - rotate;
        double backRightAngle = Math.atan2(fwd, strafe);

        // הגדרות למודולים
        frontLeft.set(frontLeftSpeed, frontLeftAngle);
        frontRight.set(frontRightSpeed, frontRightAngle);
        backLeft.set(backLeftSpeed, backLeftAngle);
        backRight.set(backRightSpeed, backRightAngle);
    }
}
