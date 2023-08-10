import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: [фамилия] [имя] [отчество] [дата_рождения(dd.mm.yyyy)] [номер_телефона(только цифры)] [пол]");
        String input = scanner.nextLine();

        try {
            writeData(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static void writeData(String input) throws IOException {

        String[] data = input.split(" ");

        if (data.length != 6) {
            throw new IllegalArgumentException("Некоореткный ввод. Требуется 6 аргументов(через пробел)");
        }

        String lastname = data[0];
        String name = data[1];
        String surname = data[2];
        String birthDate = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        validateData(lastname, name, surname, birthDate, phoneNumber, gender);

        String fileName = lastname + ".txt";
        String userDataString = lastname + " " + name + " " + surname + ", " + birthDate + ", " + phoneNumber + ", " + gender;

        FileWriter fileWriter = new FileWriter(fileName, true);
        fileWriter.write(userDataString + System.lineSeparator());
        fileWriter.close();

        System.out.println("Данные успешно записаны в файл " + fileName);
    }

    private static void validateData(String name, String patronymic, String surname, String birthDate, String phoneNumber, String gender) {
        if (!birthDate.matches("\\d{2}.\\d{2}.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается dd.mm.yyyy");
        }

        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат номера телефона. Ожидается целое число без знаков.");
        }

        if (!gender.equals("f") && !gender.equals("m")) {
            throw new IllegalArgumentException("Неверный формат пола. Ожидается 'm' или 'f'.");
        }
    }
}
