package br.com.geodrone.model.api;

import java.time.LocalDateTime;

public interface PeriodicModel {

	public LocalDateTime getDtInicioPeriodo();
	public void setDtInicioPeriodo(LocalDateTime dtInicioPeriodo);

	public LocalDateTime getDtFimPeriodo();
	public void setDtFimPeriodo(LocalDateTime dtFimPeriodo);

}