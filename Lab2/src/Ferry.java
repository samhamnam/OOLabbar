import java.awt.geom.Point2D;

public class Ferry extends Transporter {

    // Lista med cars? - first in first out

    Ferry(Navigation nav) {
        super(nav);
    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

    @Override
    double speedFactor() {
        return 0;
    }
}
