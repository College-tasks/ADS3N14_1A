package Structure;

/**
 * Classe para ser usada com o algoritmo Djinkstra
 * @author Simor / Vanessa C. / Larson R.
 *
 */
public class Dji {
	public Vertice Atual;
	public double Custo;
	public Vertice Anterior;
	
	public Dji(Vertice atual){
		this.Atual = atual;
		this.Custo = 999999;
		this.Anterior = null;
	}
}
