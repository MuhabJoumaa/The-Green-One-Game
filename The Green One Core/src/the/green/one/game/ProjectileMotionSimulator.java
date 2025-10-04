package the.green.one.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

public class ProjectileMotionSimulator {

    public void simulateProjectileMotionInBackground(final int i, final double V0, final double THETA,
            final double H0, final double K, final double M, final double G,
            final int GROUND_HEIGHT, final ProjectileMotionSimulationListener projectileMotionSimulationListener) {
        ProjectileMotionSimulatorWorker projectileMotionSimulatorWorker = new ProjectileMotionSimulatorWorker(this, V0, THETA, H0, K, M, G, GROUND_HEIGHT);
        projectileMotionSimulatorWorker.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("state") && evt.getNewValue() == SwingWorker.StateValue.DONE) {
                try {
                    List<List<Double>> result = projectileMotionSimulatorWorker.get();
                    projectileMotionSimulationListener.onProjectileMotionSimulationComplete(i, result);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        projectileMotionSimulatorWorker.execute();
    }

    public List<List<Double>> simulateProjectileMotion(final double v0, double theta,
            double h0, final double k, final double m, final double G, final int groundHeight) {
        h0 = -h0;
        double vx0, vy0;
        if (k == 0 && theta == 90) {
            vx0 = 0;
            vy0 = -v0;
        } else {
            theta = Math.toRadians(theta);
            vx0 = v0 * Math.cos(theta);
            vy0 = -v0 * Math.sin(theta);
        }
        double[] state = {0, h0, vx0, vy0};
        double dt = 0.01;
        List<Double> times = new ArrayList<>(), xValues = new ArrayList<>(),
                yValues = new ArrayList<>();
        List<List<Double>> positions = new ArrayList<>();
        times.add(0.0);
        xValues.add(0.0);
        yValues.add(h0);
        while (state[1] <= groundHeight) {
            double t = times.get(times.size() - 1);
            state = this.rungeKuttaStep(t, state, dt, k, m, G);
            times.add(t + dt);
            xValues.add(state[0]);
            yValues.add(state[1]);
        }
        positions.add(xValues);
        positions.add(yValues);
        // System.out.println("The time of the flight: " + times.get(times.size() - 1) + "s");
        return positions;
    }

    private double[] rungeKuttaStep(final double t, final double[] state, final double dt,
            final double k, final double m, final double G) {
        double[] k1 = this.calculateAccel(t, state, k, m, G);
        double[] k2 = this.calculateAccel(t + 0.5 * dt, new double[]{state[0] + 0.5 * dt * k1[0], state[1] + 0.5 * dt * k1[1],
            state[2] + 0.5 * dt * k1[2], state[3] + 0.5 * dt * k1[3]}, k, m, G);
        double[] k3 = this.calculateAccel(t + 0.5 * dt, new double[]{state[0] + 0.5 * dt * k2[0], state[1] + 0.5 * dt * k2[1],
            state[2] + 0.5 * dt * k2[2], state[3] + 0.5 * dt * k2[3]}, k, m, G);
        double[] k4 = this.calculateAccel(t + dt, new double[]{state[0] + dt * k3[0], state[1] + dt * k3[1],
            state[2] + dt * k3[2], state[3] + dt * k3[3]}, k, m, G);
        double[] newState = new double[4];
        newState[0] = state[0] + dt / 6 * (k1[0] + 2 * k2[0] + 2 * k3[0] + k4[0]);
        newState[1] = state[1] + dt / 6 * (k1[1] + 2 * k2[1] + 2 * k3[1] + k4[1]);
        newState[2] = state[2] + dt / 6 * (k1[2] + 2 * k2[2] + 2 * k3[2] + k4[2]);
        newState[3] = state[3] + dt / 6 * (k1[3] + 2 * k2[3] + 2 * k3[3] + k4[3]);
        return newState;
    }

    private double[] calculateAccel(final double t, final double[] state, final double k,
            final double m, final double G) {
        double vx = state[2], vy = state[3],
                v = Math.sqrt(vx * vx + vy * vy),
                FairX = -k * v * vx,
                FairY = -k * v * vy,
                ax = FairX / m,
                ay = (FairY + m * G) / m;
        return new double[]{vx, vy, ax, ay};
    }
}
