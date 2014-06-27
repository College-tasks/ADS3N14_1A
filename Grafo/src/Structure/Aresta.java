package Structure;

import java.lang.Math;

/**
 * Define uma Aresta
 * @author Simor / Vanessa C. / Larson R.
 *
 */
public class Aresta {
	public Vertice A;
	public Vertice B;
	public double Custo;
	public double Distancia;
	
	/**
	 * Calcula a distância
	 */
	public void calcula(){
		// Calcula distância
		this.Distancia = Math.sqrt(Math.pow(B.X - A.X, 2) + Math.pow(B.Y - A.Y, 2));
	}
}
