package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {
   
	@Autowired
	FeedingRepository fr;
	
	@Transactional
	public List<Feeding> getAll(){
        return fr.findAll();
    }
	
	@Transactional
    public List<FeedingType> getAllFeedingTypes(){
        return fr.findAllFeedingTypes();
    }

	@Transactional
    public FeedingType getFeedingType(String typeName) {
        return fr.findFeedingTypeByName(typeName);
    }

    @Transactional(rollbackOn = UnfeasibleFeedingException.class)
    public Feeding save(Feeding f) throws UnfeasibleFeedingException {
        if (f.getPet().getType() != f.getFeedingType().getPetType()) {
			throw new UnfeasibleFeedingException();
		} else {
			return fr.save(f);  
		}     
    }

    
}
