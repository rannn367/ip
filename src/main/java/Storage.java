import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Load tasks from file
    public ArrayList<String> load() throws IOException {
        ArrayList<String> tasks = new ArrayList<>();
        File file = new File(filePath);

        // If file doesn't exist, create necessary directories and file
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create parent directory if needed
            file.createNewFile();
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            tasks.add(line);
        }
        reader.close();
        return tasks;
    }

    // Save tasks to file
    public void save(ArrayList<String> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String task : tasks) {
            writer.write(task);
            writer.newLine();
        }
        writer.close();
    }
}

