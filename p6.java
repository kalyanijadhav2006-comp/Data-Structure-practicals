import java.util.Scanner;

// Node to store integer value
class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class HashTable {
    private int size;       // Hash table size (provided by user)
    private Node[] table;   // Array of chains

    // Constructor
    public HashTable(int size) {
        this.size = size;
        table = new Node[size];
    }

    // Hash function: value % size
    private int hashFunction(int value) {
        return value % size;
    }

    // Insert value
    public void insert(int value) {
        int index = hashFunction(value);
        Node newNode = new Node(value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            // Collision → insert at end of chain
            Node temp = table[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        System.out.println("Inserted value " + value + " at index " + index);
    }

    // Search value
    public void search(int value) {
        int index = hashFunction(value);
        Node temp = table[index];

        while (temp != null) {
            if (temp.value == value) {
                System.out.println("Value " + value + " found at index " + index);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Value " + value + " not found.");
    }

    // Delete value
    public void delete(int value) {
        int index = hashFunction(value);
        Node temp = table[index];
        Node prev = null;

        while (temp != null) {
            if (temp.value == value) {
                if (prev == null) {
                    // Remove head of the chain
                    table[index] = temp.next;
                } else {
                    prev.next = temp.next;
                }
                System.out.println("Value " + value + " deleted from index " + index);
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        System.out.println("Value " + value + " not found for deletion.");
    }

    // Display hash table
    public void display() {
        System.out.println("\nHash Table:");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            Node temp = table[i];
            if (temp == null) {
                System.out.println("Empty");
            } else {
                while (temp != null) {
                    System.out.print(temp.value + " -> ");
                    temp = temp.next;
                }
                System.out.println("null");
            }
        }
    }
}

public class p6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Accept size of hash table
        System.out.print("Enter size of hash table: ");
        int tableSize = sc.nextInt();

        HashTable ht = new HashTable(tableSize);

        while (true) {
            System.out.println("\n1. Insert");
            System.out.println("2. Display");
            System.out.println("3. Search");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int value = sc.nextInt();
                    ht.insert(value);
                    break;

                case 2:
                    ht.display();
                    break;

                case 3:
                    System.out.print("Enter value to search: ");
                    value = sc.nextInt();
                    ht.search(value);
                    break;

                case 4:
                    System.out.print("Enter value to delete: ");
                    value = sc.nextInt();
                    ht.delete(value);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}