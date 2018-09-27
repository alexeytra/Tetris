import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable, KeyListener {

    public static final int WIDTH = 400, HEIGHT = 565;


    public static void main(String[] args) {
        final JFrame frame = new JFrame("Tetris");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        KeyGetter.loadKeys();

        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setBounds(0, 0, 45, 24);
        bar.setBounds(0, 0, WIDTH, 25);

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting a new GAme");
            }
        });

        final JMenuItem highScore = new JMenuItem("Highscore");
        highScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int highScore = 0;
                final JFrame alert = new JFrame("High score");
                alert.setSize(200, 150);
                alert.setLayout(null);
                alert.setLocationRelativeTo(null);
                alert.setAlwaysOnTop(true);


                JLabel score = new JLabel("The highscore is " + highScore);
                score.setBounds(0, 0, 200, 50);
                JButton okayButton = new JButton("Okay");
                okayButton.setBounds(50, 80, 100, 30);
                okayButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        alert.dispose();
                    }
                });
                alert.add(score);
                alert.add(okayButton);
                alert.setResizable(false);
                alert.setVisible(true);
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit");
                System.exit(0);
            }
        });

        JMenuItem options = new JMenuItem("Option");
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuration.openConfig(frame);
            }
        });

        Main m = new Main();
        m.setBounds(0, 25, WIDTH, HEIGHT - 25);
        frame.add(m);
        file.add(newGame);
        file.add(highScore);
        file.add(options);
        file.add(exit);
        bar.add(file);
        frame.add(bar);
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

    public void init() {

    }
}
