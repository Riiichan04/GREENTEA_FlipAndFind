package flipAndfind;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameBTN extends JButton {
    private int value;
    private ImageIcon icon;
    //Set image lúc chưa click
    private ImageIcon noClickIcon  = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\fafIcon\\noclick.jpg");
    //Set image lúc đã click
    private ImageIcon doneIcon = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\fafIcon\\nothing.jpg");

    public GameBTN(int value, ImageIcon imageIcon) {
        //Set Value và Hình sẽ hiện
        this.value = value;
        this.icon = imageIcon;
    }

    //Event khi mở
    public void open() {
        setIcon(icon);
    }
    //Event khi đóng hình (lật sai)
    public void close() {
        setIcon(noClickIcon);
    }
    //Event khi đóng hình (lật đúng)
    public void trueClose() {
//        setBorder(new LineBorder(Color.RED, 2));
        setVisible(false);
    }
    public int getValue() {
        return value;
    }

    public void setUpPicture() {
        Image resizeIcon = icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        Image resizeNoClickIcon = noClickIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        Image resizeDoneIcon = doneIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);

        icon = new ImageIcon(resizeIcon);
        noClickIcon = new ImageIcon(resizeNoClickIcon);
        doneIcon = new ImageIcon(resizeDoneIcon);
        setIcon(noClickIcon);
    }
}
