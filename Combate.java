package wwe.pojo;

import java.util.*;

import wwe.exception.ContrincantesInsuficientesException;
import wwe.main.Torneo;

public class Combate {
	//parámetros
	private LinkedList<Luchador> contrincantes;

	private Luchador ganador;
	
	public Luchador ganador() {
		return ganador;
	}
	//constructor
	public Combate(LinkedList<Luchador> contrincantes) {
		super();
		this.contrincantes = contrincantes;
		this.ganador = ganador;
	}

	/**Selecciona un objeto de la clase Luchador con atributo Ko=false y ejecuta lanzarAtaque
	 * sobre otro objeto Luchador seleccionado al azar del array contrincantes mientras no sea el mismo
	 * objeto luchador y el atributo Ko=false. Por pantalla se muestran los detalles de estos ataques.
	 * La ejecución se detiene cuando sólo queda un objeto Luchador con atributo Ko=false y ejecuta 
	 * aniadirHallOfFame con este objeto Luchador por parámetro.
	 * Finalmente ejecuta el método enfermeria sobre todos los objetos Luchador.
	 * @throws ContrincantesInsuficientesException si el tamaño del array de contrincantes es menor de 2.
	 */
	public void fight() throws ContrincantesInsuficientesException {
		
		if(contrincantes.size()<2) {
			throw new ContrincantesInsuficientesException("No hay suficientes luchadores para empezar el combate");
		}

		int contadorKO = 0;
		// el do while se ejecuta hasta que todos esten KO menos uno.
		do {

			for (int i = 0; i < contrincantes.size(); i++) {
				// comprobamos si el que va a atacar no está ko
				if (contrincantes.get(i).isKo() == false) {
					// selecciona un ataque al azar propio
					Ataque ataque = contrincantes.get(i).getAtaqueCualquiera();

					int aleatorio = -1;
					// sacamos a quién va a atacar mientras sea a un rival Y no esté ko.
					//Por eso la condición de mantenimiento (while) es un || 'or' porque
					//necesitamos que se mantenga la búsqueda si se da cualquiera de las dos
					//condiciones. Con un && 'and' se podría atacar a sí mismo porque la 
					//condición solo se repetía si además el luchador estaba KO, cosa que, además,
					//no pasaba nunca porque eso se comprobaba inicialmente.
					do {

						aleatorio = (int) (Math.random() * contrincantes.size());

					} while (aleatorio == i || contrincantes.get(aleatorio).isKo() == true);

					System.out.print("¡Turno de " + contrincantes.get(i).getNombre() + "! ");
					// atacamos a ese contrincante aleatorio y se muestra su salud
					ataque.lanzarAtaque(contrincantes.get(aleatorio));

					System.out.println("Salud de " + contrincantes.get(aleatorio).getNombre() + ": "

							+ contrincantes.get(aleatorio).getSalud()+"\n");
					// comprobamos si el que ha recibido el golpe esta ko si su salud es menor o
					// igual a 0 y lo establecemos como ko

					if (contrincantes.get(aleatorio).getSalud() <= 0) {

						contrincantes.get(aleatorio).setKo(true);
						
						System.out.println("¡"+contrincantes.get(aleatorio).getNombre()+" está KO!"+"\n");
						
						contadorKO++;

					}
					//condición para que sólo quede un luchador sano
					if (contadorKO == (contrincantes.size() - 1)) {

						for (Luchador luchador : contrincantes) {

							if (luchador.isKo() == false) {

								ganador = luchador;

								System.out.println("¡" + luchador.getNombre() + " ha ganado el combate!");
								//llamamos al método de la clase Torneo para incluir al ganador en el HOF.
								Torneo.aniadirHallOfFame(luchador);

							}

						}

					}

				}

			}

		} while (contadorKO != (contrincantes.size() - 1));
		// curamos a todos los luchadores
		for (Luchador luchador : contrincantes) {
			luchador.enfermeria();
		}

	}
	
	

}
