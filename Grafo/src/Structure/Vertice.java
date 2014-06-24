package Structure;

/**
 * Define um Vertice
 * @author Simor / Vanessa C. / Larson R.
 *
 */
public class Vertice {
	public String Nome;
	public double X;
	public double Y;
	
	/**
	 * Construtor
	 * @param nome Nome do Vertice
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 */
	public Vertice(String nome, double x, double y) {
		this.Nome = nome;
		this.X = x;
		this.Y = y;
	}
}
