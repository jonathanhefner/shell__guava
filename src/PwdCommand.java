public class PwdCommand implements Command {

    @Override
    public boolean eval(Shell shell, String args) {
        System.out.println(shell.getCurrentWorkingDir().getAbsolutePath());
        return true;
    }

}
