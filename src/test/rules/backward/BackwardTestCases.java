package backward;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import backwardChaining.BackwardChainingReasoner;
import model.Arranque;
import model.Bateria;
import model.FuerzaMotor;
import model.Motor;
import model.RecomendacionPreliminar;
import model.Reparacion;
import model.ReparacionMotor;
import model.TanqueCombustible;
import utils.KnowledgeSessionHelper;

public class BackwardTestCases {

	// Buscar archivo kmodule.xml. El nombre de la sesion debe ser igual al
	// configurado en ese archivo
	String K_SESSION_NAME = "kbackward-chaining-session";

	KieSession sessionStatefull;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeallTestSetup() {
		kieContainer = KnowledgeSessionHelper.createRuleBase();
	}

	@Before
	public void setUp() {
		System.out.println("----------Start----------");
	}

	@After
	public void tearDown() {
		System.out.println("----------End----------");
	}
	
	private RuleRuntimeEventListener buildEventListener() {
		return new RuleRuntimeEventListener() {
			@Override
			public void objectUpdated(ObjectUpdatedEvent event) {
			
				System.out.println("Object updated \n" + event.getObject().toString());
			}

			@Override
			public void objectInserted(ObjectInsertedEvent event) {
				System.out.println("Object inserted \n" + event.getObject().toString());
			}

			@Override
			public void objectDeleted(ObjectDeletedEvent event) {
				System.out.println("Object deleted \n" + event.getOldObject().toString());
			}
		};
	}
	
	
	@Test
	public void noNecesitaReparacionTest() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, K_SESSION_NAME);
		// OutputDisplay outputDisplay = new OutputDisplay();
		sessionStatefull.addEventListener(buildEventListener());

		System.out.println("Caso de prueba: No necesita reparación");

		Motor motor = new Motor();
		motor.setArranque(Arranque.Gira);
		motor.setFuerzaMotor(FuerzaMotor.Normal);
		motor.setPresentaExplosiones(false);
		motor.setObservaFuncNormal(true);
		sessionStatefull.insert(motor);
		
		Bateria bateria = new Bateria();
		bateria.setTieneCarga(true);

		sessionStatefull.insert(bateria);

		TanqueCombustible tanque = new TanqueCombustible();
		tanque.setTieneCombustible(true);

		sessionStatefull.insert(tanque);

		BackwardChainingReasoner reasoner = new BackwardChainingReasoner(sessionStatefull);
		
		RecomendacionPreliminar recomendacionPreliminar = reasoner.diagnosticoPreliminar();
		ReparacionMotor reparacionMotor = reasoner.diagnosticoMotor();
		
		Reparacion reparacion = new Reparacion();
		reparacion.setRecomendacionPreliminar(recomendacionPreliminar);
		reparacion.setReparacionMotor(reparacionMotor);
		
		System.out.println("Resultado: " + reparacion);
		assert(reparacion.getRecomendacionPreliminar().isa(RecomendacionPreliminar.NoNecesita));
	}

	@Test
	public void cargarBateriaTest() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, K_SESSION_NAME);
		sessionStatefull.addEventListener(buildEventListener());
		System.out.println("Caso de prueba: Cargar Bateria");

		Motor motor = new Motor();
		motor.setArranque(Arranque.NoGira);

		sessionStatefull.insert(motor);

		Bateria bateria = new Bateria();
		bateria.setTieneCarga(false);

		sessionStatefull.insert(bateria);

		BackwardChainingReasoner reasoner = new BackwardChainingReasoner(sessionStatefull);
		
		RecomendacionPreliminar recomendacionPreliminar = reasoner.diagnosticoPreliminar();
		ReparacionMotor reparacionMotor = reasoner.diagnosticoMotor();
		
		Reparacion reparacion = new Reparacion();
		reparacion.setRecomendacionPreliminar(recomendacionPreliminar);
		reparacion.setReparacionMotor(reparacionMotor);
		
		System.out.println("Resultado: " + reparacion);
		
		assert(reparacion.getRecomendacionPreliminar().isa(RecomendacionPreliminar.CargarBateria));
	}

	@Test
	public void necesitaCombustibleTest() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, K_SESSION_NAME);
		sessionStatefull.addEventListener(buildEventListener());
		System.out.println("Caso de prueba: Necesita Combustible");

		Motor motor = new Motor();
		motor.setArranque(Arranque.Gira);

		sessionStatefull.insert(motor);

		TanqueCombustible tanque = new TanqueCombustible();
		tanque.setTieneCombustible(false);

		sessionStatefull.insert(tanque);

		BackwardChainingReasoner reasoner = new BackwardChainingReasoner(sessionStatefull);
		
		RecomendacionPreliminar recomendacionPreliminar = reasoner.diagnosticoPreliminar();
		ReparacionMotor reparacionMotor = reasoner.diagnosticoMotor();
		
		Reparacion reparacion = new Reparacion();
		reparacion.setRecomendacionPreliminar(recomendacionPreliminar);
		reparacion.setReparacionMotor(reparacionMotor);
		
		System.out.println("Resultado: " + reparacion);
		
		assert(reparacion.getRecomendacionPreliminar().isa(RecomendacionPreliminar.CargarCombustible));
	}

	
	  @Test public void limpiarTuberiasCombustibleTest() { 
		  
			sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, K_SESSION_NAME);
			sessionStatefull.addEventListener(buildEventListener());
			System.out.println("Caso de prueba: Limpiar Tuberias de combustible");

			Motor motor = new Motor();
			motor.setArranque(Arranque.Gira);
			motor.setFuerzaMotor(FuerzaMotor.Debil);
			sessionStatefull.insert(motor);

			Bateria bateria = new Bateria();
			bateria.setTieneCarga(true);

			sessionStatefull.insert(bateria);

			TanqueCombustible tanque = new TanqueCombustible();
			tanque.setTieneCombustible(true);

			sessionStatefull.insert(tanque);

			BackwardChainingReasoner reasoner = new BackwardChainingReasoner(sessionStatefull);
			
			RecomendacionPreliminar recomendacionPreliminar = reasoner.diagnosticoPreliminar();
			ReparacionMotor reparacionMotor = reasoner.diagnosticoMotor();
			
			Reparacion reparacion = new Reparacion();
			reparacion.setRecomendacionPreliminar(recomendacionPreliminar);
			reparacion.setReparacionMotor(reparacionMotor);
			
			System.out.println("Resultado: " + reparacion);
			
			assert(reparacion.getReparacionMotor().isa(ReparacionMotor.LimpiarTuberiasCombustible));
		}
	 

	@Test
	public void ajustarPuntosDeInjeccionTest() {
		
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, K_SESSION_NAME);
		sessionStatefull.addEventListener(buildEventListener());
		System.out.println("Caso de prueba: Cargar Ajustar Puntos de Injección");

		Motor motor = new Motor();
		motor.setArranque(Arranque.Gira);
		motor.setPresentaExplosiones(true);
		sessionStatefull.insert(motor);

		Bateria bateria = new Bateria();
		bateria.setTieneCarga(true);

		sessionStatefull.insert(bateria);

		TanqueCombustible tanque = new TanqueCombustible();
		tanque.setTieneCombustible(true);

		sessionStatefull.insert(tanque);

		BackwardChainingReasoner reasoner = new BackwardChainingReasoner(sessionStatefull);
		
		RecomendacionPreliminar recomendacionPreliminar = reasoner.diagnosticoPreliminar();
		ReparacionMotor reparacionMotor = reasoner.diagnosticoMotor();
		
		Reparacion reparacion = new Reparacion();
		reparacion.setRecomendacionPreliminar(recomendacionPreliminar);
		reparacion.setReparacionMotor(reparacionMotor);
		
		System.out.println("Resultado: " + reparacion);
		
		assert(reparacion.getReparacionMotor().isa(ReparacionMotor.AjustarPuntosDeInyeccion));
	}

	@Test
	public void ajustarTimingMotorTest() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, K_SESSION_NAME);
		sessionStatefull.addEventListener(buildEventListener());
		System.out.println("Caso de prueba: Ajustar Timing del Motor");

		Motor motor = new Motor();
		motor.setArranque(Arranque.Gira);
		motor.setPresentaGolpes(true);
		sessionStatefull.insert(motor);

		Bateria bateria = new Bateria();
		bateria.setTieneCarga(true);

		sessionStatefull.insert(bateria);

		TanqueCombustible tanque = new TanqueCombustible();
		tanque.setTieneCombustible(true);

		sessionStatefull.insert(tanque);

		BackwardChainingReasoner reasoner = new BackwardChainingReasoner(sessionStatefull);
		
		RecomendacionPreliminar recomendacionPreliminar = reasoner.diagnosticoPreliminar();
		ReparacionMotor reparacionMotor = reasoner.diagnosticoMotor();
		
		Reparacion reparacion = new Reparacion();
		reparacion.setRecomendacionPreliminar(recomendacionPreliminar);
		reparacion.setReparacionMotor(reparacionMotor);
		
		System.out.println("Resultado: " + reparacion);
		
		assert(reparacion.getReparacionMotor().isa(ReparacionMotor.AjustarTimingMotor));
	}

	@Test
	public void consultarExpertosTest() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, K_SESSION_NAME);
		sessionStatefull.addEventListener(buildEventListener());
		System.out.println("Caso de prueba: Consultar Experto");

		Motor motor = new Motor();
		motor.setArranque(Arranque.Gira);
		motor.setFuerzaMotor(FuerzaMotor.Normal);
		motor.setPresentaExplosiones(false);
		motor.setPresentaGolpes(false);
		motor.setObservaFuncNormal(false);
		
		sessionStatefull.insert(motor);

		Bateria bateria = new Bateria();
		bateria.setTieneCarga(true);

		sessionStatefull.insert(bateria);

		TanqueCombustible tanque = new TanqueCombustible();
		tanque.setTieneCombustible(true);

		sessionStatefull.insert(tanque);

		BackwardChainingReasoner reasoner = new BackwardChainingReasoner(sessionStatefull);
		
		RecomendacionPreliminar recomendacionPreliminar = reasoner.diagnosticoPreliminar();
		ReparacionMotor reparacionMotor = reasoner.diagnosticoMotor();
		
		Reparacion reparacion = new Reparacion();
		reparacion.setRecomendacionPreliminar(recomendacionPreliminar);
		reparacion.setReparacionMotor(reparacionMotor);
		
		System.out.println("Resultado: " + reparacion);
		
		assert(reparacion.getReparacionMotor().isa(ReparacionMotor.ConsultarExperto));
	}

}
