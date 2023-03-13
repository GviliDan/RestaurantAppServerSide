package test;

import TableModel.Food;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import util.BackupAndRestore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FoodServiceTest {


    @Test
    public void testBackupAndRestore() throws IOException {
        BackupAndRestore backupAndRestore = new BackupAndRestore();
        String foodMenuFile = "FoodMenu.txt";
        String foodBackupFile = "food_backup.txt";
        Food food = new Food(10, "Pizza");
        try (FileOutputStream fos = new FileOutputStream(foodMenuFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(food);
        }
        backupAndRestore.backup(foodMenuFile, foodBackupFile, 0, 1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Food restoredFood = backupAndRestore.restore(foodBackupFile);
        Assertions.assertEquals(food, restoredFood);
    }
}
