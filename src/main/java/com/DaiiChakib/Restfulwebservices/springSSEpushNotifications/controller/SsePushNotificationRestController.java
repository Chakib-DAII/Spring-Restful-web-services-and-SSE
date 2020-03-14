package com.DaiiChakib.Restfulwebservices.springSSEpushNotifications.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.DaiiChakib.Restfulwebservices.springSSEpushNotifications.service.SsePushNotificationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class SsePushNotificationRestController {

	@Autowired
	SsePushNotificationService notificationService;
	
	final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	
	@GetMapping("/notification")
	public ResponseEntity<SseEmitter> doNotify() throws InterruptedException, IOException {
		final SseEmitter emitter = new SseEmitter();
		notificationService.addEmitter(emitter);
		notificationService.doNotify();
		emitter.onCompletion(() -> notificationService.removeEmitter(emitter));
		emitter.onTimeout(() -> notificationService.removeEmitter(emitter));
		return new ResponseEntity<>(emitter, HttpStatus.OK);
	}
}
