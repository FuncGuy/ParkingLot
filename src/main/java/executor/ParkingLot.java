package executor;

import java.util.*;

import static java.util.Collections.sort;


public class ParkingLot {

	private int noOfParkingSlots = 0;

	private List<Integer> availableSlotList;

	private Map<Integer, Car> slotCarMap;  // slot no to car

	private Map<String, Integer> regNoCarSlotMap; // register no to car no

	private Map<String, List<String>> colorCarMap; // color to list of car
	
	private static ParkingLot parkingLotProcessor = null;
	
	//Singleton class
	private ParkingLot(int noOfParkingSlots) {
		this.noOfParkingSlots = noOfParkingSlots;
		availableSlotList = new ArrayList<Integer>();

		for (int i = 1; i <= noOfParkingSlots; i++) {
			availableSlotList.add(i);
		}

		slotCarMap = new HashMap<Integer, Car>();
		regNoCarSlotMap = new HashMap<String, Integer>();
		colorCarMap = new HashMap<String, List<String>>();
		System.out.println("Created parking lot with " + noOfParkingSlots + " slots");
		System.out.println();
	}

    public static ParkingLot createParkingLot(int noOfParkingSlots) {
        if(parkingLotProcessor != null) {
            return parkingLotProcessor;
        } else {
            parkingLotProcessor =
                    new ParkingLot(noOfParkingSlots);
            return parkingLotProcessor;
        }

    }

    public void parkCar(Car car) {
        if (noOfParkingSlots == 0) {
            System.out.println("parking lot is not created\n");
            return;
        } else if (slotCarMap.size() == noOfParkingSlots) {
            System.out.println("Sorry, parking lot is full\n");
            return;
        } else {
            sort(availableSlotList); // sorting is done to next available sort
            int slot = availableSlotList.get(0);
            slotCarMap.put(slot, car); // add car to next available slot
            regNoCarSlotMap.put(car.getRegNo(), slot); // register in which slot car is parked
            if (colorCarMap.containsKey(car.getColor())) {
                List<String> regNoList = colorCarMap.get(car.getColor());
                colorCarMap.remove(car.getColor());
                regNoList.add(car.getRegNo());
                colorCarMap.put(car.getColor(), regNoList);
            } else {
                // LinkedList because frequent updation is required
                LinkedList<String> regNoList =
                        new LinkedList<String>();
                regNoList.add(car.getRegNo());
                colorCarMap.put(car.getColor(), regNoList);
            }
            System.out.println("Allocated slot number: " + slot + "\n");
            availableSlotList.remove(0);
        }
    }
}
