package com.services;

import com.model.Passenger;
import com.randomdate.RandomDate;

import java.util.*;
import java.util.stream.Collectors;

public class LiftService {

    private RandomDate randomDate = new RandomDate();
    private final Map<Integer,List<Passenger>> passengers = randomDate.getRandomPassengers();
    public int deliveredPassengers[] = new int[passengers.size()];
    private List<Passenger> lift = new ArrayList<>();

    public void printMovements() {
        while (checkFloor()) {
            int maxFloor = passengers.size()-1;
            for (int i = 0; i <= maxFloor; i++) {
                lift = liftFilling(lift, passengers.get(i), i);
                maxFloor = minMaxFloor(lift).get("max");
                System.out.println("***Step***");
                format(i,lift);
            }
            for (int i = maxFloor; i >= 0; i--) {
                lift = liftFilling(lift, passengers.get(i), i);
                System.out.println("***Step***");
                format(i,lift);
            }
        }
    }

    private List<Passenger> liftFilling(List<Passenger> lift, List<Passenger> passengersFloor, int numberFloor) {
        int beforeLiftSize = lift.size();
        List<Passenger> filteredList = lift.stream()
                .filter(passenger -> passenger.getFloor() != numberFloor)
                .collect(Collectors.toList());
        deliveredPassengers[numberFloor] = deliveredPassengers[numberFloor] + (beforeLiftSize - filteredList.size());
        while (filteredList.size() != 5 && !passengersFloor.isEmpty()) {

            Passenger passenger = passengers.get(numberFloor).get(0);
            filteredList.add(passenger);
            passengers.get(numberFloor).remove(passenger);
        }
        return filteredList;
    }


    private Map<String,Integer> minMaxFloor(List<Passenger> lift) {
        IntSummaryStatistics statistics = lift.stream()
                .map(Passenger::getFloor)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        Map<String,Integer> result = new HashMap<>();
        if (statistics.getMin() > 0){
            result.put("min", 0);
        }else {
            result.put("min", statistics.getMin());
        }
        if (statistics.getMax() < passengers.size()){
            result.put("max", passengers.size()-1);
        }else {
            result.put("max", statistics.getMax());
        }
        return result;
    }

    private void format(int numberFloor, List<Passenger> lift) {
        for (int i = passengers.size()-1; i>=0 ; i--){
            if (i==numberFloor) {
                System.out.println(formatterDelivered(deliveredPassengers[numberFloor]) + "  |" + formatterFloor(lift) + "| " + formatterFloor(passengers.get(numberFloor)));
            }else {
                System.out.println(formatterDelivered(deliveredPassengers[i]) + "  |" + formatterFloor(new LinkedList<>())+ "| " + formatterFloor(passengers.get(i)));

            }
        }
    }

    private String formatterFloor(List<Passenger> lift) {
        StringJoiner join = new StringJoiner(",");
        lift.forEach(p -> join.add(p.toString()));
        int countSpaces = 14 - (join.toString().split("").length);
        StringJoiner joinerSpaces = new StringJoiner(" ");
        joinerSpaces.add(join.toString());
        for (int i =0; i<=countSpaces; i++) {
            joinerSpaces.add("");
        }
        return joinerSpaces.toString();
    }

    private String formatterDelivered(int delivered) {
        StringJoiner join = new StringJoiner("");
        join.add(String.valueOf(delivered));
        int countSpaces = 3 - (join.toString().split("").length);
        StringJoiner joinerSpaces = new StringJoiner(" ");
        joinerSpaces.add(join.toString());
        for (int i =0; i<=countSpaces; i++) {
            joinerSpaces.add("");
        }
        return joinerSpaces.toString();
    }

    private boolean checkFloor() {
        boolean answer = false;
        for (List<Passenger> passengerList: passengers.values()) {
            answer = !passengerList.isEmpty();
            
        }
        return answer;
    }



}
