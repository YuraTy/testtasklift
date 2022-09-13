package com.model;

import java.util.Objects;

public class Passenger {

    private int floor;

    public Passenger(){}

    public Passenger(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return floor == passenger.floor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor);
    }

    @Override
    public String toString() {
        return String.valueOf(floor);
    }
}
