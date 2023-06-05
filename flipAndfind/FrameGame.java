package flipAndfind;

import javax.swing.*;


public class FrameGame extends JFrame {

    public FrameGame(int col, int row) {
        setTitle("FlipAndFind Simulator");
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());
        setFrameSize(col, row);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\FAFicon.jpg").getImage());

        GamePanel gamePanel = new GamePanel(col, row, this.getWidth(), this.getHeight());
        add(gamePanel);

        setVisible(true);

    }

    public void setFrameSize(int col, int row) {
        if (col < row) {
            setSize((int) (1000/(1.75 - 0.2*col)), (int) (1000/(2.25 - 0.2*row)));
        }
        else if (col == row) {
            setSize((int) (1000/(2.5 - 0.2*row)), (int) (1000/(2 - 0.2*col)));
        }
        else {
            setSize((int) (1000/(2.75 - 0.2*row)), (int) (1000/(2 - 0.2*col)));
        }
        if (col * row > 20) {
            setSize(1200, 900);
        }
        if (col - row > 2) {
            setSize(450, 900);
        }
        else if (row - col > 2) {
            setSize(1200, 600);
        }
    }

}
