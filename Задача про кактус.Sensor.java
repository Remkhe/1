import java.util.Random;

// Класс `Sensor`, который имитирует датчик влажности и генерирует случайное число для влажности воздуха.
class Sensor {
    public static int getHumidity() {
        // Генерация случайного числа для влажности воздуха
        Random random = new Random();
        return random.nextInt(100);
    }
}
