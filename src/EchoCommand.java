public class EchoCommand implements Command {

    @Override
    public boolean eval(Shell shell, String args) {
        System.out.println(args);
        return true;
    }

}
