package co.morillas.fuga.detector;

import co.morillas.fuga.Image;

public interface Detector {
    int detectInvaders(Image radar, Image invader);
}
