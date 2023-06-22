package com.tiokolane.jur_gui_animals;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiokolane.jur_gui_animals.service.FilesStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class JurGuiAnimalsApplication implements CommandLineRunner{
	
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(JurGuiAnimalsApplication.class, args);
	}

	 @Override
  public void run(String... arg) throws Exception {
//    storageService.deleteAll();
    storageService.init();
  }

}
