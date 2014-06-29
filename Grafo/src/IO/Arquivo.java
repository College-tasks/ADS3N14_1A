package IO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Structure.Aresta;
import Structure.Grafo;
import Structure.ProxVertice;
import Structure.Vertice;

/**
 * Classe de manipula��o de arquivo
 * @author Simor / Vanessa C. / Larson R.
 *
 */
public class Arquivo {
	
	/**
	 * Cria um ArrayList de Vertices e Arestas
	 * @param grafo Grafo a ser preenchido
	 */
	public static void carrega(Grafo grafo){
		Path path = Paths.get("grafo.txt");
		ArrayList<String> linhas = new ArrayList<String>();
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		
		// L� arquivo
	    try {
	    	linhas = new ArrayList<String>(Files.readAllLines(path, StandardCharsets.UTF_8));
		} catch (IOException e) {e.printStackTrace();}
	    
	    // Cria Vertices e Arestas
	    boolean ver = true;
	    for (String item : linhas) {
	    	if (item.trim().equals("vertices")) continue;
	    	if (item.trim().equals("arestas")) { ver = false; continue; }
	    	
	    	String[] linhaSplit = item.trim().split(" ");
	    	
	    	// Vertices
	    	if (ver) {
	    		vertices.add(new Vertice(linhaSplit[0], Double.parseDouble(linhaSplit[1]), Double.parseDouble(linhaSplit[2])));
	    	} // Arestas
	    	else {
	    		Aresta ar = new Aresta();
	    		ProxVertice pvA = new ProxVertice();
	    		ProxVertice pvB = new ProxVertice();
	    		Vertice a = null, b = null;
	    		
	    		// A
	    		for (Vertice itemV : vertices) {
	    			if (itemV.Nome.equals(linhaSplit[0])) {
	    				pvA.ProxVert = itemV;
	    				ar.A = itemV;
	    				a = itemV;
	    				break;
	    			}
	    		}
	    		
	    		// B
	    		for (Vertice itemV : vertices) {
	    			if (itemV.Nome.equals(linhaSplit[1])) {
	    				pvB.ProxVert = itemV;
	    				ar.B = itemV;
	    				b = itemV;
	    				break;
	    			}
	    		}
	    		
	    		// Custo Aresta
	    		ar.Custo = Double.parseDouble(linhaSplit[2]);
	    		
	    		// Calcula Dist�ncia
	    		ar.calcula();
	    		
	    		// ProxVertice
	    		pvA.Conex = ar;
	    		pvB.Conex = ar;
	    		
	    		// Adiciona nos V�rtices
	    		a.Proximo.add(pvA);
	    		b.Proximo.add(pvB);
	    	}
	    }
	    
	    grafo.lstVertice = vertices;
	}
}
