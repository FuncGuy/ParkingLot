package model;

public class Car extends Vehicle {

	public Car(String regNo, String color) {
       super.regNo = regNo;
       super.color = color;
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
