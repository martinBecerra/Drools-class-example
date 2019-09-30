package model;

public enum ReparacionMotor {

	NoDeterminado,
	LimpiarTuberiasCombustible,
	AjustarTimingMotor,
	AjustarPuntosDeInyeccion,
	ConsultarExperto;
	
	
	public Boolean isa(ReparacionMotor recomendacion) {
		
		return this.toString() == recomendacion.toString();
		
	}
}
