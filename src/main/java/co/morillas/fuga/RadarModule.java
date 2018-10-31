package co.morillas.fuga;

import co.morillas.fuga.detector.Detector;
import co.morillas.fuga.detector.DetectorImpl;
import com.google.inject.AbstractModule;

public class RadarModule extends AbstractModule {
    @Override
    protected void configure() {
        binder().bind(Detector.class).to(DetectorImpl.class);
    }
}
