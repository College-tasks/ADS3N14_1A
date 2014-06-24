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
	 * Construtor
	 * @param a Vertice A
	 * @param b Vertice B
	 * @param custo Custo da Aresta
	 */
	public Aresta(Vertice a, Vertice b, double custo) {
		this.A = a;
		this.B = b;
		this.Custo = custo;
		
		// Calcula distância
		this.Distancia = Math.sqrt(Math.pow(B.X - A.X, 2) + Math.pow(B.Y - A.Y, 2));
	}
}
