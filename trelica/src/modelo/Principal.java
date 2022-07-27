package modelo;

import processor.*;
import util.*;
import post_processor.PostProcessor;
import pre_processor.*;
import algebraLinear.*;

/**
 * Classe para preenchimento dos dados de um modelo estrutural reticulado
 * espacial com entrada de dados por escolha do usuário via arquivo.
 * <p>
 * Dados:
 * <ul>
 * <li>modelo - variável do tipo Modelo
 * </ul>
 * 
 * @author Tainan Brandão
 * @version 1.0
 */

public class Principal {
	public static void main(String[] args) {

		Modelo modelo = null;

		System.out.println("**Programa de computação**");

		String fileName = FileUtilities.getFileName(); // Método que pega o arquivo de leitura desejado

		// pre_processor: preencher o modelo via arquivo
		modelo = PreProcessadorByFile.preencher_modelo(fileName);

		// processor: resolver o modelo (assembler=montagem + processor=solução)
		Processor.solverModel(modelo);

		// post_processor: apresentar os resultados do problema
		PostProcessor.printResults("saida.txt", modelo);

		System.out.println("**Fim da análise**");

	}

}