package process;

import model.Car;

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

    public void leaveSlot(Integer slotNo) {
        if (noOfParkingSlots == 0) {
            System.out.println("parking lot is not created\\n");
        } else if (slotCarMap.size() > 0) {
            Car carToLeave = slotCarMap.get(slotNo);
            if (carToLeave != null) {
                slotCarMap.remove(slotNo);
                regNoCarSlotMap.remove(carToLeave.getRegNo());
                List<String> regNoList = colorCarMap.get(carToLeave.getColor());
                if (regNoList.contains(carToLeave.getRegNo())) {
                    regNoList.remove(carToLeave.getRegNo());
                }
                // Add the Lot No. back to available slot list.
                availableSlotList.add(slotNo);
                System.out.println("Slot number " + slotNo + " is free\n");
            } else {
                System.out.println("Slot number " + slotNo + " is already empty\n");
            }
        } else {
            System.out.println("Parking lot is empty\n");
        }
    }

    public void getStatus() {
        if (noOfParkingSlots == 0) {
            System.out.println("Sorry, parking lot is not created\n");
        } else if (slotCarMap.size() > 0) {
            System.out.println("Slot No.\tRegistration No.\tColor\n");
            Car car;
            for (int i = 1; i <= noOfParkingSlots; i++) {
                if (slotCarMap.containsKey(i)) {
                    car = slotCarMap.get(i);
                    System.out.println(i + "\t" + car.getRegNo() + "\t" + car.getColor());
                }
            }
            System.out.println();
        } else {
            System.out.println("Parking lot is empty\n");
        }
    }

    public void getSlotNumbersFromColor(String color) {
        if (noOfParkingSlots == 0) {
            System.out.println("parking lot is not created\n");
        } else if (colorCarMap.containsKey(color)) {
            List<String> regNoList = colorCarMap.get(color);
            List<Integer> slotList = new ArrayList<Integer>();
            System.out.println();
            for (int i = 0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(regNoCarSlotMap.get(regNoList.get(i))));
            }
            sort(slotList);
            for (int j = 0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ", ");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found\n");
        }
    }

    public void getSlotNumberFromRegNo(String regNo) {
        if (noOfParkingSlots == 0) {
            System.out.println("Sorry, parking lot is not created\n");
        } else if (regNoCarSlotMap.containsKey(regNo)) {
            System.out.println(regNoCarSlotMap.get(regNo));
        } else {
            System.out.println("Not found\n");
        }
    }

    public void getRegistrationNumbersFromColor(String color) {
        if (noOfParkingSlots == 0) {
            System.out.println("parking lot is not created\n");
        } else if (colorCarMap.containsKey(color)) {
            List<String> regNoList = colorCarMap.get(color);
            System.out.println();
            for (int i = 0; i < regNoList.size(); i++) {
                //until all the registration number exhausts loop
                if (!(i == regNoList.size() - 1)) {
                    System.out.print(regNoList.get(i) + ",");
                } else {
                    System.out.print(regNoList.get(i));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found\n");
        }
    }
}
