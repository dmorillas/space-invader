package co.morillas.fuga;

import java.util.Arrays;

public class Image {
    private final char[][] content;

    public Image(String content) {
        String[] rows = content.split("\n");
        checkLinesLength(rows);

        this.content = new char[rows.length][rows[0].length()];
        for(int row = 0; row < rows.length; row++) {
            this.content[row] = rows[row].toCharArray();
        }
    }

    public Image(char[][] content) {
        this.content = content;
    }

    private void checkLinesLength(String[] rows) {
        int length = rows[0].length();
        for(int i = 1; i < rows.length; i++) {
            if(rows[i].length() != length) {
                throw new RuntimeException("Not all the lines have the same length");
            }
        }
    }

    public int getNumberOfColumns() {
        return content[0].length;
    }

    public int getNumberOfRows() {
        return content.length;
    }

    public char[][] getContent() {
        return content;
    }

    public Image getSubImage(int x, int y, int width, int height) {
        char[][] subImagecontent = new char[width][height];
        int k = 0;

        for (int i = x; i < x + width; i++, k++) {
            int l = 0;
            for (int j = y; j < y + height; j++, l++) {
                subImagecontent[k][l] = content[i][j];
            }
        }

        return new Image(subImagecontent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Arrays.deepEquals(content, image.content);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(content);
    }
}
