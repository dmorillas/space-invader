package co.morillas.fuga;

import co.morillas.fuga.detector.Detector;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Radar {
    private static final String INVADER1_FILE = "invader1.txt";
    private static final String INVADER2_FILE = "invader2.txt";

    private Detector detector;

    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("I am expecting one argument with the full path of the radar image. Please, Can you run me again providing this information?");
            System.err.println("For example: ./radar/image.txt");
            System.exit(-1);
        }

        Injector injector = Guice.createInjector(new RadarModule());
        Radar bot = injector.getInstance(Radar.class);
        bot.detect(args[0]);
    }

    @Inject
    Radar(Detector detector) {
        this.detector = detector;
    }

    void detect(String radarImagePath) {
        Image radar = loadRadarImage(radarImagePath);

        Image invader1 = loadInvaderImage(INVADER1_FILE);
        Image invader2 = loadInvaderImage(INVADER2_FILE);

        int numOfInvader1 = detector.detectInvaders(radar, invader1);
        int numOfInvader2 = detector.detectInvaders(radar, invader2);

        System.out.println("###############################");
        System.out.println("Radar detection finished succesfully with the following results: ");
        System.out.println("     - # of Invaders 1 detected: " + numOfInvader1);
        System.out.println("     - # of Invaders 2 detected: " + numOfInvader2);
        System.out.println("###############################");
    }

    private Image loadRadarImage(String path) {
        Path radar_image = Paths.get(path);
        return loadImage(radar_image);
    }

    private Image loadImage(Path path) {
        try {
            String content = new String(Files.readAllBytes(path));
            return new Image(content);
        } catch (IOException e) {
            System.err.println("I can not load file " + path + ". Could you check that the file exists?");
            System.exit(-1);
        }

        return null;
    }

    private Image loadInvaderImage(String fileName) {
        try {
            Path path = Paths.get(ClassLoader.getSystemClassLoader().getResource(fileName).toURI());
            return loadImage(path);
        } catch (URISyntaxException e) {
            System.err.println("I can not load file " + fileName + ".");
            System.exit(-1);
        }

        return null;
    }
}
