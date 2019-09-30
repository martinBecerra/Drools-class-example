package backwardChaining;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;

import model.RecomendacionPreliminar;
import model.ReparacionMotor;

public class BackwardChainingReasoner {
	KieSession sessionStatefull;
	
	public BackwardChainingReasoner(KieSession sessionStatefull) {
		this.sessionStatefull = sessionStatefull;
	}
	
	public RecomendacionPreliminar diagnosticoPreliminar() {
		
		QueryResults result;
		
		result = sessionStatefull.getQueryResults("Hipotesis: Automovil no necesita reparacion");
		
		if (result.size() == 1) {
			return RecomendacionPreliminar.NoNecesita;
		}
		
		result = sessionStatefull.getQueryResults("Hipotesis: Automovil no tiene combustible");
		
		if (result.size() == 1) {
			return RecomendacionPreliminar.CargarCombustible;
		}
		
		result = sessionStatefull.getQueryResults("Hipotesis: Bateria no tiene carga");
		
		if (result.size() == 1) {
			return RecomendacionPreliminar.CargarBateria;
		}
				
		return RecomendacionPreliminar.RevisarMotor;
		
	}
	
	public ReparacionMotor diagnosticoMotor() {
		
		QueryResults result = sessionStatefull.getQueryResults("Hipotesis: Tuberias del motor esan tapadas");
		
		if (result.size() == 1) {
			return ReparacionMotor.LimpiarTuberiasCombustible;
		}
		
		result = sessionStatefull.getQueryResults("Hipotesis: Los puntos de injeccion estan desajustados");
		
		if (result.size() == 1) {
			return ReparacionMotor.AjustarPuntosDeInyeccion;
		}
		
		result = sessionStatefull.getQueryResults("Hipotesis: El timer del motor no esta a punto");
		
		if (result.size() == 1) {
			return ReparacionMotor.AjustarTimingMotor;
		}
		
		result = sessionStatefull.getQueryResults("Hipotesis: No encuentro el problema, debería consultar a otro experto");
		
		if (result.size() == 1) {
			return ReparacionMotor.ConsultarExperto;
		}
		
		
		return ReparacionMotor.NoDeterminado;
	}
}
