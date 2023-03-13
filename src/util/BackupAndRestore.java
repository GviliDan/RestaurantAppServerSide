package util;


import TableModel.Food;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Timer;
import java.util.TimerTask;

public class BackupAndRestore {

    public void backup(String fromFilePath,String toPathBackup, long delay, long period) {
        TimerTask backupTask = new TimerTask() {
            public void run() {
                Path source = Paths.get("FoodMenu.txt");
                Path target = Paths.get("food_backup.txt");
                try {
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(backupTask, delay, period);
    }

    public Food restore(String fromFilePath) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(fromFilePath);
            ObjectInputStream ois = new ObjectInputStream(file);
            Food restoredFood = (Food) ois.readObject();
            return restoredFood;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
