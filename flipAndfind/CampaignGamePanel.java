package flipAndfind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class CampaignGamePanel extends JPanel {
    private GameBTN btn1;
    private GameBTN btn2;
    private ActionListener actionListener;
    private int status; //Nhận biết có button nào trước đó được click chưa
    private int col;
    private int row;
    private int countTrue = 0;
    private ImageIcon[] picIcon;
    private FileOperation imgOperation = new FileOperation(new File("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\fafIcon"));
    private int score = 0;
    private JLabel scoreLabel = new JLabel();
    private JLabel timeLabel = new JLabel();
    private int clickCount;
    private JLayeredPane layer = new JLayeredPane();
    private Timer timeCount;
    private int count;
    private int descTime;
    private int level = 1;
    public CampaignGamePanel(int col, int row, int w, int h, int scoreGame, int desctime) {
            descTime = desctime;
            count = 0;
            setBounds(0, 0, w, h);
            setLayout(null);
            status = 0;
            setCol(col);
            setRow(row);
            drawBTN(col, row);

        JLabel background = new JLabel(chooseBackground());
            background.setBounds(0, 0, this.getWidth(), this.getHeight());
            layer.setBounds(0, 0, this.getWidth(), this.getHeight());

            score = scoreGame;

            timeCount = new Timer(1000, checkTime());
            timeCount.start();

            scoreLabel.setText("Điểm: "+score);
            scoreLabel.setBounds(this.getWidth()/10, this.getHeight()*85/100, this.getWidth(), this.getHeight()/10);
            scoreLabel.setFont(new Font("Nunito", Font.PLAIN, 25));

            timeLabel.setText("Thời gian: " + setTimeRemaining(30000*col*row - desctime));
            timeLabel.setBounds(this.getWidth()/2, this.getHeight()*85/100, this.getWidth(), this.getHeight()/10);
            timeLabel.setFont(new Font("Nunito", Font.PLAIN, 20));
            timeLabel.setForeground(Color.white);

            layer.add(background, JLayeredPane.DEFAULT_LAYER);
            layer.add(scoreLabel, JLayeredPane.DRAG_LAYER);
            layer.add(timeLabel, JLayeredPane.DRAG_LAYER);
            add(layer);
    }

    public void drawBTN(int col, int row) {
        picIcon = new ImageIcon[col*row];
        //Có row button trên 1 dòng => Width = 1/row
        //Có col button trên 1 cột => Height = 1/col
        //Width button chiếm 85/100 Width tổng
        //Height button chiếm 80/100 Height tổng

        ArrayList<Integer> randomList = new ArrayList<>();
        Random ran = new Random();

        ArrayList<Integer> listValue = imgOperation.setupValue(col, row);

        int countRow = 0;
        for (int i = 0, locX = 0, locY = 0; i < col * row; i++, locX += this.getWidth() / row) {

            int ranIndex = ran.nextInt(col*row);
            while (randomList.contains(ranIndex)) {
                ranIndex = ran.nextInt(col*row);
            }
            randomList.add(ranIndex);

            if (countRow == row) {
                countRow = 0;
                locX = 0;
                locY += this.getHeight() / col * 85 / 100;
            }
            GameBTN gameBtn = new GameBTN(listValue.get(ranIndex),
                    new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\fafIcon\\" + listValue.get(ranIndex) + ".jpg"));
            gameBtn.setBounds(locX, locY, this.getWidth() / row, this.getHeight() / col * 80 / 100);
            gameBtn.setUpPicture();
            countRow++;
            gameBtn.addActionListener(btnAction());
            layer.add(gameBtn, JLayeredPane.DRAG_LAYER);
            picIcon[i] = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\fafIcon\\" + listValue.get(ranIndex) + ".jpg");

            if (gameBtn.getValue() == 0) {
                //Khi Button chưa đóng hoàn toàn
                gameBtn.open();
                gameBtn.setDisabledIcon(gameBtn.getIcon());
                gameBtn.setEnabled(false);
                gameBtn.setFocusable(false);
            }
        }
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public int getScore() {
        return score;
    }
    public boolean compareValue() {
        return btn1.getValue() != -1 && btn2.getValue() != -1
                && btn1 != btn2 && btn1.getValue() == btn2.getValue();
    }

    public void actionEqualValue() throws InterruptedException {
        Thread.sleep(300);
        btn1.trueClose();
        btn2.trueClose();
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        status = 0;
        countTrue++;
        score+= 100;
        if (clickCount > 3) {
            score -= clickCount*3;
        }
        if (score <= 0) score = 0;
        scoreLabel.setText("Điểm: "+score);
        clickCount = 0;
        if (countTrue == (col*row)/2) {
            timeCount.stop();

            Random ran = new Random();
            int ranBool = ran.nextInt(2);
            if (ranBool == 1) col += 2;
            else row += 2;

            if (col - row > 4) {
                row++;
            }
            else if (row - col > 4) {
                col++;
            }
            level++;
            CampaignWin cw = new CampaignWin(col, row, score, isLimit(col, row), level);
            cw.setVisible(true);
            //Đóng JFrame cha khi thắng
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        }
    }

    public void actionNotEqualValue() throws InterruptedException {
        Thread.sleep(250);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn1.close();
        btn2.close();
        btn1.setEnabled(true);
        btn2.setEnabled(true);
    }

    public ActionListener btnAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        if (status == 0) {
                            btn1 = (GameBTN) e.getSource();
                            //Khi Button chưa đóng hoàn toàn
                            btn1.open();
                            btn1.setDisabledIcon(btn1.getIcon());
//                            btn1.setEnabled(false);
                            btn1.setFocusable(false);
                            clickCount++;
                            status++;
                        } else {
                            btn2 = (GameBTN) e.getSource();
                            btn2.open();
                            btn2.setDisabledIcon(btn2.getIcon());
//                            btn2.setEnabled(false);
                            btn2.setFocusable(false);

                            if (btn1.getValue() != btn2.getValue() && btn1.getValue() != 0 && btn2.getValue() != 0) {
                                clickCount++;
                            }

                            //Nếu value bằng nhau
                            if (compareValue()) {
                                try {
                                    actionEqualValue();
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            //Nếu không
                            else {
                                try {
                                    actionNotEqualValue();
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            status = 0;
                        }
                    }
                }.start();
            }
        };
    }
    public ImageIcon chooseBackground() {
        int w = this.getWidth();
        int h = this.getHeight();

        ImageIcon img1 = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\gameBG1.jpg");
        ImageIcon img2 = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\gameBG2.jpg");
        ImageIcon img3 = new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\gameBG3.jpg");

        ImageIcon result;
        if (w - h >= 300 ) {
            result = img1;
            Image pic = img1.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            result = new ImageIcon(pic);
            scoreLabel.setForeground(Color.BLACK);
            return result;
        }
        else if ( h - w >= 300) {
            result = img3;
            Image pic = img3.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            result = new ImageIcon(pic);
            scoreLabel.setForeground(Color.WHITE);
            return result;
        }
        else {
            result = img2;
            Image pic = img2.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            result = new ImageIcon(pic);
            scoreLabel.setForeground(Color.WHITE);
            return result;
        }
    }

    public boolean isLimit(int col, int row) {
        return col*row > 40;
    }

    public ActionListener checkTime() {
        return e -> {
            count+=1000;
            timeLabel.setText("Thời gian: " + setTimeRemaining(30000*col*row - count - descTime));
            if ((count + descTime) == 30000*col*row) {
                timeCount.stop();
                timeCount.stop();
                FrameLose frameLose = new FrameLose(score, true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
            }
        };
    }

    public String setTimeRemaining(int time) {
        String result;
        int sec = (time % 60000) / 1000;
        int min = time / 60000;
        int hour = time / (60000 * 60);
        result = hour + ":" + min + ":" + sec;
        return result;
    }
}