package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class SwerveModule {
    private final WPI_TalonFX driveMotor;
    private final WPI_TalonFX turnMotor;

    public SwerveModule(int driveMotorId, int turnMotorId) {
        driveMotor = new WPI_TalonFX(driveMotorId);
        turnMotor = new WPI_TalonFX(turnMotorId);
    }

    public void set(double speed, double angle) {
        // קובע מהירות
        driveMotor.set(ControlMode.PercentOutput, speed);

        // קובע זווית (לדוגמה פשוטה, נשתמש ישירות בערכים)
        turnMotor.set(ControlMode.Position, angle); 
    }
}
