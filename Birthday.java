import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Birthday {
    private int id;
    private String name;
    private LocalDate date;

    public Birthday() {
    }

    public Birthday(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Override
    public String toString() {
        return id + ". " + name + ": " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
