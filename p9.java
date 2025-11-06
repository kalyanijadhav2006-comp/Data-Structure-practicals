import java.util.Scanner; 
 
// Node class 
class Node { 
    int data; 
    Node left, right; 
 
    Node(int value) { 
        data = value; 
        left = right = null; 
    } 
} 
 
// BST class 
public class p9 { 
    Node root; 
 
    // Insert a node 
    Node insert(Node root, int value) { 
        if (root == null) 
            return new Node(value); 
 
        if (value < root.data) 
            root.left = insert(root.left, value); 
        else if (value > root.data) 
            root.right = insert(root.right, value); 
 
        return root; 
    } 
 
    // Search a node 
    boolean search(Node root, int value) { 
        if (root == null) 
            return false; 
        if (root.data == value) 
            return true; 
        if (value < root.data) 
            return search(root.left, value); 
        else 
            return search(root.right, value); 
    } 
 
    // Delete a node 
    Node delete(Node root, int value) { 
        if (root == null) 
            return root; 
 
        if (value < root.data) 
            root.left = delete(root.left, value); 
        else if (value > root.data) 
            root.right = delete(root.right, value); 
        else { 
            // Node with one or no child 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
 
            // Node with two children — find inorder successor 
            root.data = minValue(root.right); 
            root.right = delete(root.right, root.data); 
        } 
        return root; 
    } 
 
    // Find minimum value in right subtree 
    int minValue(Node root) { 
        int minv = root.data; 
        while (root.left != null) { 
            minv = root.left.data; 
            root = root.left; 
        } 
        return minv; 
    } 
 
    // Display BST in Inorder (sorted order) 
    void inorder(Node root) { 
        if (root != null) { 
            inorder(root.left); 
            System.out.print(root.data + " "); 
            inorder(root.right); 
        } 
    } 
 
    // MAIN METHOD 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        p9 tree = new p9(); 
 
        while (true) { 
            System.out.println("\n\n===== Binary Search Tree Menu ====="); 
            System.out.println("1. Insert Node"); 
            System.out.println("2. Delete Node"); 
            System.out.println("3. Search Node"); 
            System.out.println("4. Display (Inorder)"); 
            System.out.println("5. Exit"); 
            System.out.print("Enter your choice: "); 
            int choice = sc.nextInt(); 
 
            switch (choice) { 
                case 1: 
                    System.out.print("Enter value to insert: "); 
                    int insertVal = sc.nextInt(); 
                    tree.root = tree.insert(tree.root, insertVal); 
                    System.out.println("Node inserted successfully!"); 
                    break; 
 
                case 2: 
                    System.out.print("Enter value to delete: "); 
                    int deleteVal = sc.nextInt(); 
                    tree.root = tree.delete(tree.root, deleteVal); 
                    System.out.println("Node deleted (if present)."); 
                    break; 
 
                case 3: 
                    System.out.print("Enter value to search: "); 
                    int searchVal = sc.nextInt(); 
                    if (tree.search(tree.root, searchVal)) 
                        System.out.println("Node found in the tree!"); 
                    else 
                        System.out.println("Node not found!"); 
                    break; 
 
                case 4: 
                    System.out.println("Inorder Traversal (Sorted Order):"); 
                    tree.inorder(tree.root); 
                    System.out.println(); 
                    break; 
 
                case 5: 
                    System.out.println("Exiting program. Goodbye!"); 
                    sc.close(); 
                    System.exit(0); 
                    break; 
 
                default: 
                    System.out.println("Invalid choice! Please try again."); 
            } 
        } 
    } 
}