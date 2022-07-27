package modelo;

import processor.*;
import util.*;
import post_processor.PostProcessor;
import pre_processor.*;
import algebraLinear.*;

/**
 * Classe para preenchimento dos dados de um modelo estrutural reticulado
 * espacial com entrada de dados por escolha do usu�rio via arquivo.
 * <p>
 * Dados:
 * <ul>
 * <li>modelo - vari�vel do tipo Modelo
 * </ul>
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public class Principal {
	public static void main(String[] args) {

		Modelo modelo = null;

		System.out.println("**Programa de computa��o**");

		String fileName = FileUtilities.getFileName(); // M�todo que pega o arquivo de leitura desejado

		// pre_processor: preencher o modelo via arquivo
		modelo = PreProcessadorByFile.preencher_modelo(fileName);

		// processor: resolver o modelo (assembler=montagem + processor=solu��o)
		Processor.solverModel(modelo);

		// post_processor: apresentar os resultados do problema
		PostProcessor.printResults("saida.txt", modelo);

		System.out.println("**Fim da an�lise**");

	}

}