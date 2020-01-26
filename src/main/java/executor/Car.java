package executor;

public class Car {
	String regNo;
	String color;
	
	public Car(String regNo, String color) {
        this.regNo = regNo;
        this.color = color;
    }

	public String getRegNo() {
		return regNo;
	}


	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Car [regNo=" + regNo + ", color=" + color + "]";
	}
	
}
