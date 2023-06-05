package flipAndfind;

import javax.swing.*;
import java.awt.*;

public class FrameMainMenu extends JFrame {
    MainPanel mainPanel = new MainPanel();
    public FrameMainMenu() {
        setTitle("Flip And Find - Trúc Liệu Có Xinh?");
        setSize(800, 800);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        FrameMainMenu mgf = new FrameMainMenu();
    }


    class MainPanel extends JPanel {
        public MainPanel() {
            setBounds(0, 0, 800, 800);
            setLayout(null);

            JLabel nameGame = new JLabel("Flip And Find - Trúc Xinh");
            nameGame.setBounds(200, 100, 450, 100);
            nameGame.setFont(new Font("Verdana", Font.BOLD, 30));
            nameGame.setForeground(new Color(59, 59, 59));

            JButton playBTN = new JButton("Chơi");
            playBTN.setBounds(300, 300, 200, 100);
            playBTN.setFocusable(false);
            playBTN.setFont(new Font("Verdana", Font.BOLD, 20));
            playBTN.setBackground(new Color(242, 194, 220));

            JButton helpBTN = new JButton("Trợ Giúp");
            helpBTN.setBounds(300, 500, 200, 100);
            helpBTN.setFocusable(false);
            helpBTN.setFont(new Font("Verdana", Font.BOLD, 20));
            helpBTN.setBackground(new Color(242, 194, 220));

            ImageIcon bgIcon = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\background.jpg");
            Image background = bgIcon.getImage().getScaledInstance(800, 800, java.awt.Image.SCALE_SMOOTH);
            bgIcon = new ImageIcon(background);


            JLabel backgroundLabel = new JLabel(bgIcon);
            backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());

            JPanel faded = new JPanel();
            faded.setBackground(new Color(255, 255, 255, 21));
            faded.setBounds(0, 0, this.getWidth(), this.getHeight());

            playBTN.addActionListener(e -> {
                FrameSelectLevel sf = new FrameSelectLevel();
                dispose();
            });

            helpBTN.addActionListener(e -> {
                FrameHelp hf = new FrameHelp();
                dispose();
            });

            JLayeredPane layer = new JLayeredPane();
            layer.setBounds(0, 0, this.getWidth(), this.getHeight());
            layer.add(nameGame, JLayeredPane.MODAL_LAYER);
            layer.add(playBTN, JLayeredPane.MODAL_LAYER);
            layer.add(helpBTN, JLayeredPane.MODAL_LAYER);
            layer.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

            add(layer);
        }
    }
}
