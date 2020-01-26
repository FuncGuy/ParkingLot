import executor.InteractiveParkingLotProcessor;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class InteractiveProcessorTest {

    static InteractiveParkingLotProcessor processor = null;

    @BeforeClass
    public static void setUp() throws Exception {
        processor = new InteractiveParkingLotProcessor();
        processor.validateAndProcess("create_parking_lot 6");
    }

    @Test
    public void should_create_six_parking_slots() {
        try {
            processor.validateAndProcess("create_parking_lot 6");
        } catch (Exception e) {
            fail("Creation of parking slot failed");
        }
    }

    @Test
    public void should_display_error_message_when_command_is_invalid() {
        try {
            processor.validateAndProcess("createe_parking_lot 6");
        } catch (Exception e) {
            fail("Creation of parking slot failed");
        }
    }

    @Test
    public void should_park_the_car() {
        try {
            processor.validateAndProcess("park KA-35-U-3784 Black");
        } catch (Exception e) {
            fail("Car parking failed!!!" + e);
        }
    }


    @Test
    public void car_parking_should_fail_when_the_slots_are_reached_maximum_capacity() {
        try {
            for (int i = 0; i < 7; i++)
                processor.validateAndProcess("park KA-35-U-3784 Black");
        } catch (Exception e) {
            fail("Car parking failed!!!" + e);
        }
    }

    @Test
    public void should_leave_the_second_slot() {
        try {
            for (int i = 0; i < 6; i++)
                processor.validateAndProcess("park KA-35-U-3784 Black");
                processor.validateAndProcess("leave 2");
        } catch (Exception e) {
            fail("Slot leave failed!!!" + e);
        }

    }

    @Test
    public void should_get_the_status_of_parking_lot(){
        try {
            for (int i = 0; i < 6; i++)
                processor.validateAndProcess("park KA-35-U-3784 Black");
            processor.validateAndProcess("status");
        } catch (Exception e) {
            fail("Car parking failed!!!" + e);
        }
    }

    @Test
    public void should_get_slot_number_for_given_color(){
        try {
                processor.validateAndProcess("park KA-35-U-3784 Black");
            processor.validateAndProcess("park KA-01-U-1234 White");
                processor.validateAndProcess("slot_numbers_for_cars_with_colour White");
        } catch (Exception e) {
            fail("Car parking failed!!!" + e);
        }
    }

    @Test
    public void should_get_slot_number_for_given_register_number(){
        try {
            processor.validateAndProcess("park KA-01-U-1234 White");
            processor.validateAndProcess("park KA-01-U-12345 Black");
            processor.validateAndProcess("slot_number_for_registration_number KA-01-U-12345");
        } catch (Exception e) {
            fail("Car parking failed!!!" + e);
        }
    }

}
