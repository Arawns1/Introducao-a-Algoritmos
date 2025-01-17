package com.residencia.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	public JavaMailSender emailSender;
	
	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.port}")
	private String mailPort;
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	@Value("${mail.from}")
	private String mailFrom;
	
	public EmailService(JavaMailSender javaMailSender){
		this.emailSender = javaMailSender;
	}
	
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(destinatario);
		mailMessage.setSubject(assunto);
		mailMessage.setText(mensagem);
		mailMessage.setFrom(mailFrom);
		
		try {
			emailSender.send(mailMessage);
		}
		catch(Exception e) {
			System.out.println("Ocorreu um erro ao enviar "
					+ "o email! " + e.getMessage());
		}
	}
	
}
