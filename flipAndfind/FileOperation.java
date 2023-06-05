package flipAndfind;

import resize.Panel;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class FileOperation {
    File folder;

    public FileOperation(File folder) {
        this.folder = folder;
    }

    public int countFile() {
        File[] listFile = folder.listFiles();
        int count = 0;
        assert listFile != null;
        for (File file : listFile) {
            if (file.isFile()) {
                count++;
            }
        }
        return count;
    }
    public ArrayList<Integer> setupValue(int col, int row) {
        ArrayList<Integer> result = new ArrayList<>();
        int count = 0;
        System.out.println(col*row/2);
        while (count < col * row / 2) {
            Random ran = new Random();
            int value = ran.nextInt(countFile()-2)+1;
            if (!result.contains(value)) {
                result.add(value);
                ++count;
            }
        }
        result.addAll(result);
        if ((col*row)%2 != 0) {
            result.add(0);
        }
        return result;
    }


}
