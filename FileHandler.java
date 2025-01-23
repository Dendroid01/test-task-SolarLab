import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileHandler {
    public static void saveBirthdaysToFile(List<Birthday> birthdayList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
            for (Birthday birthday : birthdayList) {
                writer.write(birthday.getId() + "," + birthday.getName() + "," + birthday.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    public static void loadBirthdaysFromFile(List<Birthday> birthdayList, String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String date = parts[2].trim();
                if (birthdayList.stream().noneMatch(b -> b.getId() == id)){
                Birthday birthday = new Birthday(id, name, date);
                birthdayList.add(birthday);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }
}
