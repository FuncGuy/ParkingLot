package executor;

import java.util.*;


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

}
