package executor;

public class Main {

	public static void main(String[] args) throws Exception {

        welcomeMessage();
		AbstractProcessor processor;
		if(args.length >= 1) {
			processor = new FileProcessor(args[0]);
		} else {
			processor = new InteractiveParkingLotProcessor();
		}
		processor.process();


	}

    private static void welcomeMessage()
    {
        System.out.println("Create parking lot with the command "
        +"**create_parking_lot ** followed by the size of the parking lot\n" +
                "To park a car use the command **park  ** followed by the car registration number and color of the car\n" +
                "To know the status of parking lot use **status** command\n" +
                "To leave the slot use **leave** command\n" +
                "To get the slot numbers of specified car color use **slot_numbers_for_cars_with_colour ** followed by car color\n" +
                "To get car registration number based on car color use " +
                "**registration_numbers_for_cars_with_colour ** followed by color of car\n" +
                "To get the slot number for the given registered number use **slot_number_for_registration_number ** followed by the car registration number\n");
    }

}
