package wwe.main;

import java.util.*;

import wwe.exception.ContrincantesInsuficientesException;
import wwe.pojo.*;

public class Torneo {
	// atributos
	private static HashMap<String, Integer> hallOfFame;

	private static LinkedList<Luchador> luchadores;
	// scanner
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// inicializamos o instanciamos las colecciones declaradas antes
		hallOfFame = new HashMap<String, Integer>();

		luchadores = new LinkedList<Luchador>();

		int respuesta = -1;

		System.out.println("*** BIENVENIDO AL TORNEO DE LUCHA LIBRE DAWWE ***");
		esperar(12);
		System.out.println("cargando...");
		esperar(10);
		System.out.println("           █     █ █     █ ███████ ");
		esperar(5);
		System.out.println("           █     █ █     █ █       ");
		esperar(5);
		System.out.println("           █  █  █ █  █  █ █████   ");
		esperar(4);
		System.out.println("           █ █ █ █ █ █ █ █ █       ");
		esperar(2);
		System.out.println("           ██   ██ ██   ██ ███████ ");
		esperar(2);
		System.out.println();
		esperar(2);
		System.out.println("           W     W  W     W  EEEEE ");
		esperar(2);
		System.out.println("           W     W  W     W  E     ");
		esperar(2);
		System.out.println("           W  W  W  W  W  W  EEEE  ");
		esperar(2);
		System.out.println("           W W W W  W W W W  E     ");
		esperar(2);
		System.out.println("           WW   WW  WW   WW  EEEEE ");

		esperar(20);
		System.out.println("Pulse enter para continuar");
		sc.nextLine();
		// mostramos el menú mientras no se seleccione el '0' para salir.
		do {
			System.out.println("\n****MENÚ PRINCIPAL***"

					+ "\n1 - Mostrar luchadores" + "\n2 - Añadir Luchador" + "\n3 - Ready? FIGHT!"
					+ "\n4 - Mostrar Hall of Fame" + "\n0 - Salir" + "\n(Seleccione una opción del 0 al 4)");
			// capturamos la excepción por si el usuario no introduce números
			try {
				respuesta = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Introduzca sólo números");
				respuesta = -1;
				// con continue evitamos que ejecute el resto del bucle y vuelva al principio
				continue;
			}
			
			switch (respuesta) {
			case 1:
				if (luchadores.size() == 0) {
					System.out.println("¡Aún no has añadido luchadores que mostrar!\n");
				} else {
					mostrarLuchador();
				}
				break;
			case 2:

				aniadirLuchador();

				break;
			case 3:
				Combate combate = new Combate(luchadores);
				try {
					combate.fight();
				} catch (ContrincantesInsuficientesException cie) {
					String respuestaExc = cie.getMessage();
					System.out.println(respuestaExc);
				}
				break;
			case 4:
				// si no hay ningún objeto de la clase Luchador en el array de hallOfFame no
				// muestra
				// la informaciñon del array.
				if (hallOfFame.size() < 1) {
					System.out.println("Aún no hay ganadores, lucha primero");
				} else {
					System.out.println("** LISTADO DE PUNTUACIONES **");
					mostrarHallOfFame();
				}
				break;
			case 0:
				System.out.println("¡Gracias por jugar!");
				break;
			default:
				System.out.println("Elige una opción del 0 al 4, por favor");
				break;
			}

		} while (respuesta != 0);

	}

	/**
	 * Crea objeto de la clase Luchador pidiendo por teclado al usuario el nombre y
	 * la categoría y lo añade al array de luchadores. Si indica que tiene
	 * AtaqueEspecial pide por teclado el nombre del ataque y genera un objeto
	 * Luchador con dicho atributo pasado por parámetro.
	 * 
	 */
	public static void aniadirLuchador() {
		boolean salida = true;
		int categoria = -1;
		System.out.println("**LUCHADOR NUEVO**");
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();

		do {
			System.out.println("Categoría:\n" + "1: Peso mosca\n" + "2: Peso pluma\n" + "3: Peso ligero\n"
					+ "4: Peso medio\n" + "5: Peso pesado");

			try {

				categoria = Integer.parseInt(sc.nextLine());
				if (categoria > 5 || categoria < 1) {
					System.out.println("Seleccione del 1 al 5");
				}
				salida = false;

			} catch (NumberFormatException e) {
				System.out.println("Introduzca sólo números");

			}
		} while (salida == true || (categoria > 5 || categoria < 1));

		System.out.println("¿Tiene ataque especial?(s/n)");
		boolean salida2 = true;
		do {
			String respAtaqEsp = sc.nextLine();
			if (respAtaqEsp.toLowerCase().equals("s")) {
				System.out.println("Nombre del ataque:");
				String nombreAtaqueEspecial = sc.nextLine();
				Luchador luchador = new Luchador(nombre, categoria, nombreAtaqueEspecial);
				luchadores.add(luchador);
				salida2 = false;

			} else if (respAtaqEsp.toLowerCase().equals("n")) {
				Luchador luchador = new Luchador(nombre, categoria);
				luchadores.add(luchador);
				salida2 = false;
			} else {
				System.out.println("Indique únicamente Sí(s) o No(n)");
			}
		} while (salida2 == true);
	}

	/**
	 * Recorre el array de luchadores y ejecuta el método info en cada objeto
	 * Luchador del array.
	 * 
	 */
	public static void mostrarLuchador() {
		for (Luchador luchador : luchadores) {
			luchador.info();
		}
	}

	/**
	 * Añade al HashMap hallOfFame el nombre del objeto Luchador que se pase por
	 * parámetro como clave y un 1 como valor. Si la clave ya existe, suma 1 al
	 * valor.
	 * 
	 * @param luchador
	 */
	public static void aniadirHallOfFame(Luchador luchador) {
		if (hallOfFame.containsKey(luchador.getNombre())) {
			hallOfFame.put(luchador.getNombre(), hallOfFame.get(luchador.getNombre()) + 1);
		} else {
			hallOfFame.put(luchador.getNombre(), 1);
		}
	}

	/**
	 * Recorre el HahsMap hallOfFame e imprime la clave y su valor.
	 * 
	 */
	public static void mostrarHallOfFame() {
		for (String clave : hallOfFame.keySet()) {
			System.out.println(clave + ": " + hallOfFame.get(clave));
		}
	}

	public static void esperar(int decimas) {
		try {
			Thread.sleep(decimas * 100);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
