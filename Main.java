import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Birthday> birthdayList = new ArrayList<>();
        FileHandler.loadBirthdaysFromFile(birthdayList, "birthdays.txt");

        BirthdayManager birthdayManager = new BirthdayManager();
        for (Birthday birthday : birthdayList) {
            birthdayManager.addBirthday(birthday);
        }

        Console console = new Console(birthdayManager);
        System.out.println("Данные автоматически загружены из файла birthdays.txt");
        console.displayUpcomingBirthdays(); // Вывод сегодняшних и ближайших дней рождений при запуске
        console.run();
    }
}