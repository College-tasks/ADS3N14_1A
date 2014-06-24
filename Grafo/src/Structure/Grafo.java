package Structure;

import java.util.ArrayList;
import IO.Arquivo;

/**
 * Define um grafo
 * @author Simor / Vanessa C. / Larson R.
 *
 */
public class Grafo {
	public ArrayList<Vertice> lstVertice;
	
	/**
	 * Construtor - Cria o grafo a partir de um arquivo
	 */
	public Grafo(){
		this.lstVertice = Arquivo.carrega();
	}
}