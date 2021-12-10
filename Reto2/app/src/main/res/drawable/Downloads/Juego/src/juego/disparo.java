package juego;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class disparo {

    private String ruta = "Municiones burbujas.png";
    private Image img;
    private final int ancho;
    private final int alto;
    private int pix;
    private int X;
    private int Y;
    private boolean visible;

    public disparo(int cx, int cy) {
        ImageIcon ic = new ImageIcon(getClass().getResource("/img/" + ruta));
        img = ic.getImage();

        ancho = img.getWidth(null);
        alto = img.getHeight(null);

        X = cx;
        Y = cy;
        pix = 20;

        visible = true;
    }

    public Image getImg() {
        return img;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    void mover() {

        X += pix;
        if (X >= 730) {
            visible = false;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(X, Y, ancho, alto);
    }
}
