import java.util.Arrays; //Allows the use of Arrays.fill() to initialize array elements easily.
import java.util.Scanner;

class HashTableLinearProbing {
    private int[] table;   // table: integer array representing the hash table.
    private int size;   // size: total number of slots (or buckets) in the hash table.

    public HashTableLinearProbing(int size) {
        this.size = size;  //Assigns the user-defined size to the object’s size variable.
        table = new int[size];  // Creates an integer array of that size.
        Arrays.fill(table, -1); // Fills all array slots with -1, meaning they are empty. 
        
    }

    
    private int hashKey(int key) {
        return key % size;
    }

    public void insert(int key) {
        int index = hashKey(key);

        if (table[index] == -1) { // If the calculated index is empty (-1), simply insert the key there.
            table[index] = key;
          
           
        } else {
            int start = index;
            do {
                index = (index + 1) % size;
                
            } while (table[index] != -1 && index != start);

            if (table[index] == -1) {
                table[index] = key;
               
               
            } else {
                System.out.println("Hash table is full. Cannot insert key " + key);
            }
        }
    }
    public boolean search(int key) {
        int index = hashKey(key);
        int start = index;

        while (table[index] != -1) {
            if (table[index] == key) {
                System.out.println("Key " + key + " found at index " + index);
                return true;
            }
            index = (index + 1) % size;
            if (index == start) break;
        }

        System.out.println("Key " + key + " not found.");
        return false;
    }

    public boolean delete(int key) {
        int index = hashKey(key);
        int start = index;

        while (table[index] != -1) {
            if (table[index] == key) {
                table[index] = -1;
                System.out.println("Key " + key + " deleted from index " + index);
                return true;
            }
            index = (index + 1) % size;
            if (index == start) break;
        }

        System.out.println("Key " + key + " not found for deletion.");
        return false;
    }

    public void display() {
        System.out.println("Index\tKey\tComparisons");
        for (int i = 0; i < size; i++) {
            if (table[i] != -1)
                System.out.println(i + "\t" + table[i] );
            else
                System.out.println(i + "\tEmpty");
        }
    }
}


public class p7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of hash table: ");
        int size = sc.nextInt();

        HashTableLinearProbing ht = new HashTableLinearProbing(size);

        boolean running = true;
        while (running) {
            System.out.println("\n1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                   {
                        System.out.print("Enter key: ");
                        int key = sc.nextInt();
                        ht.insert(key);
                    }
                    break;

                case 2:
                    System.out.print("Enter key to search: ");
                    int s = sc.nextInt();
                    ht.search(s);
                    break;

                case 3:
                    System.out.print("Enter key to delete: ");
                    int d = sc.nextInt();
                    ht.delete(d);
                    break;

                case 4:
                    ht.display();
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
