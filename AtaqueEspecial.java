package wwe.pojo;

public class AtaqueEspecial extends Ataque {
	
	
	//atributo del nombre del ataque para poder imprimirlo
	private String nombre;
	private double powerUp=1.8;
	private int danioMinimo=potencia/3;
	
	public AtaqueEspecial(int potencia, String nombre) {
		super(potencia);
		this.nombre=nombre;
	}
	
	

	public String getNombre() {
		return nombre;
	}

	
	/**Método heredado de la clase Ataque.
	 * Genera un número aletorio de rango 1 a fuerza del objeto Luchador que lo llame multiplicado por 
	 * el powerUp más el dañoMínimo y resta dicho número a la salud del objeto Luchador que reciba el 
	 * método por parámetro mediante el método recibeGolpe.
	 * Imprime por pantalla la acción.
	 * Éste número puede verse dividido entre 2 al ejecutarse el método ataqueBloqueado.
	 *
	 */
	@Override
	public int lanzarAtaque(Luchador receptor) {
		int danio =(int)( Math.random() * getPotencia()*powerUp+1+danioMinimo);
		if(ataqueBloqueado(danio, receptor)==true) {
			danio=danio/2;
		}System.out.println("¡Ataque especial "+nombre+" a "+receptor.getNombre()+" de "+danio+" de daño!");
		receptor.recibeGolpe(danio);
		return danio;
		
	}
	
	/**Devuelve la impresión del objeto
	 *
	 */
	public String toString() {
	String info="Ataque especial "+nombre;
	return info;
	}
}
