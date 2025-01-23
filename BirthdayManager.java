import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

public class BirthdayManager {
    private List<Birthday> birthdayList;

    public BirthdayManager() {
        birthdayList = new ArrayList<>();
    }

    public List<Birthday> getAllBirthdays() {
        return birthdayList;
    }

    public List<Birthday> getUpcomingBirthdays() {
        LocalDate today = LocalDate.now();
        MonthDay todayMonthDay = MonthDay.from(today);
        List<Birthday> upcomingBirthdays = new ArrayList<>();
        for (Birthday birthday : birthdayList) {
            MonthDay birthdayMonthDay = MonthDay.from(birthday.getDate());
            if (!birthdayMonthDay.isBefore(todayMonthDay)) {
                upcomingBirthdays.add(birthday);
            }
        }
        return upcomingBirthdays;
    }

    public void addBirthday(Birthday birthday) {
        birthdayList.add(birthday);
    }

    public void deleteBirthday(int id) {
        birthdayList.removeIf(birthday -> birthday.getId() == id);
    }

    public void editBirthday(int id, String newLine) {
        for (Birthday birthday : birthdayList) {
            if (birthday.getId() == id) {
                String[] parts = newLine.split(","); // Разбиение строки на части, разделитель - запятая
                if (parts.length == 2) { // Проверка количества полей, ID должен остаться прежним
                    String newName = parts[0].trim();
                    String newDate = parts[1].trim();
                    birthday.setName(newName); // Обновление имени
                    birthday.setDate(newDate); // Обновление даты
                } else {
                    System.out.println("Некорректный формат данных. Используйте формат 'Имя,Дата' (например, Иван Иванов, 12.01.1990).");
                }
                return;
            }
        }
        System.out.println("Запись с таким ID не найдена.");
    }
    
}
