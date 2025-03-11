package wwe.pojo;

public class Punietazo extends Ataque {
	
	private double powerUp=1;
	
	//constructor
	public Punietazo(int potencia) {
		super(potencia);
	}
	
	/**Método heredado de la clase Ataque.
	 * Genera un número aletorio de rango 1 a fuerza del objeto Luchador que lo llame multiplicado por 
	 * el powerUp y resta dicho número a la salud del objeto Luchador que reciba el método por parámetro
	 * mediante el método recibeGolpe.
	 * Imprime por pantalla la acción.
	 * Éste número puede verse dividid entre 2 al ejecutarse el método ataqueBloqueado.
	 *
	 */
	@Override
	public int lanzarAtaque(Luchador receptor) {
		int danio =(int)( Math.random() * getPotencia()*powerUp+1);
		if(ataqueBloqueado(danio, receptor)==true) {
			danio=danio/2;
		}System.out.println("¡Puñetazo a "+receptor.getNombre()+" de "+danio+" de daño!");
		receptor.recibeGolpe(danio);
		return danio;
		
	}
	/**Devuelve la impresión del objeto.
	 *
	 */
	public String toString() {
		String info="Puñetazo de potencia "+(int)(potencia*powerUp);
		return info;
	}
	
}
