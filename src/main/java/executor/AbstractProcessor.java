package executor;

import constants.Command;
import model.Car;
import process.ParkingLot;

import static constants.Command.findByName;

public abstract class AbstractProcessor {

    ParkingLot parkingLot;

    public void validateAndProcess(String inputString) throws Exception {
        //split the sentence by spaces
        String[] inputStrArr = inputString.split(" ");
        //if input string is empty terminate
        if (inputStrArr[0].equals("")) {
            System.out.println("No input found!!!");
            return;
        }

        Command command = findByName(inputStrArr[0]);
        //if input string is invalid terminate
        if (command == null) {
            System.out.println("Invalid command");
            return;
        }

        switch (command) {

            case CREATE:
                if (inputStrArr.length != 2) {
                    throw new Exception("Invalid no of arguments for command : " + command);
                }
                int noOfPrakingSlots = Integer.parseInt(inputStrArr[1]);
                parkingLot = ParkingLot.createParkingLot(noOfPrakingSlots);
                break;

            case PARK:
                if (inputStrArr.length != 3) {
                    throw new Exception("Invalid no of arguments for command : " + command);
                }
                String regNo = inputStrArr[1]; // regNo
                String color = inputStrArr[2]; // color
                parkingLot.parkCar(new Car(regNo, color));
                break;

            case LEAVE:
                if(inputStrArr.length != 2) {
                    throw new Exception("Invalid no of arguments for command : " + command);
                }
                int slotNo = Integer.parseInt(inputStrArr[1]);
                parkingLot.leaveSlot(slotNo);
                break;

            case STATUS:
                if(inputStrArr.length != 1) {
                    throw new Exception("Invalid no of arguments for command : " + command);
                }
                parkingLot.getStatus();
                break;

            case FETCH_SLOT_FROM_COLOR:
                if(inputStrArr.length != 2) {
                    throw new Exception("Invalid no of arguments for command : " + command);
                }
                parkingLot.getSlotNumbersFromColor(inputStrArr[1]);  //color
                break;
            case FETCH_SLOT_FROM_REG_NO:
                if(inputStrArr.length != 2) {
                    throw new Exception("Invalid no of arguments for command : " + command);
                }
                parkingLot.getSlotNumberFromRegNo(inputStrArr[1]);  //regNo
                break;

            case FETCH_CAR_FROM_COLOR:
                if(inputStrArr.length != 2) {
                    throw new Exception("Invalid no of arguments for command : " + command);
                }
                parkingLot.getRegistrationNumbersFromColor(inputStrArr[1]);  //color
                break;
        }
        }


    public abstract void process() throws Exception;
}
