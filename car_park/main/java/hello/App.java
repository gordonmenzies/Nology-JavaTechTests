package hello;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CarPark carPark = new CarPark();

        carPark.printSpots();
        System.out.println();
        carPark.addCar();
        carPark.printSpots();
        // carPark.removeCar();
        System.out.println();

        carPark.addCycle();
        carPark.printSpots();
        System.out.println();

        carPark.addVan();
        carPark.printSpots();
        System.out.println();

        // carPark.addCycle();
        // carPark.removeCycle();
        // carPark.addVan();
        // //carPark.removeVan();
        // carPark.addCycle();
        // carPark.removeCycle();
        // carPark.printSpots();
        // System.out.println(carPark.isFull());
        // carPark.describeCarPark();
    }
}
