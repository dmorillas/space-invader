package co.morillas.fuga.detector;

import co.morillas.fuga.Image;

public class DetectorImpl implements Detector {
    @Override
    public int detectInvaders(Image radar, Image invader) {
        if(radar == null || invader == null) {
            System.err.println("Sorry but I can not work with 'null' images. Something went wrong.");
            return 0;
        }

        if(radar.equals(invader)) {
            return 1;
        }

        int occurences = 0;
        for(int i = 0; i <= radar.getNumberOfRows() - invader.getNumberOfRows(); i++) {
            for(int j = 0; j <= radar.getNumberOfColumns() - invader.getNumberOfColumns(); j++) {
                Image subImage = radar.getSubImage(i, j, invader.getNumberOfRows(), invader.getNumberOfColumns());
                if(subImage.equals(invader)) {
                    occurences++;
                }
            }
        }

        return occurences;
    }
}
