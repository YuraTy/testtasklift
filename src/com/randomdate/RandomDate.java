package com.randomdate;

import com.model.Passenger;

import java.util.*;

public class RandomDate {

    public final int numberFloors = getRandomNumber(5, 20);



    public int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public Map<Integer, List<Passenger>> getRandomPassengers() {
        Map<Integer, List<Passenger>> mapPassenger = new HashMap<>();
        for (int j = 0; j <= numberFloors; j++) {
            List<Passenger> passengers = new ArrayList<>();
            passengers.clear();
            int sizePassengerOnFloor = getRandomNumber(0,10);
            for (int i = 0; i <= sizePassengerOnFloor; i++) {
                int desiredFloor = getRandomNumber(0, numberFloors);
                while (j == desiredFloor) {
                    desiredFloor = getRandomNumber(0, numberFloors);
                }
                passengers.add(new Passenger(desiredFloor));
            }
            mapPassenger.put(j,passengers);

        }
        return mapPassenger;
    }
}
