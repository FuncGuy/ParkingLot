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
    public void should_park_the_car(){
        try {
            processor.validateAndProcess("park KA-35-U-3784 Black");
        } catch (Exception e) {
            fail("Car parking failed!!!" + e);
        }
    }


    @Test
    public void car_parking_should_fail_when_the_slots_are_reached_maximum_capacity(){
        try {
            for(int i = 0; i<7; i++)
            processor.validateAndProcess("park KA-35-U-3784 Black");
        } catch (Exception e) {
            fail("Car parking failed!!!" + e);
        }
    }

}
