package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feeding extends BaseEntity{

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name = "start_date")        
    LocalDate startDate;
	
	@NotNull
	@Min(1)
	@Column(name = "weeks_duration")
    double weeksDuration;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pet_id", unique = true)
    Pet pet;   
	
    @ManyToOne
    @NotNull
    @JoinColumn(name = "feeding_type_id")
	FeedingType feedingType;
}
