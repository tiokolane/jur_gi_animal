package com.tiokolane.jur_gui_animals.service;

import com.tiokolane.jur_gui_animals.model.EmailDetails;

// Importing required classes
 
// Interface
public interface EmailService {
 
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
 
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}