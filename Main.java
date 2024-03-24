import java.util.*;

class Laptop {
    private String model;
    private int ram;
    private int hddSize;
    private String operatingSystem;
    private String color;

    public Laptop(String model, int ram, int hddSize, String operatingSystem, String color) {
        this.model = model;
        this.ram = ram;
        this.hddSize = hddSize;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    // Геттеры для полей

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getHddSize() {
        return hddSize;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Model: " + model + ", RAM: " + ram + "GB, HDD Size: " + hddSize + "GB, OS: " + operatingSystem + ", Color: " + color;
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем множество ноутбуков
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("HP", 16, 256, "Windows", "Gray"));
        laptops.add(new Laptop("MacBook", 16, 512, "macOS", "Space Gray"));
        laptops.add(new Laptop("Lenovo", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Asus", 16, 512, "Linux", "Blue"));

        // Запрашиваем критерии фильтрации
        Map<String, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите критерии фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criteria = scanner.nextInt();
        scanner.nextLine(); // очищаем буфер

        switch (criteria) {
            case 1:
                System.out.println("Введите минимальное количество RAM(GB):");
                int minRam = scanner.nextInt();
                filters.put("RAM", minRam);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД(GB):");
                int minHddSize = scanner.nextInt();
                filters.put("HDD", minHddSize);
                break;
            case 3:
                System.out.println("Введите операционную систему('Linux', 'Windows', 'macOS'):");
                String os = scanner.nextLine();
                filters.put("OS", os);
                break;
            case 4:
                System.out.println("Введите цвет:");
                String color = scanner.nextLine();
                filters.put("Color", color);
                break;
            default:
                System.out.println("Некорректный критерий.");
                return;
        }

        // Фильтруем ноутбуки
        List<Laptop> filteredLaptops = filterLaptops(laptops, filters);

        // Выводим результаты фильтрации
        System.out.println("Ноутбуки, отвечающие условиям фильтра:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    // Метод для фильтрации ноутбуков по заданным критериям
    private static List<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Object> filters) {
        List<Laptop> filteredLaptops = new ArrayList<>();

        for (Laptop laptop : laptops) {
            boolean meetsCriteria = true;

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String criteria = entry.getKey();
                Object value = entry.getValue();

                switch (criteria) {
                    case "RAM":
                        if (laptop.getRam() < (int) value) {
                            meetsCriteria = false;
                        }
                        break;
                    case "HDD":
                        if (laptop.getHddSize() < (int) value) {
                            meetsCriteria = false;
                        }
                        break;
                    case "OS":
                        if (!laptop.getOperatingSystem().equalsIgnoreCase((String) value)) {
                            meetsCriteria = false;
                        }
                        break;
                    case "Color":
                        if (!laptop.getColor().equalsIgnoreCase((String) value)) {
                            meetsCriteria = false;
                        }
                        break;
                    default:
                        break;
                }
            }

            if (meetsCriteria) {
                filteredLaptops.add(laptop);
            }
        }

        return filteredLaptops;
    }
}
