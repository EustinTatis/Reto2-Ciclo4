package juego;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class juego extends Applet implements KeyListener, Runnable {

    final ImageIcon Fondo;
    final ImageIcon Fondo1;
    final ImageIcon fin;
    final int ANCHO_PANTALLA = 800;
    final int ALTO_PANTALLA = 600;
    int distancia = 10;
    final int MS = 100;
    int x = 0, y = 0;
    private ArrayList enemigos, enemigos1, enemigos2, enemigos3;
    Thread juego = new Thread(this);
    Objeto heroe, enemigo1;
    Clip sonido;
    private int seg, mili;
    private int life = 100;
    private int point = 0;
    private int n = 0;
    private int time = 0;

    @Override
    public void init() {
        resize(ANCHO_PANTALLA, ALTO_PANTALLA);
        setFocusable(true);
    }

    public juego() {
        this.heroe = new Objeto("Heroe", "Heroe Bob esponja.png", x, y + 450, ANCHO_PANTALLA, ALTO_PANTALLA);
        this.enemigo1 = new Objeto("Enemigo1", "Enemigo1.png", x + 700, y + 420, ANCHO_PANTALLA, ALTO_PANTALLA);
        enemigos = new ArrayList();
        enemigos1 = new ArrayList();
        enemigos2 = new ArrayList();
        enemigos3 = new ArrayList();
        Fondo = new ImageIcon(getClass().getResource("/img/" + "fondo_1.jpg"));
        Fondo1 = new ImageIcon(getClass().getResource("/img/" + "fondo_1.jpg"));
        fin = new ImageIcon(getClass().getResource("/img/" + "game over.jpg"));
        this.addKeyListener(this);
    }

    @Override
    public void start() {
        juego.start();
    }

    public void paint(Graphics g) {
        Limpiarpantalla(g);
        PintarFondo(g, this);
        heroe.pintar(g, this);
        enemigo1.pintar(g, this);
        Integrantes(g);

        //obtener los disparos del objeto
        ArrayList a = heroe.getDisparos();

        //pintar los disparos del objeto
        for (int i = 0; i < a.size(); i++) {
            disparo disp = (disparo) a.get(i);
            if (disp.isVisible()) {
                g.drawImage(disp.getImg(), disp.getX(), disp.getY(), this);
            }
        }

        // Enemigos 
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo en = (Enemigo) enemigos.get(i);
            if (en.isVisible()) {
                g.drawImage(en.getImg(), en.getx(), en.gety(), this);
            }
        }

        for (int i = 0; i < enemigos1.size(); i++) {
            Enemigo1 en = (Enemigo1) enemigos1.get(i);
            if (en.isVisible()) {
                g.drawImage(en.getImg(), en.getx(), en.gety(), this);
            }
        }

        for (int i = 0; i < enemigos2.size(); i++) {
            Enemigo2 en = (Enemigo2) enemigos2.get(i);
            if (en.isVisible()) {
                g.drawImage(en.getImg(), en.getx(), en.gety(), this);
            }
        }

        for (int i = 0; i < enemigos3.size(); i++) {
            Enemigo3 en = (Enemigo3) enemigos3.get(i);
            if (en.isVisible()) {
                g.drawImage(en.getImg(), en.getx(), en.gety(), this);
            }
        }


        if (heroe.isVivo() == false) {
            efectos("Game over.wav");
            g.drawImage(fin.getImage(), 250, 200, this);
            juego.stop();

            JOptionPane.showMessageDialog(this, "VIDAS: " + life);
            JOptionPane.showMessageDialog(this, "ENEMIGOS DESTRUIDOS:" + (point / 2));
            JOptionPane.showMessageDialog(this, "ENEMIGOS RESTANTES: " + (n - (point / 2) - (4 - (point / 25))));
        }

        if (time == 400) {
            juego.stop();
            g.drawImage(fin.getImage(), 250, 200, this);
            efectos("Game over.wav");

            JOptionPane.showMessageDialog(this, "VIDAS: " + life);
            JOptionPane.showMessageDialog(this, "ENEMIGOS DESTRUIDOS:" + (point / 2));
            JOptionPane.showMessageDialog(this, "ENEMIGOS RESTANTES: " + (n - (point / 2) - (4 - (point / 25))));
        }
        g.dispose();
    }

    private void efectos(String son) {
        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(getClass().getResource("/juego/" + son)));
            sonido.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void PintarFondo(Graphics g, Container c) {
        g.drawImage(Fondo1.getImage(), 0, 0, c);
        g.drawImage(Fondo.getImage(), 0, -20, c);

    }

    private void Limpiarpantalla(Graphics g) {
        g.clearRect(0, 0, ANCHO_PANTALLA, ALTO_PANTALLA);
    }

    private void Colisiones() {

        Rectangle r1 = heroe.getRectangle();

        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = (Enemigo) enemigos.get(i);
            Rectangle r2 = e.getBounds();

            if (r2.intersects(r1)) {
                if (life > 0) {
                    life -= 10;
                    enemigos.remove(i);

                    efectos("Destrucción de enemigo.wav");
                }
                if (life == 0) {
                    heroe.setVivo(false);
                }
            }
        }

        ArrayList a = heroe.getDisparos();
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo ene = (Enemigo) enemigos.get(i);
            Rectangle e1 = ene.getBounds();
            for (int j = 0; j < a.size(); j++) {
                disparo disp = (disparo) a.get(j);
                Rectangle r2 = disp.getBounds();
                if (e1.intersects(r2)) {
                    enemigos.remove(i);
                    a.remove(j);
                    point += 2;
                    efectos("disparoburbuja.wav");
                }
            }
        }
    }

    private void Colisiones1() {

        Rectangle r1 = heroe.getRectangle();

        for (int i = 0; i < enemigos1.size(); i++) {
            Enemigo1 e = (Enemigo1) enemigos1.get(i);
            Rectangle r2 = e.getBounds();

            if (r2.intersects(r1)) {
                if (life > 0) {
                    life -= 10;
                    enemigos1.remove(i);

                    efectos("Destrucción de enemigo.wav");
                }
                if (life == 0) {
                    heroe.setVivo(false);
                }
            }
        }

        ArrayList a = heroe.getDisparos();
        for (int i = 0; i < enemigos1.size(); i++) {
            Enemigo1 ene = (Enemigo1) enemigos1.get(i);
            Rectangle e1 = ene.getBounds();
            for (int j = 0; j < a.size(); j++) {
                disparo disp = (disparo) a.get(j);
                Rectangle r2 = disp.getBounds();
                if (e1.intersects(r2)) {
                    enemigos1.remove(i);
                    a.remove(j);
                    point += 2;
                    efectos("disparoburbuja.wav");
                }
            }
        }
    }

    private void Colisiones2() {

        Rectangle r1 = heroe.getRectangle();

        for (int i = 0; i < enemigos2.size(); i++) {
            Enemigo2 e = (Enemigo2) enemigos2.get(i);
            Rectangle r2 = e.getBounds();

            if (r2.intersects(r1)) {
                if (life > 0) {
                    life -= 10;
                    enemigos2.remove(i);

                    efectos("Destrucción de enemigo.wav");
                }
                if (life == 0) {
                    heroe.setVivo(false);
                }
            }
        }

        ArrayList a = heroe.getDisparos();
        for (int i = 0; i < enemigos2.size(); i++) {
            Enemigo2 ene = (Enemigo2) enemigos2.get(i);
            Rectangle e1 = ene.getBounds();
            for (int j = 0; j < a.size(); j++) {
                disparo disp = (disparo) a.get(j);
                Rectangle r2 = disp.getBounds();
                if (e1.intersects(r2)) {
                    enemigos2.remove(i);
                    a.remove(j);
                    point += 2;
                    efectos("disparoburbuja.wav");
                }
            }
        }
    }

    private void Colisiones3() {

        Rectangle r1 = heroe.getRectangle();

        for (int i = 0; i < enemigos3.size(); i++) {
            Enemigo3 e = (Enemigo3) enemigos3.get(i);
            Rectangle r2 = e.getBounds();

            if (r2.intersects(r1)) {
                if (life > 0) {
                    life -= 10;
                    enemigos3.remove(i);

                    efectos("Destrucción de enemigo.wav");
                }
                if (life == 0) {
                    heroe.setVivo(false);
                }
            }
        }

        ArrayList a = heroe.getDisparos();
        for (int i = 0; i < enemigos3.size(); i++) {
            Enemigo3 ene = (Enemigo3) enemigos3.get(i);
            Rectangle e1 = ene.getBounds();
            for (int j = 0; j < a.size(); j++) {
                disparo disp = (disparo) a.get(j);
                Rectangle r2 = disp.getBounds();
                if (e1.intersects(r2)) {
                    enemigos3.remove(i);
                    a.remove(j);
                    point += 2;
                    efectos("disparoburbuja.wav");
                }
            }
        }
    }
    
        private void generarEnemigos() {
        if (n <= 35) {
            Enemigo en = new Enemigo();
  
            en.setX(700);
            en.setY ((int)(Math.random() * 30.0 + 450));
            enemigos.add(en);
               
            n += 1;
        }
        }

    private void generarEnemigos2() {
        if (n <= 0) {
            Enemigo1 en = new Enemigo1();
            Enemigo2 e = new Enemigo2();
            Enemigo3 em = new Enemigo3();
            Enemigo eme = new Enemigo();
            Enemigo an = new Enemigo();
            Enemigo a = new Enemigo();
            Enemigo am = new Enemigo();
            Enemigo ame = new Enemigo();
            Enemigo en1 = new Enemigo();
            Enemigo e1 = new Enemigo();
            Enemigo em1 = new Enemigo();
            Enemigo eme1 = new Enemigo();
            Enemigo an1 = new Enemigo();
            Enemigo a1 = new Enemigo();
            Enemigo am1 = new Enemigo();
            Enemigo ame1 = new Enemigo();
            Enemigo en2 = new Enemigo();
            Enemigo e2 = new Enemigo();
            Enemigo em2 = new Enemigo();
            Enemigo eme2 = new Enemigo();
            Enemigo an2 = new Enemigo();
            Enemigo a2 = new Enemigo();
            Enemigo am2 = new Enemigo();
            Enemigo ame2 = new Enemigo();
            Enemigo en3 = new Enemigo();
            Enemigo e3 = new Enemigo();
            Enemigo em3 = new Enemigo();
            Enemigo eme3 = new Enemigo();
            Enemigo an3 = new Enemigo();
            Enemigo a3 = new Enemigo();
            Enemigo am3 = new Enemigo();
            Enemigo ame3 = new Enemigo();
            Enemigo a4 = new Enemigo();
            Enemigo am4 = new Enemigo();
            Enemigo ame4 = new Enemigo();

            en.setX(950);
            en.setY(400);
            enemigos1.add(en);
            e.setX(1500);
            e.setY(400);
            enemigos2.add(e);
            em.setX(1900);
            em.setY(400);
            enemigos3.add(em);
            eme.setX(800);
            eme.setY(490);
            enemigos.add(eme);
            an.setX(830);
            an.setY(470);
            enemigos.add(an);
            a.setX(850);
            a.setY(480);
            enemigos.add(a);
            am.setX(895);
            am.setY(470);
            enemigos.add(am);
            ame.setX(900);
            ame.setY(490);
            enemigos.add(ame);
            en1.setX(1000);
            en1.setY(450);
            enemigos.add(en1);
            e1.setX(1150);
            e1.setY(480);
            enemigos.add(e1);
            em1.setX(1190);
            em1.setY(460);
            enemigos.add(em1);
            eme1.setX(1200);
            eme1.setY(490);
            enemigos.add(eme1);
            an1.setX(1290);
            an1.setY(470);
            enemigos.add(an1);
            a1.setX(1250);
            a1.setY(480);
            enemigos.add(a1);
            am1.setX(1310);
            am1.setY(470);
            enemigos.add(am1);
            ame1.setX(1350);
            ame1.setY(490);
            enemigos.add(ame1);
            en2.setX(1400);
            en2.setY(450);
            enemigos.add(en2);
            e2.setX(1450);
            e2.setY(480);
            enemigos.add(e2);
            em2.setX(1490);
            em2.setY(460);
            enemigos.add(em2);
            eme2.setX(1500);
            eme2.setY(490);
            enemigos.add(eme2);
            an2.setX(1560);
            an2.setY(470);
            enemigos.add(an2);
            a2.setX(1590);
            a2.setY(480);
            enemigos.add(a2);
            am2.setX(1610);
            am2.setY(470);
            enemigos.add(am2);
            ame2.setX(1650);
            ame2.setY(490);
            enemigos.add(ame2);
            en3.setX(1690);
            en3.setY(480);
            enemigos.add(en3);
            e3.setX(1720);
            e3.setY(450);
            enemigos.add(e3);
            em3.setX(1760);
            em3.setY(460);
            enemigos.add(em3);
            eme3.setX(1780);
            eme3.setY(490);
            enemigos.add(eme3);
            an3.setX(1800);
            an3.setY(470);
            enemigos.add(an3);
            a3.setX(1820);
            a3.setY(480);
            enemigos.add(a3);
            am3.setX(1850);
            am3.setY(490);
            enemigos.add(am3);
            ame3.setX(1890);
            ame3.setY(450);
            enemigos.add(ame3);
            a4.setX(1900);
            a4.setY(450);
            enemigos.add(a4);
            am4.setX(1950);
            am4.setY(490);
            enemigos.add(am4);
            ame4.setX(1990);
            ame4.setY(450);
            enemigos.add(ame4);

            //en.setY ((int)(Math.random() * 50));
            n += 1;
        }
    }

    private void mover(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                heroe.trasladar(-distancia, 0);
                limitar(heroe);
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                heroe.trasladar(distancia, 0);
                limitar(heroe);
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                heroe.trasladar(0, distancia);
                limitar(heroe);
                repaint();
                break;
            case KeyEvent.VK_UP:
                heroe.trasladar(0, -distancia);
                limitar(heroe);
                repaint();
                break;
            case KeyEvent.VK_SPACE:
                heroe.disparar();
                repaint();
                break;

            default:
                e.consume();
                break;
        }
    }

    private void limitar(Objeto obj) {
        if (obj.getX() < 0) {
            obj.setX(0);
        }
        if (obj.getY() < 420) {
            obj.setY(420);
        }
        if (obj.getX() > 650) {
            obj.setX(650);
        }
        if (obj.getY() > 450) {
            obj.setY(450);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        mover(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void run() {
        while (true) {
            try {
                ArrayList a = heroe.getDisparos();
                for (int i = 0; i < a.size(); i++) {
                    disparo disp = (disparo) a.get(i);
                    if (disp.isVisible()) {
                        disp.mover();
                    } else {
                        a.remove(i);
                    }

                }
                for (int i = 0; i < enemigos.size(); i++) {
                    Enemigo disp = (Enemigo) enemigos.get(i);
                    if (disp.isVisible()) {
                        disp.mover();
                    } else {
                        a.remove(i);
                    }
                }

                for (int i = 0; i < enemigos1.size(); i++) {
                    Enemigo1 disp = (Enemigo1) enemigos1.get(i);
                    if (disp.isVisible()) {
                        disp.mover();
                    } else {
                        a.remove(i);
                    }
                }

                for (int i = 0; i < enemigos2.size(); i++) {
                    Enemigo2 disp = (Enemigo2) enemigos2.get(i);
                    if (disp.isVisible()) {
                        disp.mover();
                    } else {
                        a.remove(i);
                    }
                }

                for (int i = 0; i < enemigos3.size(); i++) {
                    Enemigo3 disp = (Enemigo3) enemigos3.get(i);
                    if (disp.isVisible()) {
                        disp.mover();
                    } else {
                        a.remove(i);
                    }
                }


                if (seg == 0) {
                    seg = ((int) Math.ceil(Math.random() * 2)) + 1;
                    mili = 0;
                } else {
                    mili += 100;
                    if (mili >= 800) {
                        seg--;
                        mili = 0;
                        generarEnemigos();
                    }
                }
                Colisiones();
                Colisiones1();
                Colisiones2();
                Colisiones3();
                time += 1;

                repaint();
                Thread.sleep(MS);
            } catch (InterruptedException ex) {
                System.out.println("[ERROR]" + ex);

            }
        }
    }

    public void Integrantes(Graphics g) {
        g.setColor(Color.white);
        g.drawString("IX Semestre - Nocturno", 10, 170);
        g.drawString("Eutimio Tatis Tovar", 10, 180);
        g.drawString("CUN - Ingenieria de sistemas", 10, 190);
        g.drawString("2020-A", 10, 200);
    }
}
