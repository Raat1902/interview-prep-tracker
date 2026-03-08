public class Problem {
    private final String name;
    private final String category;
    private final String difficulty;
    private final String date;     // YYYY-MM-DD (kept as plain text to stay simple)
    private final String status;   // solved / unsolved (kept as plain text)

    public Problem(String name, String category, String difficulty, String date, String status) {
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.date = date;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String toCsvLine() {
        return name + "," + category + "," + difficulty + "," + date + "," + status;
    }

    public static Problem fromCsvLine(String line) {
        if (line == null) return null;

        String trimmed = line.trim();
        if (trimmed.isEmpty()) return null;

        String[] parts = trimmed.split(",", -1);
        if (parts.length < 5) return null;

        return new Problem(
                parts[0].trim(),
                parts[1].trim(),
                parts[2].trim(),
                parts[3].trim(),
                parts[4].trim()
        );
    }

    @Override
    public String toString() {
        return name + " | " + category + " | " + difficulty + " | " + date + " | " + status;
    }
}