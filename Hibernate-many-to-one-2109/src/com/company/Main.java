package com.company;

public class Main {

    public static void main(String[] args) {
        var repo = new CarDataRepository();

//        var car1 = new Car(0,"BBB","BBB123", null);
//
//        repo.add(car1);

        var car1 = repo.get(3);

        var owner = new Owner(0, "Pēteris", "Bērzs");

        car1.setOwner(owner);

        repo.update(car1);

        for (var car : repo.getList()) {
            System.out.println(car.getNumber());

            if(car.getOwner() != null) {
                System.out.println("Owner: " + car.getOwner().getName());
            }
        }
    }
}
