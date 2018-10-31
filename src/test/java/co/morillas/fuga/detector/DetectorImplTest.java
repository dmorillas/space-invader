package co.morillas.fuga.detector;

import co.morillas.fuga.Image;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DetectorImplTest {
    DetectorImpl detector;

    @Before
    public void setUp() {
        detector = new DetectorImpl();
    }

    @Test
    public void detectInvadersIfRadarImageNullReturnsZero() {
        int result = detector.detectInvaders(null, new Image(""));

        assertThat(result, is(0));
    }

    @Test
    public void detectInvadersIfInvaderImageNullReturnsZero() {
        int result = detector.detectInvaders(new Image(""), null);

        assertThat(result, is(0));
    }

    @Test
    public void detectInvadersIfSameImagesReturnOne() {
        Image image = new Image("");
        int result = detector.detectInvaders(image, image);

        assertThat(result, is(1));
    }

    @Test
    public void detectInvadersIfSameImageContentReturnOne() {
        int result = detector.detectInvaders(new Image(""), new Image(""));

        assertThat(result, is(1));
    }

    @Test
    public void detectInvadersWorksAsExpected() {
        final Image radar = new Image("1234\nab23\nwxb2");
        final Image invader = new Image("23\nb2");

        int occurrences = detector.detectInvaders(radar, invader);

        assertThat(occurrences, is(2));
    }
}
