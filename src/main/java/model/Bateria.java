package model;

public class Bateria {
	Boolean tieneCarga;
	
	public Boolean getTieneCarga() {
		return tieneCarga;
	}

	public void setTieneCarga(Boolean tieneCarga) {
		this.tieneCarga = tieneCarga;
	}

	public Bateria() {
		tieneCarga = false;
	}

	@Override
	public String toString() {
		return "Bateria [tieneCarga=" + tieneCarga + "]";
	}
	
	
}
