package principal;

import javafx.scene.paint.Color;

public class Pixel {

    private int x;
    private int y;

    private Color color;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
