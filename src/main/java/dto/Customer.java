package dto;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;  // download lombok Download 1.18.28 -- open -- make sure that your eclipse and lombok location is same
                         // if not select that folder where eclipse is strored, otherwise getters and setters will not come

@Entity
@Data     // we dont need to generate getters and setters, Data only create them internally
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fullname;
	private String pswd;
	
	@Column(unique=true)
	private String email;
	private long num;
	private LocalDate dob;
	private String gender;
	private String city;
	private String agreebox;
	private int age;
	
	@Lob
	private byte[] image;

}
