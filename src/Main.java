import gui.view.RoomWithOrderDisplayForm;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.Constant;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static String[] DEFAULT_FONT = new String[]{
            "Table.font"
            , "TableHeader.font"
            , "CheckBox.font"
            , "Tree.font"
            , "Viewport.font"
            , "ProgressBar.font"
            , "RadioButtonMenuItem.font"
            , "ToolBar.font"
            , "ColorChooser.font"
            , "ToggleButton.font"
            , "Panel.font"
            , "TextArea.font"
            , "Menu.font"
            , "TableHeader.font"
             ,"TextField.font"
            , "OptionPane.font"
            , "MenuBar.font"
            , "Button.font"
            , "Label.font"
            , "PasswordField.font"
            , "ScrollPane.font"
            , "MenuItem.font"
            , "ToolTip.font"
            , "List.font"
            , "EditorPane.font"
            , "Table.font"
            , "TabbedPane.font"
            , "RadioButton.font"
            , "CheckBoxMenuItem.font"
            , "TextPane.font"
            , "PopupMenu.font"
            , "TitledBorder.font"
            , "ComboBox.font"
    };

    public static void main(String[] args) {

//        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
//        try {
//            UIManager.setLookAndFeel(lookAndFeel);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            UIManager.put("RootPane.setupButtonVisible", false);
            for (int i = 0; i < DEFAULT_FONT.length; i++)
                UIManager.put(DEFAULT_FONT[i], new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            //TODO exception
        }

        JFrame frame = new JFrame("RoomWithOrderDisplayForm");
        frame.setContentPane(new RoomWithOrderDisplayForm().getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension size = new Dimension(
                (int) (Constant.screenSize.getWidth() * 3 / 4),
                (int) (Constant.screenSize.getHeight() * 3 / 4));
        frame.setSize(size);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
