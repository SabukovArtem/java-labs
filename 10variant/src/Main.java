import java.io.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        // Добавляем начальную литературу (из примера пользователя)
        library.addLiterature(new Literature(101, "Книга", "Война и мир", 1869, "Русский вестник", 1225, "Лев Толстой"));
        library.addLiterature(new Literature(102, "Журнал", "Наука и жизнь", 2022, "Наука", 80, "Редакция"));
        library.addLiterature(new Literature(103, "Книга", "Мастер и Маргарита", 1967, "Москва", 480, "Михаил Булгаков"));
        library.addLiterature(new Literature(104, "Учебник", "Java для начинающих", 2020, "Питер", 450, "Иванов И.И."));
        library.addLiterature(new Literature(105, "Книга", "1984", 1949, "Secker & Warburg", 328, "Джордж Оруэлл"));

        System.out.println("=== Система учёта литературы ===");
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addLiterature();
                    break;
                case "2":
                    library.printAll();
                    break;
                case "3":
                    sortAndPrint();
                    break;
                case "4":
                    saveToFile();
                    break;
                case "5":
                    loadFromFile();
                    break;
                case "6":
                    exit = true;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Добавить литературу");
        System.out.println("2. Показать весь список");
        System.out.println("3. Показать отсортированный список");
        System.out.println("4. Сохранить в файл");
        System.out.println("5. Загрузить из файла");
        System.out.println("6. Выход");
        System.out.print("Выберите пункт: ");
    }

    private static void addLiterature() {
        System.out.println("\nВыберите тип литературы:");
        System.out.println("1. Литература (базовый)");
        System.out.println("2. Научно-техническая");
        System.out.println("3. Периодика");
        System.out.println("4. Справочник");
        System.out.print("Ваш выбор: ");
        String typeChoice = scanner.nextLine().trim();

        // Общие поля для всех типов
        System.out.print("Код источника: ");
        int code = Integer.parseInt(scanner.nextLine());
        System.out.print("Тип литературы: ");
        String type = scanner.nextLine();
        System.out.print("Название: ");
        String title = scanner.nextLine();
        System.out.print("Год издательства: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Название издательства: ");
        String publisher = scanner.nextLine();
        System.out.print("Количество страниц: ");
        int pages = Integer.parseInt(scanner.nextLine());
        System.out.print("Автор: ");
        String author = scanner.nextLine();

        switch (typeChoice) {
            case "1":
                Literature lit = new Literature(code, type, title, year, publisher, pages, author);
                library.addLiterature(lit);
                System.out.println("Добавлено.");
                break;
            case "2":
                System.out.print("Область науки: ");
                String scienceField = scanner.nextLine();
                System.out.print("Количество экземпляров: ");
                int copies = Integer.parseInt(scanner.nextLine());
                ScientificTechnicalLiterature sciLit = new ScientificTechnicalLiterature(
                        code, type, title, year, publisher, pages, author, scienceField, copies);
                library.addLiterature(sciLit);
                System.out.println("Добавлено.");
                break;
            case "3":
                System.out.print("Вид периодики (газета, журнал и т.д.): ");
                String periodicalType = scanner.nextLine();
                System.out.print("Период издательства (например, 'ежемесячно'): ");
                String period = scanner.nextLine();
                Periodical perLit = new Periodical(
                        code, type, title, year, publisher, pages, author, periodicalType, period);
                library.addLiterature(perLit);
                System.out.println("Добавлено.");
                break;
            case "4":
                System.out.print("Направление (тематика): ");
                String direction = scanner.nextLine();
                System.out.print("Том: ");
                int volume = Integer.parseInt(scanner.nextLine());
                System.out.print("Часть: ");
                int part = Integer.parseInt(scanner.nextLine());
                ReferenceBook refLit = new ReferenceBook(
                        code, type, title, year, publisher, pages, author, direction, volume, part);
                library.addLiterature(refLit);
                System.out.println("Добавлено.");
                break;
            default:
                System.out.println("Неверный выбор. Литература не добавлена.");
        }
    }

    private static void sortAndPrint() {
        System.out.println("\nСортировать по:");
        System.out.println("1. Наименованию");
        System.out.println("2. Году издательства");
        System.out.print("Ваш выбор: ");
        String sortChoice = scanner.nextLine().trim();

        List<Literature> sorted = null;
        if (sortChoice.equals("1")) {
            sorted = library.getSortedByTitle();
        } else if (sortChoice.equals("2")) {
            sorted = library.getSortedByYear();
        } else {
            System.out.println("Неверный выбор.");
            return;
        }

        System.out.println("\nОтсортированный список:");
        for (Literature l : sorted) {
            System.out.println(l);
        }
    }

    private static void saveToFile() {
        System.out.print("Введите имя файла для сохранения: ");
        String filename = scanner.nextLine().trim();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Literature l : library.getAll()) {
                writer.println(l.toFileString());
            }
            System.out.println("Данные сохранены в файл " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        System.out.print("Введите имя файла для загрузки: ");
        String filename = scanner.nextLine().trim();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            library.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Literature l = Literature.fromFileString(line);
                if (l != null) {
                    library.addLiterature(l);
                }
            }
            System.out.println("Данные загружены из файла " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке: " + e.getMessage());
        }
    }

    // ======================== Классы предметной области ========================

    public static class Literature {
        private int sourceCode;
        private String literatureType;
        private String title;
        private int publicationYear;
        private String publisher;
        private int pageCount;
        private String author;

        public Literature(int sourceCode, String literatureType, String title,
                          int publicationYear, String publisher, int pageCount, String author) {
            this.sourceCode = sourceCode;
            this.literatureType = literatureType;
            this.title = title;
            this.publicationYear = publicationYear;
            this.publisher = publisher;
            this.pageCount = pageCount;
            this.author = author;
        }

        public int getSourceCode() { return sourceCode; }
        public String getLiteratureType() { return literatureType; }
        public String getTitle() { return title; }
        public int getPublicationYear() { return publicationYear; }
        public String getPublisher() { return publisher; }
        public int getPageCount() { return pageCount; }
        public String getAuthor() { return author; }

        public void setSourceCode(int sourceCode) { this.sourceCode = sourceCode; }
        public void setLiteratureType(String literatureType) { this.literatureType = literatureType; }
        public void setTitle(String title) { this.title = title; }
        public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
        public void setPublisher(String publisher) { this.publisher = publisher; }
        public void setPageCount(int pageCount) { this.pageCount = pageCount; }
        public void setAuthor(String author) { this.author = author; }

        // Красивый вывод в формате пользователя
        @Override
        public String toString() {
            return String.format("""
                    ========================================
                    Код: %d
                    Тип: %s
                    Название: %s
                    Год: %d
                    Издательство: %s
                    Страниц: %d
                    Автор: %s
                    ========================================
                    """, sourceCode, literatureType, title, publicationYear, publisher, pageCount, author);
        }

        public String toFileString() {
            return "Literature;" + sourceCode + ";" + literatureType + ";" + title + ";" +
                    publicationYear + ";" + publisher + ";" + pageCount + ";" + author;
        }

        public static Literature fromFileString(String line) {
            String[] parts = line.split(";", -1);
            if (parts.length < 8) return null;
            String className = parts[0];
            try {
                int code = Integer.parseInt(parts[1]);
                String litType = parts[2];
                String title = parts[3];
                int year = Integer.parseInt(parts[4]);
                String publisher = parts[5];
                int pages = Integer.parseInt(parts[6]);
                String author = parts[7];

                switch (className) {
                    case "Literature":
                        return new Literature(code, litType, title, year, publisher, pages, author);
                    case "ScientificTechnicalLiterature":
                        if (parts.length < 10) return null;
                        String scienceField = parts[8];
                        int copies = Integer.parseInt(parts[9]);
                        return new ScientificTechnicalLiterature(code, litType, title, year, publisher, pages,
                                author, scienceField, copies);
                    case "Periodical":
                        if (parts.length < 10) return null;
                        String periodicalType = parts[8];
                        String period = parts[9];
                        return new Periodical(code, litType, title, year, publisher, pages,
                                author, periodicalType, period);
                    case "ReferenceBook":
                        if (parts.length < 11) return null;
                        String direction = parts[8];
                        int volume = Integer.parseInt(parts[9]);
                        int part = Integer.parseInt(parts[10]);
                        return new ReferenceBook(code, litType, title, year, publisher, pages,
                                author, direction, volume, part);
                    default:
                        return null;
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    public static class ScientificTechnicalLiterature extends Literature {
        private String scienceField;
        private int copiesCount;

        public ScientificTechnicalLiterature(int sourceCode, String literatureType, String title,
                                             int publicationYear, String publisher, int pageCount, String author,
                                             String scienceField, int copiesCount) {
            super(sourceCode, literatureType, title, publicationYear, publisher, pageCount, author);
            this.scienceField = scienceField;
            this.copiesCount = copiesCount;
        }

        public String getScienceField() { return scienceField; }
        public int getCopiesCount() { return copiesCount; }

        public void setScienceField(String scienceField) { this.scienceField = scienceField; }
        public void setCopiesCount(int copiesCount) { this.copiesCount = copiesCount; }

        // Добавляем специфические поля к красивому выводу
        @Override
        public String toString() {
            return super.toString().replace("========================================\n", "") +
                    String.format("Область науки: %s\nЭкземпляров: %d\n========================================\n",
                            scienceField, copiesCount);
        }

        @Override
        public String toFileString() {
            return "ScientificTechnicalLiterature;" + getSourceCode() + ";" + getLiteratureType() + ";" +
                    getTitle() + ";" + getPublicationYear() + ";" + getPublisher() + ";" +
                    getPageCount() + ";" + getAuthor() + ";" + scienceField + ";" + copiesCount;
        }
    }

    public static class Periodical extends Literature {
        private String periodicalType;
        private String publicationPeriod;

        public Periodical(int sourceCode, String literatureType, String title,
                          int publicationYear, String publisher, int pageCount, String author,
                          String periodicalType, String publicationPeriod) {
            super(sourceCode, literatureType, title, publicationYear, publisher, pageCount, author);
            this.periodicalType = periodicalType;
            this.publicationPeriod = publicationPeriod;
        }

        public String getPeriodicalType() { return periodicalType; }
        public String getPublicationPeriod() { return publicationPeriod; }

        public void setPeriodicalType(String periodicalType) { this.periodicalType = periodicalType; }
        public void setPublicationPeriod(String publicationPeriod) { this.publicationPeriod = publicationPeriod; }

        @Override
        public String toString() {
            return super.toString().replace("========================================\n", "") +
                    String.format("Вид периодики: %s\nПериод: %s\n========================================\n",
                            periodicalType, publicationPeriod);
        }

        @Override
        public String toFileString() {
            return "Periodical;" + getSourceCode() + ";" + getLiteratureType() + ";" +
                    getTitle() + ";" + getPublicationYear() + ";" + getPublisher() + ";" +
                    getPageCount() + ";" + getAuthor() + ";" + periodicalType + ";" + publicationPeriod;
        }
    }

    public static class ReferenceBook extends Literature {
        private String direction;
        private int volume;
        private int part;

        public ReferenceBook(int sourceCode, String literatureType, String title,
                             int publicationYear, String publisher, int pageCount, String author,
                             String direction, int volume, int part) {
            super(sourceCode, literatureType, title, publicationYear, publisher, pageCount, author);
            this.direction = direction;
            this.volume = volume;
            this.part = part;
        }

        public String getDirection() { return direction; }
        public int getVolume() { return volume; }
        public int getPart() { return part; }

        public void setDirection(String direction) { this.direction = direction; }
        public void setVolume(int volume) { this.volume = volume; }
        public void setPart(int part) { this.part = part; }

        @Override
        public String toString() {
            return super.toString().replace("========================================\n", "") +
                    String.format("Направление: %s\nТом: %d\nЧасть: %d\n========================================\n",
                            direction, volume, part);
        }

        @Override
        public String toFileString() {
            return "ReferenceBook;" + getSourceCode() + ";" + getLiteratureType() + ";" +
                    getTitle() + ";" + getPublicationYear() + ";" + getPublisher() + ";" +
                    getPageCount() + ";" + getAuthor() + ";" + direction + ";" + volume + ";" + part;
        }
    }

    public static class Library {
        private List<Literature> items;

        public Library() {
            items = new ArrayList<>();
        }

        public void addLiterature(Literature lit) {
            items.add(lit);
        }

        public List<Literature> getAll() {
            return items;
        }

        public void clear() {
            items.clear();
        }

        public void printAll() {
            if (items.isEmpty()) {
                System.out.println("Список пуст.");
                return;
            }
            System.out.println("\n--- Весь список литературы ---");
            for (int i = 0; i < items.size(); i++) {
                System.out.println(items.get(i));
            }
        }

        public List<Literature> getSortedByTitle() {
            List<Literature> sorted = new ArrayList<>(items);
            sorted.sort(Comparator.comparing(Literature::getTitle, String.CASE_INSENSITIVE_ORDER));
            return sorted;
        }

        public List<Literature> getSortedByYear() {
            List<Literature> sorted = new ArrayList<>(items);
            sorted.sort(Comparator.comparingInt(Literature::getPublicationYear));
            return sorted;
        }
    }
}