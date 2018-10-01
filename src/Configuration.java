import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Configuration {
    public static String rotate = "Up", left = "Left", right = "Right", down = "Down", pause = "P";
    private static ArrayList<Choice> choices;

    public static void openConfig(JFrame frame){
        choices = new ArrayList<Choice>();
        final JFrame option = new JFrame("Options");
        option.setSize(400, 300);
        option.setResizable(false);
        option.setLocationRelativeTo(frame);
        option.setVisible(true);
        option.setLayout(null);
        Choice left = addChoice("Left", option, 40, 40);
        left.select(Configuration.left);

        Choice right = addChoice("Right", option, 40, 80);
        right.select(Configuration.right);

        Choice down = addChoice("Down", option, 40, 120);
        down.select(Configuration.down);

        Choice pause = addChoice("Pause", option, 40, 160);
        pause.select(Configuration.pause);

        Choice rotate = addChoice("Rotate", option, 40, 200);
        rotate.select(Configuration.rotate);

        JButton done = new JButton("Ok");
        done.setBounds(150, 200, 100, 30);
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option.dispose();
                saveChanges();

            }
        });

        option.add(done);
    }

    public static void saveChanges () {
        Choice left = choices.get(0);
        Choice right = choices.get(1);
        Choice down = choices.get(2);
        Choice pause = choices.get(3);
        Choice rotate = choices.get(4);
        Configuration.left = left.getSelectedItem();
        Configuration.right = right.getSelectedItem();
        Configuration.down = down.getSelectedItem();
        Configuration.pause = pause.getSelectedItem();
        Configuration.rotate = rotate.getSelectedItem();
        try {
            saveConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Choice addChoice(String name, JFrame option, int x, int y){
        JLabel label = new JLabel(name);
        label.setBounds(x, y -20, 100, 20);
        Choice key = new Choice();
        for (String s: getKeyNames()){
            key.add(s);
        }
        key.setBounds(x, y, 100, 20);
        option.add(key);
        option.add(label);
        choices.add(key);
        return key;
    }

    public static ArrayList<String> getKeyNames() {
        ArrayList<String> result = new ArrayList<String>();
        for (String s: KeyGetter.keyNames){
            result.add(s);
           /* if(s.equalsIgnoreCase("RIGHT")){
                break;
            }*/
        }

        return result;
    }

    public static void loadConfig() throws IOException {
        File directory = new File(getDefaultDirectory(), "/Tetris");
        if (!directory.exists()){
            directory.mkdirs();
        }
        File config = new File(directory, "/config.txt");
        if (!config.exists()){

            config.createNewFile();
            saveConfig();
            return;
        }

        Scanner s = new Scanner(config);
        HashMap<String, String> values = new HashMap<String, String>();

        while (s.hasNext()){
            String[] entry = s.nextLine().split("\\:");
            String key = entry[0];
            String value = entry[1];
            values.put(key, value);
        }

        if (values.size() != 5) {
            saveConfig();
            return;
        }

        if (!values.containsKey("left") || !values.containsKey("right") || !values.containsKey("rotate") || !values.containsKey("down") || !values.containsKey("pause")){
            System.out.println("Invalid names in config");
            saveConfig();
            return;
        }

        String left = values.get("left");
        String right = values.get("right");
        String rotate = values.get("rotate");
        String down = values.get("down");
        String pause = values.get("pause");

        if (getKeyNames().contains(left) && getKeyNames().contains(right) && getKeyNames().contains(rotate) && getKeyNames().contains(down) && getKeyNames().contains(pause)){
            System.out.println("Invalid keys");
            return;
        }

        Configuration.left = left;
        Configuration.right = right;
        Configuration.rotate = rotate;
        Configuration.down = down;
        Configuration.pause = pause;



    }

    public static void saveConfig() throws IOException {
        File directory = new File(getDefaultDirectory(), "/Tetris");
        if (!directory.exists()){
            directory.mkdirs();
        }
        File config = new File(directory, "/config.txt");
        PrintWriter pw = new PrintWriter(config);
        pw.println("right:" + right);
        pw.println("left:" + left);
        pw.println("rotate:" + rotate);
        pw.println("down:" + down);
        pw.println("pause:" + pause);
        pw.close();
    }

    public static String getDefaultDirectory() {
        String os = System.getProperty("os.name");
        if (os.contains("WIN")){
            return System.getenv("APPDATA");
        }else if (os.contains("NUX")){
            return System.getProperty("user.home");
        }
        return System.getProperty("user.home");
    }
}
