import java.util.List;
import java.util.Scanner;

public class Console {
    private final BirthdayManager birthdayManager;
    private final Scanner scanner;

    public Console(BirthdayManager birthdayManager) {
        this.birthdayManager = birthdayManager;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n1. Отобразить весь список ДР");
            System.out.println("2. Отобразить сегодняшние и ближайшие ДР");
            System.out.println("3. Добавить запись в список ДР");
            System.out.println("4. Удалить запись из списка ДР");
            System.out.println("5. Редактировать запись в списке ДР");
            System.out.println("6. Загрузить данные из файла");
            System.out.println("7. Сохранить данные в файл");
            System.out.println("8. Выйти");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> displayAllBirthdays();
                case 2 -> displayUpcomingBirthdays();
                case 3 -> addBirthday();
                case 4 -> deleteBirthday();
                case 5 -> editBirthday();
                case 6 -> loadBirthdays();
                case 7 -> saveBirthdays();
                case 8 -> {
                    saveOnExit();
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void displayAllBirthdays() {
        System.out.println("\nВсе дни рождения:");
        List<Birthday> birthdays = birthdayManager.getAllBirthdays();
        for (Birthday birthday : birthdays) {
            System.out.println(birthday);
        }
    }

    public void displayUpcomingBirthdays() {
        System.out.println("\nСегодня и ближайшие дни рождения:");
        List<Birthday> birthdays = birthdayManager.getUpcomingBirthdays();
        for (Birthday birthday : birthdays) {
            System.out.println(birthday);
        }
    }

    private void addBirthday() {
        System.out.println("Введите id, имя и дату рождения через запятую (дд.мм.гггг):");
        String line = scanner.nextLine();
        Birthday birthday = Transfer.lineToBirthday(line, 3);

        if (birthday != null) {
            if (birthdayManager.getAllBirthdays().stream().anyMatch(b -> b.getId() == birthday.getId())) {
                System.out.println("Запись с таким ID уже существует. Пожалуйста, попробуйте снова.");
            } else {
                birthdayManager.addBirthday(birthday);
                System.out.println("Запись добавлена.");
            }
        } else {
            System.out.println("Некорректные данные. Пожалуйста, попробуйте снова.");
        }
    }

    private void deleteBirthday() {
        System.out.println("\nВведите ID для удаления:");
        int id = Integer.parseInt(scanner.nextLine());
        birthdayManager.deleteBirthday(id);
        System.out.println("Запись удалена.");
    }

    private void editBirthday() {
        System.out.println("\nВведите ID для редактирования:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите имя и дату рождения через запятую (Имя, дд.мм.гггг):");
        String newLine = scanner.nextLine();
        birthdayManager.editBirthday(id, newLine);
        System.out.println("Запись обновлена.");
    }

    private void loadBirthdays() {
        System.out.println("\nВведите имя файла для загрузки данных:");
        String fileName = scanner.nextLine();
        FileHandler.loadBirthdaysFromFile(birthdayManager.getAllBirthdays(), fileName);
        System.out.printf("Данные загружены из файла %s.", fileName);
    }

    private void saveBirthdays() {
        System.out.println("\nВведите имя файла для сохранения данных:");
        String fileName = scanner.nextLine();
        FileHandler.saveBirthdaysToFile(birthdayManager.getAllBirthdays(), fileName);
        System.out.printf("Данные сохранены в файл %s.", fileName);
    }

    private void saveOnExit() {
        FileHandler.saveBirthdaysToFile(birthdayManager.getAllBirthdays(), "birthdays.txt");
        System.out.println("Данные автоматически сохранены в файл birthdays.txt.");
    }
}
