package flipAndfind;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FrameLose extends JFrame {
    JButton playMoreBtn = new JButton("Chơi Lại");
    JButton menuBTN = new JButton("Trở về Menu");
    int scoreGame;
    int row, col;
    boolean isCampaign;
    public FrameLose(int score, boolean camp) {
        scoreGame = score;
        isCampaign = camp;

        setTitle("Loss :< ");
        setResizable(false);
        setLayout(null);
        setSize(650, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());

        ImageIcon imgBG = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\winBG.jpg");
        Image img = imgBG.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        imgBG = new ImageIcon(img);

        JLabel background = new JLabel(imgBG);
        background.setBounds(0, 0, this.getWidth(), this.getHeight());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, this.getWidth(), this.getHeight());

        JLabel winLabel = new JLabel("Bạn đã thất bại rồi!!! Bạn nhận được: " + score + " điểm");
        winLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        winLabel.setBounds(15, 30, this.getWidth(), 50);

        playMoreBtn.setFont(new Font("Nunito", Font.BOLD, 25));
        menuBTN.setFont(new Font("Nunito", Font.BOLD, 25));
        setEvent();

        playMoreBtn.setBounds(100, 150, 200, 70);
        menuBTN.setBounds(350, 150, 200, 70);

        playMoreBtn.setBackground(new Color(253, 188, 207));
        menuBTN.setBackground(new Color(253, 188, 207));

        layer.add(playMoreBtn, JLayeredPane.DRAG_LAYER);
        layer.add(winLabel, JLayeredPane.DRAG_LAYER);
        layer.add(menuBTN, JLayeredPane.DRAG_LAYER);
        layer.add(background, JLayeredPane.DEFAULT_LAYER);

        mainPanel.add(layer);
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setEvent() {
        playMoreBtn.setFocusable(false);
        playMoreBtn.addActionListener(e -> {
            if (isCampaign) {
                Random ran = new Random();
                int ranCol = ran.nextInt(2)+1;
                int ranRow = ran.nextInt(2)+1;
                if (ranRow*ranCol == 1) ranRow++;
                FrameCampaign fg = new FrameCampaign(ranCol, ranRow, 0, 0);
                dispose();
            }
            else {
                FrameSelectLevel sf = new FrameSelectLevel();
                dispose();
            }
        });

        menuBTN.setFocusable(false);
        menuBTN.addActionListener(e -> {
            FrameMainMenu mgf = new FrameMainMenu();
            dispose();
        });
    }
}
