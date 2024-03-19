import java.util.List;

public class FileHandler {

    private final String foodsFilePath;
    private final String logFilePath;

    public FileHandler(String foodsFilePath, String logFilePath) {
        this.foodsFilePath = foodsFilePath;
        this.logFilePath = logFilePath;
    }

    // Methods will be used for writing and reading foods and logs to/from CSV files
    public List<String[]> readFoodsFile() {
        return null;

    }

    public void writeFoodsFile(List<String[]> foods) {

    }

    public List<String[]> readLogFile() {
        return null;
    }

    public void writeLogFile(List<String[]> logs) {

    }

}
