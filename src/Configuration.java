import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
}
