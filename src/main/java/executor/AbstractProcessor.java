package executor;

import static executor.Command.findByName;

public abstract class AbstractProcessor {

	ParkingLot parkingLot = null;
	
	public void validateAndProcess(String inputString) throws Exception {
	    //segregate the sentence by spaces
		String[] inputStrArr = inputString.split(" ");
		//if input string is empty terminate
		if(inputStrArr[0].equals("")) {
			System.out.println("No input found!!!");
			return;
		}

		Command command = findByName(inputStrArr[0]);
        //if input string is invalid terminate
		if(command == null) {
			System.out.println("Invalid command");
			return;
		}
		
		switch(command) {

		case CREATE:
			if(inputStrArr.length != 2) {
				throw new Exception("Invalid no of arguments for command : " + command);
			} 
			int noOfPrakingSlots = Integer.parseInt(inputStrArr[1]);
			parkingLot = ParkingLot.createParkingLot(noOfPrakingSlots);

		}
	}
	
	public abstract void process() throws Exception;
}
