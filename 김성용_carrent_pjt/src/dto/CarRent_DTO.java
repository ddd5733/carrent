package dto;

public class CarRent_DTO {
	String rent_id , car_id , member_id , driving_km, rent_start_date, rent_return ,car_name ,driving_total_km ;

	public String getRent_id() {
		return rent_id;
	}

	public void setRent_id(String rent_id) {
		this.rent_id = rent_id;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getDriving_km() {
		return driving_km;
	}

	public void setDriving_km(String driving_km) {
		this.driving_km = driving_km;
	}

	public String getRent_start_date() {
		return rent_start_date;
	}

	public void setRent_start_date(String rent_start_date) {
		this.rent_start_date = rent_start_date;
	}

	public String getRent_return() {
		return rent_return;
	}

	public void setRent_return(String rent_return) {
		this.rent_return = rent_return;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getDriving_total_km() {
		return driving_total_km;
	}

	public void setDriving_total_km(String driving_total_km) {
		this.driving_total_km = driving_total_km;
	} 
}
