package the.green.one.game;

import javax.swing.SwingWorker;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProjectileMotionSimulatorWorker extends SwingWorker<List<List<Double>>, Void> {

    private final ProjectileMotionSimulator projectileMotionSimulator;
    private final double V0, THETA, H0, K, M, G;
    private final int GROUND_HEIGHT;

    public ProjectileMotionSimulatorWorker(final ProjectileMotionSimulator projectileMotionSimulator, 
            final double V0, final double THETA, final double H0, 
            final double K, final double M, final double G, final int GROUND_HEIGHT) {
        this.projectileMotionSimulator = projectileMotionSimulator;
        this.V0 = V0;
        this.THETA = THETA;
        this.H0 = H0;
        this.K = K;
        this.M = M;
        this.G = G;
        this.GROUND_HEIGHT = GROUND_HEIGHT;
    }

    @Override
    protected List<List<Double>> doInBackground() throws Exception {
        return ProjectileMotionSimulatorWorker.this.projectileMotionSimulator.simulateProjectileMotion(
                ProjectileMotionSimulatorWorker.this.V0, ProjectileMotionSimulatorWorker.this.THETA, 
                ProjectileMotionSimulatorWorker.this.H0, ProjectileMotionSimulatorWorker.this.K, 
                ProjectileMotionSimulatorWorker.this.M, ProjectileMotionSimulatorWorker.this.G, 
                ProjectileMotionSimulatorWorker.this.GROUND_HEIGHT);
    }

    @Override
    protected void done() {
        try {
            List<List<Double>> result = get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}
