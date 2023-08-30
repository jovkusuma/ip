import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String DIRECTORY = "./data";
    private final String FILE_PATH = DIRECTORY + "/duke.txt";
    private File STORAGE_FILE;

    public Storage() throws DukeException {
        try {
            File dir = new File(this.DIRECTORY);
            if (!dir.exists()) {
                dir.mkdir();
            }

            this.STORAGE_FILE = new File(this.FILE_PATH);
            if (!STORAGE_FILE.exists()) {
                STORAGE_FILE.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS !!! It seems that a new file cannot be created.");
        }
    }

    public ArrayList<Task> readFromFile() throws DukeException {
        try {
            ArrayList<Task> listOfTasks = new ArrayList<>();
            Scanner scanner = new Scanner(this.STORAGE_FILE);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String type = task.substring(1, 2);
                String mark = task.substring(4, 5);
                String description = task.substring(7);

                if (type.equals("T")) {
                    Task todo = new Todo(description);
                    if (mark.equals("X")) {
                        todo.markDone();
                    }
                    listOfTasks.add(todo);
                } else if (type.equals("E")) {
                    String[] s = description.split(" \\(from: | to: |\\)");
                    Task event = new Event(s[0], s[1], s[2]);
                    if (mark.equals("X")) {
                        event.markDone();
                    }
                    listOfTasks.add(event);
                } else if (type.equals("D")) {
                    String[] s = description.split(" \\(by: |\\)");
                    Task deadline = new Deadline(s[0], s[1]);
                    if (mark.equals("X")) {
                        deadline.markDone();
                    }
                    listOfTasks.add(deadline);
                } else {
                    throw new DukeException("OOPS !!! Can't Load Task from File");
                }
            }
            return listOfTasks;
        } catch (IOException e) {
            throw new DukeException("OOPS !!! Can't Load Task from File");
        }
    }

    public void writeToFile(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(STORAGE_FILE);
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                fileWriter.write(currTask.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS !!! Can't write to file");
        }
    }
}

