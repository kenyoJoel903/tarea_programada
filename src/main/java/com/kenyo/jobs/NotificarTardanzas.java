package com.kenyo.jobs;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kenyo.service.AsistenciaService;
import com.kenyo.util.DemoUtil;

@Component
public class NotificarTardanzas {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DemoUtil demoUtil;
	
	
	@Autowired
	private AsistenciaService service;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate = 300000)
	public void executeTask() {
		logger.info("The time is now {}", dateFormat.format(new Date()));
		Date now = new Date();
		try {
			Date task = dateFormat.parse(demoUtil.getTareaprogramada_hora());
			logger.info("The time for execute Job is {}", dateFormat.format(task));
			String[] _horaLimite = demoUtil.getHora_limite().split(":");
			if(now.getHours() == task.getHours() ) {
				logger.info("Hora limite es: " + demoUtil.getHora_limite());
				LocalDateTime _now = LocalDateTime.now().withHour(Integer.parseInt(_horaLimite[0])).withMinute(Integer.parseInt(_horaLimite[1]));
				service.obtenerTardanzas(_now);
			}
		} catch (Exception e) {
			logger.error("Error en executeTaks", e);
		}
	}

}
