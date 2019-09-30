package model;

public class TanqueCombustible {
	Boolean tieneCombustible;
	
	public TanqueCombustible() {
		tieneCombustible = false;
	}	
	
	public Boolean getTieneCombustible() {
		return tieneCombustible;
	}

	public void setTieneCombustible(Boolean tieneCombustible) {
		this.tieneCombustible = tieneCombustible;
	}

	@Override
	public String toString() {
		return "TanqueCombustible [tieneCombustible=" + tieneCombustible + "]";
	}
	
	
}
