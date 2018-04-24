package projectile;

public class Model
{
    private final double initialHeight, angle, initialSpeed, dragCoefficient, mass;
    private final double ANIMATION_FPS = 50;
    private final double dt = 1/ANIMATION_FPS;
    private final double gravitationalAcceleration = 9.81;
    private double currentDisplacement, currentHeight, speedY, speedX;


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
        double accelerationX, accelerationY;
        currentHeight = currentHeight + speedY * dt;
        currentDisplacement = currentDisplacement + speedX * dt;
        accelerationX = (-dragCoefficient / mass) * speedX * speedX;
        accelerationY = Math.signum(-speedY) * (dragCoefficient/mass) * speedY * speedY;
        speedY = speedY + gravitationalAcceleration * dt + accelerationY * dt;
        speedX = speedX + accelerationX * dt;



    }

    public Model(double initialHeight, double angle, double initialSpeed, double dragCoefficient, double mass)
    {
        this.initialHeight = initialHeight;
        this.angle = angle;
        this.initialSpeed = initialSpeed;
        this.speedX = initialSpeed * Math.cos(angle*3.14/180);
        this.speedY = -initialSpeed * Math.sin(angle*3.14/180);
        this.currentHeight = initialHeight;
        this.currentDisplacement = 0;
        this.dragCoefficient = dragCoefficient;
        this.mass = mass;

    }

}
