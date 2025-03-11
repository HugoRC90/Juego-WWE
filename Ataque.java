package wwe.pojo;

public abstract class Ataque {
	// atributo
	protected int potencia;

	// constructor
	public Ataque(int potencia) {
		this.potencia = potencia;
	}

	// getter de potencia
	public int getPotencia() {
		return potencia;
	}

	// método abstracto a implementar en las clases hijas
	public abstract int lanzarAtaque(Luchador receptor);

	/**
	 * Genera un número aleatorio con el rango de salud del receptor del ataque y lo
	 * compara con la fuerza del ataque. .
	 * 
	 * @param fuerzaAtaque
	 * @param receptor
	 * @return Si el número aleatorio es mayor se bloquea el ataque (true), si es
	 *         menor no bloquea el ataque (false).
	 */
	public boolean ataqueBloqueado(int fuerzaAtaque, Luchador receptor) {
		int bloqueo = (int) (Math.random() * (receptor.getSalud() + 1));
		if (bloqueo > fuerzaAtaque) {
			System.out.println("¡" + receptor.getNombre() + " ha bloqueado el ataque!");
			return true;
		} else {
			return false;
		}

	}

}
