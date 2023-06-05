package flipAndfind;

import javax.swing.*;


public class FrameCampaign extends JFrame {

    public FrameCampaign(int col, int row, int score, int descTime) {
        setTitle("FlipAndFind Simulator");
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());
        setFrameSize(col, row);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());


        CampaignGamePanel cgp = new CampaignGamePanel(col, row, this.getWidth(), this.getHeight(), score, descTime);
        add(cgp);

        setVisible(true);

    }

    public void setFrameSize(int col, int row) {
//        if (col < row) {
//            setSize((int) (1000/(1.75 - 0.2*col)), (int) (1000/(2.25 - 0.2*row)));
//        }
//        else if (col == row) {
//            setSize((int) (1000/(2.5 - 0.2*row)), (int) (1000/(2 - 0.2*col)));
//        }
//        else {
//            setSize((int) (1000/(4 - 0.1*row)), (int) (1000/(2 - 0.2*col)));
//        }
//        if (col * row > 12 || Math.abs(col - row) > 2) {
//            setSize(1600, 900);
//        }

        if (col < row) {
            setSize((int) (1000/(1.75 - 0.3*col)), (int) (1000/(2.25 - 0.2*row)));
        }
        else if (col == row) {
            setSize((int) (1000/(2.5 - 0.2*row)), (int) (1000/(2 - 0.2*col)));
        }
        else {
            setSize((int) (1000/(3 - 0.2*row)), (int) (1000/(2 - 0.2*col)));
        }
        if (col * row > 20 || Math.abs(col - row) > 2) {
            setSize(1080, 900);
        }
    }
}
