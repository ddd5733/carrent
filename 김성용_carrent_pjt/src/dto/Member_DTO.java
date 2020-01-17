package dto;
public class Member_DTO{
	String id,name,dept,rank,address,reg_date;
	int age;
	
	public Member_DTO(){}
	
	public Member_DTO(String id, String name, int age, String dept_no, String rank_no, String address,String reg_date){
		this.id = id;
		this.name = name;
		this.age = age;
		this.dept = dept_no;
		this.rank = rank_no;
		this.address = address;
		this.reg_date = reg_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}