package co.morillas.fuga;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ImageTest {
    private static final String IMAGE_STRING = "1234\nabcd\nwxyz";

    private Image image;

    @Before
    public void setUp() {
        image = new Image(IMAGE_STRING);
    }

    @Test(expected = RuntimeException.class)
    public void ifImageLineWithDifferentLenghtsThrowsException() {
        final String imageContent = "1234\n123";
        new Image(imageContent);
    }

    @Test
    public void imageDimensionAreCorrect() {
        assertThat(image.getNumberOfColumns(), is(4));
        assertThat(image.getNumberOfRows(), is(3));
    }

    @Test
    public void imageHasCorrectContent() {
        char[][] expectedContent = new char[3][4];
        expectedContent[0][0] = '1';
        expectedContent[0][1] = '2';
        expectedContent[0][2] = '3';
        expectedContent[0][3] = '4';
        expectedContent[1][0] = 'a';
        expectedContent[1][1] = 'b';
        expectedContent[1][2] = 'c';
        expectedContent[1][3] = 'd';
        expectedContent[2][0] = 'w';
        expectedContent[2][1] = 'x';
        expectedContent[2][2] = 'y';
        expectedContent[2][3] = 'z';

        assertThat(image.getContent(), is(expectedContent));
    }

    @Test
    public void getSubImageWorksAsExpected() {
        Image subImage = image.getSubImage(0, 1, 2, 2);

        Image expectedImage = new Image("23\nbc");
        assertThat(subImage, is(expectedImage));
    }
}
