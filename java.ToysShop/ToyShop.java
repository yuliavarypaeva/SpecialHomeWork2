package Toys;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ToyShop {
    private final PriorityQueue<Toy> toyQueue;
    private final BufferedWriter logWriter;
    private final ArrayList<Toy> winners;

    public ToyShop() {
        toyQueue = new PriorityQueue<>(10, (t1, t2) -> t1.getWeight() - t2.getWeight());
        winners = new ArrayList<>();

        try {
            logWriter = new BufferedWriter(new FileWriter("toy_shop_log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ToyShop.");
        }
    }

    public void addToy(Toy toy) {
        toyQueue.offer(toy);
    }

    public Toy getToy() {
        return toyQueue.poll();
    }

    public void getAndWriteToFiles() {
        for (int i = 0; i < 10; i++) {
            Toy toy = getToy();
            if (toy != null) {
                System.out.println("Received Toy: " + toy);
                writeToFile(toy);
                logTransaction(toy);
                winners.add(toy);
            } else {
                System.out.println("No more toys available.");
            }
        }
    }

    private void writeToFile(Toy toy) {
        try (FileOutputStream fos = new FileOutputStream("toys.txt", true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(toy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logTransaction(Toy toy) {
        try (BufferedWriter logEntry = new BufferedWriter(new FileWriter("toy_shop_log.txt", true))) {
            logEntry.write("Received Toy: " + toy + " at " + System.currentTimeMillis() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeLogWriter() {
        try {
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Toy> getWinners() {
        return winners;
    }
}
