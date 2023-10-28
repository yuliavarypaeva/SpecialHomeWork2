package Toys;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();

        toyShop.addToy(new Toy(1, "Doll", 5));
        toyShop.addToy(new Toy(2, "Car", 8));
        toyShop.addToy(new Toy(3, "Teddy Bear", 6));
        toyShop.addToy(new Toy(4, "Lego Set", 10));
        toyShop.addToy(new Toy(5, "Puzzle", 4));
        toyShop.addToy(new Toy(6, "Action Figure", 7));
        toyShop.addToy(new Toy(7, "Board Game", 9));
        toyShop.addToy(new Toy(8, "Soccer Ball", 3));
        toyShop.addToy(new Toy(9, "Kitchen Set", 8));
        toyShop.addToy(new Toy(10, "Art Supplies", 5));

        toyShop.getAndWriteToFiles();

        toyShop.closeLogWriter();

        saveWinnersToFile(toyShop.getWinners());
    }

    private static void saveWinnersToFile(ArrayList<Toy> winners) {
        try (ObjectOutputStream winnerWriter = new ObjectOutputStream(new FileOutputStream("winners.txt"))) {
            winnerWriter.writeObject(winners);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
