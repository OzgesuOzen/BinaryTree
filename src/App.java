import java.util.Scanner;

public class App {

    private String command;
    private BTree_Functions b;
    private Node root;
    private boolean flag;

    public App() {
        b = new BTree_FunctionsImpl();
        setFlag(true);
        generate();
    }

    private void generate(){
        Scanner input = new Scanner(System.in);
        System.out.println("BINARY TREE\nFORMAT : command, integer\na : add\nr : remove\nbfs : Breadth First Search\ndfs : Depth First Search\nq : Quit\n-----------------");
        setCommand(input.nextLine());
        while(!getCommand().toLowerCase().equals("q")){
            func();
            setCommand(input.nextLine());
        }

    }
    private String[] parse() {

        if(command.contains("/")||command.contains("\\")){
            System.out.println("Nice try! FORMAT for a(add) and r(remove): command, integer");
            return null;
        }
        command = command.replaceAll("\\s+", "");
        command = command.toLowerCase();

        if(!command.equals("dfs") && !command.equals("bfs")){
            String[] arrOfStr = command.split(",");
            if (arrOfStr.length != 2) {
                System.out.println("FORMAT for a(add) and r(remove): command, integer");
                return null;
            }
            return arrOfStr;
        }else{
            return new String[] {command};
        }
    }

    private void func(){
        String[] arrOfStr = parse();
        if(arrOfStr==null){
            return;
        }

        int key = 0;
        if(arrOfStr.length>1) {
            key = Integer.parseInt(arrOfStr[1]);
        }
        switch (arrOfStr[0]){
            case ("a"):
                if(isFlag()) {
                    setRoot(new Node(key));
                    System.out.println(key + "(root) is added.");
                    setFlag(false);
                }else {
                    b.add(getRoot(), key);
                }
                break;
            case ("r"):
                if(isFlag()) {
                    System.out.println("Binary tree is empty.");
                }else{
                    root = b.remove(getRoot(),key);
                    setRoot(root);
                }
                if(getRoot() == null){
                    setFlag(true);
                }
                break;
            case ("bfs"):
                if(isFlag()) {
                    System.out.println("Binary tree is empty.");
                }else b.pBFS(getRoot());
                break;
            case ("dfs"):
                if(isFlag()) {
                    System.out.println("Binary tree is empty.");
                }else b.pDFS(getRoot());
                break;
        }
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
