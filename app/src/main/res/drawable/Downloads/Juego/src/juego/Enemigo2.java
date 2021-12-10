package juego;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemigo2 {

    private String ruta = "Enemigo4.png";
    private Image img;
    private final int ancho;
    private final int alto;
    private int pix;
    private boolean visible;
    private int x;
    private int y;

    //Contructor
    public Enemigo2() {
        ImageIcon ic = new ImageIcon(getClass().getResource("/img/" + ruta));
        img = ic.getImage();
        ancho = img.getWidth(null);
        alto = img.getHeight(null);
        visible = true;
        pix = 4;
    }//Fin Constructor

    public Image getImg() {
        return img;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void mover() {

        setX(x - pix);

        if (x >= 2000) {
            visible = false;
        }
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    Rectangle getBounds() {

        return new Rectangle(x, y, ancho, alto);
    }

    void setVisible(boolean b) {
        this.visible = visible;
    }
}