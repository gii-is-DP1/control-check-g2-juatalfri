package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType>{

	@Autowired
	private FeedingService ps;
	
    @Override
    public String print(FeedingType object, Locale locale) {
        String nombre = object.getName();
        return nombre;
    }

    @Override
    public FeedingType parse(String text, Locale locale) throws ParseException {
        FeedingType ft = ps.getFeedingType(text);
        if (ft == null) {
			throw new ParseException(text, 0);
		} else {
	        return ft;
		}
    }
    
}
