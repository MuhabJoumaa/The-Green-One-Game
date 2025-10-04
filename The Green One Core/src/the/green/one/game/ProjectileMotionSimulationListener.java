package the.green.one.game;

import java.util.List;

public interface ProjectileMotionSimulationListener {

    void onProjectileMotionSimulationComplete(final int i, List<List<Double>> projectilePositions);
}
