package day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class HouseNumbers {

    private Map<String, List<Integer>> streets = new LinkedHashMap<>();

    public void readFile(Path path) {

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(" ");
                if(!setUpStreet(temp[0], Integer.parseInt(temp[1]))){
                    addNewNumber(temp[0],Integer.parseInt(temp[1]));
                }
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    private void addNewNumber(String streetName, int number){
           int maxNumber  = streets.get(streetName)
                   .stream().mapToInt(i -> i)
                   .filter(i -> i % 2 == number)
                   .max()
                   .orElse(-number);
            streets.get(streetName).add(maxNumber + 2);
    }

    private boolean setUpStreet(String street, int number) {
        if (!streets.containsKey(street)) {
            streets.put(street, new ArrayList<>());
            if (number == 0) {
                streets.get(street).add(2);
            } else {
                streets.get(street).add(1);
            }
            return true;
        }
        return false;
    }

    public Map<String, List<Integer>> getStreets() {
        return streets;
    }

    //utcanev alapjan kerdezzuk le a paros hazszamok db-szamat.
    public long countEvenNumbers(String streetname){
       return streets.get(streetname).stream().filter(i -> i % 2 == 0).count();
    }

    public static void main(String[] args) {
        HouseNumbers houseNumbers = new HouseNumbers();
        houseNumbers.readFile(Paths.get("src/main/resources/Streets.txt"));

        houseNumbers.streets.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
        System.out.println(houseNumbers.countEvenNumbers("Petofi"));
        System.out.println(houseNumbers.countEvenNumbers("Kossuth"));

    }




}
