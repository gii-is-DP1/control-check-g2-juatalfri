package org.springframework.samples.petclinic.feeding;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
    
	@Autowired 
	FeedingService fs;
	
	@Autowired 
	PetService ps;
	
	@GetMapping(path = "/create")
	public String createFeeding(ModelMap modelmap) {
		
		modelmap.addAttribute("feeding", new Feeding());
		modelmap.addAttribute("pets", ps.getAllPets());
		modelmap.addAttribute("feedingTypes", fs.getAllFeedingTypes());
		
		return "feedings/createOrUpdateFeedingForm";
	}

	@PostMapping(path = "/create")
	public String saveFeeding(@Valid Feeding feeding, BindingResult result, ModelMap modelmap) throws UnfeasibleFeedingException {
		
		modelmap.addAttribute("pets", ps.getAllPets());
		modelmap.addAttribute("feedingTypes", fs.getAllFeedingTypes());
		if (!feeding.getFeedingType().getPetType().equals(feeding.getPet().getType())) {
			modelmap.addAttribute("message", "La mascota seleccionada no se le puede asignar el plan de alimentación especificado.");
			return "feedings/createOrUpdateFeedingForm";
		}
		else {
			fs.save(feeding);
			return "redirect:/welcome";
		}
	}

}
