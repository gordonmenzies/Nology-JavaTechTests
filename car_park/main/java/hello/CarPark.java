package hello;

public class CarPark {
    int[] cycleSpaces;
    int[] carSpaces;
    int[] largeSpaces;

    CarPark () {
        this.cycleSpaces = new int [25];
        this.carSpaces = new int [25];
        this.largeSpaces = new int [100];
    }

    public String addCar () {
        for (int i = 0; i < this.carSpaces.length; i++) {
            if (this.carSpaces[i] == 0) {
                this.carSpaces[i] = 2;
                return "car parked successfully";
            }
        }
        for (int i = 0; i < this.cycleSpaces.length; i++) {
            if (this.carSpaces[i] == 0) {
                this.carSpaces[i] = 2;
                return "car parked successfully";
            }
        }
        return "car was unable to park small and regular spaces full";
    }

    public String removeCar() {
        for (int i = 0; i < this.carSpaces.length; i++) {
            if (this.carSpaces[i] == 2) {
                this.carSpaces[i] = 0;
                return "car removed";
            }
        }
        for (int i = 0; i < this.cycleSpaces.length; i++) {
            if (this.carSpaces[i] == 2) {
                this.carSpaces[i] = 0;
                return "car removed";
            }
        }
        return "there are no cars to remove";
    }

    public String addVan () {
        for (int i = 0 ; i < this.largeSpaces.length; i++) {
            if (this.largeSpaces[i] == 0) {
                if (this.largeSpaces[i + 1] == 0 && this.largeSpaces[i + 2] == 0) {
                    this.largeSpaces[i] = 3;
                    this.largeSpaces[i + 1] = 3;
                    this.largeSpaces[i + 2] = 3;
                }
                return "van parked successfully";
            }
        }
        for (int i = 0 ; i < this.carSpaces.length; i++) {
            if (this.carSpaces[i] == 0) {
                this.largeSpaces[i] = 3;
                return "van parked successfully";
            }
        }
        return "there are no van parking spots";
    }

    // public void removeVan() {
    //     for (int i = 0; i < this.largeSpaces.length; i++) {
    //         if (this)
    //     }
    // }

    public String addCycle () {
        for (int i = 0; i < this.cycleSpaces.length; i++) {
            if (this.cycleSpaces[i] == 0) {
                this.cycleSpaces[i] = 1;
                return "bike parked successfully";
            }
        }
        return "bike was unable to park spaces currently full";
    }

    public String removeCycle() {
        for (int i = 0; i < this.cycleSpaces.length; i++) {
            if (this.carSpaces[i] == 0) {
                this.carSpaces[i] = 1;
                return "bike removed";
            }
        }
        return "there are no bikes to remove";
    }

    public void printSpots () {
        int carSpots = 0;
        int vanSpots = 0; 
        int cycleSpots = 0;
        for (int i = 0; i < this.carSpaces.length; i++) {
            if (carSpaces[i] == 0) {
                carSpots = carSpots + 1;
            }
        }
        for (int i = 0; i < this.cycleSpaces.length; i++) {
            if (cycleSpaces[i] == 0) {
                cycleSpots = cycleSpots + 1;
            }
        }
        for (int i = 0; i < this.largeSpaces.length; i++) {
            if (largeSpaces[i] == 0) {
                vanSpots = vanSpots + 1;
            }
        }
        System.out.println("there are "  + carSpots + " car spaces");
        System.out.println("there are "  + cycleSpots + " cycle spaces");
        System.out.println("there are "  + vanSpots + " van spaces");
    }

    public boolean isFull () {
       for (int i = 0; i < cycleSpaces.length; i++) {
        if (cycleSpaces[i] == 0) {
            return false;
        }
       }
       for (int i = 0; i < carSpaces.length; i++) {
        if (carSpaces[i] == 0) {
            return false;
        }
       }
       for (int i = 0; i < largeSpaces.length; i++) {
        if (largeSpaces[i] == 0) {
            return false;
        }
       }
       return true;
    }

    public void describeCarPark () {
        System.out.println("The car park holds");
        System.out.println(this.cycleSpaces.length + " cycle spaces");
        System.out.println(this.carSpaces.length + " car spaces");
        System.out.println(this.largeSpaces.length + " large spaces");
    }
}
