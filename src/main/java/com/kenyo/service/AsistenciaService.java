package com.kenyo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.kenyo.entity.Asistencia;
import com.kenyo.entity.Usuario;
import com.kenyo.util.Mail;

public interface AsistenciaService {
	
	List<Asistencia> usuarioTarde(LocalDateTime date);
	
	void prepareEmail(Usuario destinatario);
	
	void sendEmail(Mail mail);
	
	void obtenerTardanzas(LocalDateTime date);

}
