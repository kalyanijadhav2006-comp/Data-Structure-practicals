import java.util.Scanner;

class EventQueueSystem {
    private String[] queue;
    private int front;
    private int rear;
    private int size;

    // Constructor
    public EventQueueSystem(int capacity) {
        queue = new String[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    // Add an event to the queue
    public void addEvent(String event) {
        if (size == queue.length) {
            System.out.println("Queue is full. Cannot add event.");
            return;
        }
        queue[rear] = event;
        rear = (rear + 1) % queue.length;
        size++;
        System.out.println("Event \"" + event + "\" added to the queue.");
    }

    // Process the next event (FIFO)
    public void processNextEvent() {
        if (size == 0) {
            System.out.println("No events to process.");
            return;
        }
        String event = queue[front];
        front = (front + 1) % queue.length;
        size--;
        System.out.println("Processing event: " + event);
    }

    // Display all pending events
    public void displayPendingEvents() {
        if (size == 0) {
            System.out.println("No pending events.");
            return;
        }
        System.out.println("Pending events:");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % queue.length;
            System.out.println("- " + queue[index]);
        }
    }

    // Cancel a specific event
    public void cancelEvent(String event) {
        if (size == 0) {
            System.out.println("No events to cancel.");
            return;
        }

        boolean found = false;
        String[] newQueue = new String[queue.length];
        int newFront = 0;
        int newRear = 0;
        int newSize = 0;

        for (int i = 0; i < size; i++) {
            int index = (front + i) % queue.length;
            if (!queue[index].equals(event)) {
                newQueue[newRear] = queue[index];
                newRear = (newRear + 1) % queue.length;
                newSize++;
            } else {
                found = true;
            }
        }

        if (found) {
            queue = newQueue;
            front = newFront;
            rear = newRear;
            size = newSize;
            System.out.println("Event \"" + event + "\" has been canceled.");
        } else {
            System.out.println("Event \"" + event + "\" not found or already processed.");
        }
    }

    // Main method to test the system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventQueueSystem system = new EventQueueSystem(10); // Capacity = 10
        int choice;
        String event;

        do {
            System.out.println("\n--- Real-Time Event Processing System ---");
            System.out.println("1. Add Event");
            System.out.println("2. Process Next Event");
            System.out.println("3. Display Pending Events");
            System.out.println("4. Cancel Event");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    event = scanner.nextLine();
                    system.addEvent(event);
                    break;
                case 2:
                    system.processNextEvent();
                    break;
                case 3:
                    system.displayPendingEvents();
                    break;
                case 4:
                    System.out.print("Enter event to cancel: ");
                    event = scanner.nextLine();
                    system.cancelEvent(event);
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }
}
