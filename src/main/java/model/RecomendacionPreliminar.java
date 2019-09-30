package model;

public enum RecomendacionPreliminar {

	NoDeterminado,
	NoNecesita,
	CargarBateria,
	CargarCombustible,
	RevisarMotor;
	
	public Boolean isa(RecomendacionPreliminar recomendacion) {
		
		return this.toString() == recomendacion.toString();
		
	}
}
