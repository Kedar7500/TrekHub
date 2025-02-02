package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.PlanTrek;
import com.example.demo.entities.TrekBooking;
import com.example.demo.entities.TrekBookingDummy;
import com.example.demo.entities.User;
import com.example.demo.services.PlanTrekService;
import com.example.demo.services.TrekBookingService;
import com.example.demo.services.UserRegService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TrekBookingController {

	@Autowired
	TrekBookingService tservices;
	
	@Autowired
	PlanTrekService plantrekserv;
	
	@Autowired
	UserRegService uregservice;
	
	@PostMapping("/trekbook")
	public TrekBooking booking(@RequestBody TrekBookingDummy tb)
	{
		
		User user= uregservice.getUserInfo(tb.getTrekker_id());
		PlanTrek pk=plantrekserv.getPlantrekbyid(tb.getPlantrek_id());
		
		TrekBooking tbooking=new TrekBooking( user, pk, tb.getMembers(), tb.getAmount());
		
		return tservices.save(tbooking);
	}
	
	@GetMapping("/getbookingsbytrekkerid")
	public List<TrekBooking>getBookingsbyTrekkerid(@RequestParam ("user_id")int user_id)
	{
		User tid=new User(user_id);
		return tservices.getBookingsbyTrekkerid(tid);
	}
}

