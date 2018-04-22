package projectile;

public class Model
{
    private final double initialHeight, angle, initialSpeed, speedX;
    private final double ANIMATION_FPS = 120;
    private final double dt;
    private final double gravitationalAcceleration = 9.81;
    private double currentDisplacement, currentHeight, speedY;


    public double getCurrentDisplacement()
    {
        return currentDisplacement;
    }

    public double getCurrentHeight()
    {
        return currentHeight;
    }
    public double getInitialHeight()
    {
        return initialHeight;
    }

    public double get_dt()
    {
        return this.dt;
    }

    public void update()
    {
        currentDisplacement = currentDisplacement + speedX * dt;
        currentHeight = currentHeight + speedY * dt;
        speedY = speedY + gravitationalAcceleration * dt;
    }

    public Model(double initialHeight, double angle, double initialSpeed)
    {
        this.initialHeight = initialHeight;
        this.angle = angle;
        this.initialSpeed = initialSpeed;
        this.speedX = initialSpeed * Math.cos(angle*3.14/180);
        this.speedY = -initialSpeed * Math.sin(angle*3.14/180);
        this.dt = (1/ANIMATION_FPS);
        this.currentHeight = initialHeight;
        this.currentDisplacement = 0;

    }

}
