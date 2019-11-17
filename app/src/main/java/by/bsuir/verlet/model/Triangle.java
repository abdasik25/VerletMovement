package by.bsuir.verlet.model;

/**
 * The type Triangle.
 */
public class Triangle {

    private int x;
    private int y;

    private double force;
    private double mass;
    private double angle;

    private double aX;
    private double aY;

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets force.
     *
     * @return the force
     */
    public double getForce() {
        return force;
    }

    /**
     * Sets force.
     *
     * @param force the force
     */
    public void setForce(double force) {
        this.force = force;
    }

    /**
     * Gets mass.
     *
     * @return the mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * Sets mass.
     *
     * @param mass the mass
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * Gets angle.
     *
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Sets angle.
     *
     * @param angle the angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Gets ax.
     *
     * @return the ax
     */
    public double getAX() {
        return aX;
    }

    /**
     * Gets ay.
     *
     * @return the ay
     */
    public double getAY() {
        return aY;
    }

    /**
     * Calculate a.
     */
    public void calculateA() {
        calculateAX();
        calculateAY();
    }

    private void calculateAX() {
        aX = force / mass * Math.cos(angle * Math.PI / 180);
    }

    private void calculateAY() {
        aY = force / mass * Math.sin(angle * Math.PI / 180);
    }
}
