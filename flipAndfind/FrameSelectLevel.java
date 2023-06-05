package flipAndfind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FrameSelectLevel extends JFrame {
    int row;
    int col;
    JLabel errLabel = new JLabel();
    JTextField colField = new JTextField(20);
    JTextField rowField = new JTextField(20);
    public FrameSelectLevel() {
        setTitle("Filp And Find");
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());
        setLayout(null);
        setSize(650, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());

        ImageIcon bg = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\selectLevelBG.jpg");
        Image background = bg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        bg = new ImageIcon(background);
        JLabel bgLabel = new JLabel(bg);
        bgLabel.setBounds(0, 0, this.getWidth(), this.getHeight());

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(this.getSize());
        mainPanel.setLayout(null);

        JLabel noticeLabel = new JLabel("Nhập kích thước bạn muốn chơi.");
        JLabel notice2Label = new JLabel("Nhưng (số dòng * số cột) từ 2 đến 40 và không chênh nhau quá 4 nhé!!!");
        noticeLabel.setBounds(0, 0, this.getWidth(), 30);
        notice2Label.setBounds(0, 30, this.getWidth(), 30);

        JLabel colLabel = new JLabel("Nhập số dòng bạn muốn chơi");
        colLabel.setBounds(70, 70, 400, 40);
        colLabel.setForeground(Color.WHITE);
        colField.setBounds(420, 70, 100, 40);

        JLabel rowLabel = new JLabel("Nhập số cột bạn muốn chơi");
        rowLabel.setForeground(Color.WHITE);
        rowLabel.setBounds(70, 110, 400, 40);
        rowField.setBounds(420, 110,100, 40);

        JButton button = new JButton("Chơi");
        button.addActionListener(btnEvent());
        button.setBounds(this.getWidth()/2 - 150, 170, 300, 40);

        JButton campButton = new JButton("Chơi Chiến Dịch");
        campButton.setBounds(this.getWidth()/2 - 150, 220, 300, 40);
        campButton.addActionListener(campaignEvent());

        errLabel.setBounds(0, 300, this.getWidth(), 40);
        noticeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        notice2Label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        colLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        rowLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));

        errLabel.setFont(new Font("Nunito", Font.PLAIN, 20));
        errLabel.setForeground(Color.PINK);

        noticeLabel.setForeground(Color.WHITE);
        notice2Label.setForeground(Color.WHITE);

        button.setFont(new Font("Nunito", Font.BOLD, 25));
        button.setFocusable(false);
        button.setBackground(Color.CYAN);

        campButton.setFont(new Font("Nunito", Font.BOLD, 25));
        campButton.setFocusable(false);
        campButton.setBackground(Color.CYAN);

        colField.setFont(new Font("Verdana", Font.PLAIN, 23));
        rowField.setFont(new Font("Verdana", Font.PLAIN, 23));

        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, this.getWidth(), this.getHeight());
        layer.add(colLabel, JLayeredPane.POPUP_LAYER);
        layer.add(colField, JLayeredPane.POPUP_LAYER);
        layer.add(rowLabel, JLayeredPane.POPUP_LAYER);
        layer.add(rowField, JLayeredPane.POPUP_LAYER);
        layer.add(noticeLabel, JLayeredPane.POPUP_LAYER);
        layer.add(notice2Label, JLayeredPane.POPUP_LAYER);
        layer.add(button, JLayeredPane.POPUP_LAYER);
        layer.add(campButton, JLayeredPane.POPUP_LAYER);
        layer.add(errLabel, JLayeredPane.POPUP_LAYER);
        layer.add(bgLabel, JLayeredPane.DEFAULT_LAYER);

        mainPanel.add(layer);
        add(mainPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public ActionListener btnEvent() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rowString = rowField.getText();
                String colString = colField.getText();

                try {
                    row = Integer.parseInt(rowString);
                    col = Integer.parseInt(colString);

                    if (row*col > 40 || row*col < 2 || Math.abs(row - col) > 4) {
                        errLabel.setText("Số dòng và cột không hợp lệ");
                        colField.setText("");
                        rowField.setText("");
                    }
                    else {
                        errLabel.setText("");
                        FrameGame gf = new FrameGame(col, row);
                        dispose();
                    }

                }
                catch (NumberFormatException ex) {
                    errLabel.setText("Số dòng và cột không hợp lệ");
                    colField.setText("");
                    rowField.setText("");
                }
            }
        };
    }
    public ActionListener campaignEvent() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random ran = new Random();
                int ranCol = ran.nextInt(2)+1;
                int ranRow = ran.nextInt(2)+1;
                if (ranRow*ranCol == 1) ranRow++;
                FrameCampaign fg = new FrameCampaign(ranCol, ranRow, 0, 0);
                dispose();
            }
        };
    }
}
