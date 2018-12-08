package com.kenyo.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.kenyo.entity.Asistencia;
import com.kenyo.entity.Usuario;
import com.kenyo.repository.AsistenciaRepository;
import com.kenyo.util.DemoUtil;
import com.kenyo.util.Mail;

@Service
public class AsistenciaServiceImpl implements AsistenciaService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AsistenciaRepository repository;
	
	@Autowired
    private SpringTemplateEngine templateEngine;
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private DemoUtil demoUtil;

	@Override
	public List<Asistencia> usuarioTarde(LocalDateTime date) {
		return repository.obtenerTardanzas(date);
	}

	@Override
	public void obtenerTardanzas(LocalDateTime date) {
		logger.info("Obtener tardanzas: "+date.toString());
		List<Asistencia> asistencias = usuarioTarde(date);
		asistencias.forEach(a->{
			logger.info("Enviando a: " +a.getUsuario().getEmail());
			prepareEmail(a.getUsuario());
		});
		
	}

	@Override
	public void prepareEmail(Usuario destinatario) {
		Mail mail = new Mail();
		mail.setFrom(demoUtil.getEmail());
		mail.setTo(destinatario.getEmail());
		mail.setSubject("Notificacion de tardanza");
		
		Map<String, Object> model = new HashMap<>();
		model.put("user", destinatario.getNombres());
		mail.setModel(model);
		sendEmail(mail);
	}

	@Override
	public void sendEmail(Mail mail) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            
            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process("email/email-template", context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

	

}
