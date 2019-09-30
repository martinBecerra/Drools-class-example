package model;

public enum FuerzaMotor {

	Normal,
	Debil,
	NoInformado;
	
	public  Boolean isa(FuerzaMotor fuerzaMotor) {
		
		return this.toString() == fuerzaMotor.toString();
		
	}
}
