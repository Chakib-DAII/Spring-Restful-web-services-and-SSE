package com.DaiiChakib.Restfulwebservices.filtering;

import java.util.List;
import java.util.Arrays;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public /*SomeBean*/MappingJacksonValue retreiveSomeBean()
	{	//dynamic filtering
		//we will send only field1,field2
		SomeBean somebean = new SomeBean("value1","value2","value3");
		
		MappingJacksonValue mapping = new MappingJacksonValue(somebean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mapping.setFilters(filters );
		
		return mapping;
	}
	
	//send field 2 and 3 only
	@GetMapping("/filtering-list")
	public /*List<SomeBean>*/MappingJacksonValue retreiveListOfSomeBean()
	{
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2","value3"),
							new SomeBean("value11","value22","value33"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mapping.setFilters(filters );
		return mapping;
	}
}
