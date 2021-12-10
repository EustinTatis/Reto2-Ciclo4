package juego;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Objeto {

    private String nombre;
    private String imagen;
    private int x, y;
    private ImageIcon imagenIcono;
    private int max_x, max_y;
    private int ancho, alto;
    private boolean vivo;
    private ArrayList disparos;
    private ArrayList Enemigos;
    private ArrayList Enemigos1;
    private ArrayList Enemigos2;
    private ArrayList Enemigos3;

    public Objeto(String nombre, String imagen, int x, int y, int max_x, int max_y) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.x = x;
        this.y = y;
        this.imagenIcono = new ImageIcon(getClass().getResource("/img/" + imagen));
        this.max_x = max_x;
        this.max_y = max_y;
        this.ancho = imagenIcono.getIconWidth();
        this.alto = imagenIcono.getIconHeight();
        this.vivo = true;
        disparos = new ArrayList();
        Enemigos = new ArrayList();
        Enemigos1 = new ArrayList();
        Enemigos2 = new ArrayList();
        Enemigos3 = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void trasladar(int dx, int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;

    }

    public void pintar(Graphics g, Container con) {
        if (vivo == true) {
            g.drawImage(imagenIcono.getImage(), x, y, con);
        }
    }

    public boolean colisiona(Objeto otro) {
        Rectangle r = new Rectangle(x, y, ancho, alto);
        Rectangle rec_otro = new Rectangle(otro.x, otro.y, otro.ancho, otro.alto);
        return (r.intersects(rec_otro));
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public ArrayList getEnemigos() {
        return Enemigos;
    }

    public void setEnemigos(ArrayList Enemigos) {
        this.Enemigos = Enemigos;
    }

    public ArrayList getEnemigos1() {
        return Enemigos1;
    }

    public void setEnemigos1(ArrayList Enemigos1) {
        this.Enemigos1 = Enemigos1;
    }

    public ArrayList getEnemigos2() {
        return Enemigos2;
    }

    public void setEnemigos2(ArrayList Enemigos2) {
        this.Enemigos2 = Enemigos;
    }

    public ArrayList getEnemigos3() {
        return Enemigos3;
    }

    public void setEnemigos3(ArrayList Enemigos3) {
        this.Enemigos3 = Enemigos3;
    }

    public ArrayList getDisparos() {
        return disparos;
    }

    public void setDisparos(ArrayList disparos) {
        this.disparos = disparos;
    }

    public int getMax_x() {
        return max_x;
    }

    public void setMax_x(int max_x) {
        this.max_x = max_x;
    }

    public int getMax_y() {
        return max_y;
    }

    public void setMax_y(int max_y) {
        this.max_y = max_y;
    }

    public ImageIcon getImagenIcono() {
        return imagenIcono;
    }

    public void setImagenIcono(ImageIcon imagenIcono) {
        this.imagenIcono = imagenIcono;
    }

    public void disparar() {
        disparo d = new disparo(x + ancho, y + alto / 2);
        disparos.add(d);
    }

    public Rectangle getRectangle() {
        return (new Rectangle(x, y, ancho, alto));
    }
}
