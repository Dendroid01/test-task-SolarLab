import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Transfer {
    public static Birthday lineToBirthday(String line, int num) {
        String[] words = line.split(","); // Разбиение строки на части, разделитель - запятая

        if (words.length != num) return null; // Проверка количества полей

        try {
            int id = Integer.parseInt(words[0].trim()); // Преобразование и установка id
            String name = words[1].trim(); // Получение имени
            String date = words[2].trim(); // Преобразование строки в LocalDate
            LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return new Birthday(id, name, parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))); // Создание нового объекта Birthday
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Ошибка при преобразовании данных: " + e.getMessage());
            return null; // Возврат null в случае ошибки
        }
    }
}
