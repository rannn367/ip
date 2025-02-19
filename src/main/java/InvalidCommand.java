public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Whot on earth are you saying!!!");
        ui.showLine();
    }
}