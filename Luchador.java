package wwe.pojo;

import java.util.*;

public class Luchador {
	//constante para evitar número mágico de la salud
	public final int SALUD_COMPLETA = 300;
	//atributos
	public int salud;
	private String nombre;
	private int categoria;
	private int fuerza;
	//atributo nombre del ataque especial
	private String nomAtaEsp;

	private ArrayList<Ataque> ataques;

	private boolean ko;
	
	//getters y setters
	public int getSalud() {
		return salud;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public ArrayList<Ataque> getAtaques() {
		return ataques;
	}

	public void setAtaques(ArrayList<Ataque> ataques) {
		this.ataques = ataques;
	}
	
	public boolean isKo() {
		return ko;
	}

	public void setKo(boolean ko) {
		this.ko = ko;
	}
	
	public String getNomAtaEsp() {
		return nomAtaEsp;
	}
	
	//constructor con 2 parámetros y el método generarAtaques integrado
	public Luchador(String nombre, int categoria) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.fuerza = categoria * 10 + 10;
		this.salud = SALUD_COMPLETA;
		this.ko = false;
		this.nomAtaEsp=null;
		generarAtaques();
	}
	//constructor con el nombre del ataque especial
	public Luchador(String nombre, int categoria, String ataqEsp) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.fuerza = categoria * 10 + 10;
		this.salud = SALUD_COMPLETA;
		this.ko = false;
		this.nomAtaEsp = ataqEsp;

		generarAtaques();
	}
	

	/**Crea tres objetos de cada clase de ataque y, de forma aleatoria, los
	 * selecciona y añade al array de ataques. Si nomAtaEsp tiene nombre (porque
	 * así se le ha dado al crear el objeto Luchador) se añade un objeto extra
	 * de la clase AtaqueEspecial y aumenta el tamaño del ArrayList ataques.
	 * 
	 */
	public void generarAtaques() {
		Punietazo punietazo = new Punietazo(fuerza);
		Patada patada = new Patada(fuerza);
		Salto salto = new Salto(fuerza);
		AtaqueEspecial ataEsp = new AtaqueEspecial(fuerza, nomAtaEsp);
		ataques = new ArrayList <Ataque>();
		for (int i = 0; i < categoria; i++) {
			switch ((int) (Math.random() * 3)) {
			case 0:
				ataques.add(punietazo);
				break;
			case 1:
				ataques.add(patada);
				break;
			case 2:
				ataques.add(salto);
				break;
			}
		}
		//se añade el ataque especial si se le ha dado un nombre
		if (nomAtaEsp!=null) {
			ataques.add(ataEsp);
		}

	}
	
	/**Genera un número al azar de rango del tamaño del array de ataques.
	 * @return Nombre del ataque seleccionado.
	 */
	public Ataque getAtaqueCualquiera() {
		return ataques.get((int)(Math.random()*(ataques.size())));
	}

	/**Recibe por parámetro la potencia del golpe recibido y lo resta a la
	 *  salud del luchador.
	 * @param golpe
	 */
	public void recibeGolpe(int golpe) {
		salud -= golpe;
		//salud = -golpe;
	}

	/**Pone el atributo salud al valor de la constante SALUD_COMPLETA. También
	 * establece como 'false' el atributo booleano ko del luchador.
	 * 
	 */
	public void enfermeria() {
		salud = SALUD_COMPLETA;
		this.ko=false;
	}

	/**Imprime la información del luchador: nombre, categoría, fuerza máxima, salud
	 * y recorre el listado de los ataques que tiene el array de ataques y ejecuta
	 * toString en cada objeto ataque.
	 * 
	 */
	public void info() {
		System.out.println("\n* " + nombre + " *"+ "\nCategoría: " + categoria +
				"\nFuerza máxima: "+fuerza+
				"\nSalud: "+salud+
				"\nAtaques disponibles:");
				
		for (Ataque ataque : ataques) {
			System.out.println("- "+ataque.toString());
		}

	}

}
