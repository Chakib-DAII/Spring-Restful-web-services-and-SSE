package com.DaiiChakib.Restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	
	//versioning using Uri
	@GetMapping("v1/person")
	public PersonV1 personV1()
	{
		return new PersonV1("chakib DAII");
	}
	@GetMapping("v2/person")
	public PersonV2 personV2()
	{
		return new PersonV2(new Name("chakib","DAII"));
	}
	
	//versioning using params
	@GetMapping(value="/person",params="v=1")
	public PersonV1 personParamV1()
	{
		return new PersonV1("chakib DAII");
	}
	@GetMapping(value="/person",params="v=2")
	public PersonV2 personParamV2()
	{
		return new PersonV2(new Name("chakib","DAII"));
	}
	
	//versioning using headers
	@GetMapping(value="/person",headers="X-API-VERSION=1")
	public PersonV1 personHeaderV1()
	{
		return new PersonV1("chakib DAII");
	}
	@GetMapping(value="/person",headers="X-API-VERSION=2")
	public PersonV2 personHeaderV2()
	{
		return new PersonV2(new Name("chakib","DAII"));
	}
	
	//versioning using produces
	//accept header versioning
	@GetMapping(value="/person",produces="application/vnd.company.app-v1+json")
	public PersonV1 personProducesV1()
	{
		return new PersonV1("chakib DAII");
	}
	@GetMapping(value="/person",produces="application/vnd.company.app-v2+json")
	public PersonV2 personProducesV2()
	{
		return new PersonV2(new Name("chakib","DAII"));
	}
	
}
