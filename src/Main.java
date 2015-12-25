import gui.view.RoomWithOrderDisplayForm;
import utils.Constant;

import javax.swing.*;
import java.awt.*;

public class Main {



    public static void main(String[] args){

        JFrame frame = new JFrame("RoomWithOrderDisplayForm");
        frame.setContentPane(new RoomWithOrderDisplayForm().getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension size = new Dimension((int)(Constant.screenSize.getWidth()/2),(int)(Constant.screenSize.getHeight()/2));
        frame.setSize(size);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
