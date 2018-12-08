package com.kenyo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kenyo.entity.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long>{

	
	@Query("from Asistencia a where a.fecha_registro > :fechaConsulta")
	List<Asistencia> obtenerTardanzas(@Param("fechaConsulta") LocalDateTime fechaConsulta);
}
