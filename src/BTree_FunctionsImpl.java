import java.util.LinkedList;
import java.util.Queue;

public class BTree_FunctionsImpl implements BTree_Functions{

    @Override
    public void add(Node temp, int key)
    {
        if(!exist(temp,key)){
            Queue<Node> q = new LinkedList<Node>();
            q.add(temp);
            // Find an empty place.
            while (!q.isEmpty()) {
                temp = q.peek();
                q.remove();

                if (temp.left == null) {
                    temp.left = new Node(key);
                    System.out.println(key + " is added.");
                    break;
                } else
                    q.add(temp.left);

                if (temp.right == null) {
                    temp.right = new Node(key);
                    System.out.println(key + " is added.");
                    break;
                } else
                    q.add(temp.right);
            }
        }else
            System.out.println(key + " already exists.");
    }

    @Override
    public Node remove(Node root, int key) {
        if(root==null){
            return null;
        }
        if (root.left == null && root.right == null) {
            if (root.key == key){
                root = null;
                System.out.println(key + "(root) is removed.");
                return root;
            }else
                return root;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        Node temp = root;
        Node key_node = null;
        // Find deepest node(temp) and node to be deleted (key_node)
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();
            if (temp.key == key)
                key_node = temp;

            if (temp.left != null)
                q.add(temp.left);

            if (temp.right != null)
                q.add(temp.right);
        }
        if (key_node != null) {
            int x = temp.key;
            deleteDeepest(root, temp);
            key_node.key = x;
            System.out.println(key + " is removed.");
        } else
            System.out.println(key + " does not exist to be removed.");
        return root;
    }

    private void deleteDeepest(Node root, Node dnode){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        Node temp = root;
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();
            if (temp == dnode) {
                temp = null;
                break;
            }
            if (temp.right != null) {
                if(temp.right == dnode){
                    temp.right = null;
                    break;
                } else
                    q.add(temp.right);
            }
            if (temp.left != null) {
                if(temp.left == dnode){
                    temp.left = null;
                    break;
                } else
                    q.add(temp.left);
            }
        }
    }

    private boolean exist(Node temp, int key){
        if(temp.key==key){
            return true;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(temp);
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.left != null) {
                if (temp.left.key == key) {
                    return true;
                } else
                    q.add(temp.left);
            }
            if (temp.right != null) {
                if (temp.right.key == key) {
                    return true;
                } else
                    q.add(temp.right);
            }
        }
        return false;
    }

    @Override
    public Node pBFS(Node temp) {
        System.out.println(temp.key);
        if(temp.left != null){
            pBFS(temp.left);
        }
        if(temp.right != null){
            pBFS(temp.right);
        }
        return temp;
    }

    @Override
    public Node pDFS(Node temp) {
        if(temp.left != null){
            pDFS(temp.left);
        }
        if(temp.right != null){
            pDFS(temp.right);
        }
        System.out.println(temp.key);
        return temp;
    }

}
