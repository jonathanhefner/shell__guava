public class PwdCommand implements Command {

    @Override
    public boolean eval(Shell shell, String args) {
        shell.println(shell.getWorkingDir().getAbsolutePath());
        return true;
    }

}
