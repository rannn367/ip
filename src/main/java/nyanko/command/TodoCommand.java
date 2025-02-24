package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.task.ToDo;
import nyanko.ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String argument) {
        this.description = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ToDo todo = new ToDo(description);
        tasks.addTask(todo);
        System.out.println("WOW you're so hardworking\nok fine... your task has been added!\nadded: " + todo.toString());
        System.out.println("Oh my! You have " + tasks.size() + " tasks!");
        storage.save(tasks.getTasks());
    }
}