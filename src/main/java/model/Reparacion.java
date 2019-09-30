package model;

public class Reparacion {
	RecomendacionPreliminar recomendacionPreliminar;
	ReparacionMotor reparacionMotor;
	
	public Reparacion() {
		recomendacionPreliminar = RecomendacionPreliminar.NoDeterminado;
		reparacionMotor = ReparacionMotor.NoDeterminado; 
	}
	
	public RecomendacionPreliminar getRecomendacionPreliminar() {
		return recomendacionPreliminar;
	}

	public void setRecomendacionPreliminar(RecomendacionPreliminar recomendacionPreliminar) {
		this.recomendacionPreliminar = recomendacionPreliminar;
	}

	public ReparacionMotor getReparacionMotor() {
		return reparacionMotor;
	}

	public void setReparacionMotor(ReparacionMotor reparacionMotor) {
		this.reparacionMotor = reparacionMotor;
	}

	@Override
	public String toString() {
		return "Reparacion [recomendacionPreliminar=" + recomendacionPreliminar + ", reparacionMotor=" + reparacionMotor
				+ "]";
	}
	
}
