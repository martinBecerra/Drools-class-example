package model;

public class Motor {
	
	Arranque  arranque;
	FuerzaMotor  fuerzaMotor;
	
	Boolean observaFuncNormal;
	Boolean presentaExplosiones;
	Boolean presentaGolpes;
	
	public Motor() {
		arranque = Arranque.NoInformado;
		fuerzaMotor = FuerzaMotor.NoInformado;
		presentaExplosiones = false;
		presentaGolpes = false;
		observaFuncNormal = false;
	}
	
	public Arranque getArranque() {
		return arranque;
	}

	public void setArranque(Arranque arranque) {
		this.arranque = arranque;
	}

	public FuerzaMotor getFuerzaMotor() {
		return fuerzaMotor;
	}

	public void setFuerzaMotor(FuerzaMotor fuerzaMotor) {
		this.fuerzaMotor = fuerzaMotor;
	}

	public Boolean getPresentaExplosiones() {
		return presentaExplosiones;
	}

	public void setPresentaExplosiones(Boolean presentaExplosiones) {
		this.presentaExplosiones = presentaExplosiones;
	}

	public Boolean getPresentaGolpes() {
		return presentaGolpes;
	}

	public void setPresentaGolpes(Boolean presentaGolpes) {
		this.presentaGolpes = presentaGolpes;
	}

	public Boolean getObservaFuncNormal() {
		return observaFuncNormal;
	}

	public void setObservaFuncNormal(Boolean observFuncNormal) {
		this.observaFuncNormal = observFuncNormal;
	}

	@Override
	public String toString() {
		return "Motor [arranque=" + arranque + ", fuerzaMotor=" + fuerzaMotor + ", observaFuncNormal=" + observaFuncNormal
				+ ", presentaExplosiones=" + presentaExplosiones + ", presentaGolpes=" + presentaGolpes + "]";
	}

	
	
	
}
