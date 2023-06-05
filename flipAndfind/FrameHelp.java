package flipAndfind;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FrameHelp extends JFrame {
    private JButton backBTN = new JButton("Trở Lại");
    public FrameHelp() {
        setTitle("Flip And Find - Hướng Dẫn");
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());
        setSize(1200, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());

        ImageIcon imgIcon = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\helpBG.jpg");
        Image image = imgIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(image);
        JLabel backgroud = new JLabel(imgIcon);
        backgroud.setBounds(0, 0, this.getWidth(), this.getHeight());

        JLabel rule = new JLabel("HƯỚNG DẪN");
        JLabel rule1 = new JLabel("1. Bạn cần phải tìm được tất cả cặp hình giống nhau xuất hiện trên màn hình");
        JLabel rule2 = new JLabel("2. Với mỗi lần click thừa (click thứ 3) kể từ sau khi bạn chọn đúng, điểm nhận được sẽ giảm dần");
        JLabel rule3 = new JLabel("3. Bạn sẽ nhận được tối đa 100 điểm/mỗi lần click đúng");

        rule.setFont(new Font("Verdana", Font.BOLD, 30));
        rule1.setFont(new Font("Verdana", Font.PLAIN, 23));
        rule2.setFont(new Font("Verdana", Font.PLAIN, 23));
        rule3.setFont(new Font("Verdana", Font.PLAIN, 23));

        rule.setBounds(0, 50, this.getWidth(), 50);
        rule1.setBounds(20, 100, this.getWidth(), 50);
        rule2.setBounds(20, 150, this.getWidth(), 50);
        rule3.setBounds(20, 200, this.getWidth(), 50);

        rule.setForeground(Color.WHITE);
        rule1.setForeground(Color.WHITE);
        rule2.setForeground(Color.WHITE);
        rule3.setForeground(Color.WHITE);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(this.getWidth(), this.getHeight());
        mainPanel.setLayout(null);
//        TitledBorder titleBorder = new TitledBorder(new LineBorder(Color.BLACK, 2), "Luật Chơi",
//                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Nunito", Font.BOLD, 22));

        backBTN.setBounds(1000, 300,150, 50);
        backBTN.setFocusable(false);
        backBTN.setBackground(new Color(253, 188, 207));
        backBTN.setFont(new Font("Verdana", Font.PLAIN, 23));
        backBTN.addActionListener(e -> {
            FrameMainMenu mgf = new FrameMainMenu();
            dispose();
        });

        JLayeredPane layer = new JLayeredPane();

        layer.setBounds(0, 0, this.getWidth(), this.getHeight());
//        layer.setBorder(titleBorder);
        layer.add(rule, JLayeredPane.POPUP_LAYER);
        layer.add(rule1, JLayeredPane.POPUP_LAYER);
        layer.add(rule2, JLayeredPane.POPUP_LAYER);
        layer.add(rule3, JLayeredPane.POPUP_LAYER);
        layer.add(backBTN, JLayeredPane.POPUP_LAYER);
        layer.add(backgroud, JLayeredPane.DEFAULT_LAYER);

        mainPanel.add(layer);
        add(mainPanel);
        setVisible(true);
    }

}
