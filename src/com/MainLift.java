package com;
import com.services.LiftService;

public class MainLift {

    public static void main(String[] args) {
        LiftService liftService = new LiftService();
        liftService.printMovements();
    }
}
