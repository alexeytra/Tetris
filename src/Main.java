import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable, KeyListener {

    public static final int WIDTH = 400, HEIGHT = 540;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        Main m = new Main();
        frame.add(m);
        frame.setVisible(true);
        m.start();
    }

    public void start(){
        Thread t = new Thread(this);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            update();
            BufferStrategy buf = getBufferStrategy();
            if (buf == null){
                createBufferStrategy(3);
                continue;
            }

            Graphics2D g = (Graphics2D) buf.getDrawGraphics();
            render(g);
            buf.show();
        }
    }

    public void update() {

    }

    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri", Font.PLAIN, 20));
        g.drawString("Tetris", 170, 50);

    }
}
