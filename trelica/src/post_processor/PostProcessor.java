package post_processor;

import java.io.FileWriter;
import java.util.Iterator;

import modelo.Barra;
import modelo.Modelo;
import modelo.Node;

/**
 * Classe que efetua o pós processamento de dados de um modelo estrutural
 * reticulado espacial, gerando um arquivo com os dados de saída da análise
 * estrutural: deslocamentos nodais e forças de extremidade.
 * <p>
 * 
 * @author Tainan Brandão
 * @version 1.0
 */

public class PostProcessor {

	/**
	 * Método que altera o modelo fornecido outro modelo como argumento.
	 * 
	 * @param filename nome do arquivo de saída com os resultados
	 * @param modelo   objeto do tipo modelo
	 */
	public static void printResults(String filename, Modelo modelo) {

		FileWriter fout = null;
		String text = "";
		try {
			fout = new FileWriter(filename);
//			fout = new FileWriter ("saida.txt");
			fout.write(modelo.toString());

			text += "\n___________________DADOS DE SAÍDA___________________";
			text += "\n******************Deslocamentos nodais******************\n";
			switch (modelo.get_tipoBarra()) {
			case ("Truss"):
				text += ("ID: (Dx, Dy)\n");
				break;
			case ("Beam"):
				text += ("ID: (Dy, rot)\n");
				break;
			case ("Frame"):
				text += ("ID: (Dx, Dy, rot)\n");
				break;
			}

			Iterator<Node> itListaNode = modelo.get_listaNode().iterator();

			while (itListaNode.hasNext()) {
				Node node_aux = itListaNode.next();
//				text += "Nó " + node_aux.get_id() + ":";				
				text += "Nó " + modelo.getNode(node_aux.get_id()).get_id() + ":";
//				for (int i=0; i<node_aux.get_deslocamentos().length; i++) {
//				text += String.format("%10.3E", node_aux.get_deslocamento(i)) + " "	;
//				}
				text += modelo.getNode(node_aux.get_id()).get_deslocamentos_vec().toString() + "\n";
//				text +="\n";
			}

			text += "\n*****************Forças de extremidade******************\n";

			switch (modelo.get_tipoBarra()) {
			case ("Truss"):
				text += ("ID: Nó inicial (Fx, Fy) + Nó final (Fx, Fy)\n");
				break;
			case ("Beam"):
				text += ("ID: Nó inicial (Fy, M) + Nó final (Fy, M)\n");
				break;
			case ("Frame"):
				text += ("ID: Nó inicial (Fx, Fy, M) + Nó final (Fx, Fy, M)\n");
				break;
			}

			Iterator<Barra> itListaBarra = modelo.get_listaBarra().iterator();
			while (itListaBarra.hasNext()) {
				Barra bar_aux = itListaBarra.next();
				text += "Barra " + modelo.getBarra(bar_aux.get_id()).get_id() + ":"; //
				text += modelo.getBarra(bar_aux.get_id()).getEndForces().toString() + "\n";
			}

			fout.write(text);
			fout.close();
			System.out.println("**Verificar arquivo de saída com resultados**\n");

		} catch (Exception e) {
			String msg = "Erro na saída de dados." + e.getMessage();
			System.out.println(msg);
		}
	}
}
