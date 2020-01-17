package dto;

public class MemberHistory_DTO {
	 String rent_id, name, car_name, rent_start_date ,rent_return_date,driving_km;

	public String getRent_id() {
		return rent_id;
	}

	public String getRent_return_date() {
		return rent_return_date;
	}

	public void setRent_return_date(String rent_return_date) {
		this.rent_return_date = rent_return_date;
	}

	public void setRent_id(String rent_id) {
		this.rent_id = rent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getRent_start_date() {
		return rent_start_date;
	}

	public void setRent_start_date(String rent_start_date) {
		this.rent_start_date = rent_start_date;
	}

	public String getDriving_km() {
		return driving_km;
	}

	public void setDriving_km(String driving_km) {
		this.driving_km = driving_km;
	}
}
