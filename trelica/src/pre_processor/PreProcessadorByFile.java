package pre_processor;

import java.io.FileReader;
import java.util.StringTokenizer;
import modelo.*;
import util.*;
import structureLoad.*;

/**
 * Classe que efetua o pr� processamento de dados de um modelo estrutural
 * reticulado espacial, preenchendo o modelo a partir da leitura de dados via
 * arquivo.
 * <p>
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public class PreProcessadorByFile {

	/**
	 * Declara��o dos campos da classe.
	 */
	static private Modelo modelo = null;

	/**
	 * Conjunto de m�todos que gera o modelo estrutural: inicializar o modelo
	 * estrutural e preencher os campos dos n�s, materiais, se��es e barras,
	 * conforme leitura realizada no arquivo de dados. Caso algum dado necess�rio
	 * para preenchimento do modelo n�o seja informado ser� relatado ao usu�rio o
	 * erro ocorrido.
	 * 
	 * @return modelo com os dados preenchidos
	 * @param fileName nome do arquivo que cont�m as informa��es do modelo
	 */
	public static final Modelo preencher_modelo(String fileName) {
		try {
			FileReader fr = FileUtilities.createFileReader(fileName);
			inicializar_modelo(fr);
			preencher_nodes(fr);
			preencher_materiais(fr);
			preencher_secoes(fr);
			preencher_barras(fr);
			preencher_forcas(fr);
			preencher_PointLoads(fr);
			preencher_DistributedLoads(fr);
			preencher_restricoes(fr);
			preencher_deslocamentos(fr);
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelo;
	}

	/**
	 * M�todo para inicializar e preencher por meio da leitura dos dados contidos no
	 * arquivo indicado pelo usu�rio o n�mero de n�s (n_nodes), n�mero de materiais
	 * (n_materiais), n�mero de se��es transversais (n_secoes), n�mero de barras
	 * (n_barras), tipo das barras (tipo_barra) e t�tulo do modelo estrutural.
	 * 
	 * @return modelo com os dados preenchidos
	 */
	private static void inicializar_modelo(FileReader fr) { // � provado pq n�o deve ser acessado fora da classe e �
															// static pq o inicializar modelo � static

		String titulo = null, linha = null, tipoBarra = null;
		StringTokenizer st;

		try {
			titulo = FileUtilities.leLinha(fr);

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_INITIAL_DATA").equals("")) {
				FileUtilities.leLinha(fr); // L� a linha com palavra type
				linha = FileUtilities.leLinha(fr); // L� o tipo de barra

				while (!linha.startsWith("%END_INITIAL_DATA")) { // enquanto a linha n�o for %END_INITIAL_DATA
					st = new StringTokenizer(linha);// Cria os tokens da linha, poderia ser separado por \ ao inv�s de
													// espa�o, por exemplo
					int nt = st.countTokens(); // Conta o n�mero de tokens da linha
					if (nt == 1) {// nesse caso deve ser um s� com o tipo de barra do modelo
						tipoBarra = st.nextToken();
						modelo = new Modelo(titulo, tipoBarra);
						linha = FileUtilities.leLinha(fr);// Essa linha j� ser� %END_INITIAL_DATA
					} // ent�o o loop acaba
					else {
						System.out.println("N�mero de par�metros iniciais incorreto");
						System.exit(1);
					}
				}
			} else {
				System.out.println("Marcador \"%BEGIN_INITIAL_DATA\" inexistente ou na posi��o incorreta.");
				System.exit(1);
			}

		} catch (Exception e) {
			String msg = "Erro na entrada de dados!!\n" + e.getMessage();
			System.out.println(msg);
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista com v�rios n�s do modelo estrutural (sem
	 * retorno).
	 */
	private static void preencher_nodes(FileReader fr) {
		String linha = null;

		try {
//			FileUtilities.leLinha(fr); 			

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_NODES").equals("")) {
				FileUtilities.leLinha(fr); // essa linha possui o cabe�alho: Nodes, cx, cy, cz
				linha = FileUtilities.leLinha(fr);// Nessa j� est�o os dados referentes aos n�s
				while (!linha.startsWith("%END_NODES")) {// Enquanto a linha n�o iniciar com %END_NODES
					preencher_node(linha); // Esse .startsWith � um m�todo do pr�prio java
					linha = FileUtilities.leLinha(fr);
				}
			} else {
				System.out.println("Marcador \"%BEGIN_NODES\" inexistente ou na posi��o incorreta.");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio os dados dos n�s existentes do modelo estrutural (sem
	 * retorno).
	 * 
	 * @param node  elemento do tipo Node que ser� preenchido
	 * @param linha linha do arquivo de dados que cont�m as informa��es do n�
	 */
	private static void preencher_node(String linha) {
		StringTokenizer st = new StringTokenizer(linha); // linha com parametros lida no m�todo anterior
		int nt = st.countTokens();
		String id = null;
		Node node = new Node();
//		Node node;
		if (nt == 4) {
			id = st.nextToken();
			if (modelo.procurar_Node(id)) {
				System.out.println("O n� inserido j� � existente");
				System.exit(1);
			}
//			node = new Node (id,modelo.get_n_DOF(), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			node.initNode(id, modelo.get_n_DOF(), Double.parseDouble(st.nextToken()),
					Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			modelo.addNode(node);// Como o array list n�o precisa especificar tamanho, aqui � s� ir adicionando
									// os n�s no array list de n�s do modelo
		} else {
			System.out.println("N�mero de par�metros iniciais para o n� incorreto");
			System.exit(1);
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista com v�rios materiais do modelo estrutural (sem
	 * retorno).
	 */
	private static void preencher_materiais(FileReader fr) {
		String linha = null;
		try {
//			FileUtilities.leLinha(fr);

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_MATERIALS").equals("")) {
				FileUtilities.leLinha(fr);
				linha = FileUtilities.leLinha(fr);
				while (!linha.startsWith("%END_MATERIALS")) {
					preencher_material(linha);
					linha = FileUtilities.leLinha(fr);
				}
			} else {
				System.out.println("Marcador \"%BEGIN_MATERIALS\" inexistente ou na posi��o incorreta.");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio os dados dos materiais existentes do modelo estrutural
	 * (sem retorno).
	 * 
	 * @param material elemento do tipo Material que ser� preenchido
	 * @param linha    linha do arquivo de dados que cont�m as informa��es do
	 *                 material
	 */
	private static void preencher_material(String linha) {
		StringTokenizer st = new StringTokenizer(linha);
		int nt = st.countTokens();
		String id = null;
		Material mat;

		if (nt == 3) {
			id = st.nextToken();
			if (modelo.procurar_Material(id)) {
				System.out.println("O material inserido j� � existente");
				System.exit(1);
			}
			mat = new Material(id, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			// Nesse caso eu n�o criei m�todo initMaterial, simplesmente usei o construtor
			// outra op��o seria ter setado um por um ap�s criar o material com construtor
			// sem argumento
			modelo.addMaterial(mat);
		} else {
			System.out.println("N�mero de par�metros iniciais para o material incorreto");
			System.exit(1);
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista com v�rias se��es transversais do modelo
	 * estrutural (sem retorno).
	 */
	private static void preencher_secoes(FileReader fr) {
		String linha = null;
		try {
//			FileUtilities.leLinha(fr);			

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_SECTIONS").equals("")) {
				FileUtilities.leLinha(fr);
				linha = FileUtilities.leLinha(fr);
				while (!linha.startsWith("%END_SECTIONS")) {
					preencher_secao(linha);
					linha = FileUtilities.leLinha(fr);
				}
			} else {
				System.out.println("Marcador \"%BEGIN_SECTIONS\" inexistente ou na posi��o incorreta.");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio os dados das se��es transversais existentes do modelo
	 * estrutural (sem retorno).
	 * 
	 * @param secao elemento do tipo SecaoTransversal que ser� preenchido
	 * @param linha linha do arquivo de dados que cont�m as informa��es da se��o
	 *              transversal
	 */
	private static void preencher_secao(String linha) {

		StringTokenizer st = new StringTokenizer(linha);
		String id = st.nextToken();
		String tipoSecao = st.nextToken();
		SecaoTransversal sec;

		if (modelo.procurar_Secao(id)) {
			System.out.println("A se��o inserida j� � existente");
			System.exit(1);
		}

		sec = SecaoTransversalFac.factory(id, tipoSecao, st);
		modelo.addSecao(sec);

	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista com v�rias barras do modelo estrutural (sem
	 * retorno).
	 */
	private static void preencher_barras(FileReader fr) {
		String linha = null;

		try {
//			linha = FileUtilities.leLinha(fr); 			                                   

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_BARS").equals("")) {
				FileUtilities.leLinha(fr); // l� a linha de titulo dos par�metros
				linha = FileUtilities.leLinha(fr);// j� l� os par�metros
				while (!linha.startsWith("%END_BARS")) {
					preencher_barra(linha);
					linha = FileUtilities.leLinha(fr);
				}
			} else {
				System.out.println("Marcador \"%BEGIN_BARS\" inexistente ou na posi��o incorreta.");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura de arquivo os dados das barras
	 * existentes do modelo estrutural (sem retorno).
	 * 
	 * @param barra elemento do tipo Node que ser� preenchido
	 */
	private static void preencher_barra(String linha) {
		StringTokenizer st = new StringTokenizer(linha);
		int nt = st.countTokens();
		String id = null, id_no_inicial, id_no_final, id_mat, id_sec;
		Barra barra = Barra.factory(modelo.get_tipoBarra());// usa a f�brica para criar o objeto adequado
															// At� mesmo pq classe abstrata n�o cria objeto
		if (nt == 5) { // polimorfismo?
			id = st.nextToken();
			// verificar se o nome informado j� foi utilizado
			if (modelo.procurar_Barra(id)) {
				System.out.println("A barra inserida j� � existente");// � aqui que aquele get d� problema
				System.exit(1); // quando tem mensagem
			}

			id_no_inicial = st.nextToken();
			// Verifica o n� inicial foi lan�ado
			if (modelo.procurar_Node(id_no_inicial)) {
				id_no_final = st.nextToken();
				// Verifica se o n� final foi lan�ado
				if (modelo.procurar_Node(id_no_final)) {
					// Verifica se o n� inicial � igual ao n� final
					if (id_no_inicial.equals(id_no_final)) {
						System.out.println("O n� inicial n�o pode ser igual ao n� final da barra.");
						System.exit(1);
					}
					// Sendo os n�s inicial e final diferentes continua o preenchimento da barra
					id_mat = st.nextToken();
					// Verifica se o material foi lan�ado
					if (modelo.procurar_Material(id_mat)) {
						id_sec = st.nextToken();
						// Verifica se a se��o transversal foi lan�ada
						if (modelo.procurar_Secao(id_sec)) {
							barra.initBarra(id, modelo.getNode(id_no_inicial), modelo.getNode(id_no_final),
									modelo.getMaterial(id_mat), modelo.getSecao(id_sec));
							modelo.addBarra(barra);
						}
					}
				}
			}
		} else {
			System.out.println("N�mero de tokens inconsistente para a barra");
			System.exit(1);
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista com as v�rias for�as nodais (sem retorno).
	 * 
	 * @param fr objeto do tipo FileReader
	 */
	private static void preencher_forcas(FileReader fr) {
		String linha = null;

		try {
//			linha=FileUtilities.leLinha(fr);	

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_NODAL_FORCES").equals("")) {
				FileUtilities.leLinha(fr);
				linha = FileUtilities.leLinha(fr);
				while (!linha.startsWith("%END_NODAL_FORCES")) {
					preencher_forca(linha);
					linha = FileUtilities.leLinha(fr);
				}

			} else {
				System.out.println("Marcador \"%BEGIN_NODAL_FORCES\" inexistente ou na posi��o incorreta.");
				System.exit(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio as for�as nodais.
	 * 
	 * @param linha linha do arquivo de dados que cont�m as informa��es do material
	 */
	private static void preencher_forca(String linha) {
		StringTokenizer st = new StringTokenizer(linha);
		int nt = st.countTokens();
		String id_node = null;

		if (nt == modelo.get_n_DOF() + 1) {
			id_node = st.nextToken();
			if (modelo.procurar_Node(id_node)) {
				for (int j = 0; j < modelo.get_n_DOF(); j++) {
					modelo.getNode(id_node).set_forca(Double.parseDouble(st.nextToken()), j);
				}
			} else {
				System.out.println("O id do n� informado n�o foi instanciado!");
				System.exit(1);
			}
		} else {
			System.out.println(" O n�mero de for�as n�o � coerente com o n�mero de graus de liberdade do modelo!");
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista de carregamentos pontuais presentes no modelo
	 * (sem retorno).
	 * 
	 * @param fr objeto do tipo FileReader
	 */
	private static void preencher_PointLoads(FileReader fr) {
		String linha = null;

		try {
//			linha=FileUtilities.leLinha(fr);
			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_POINT_LOADS").equals("")) {
				FileUtilities.leLinha(fr);// L� linha de subtitulo
				linha = FileUtilities.leLinha(fr);// l� os dados
				while (!linha.startsWith("%END_POINT_LOADS")) {
					preencher_PointLoad(linha);
					linha = FileUtilities.leLinha(fr);// l� a proxima linha com dados
				}

			} else {
				System.out.println("Marcador \"%BEGIN_POINT_LOADS\" inexistente ou na posi��o incorreta.");
				System.exit(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista de carregamentos pontuais.
	 * 
	 * @param linha linha do arquivo de dados que cont�m as informa��es do material
	 */
	private static PointLoad preencher_PointLoad(String linha) {
		StringTokenizer st = new StringTokenizer(linha);
		int nt = st.countTokens();
		String id_load;
		String id_barra;
		double position;
		double fx;
		double fy;
		double m;
		PointLoad load = null;

		if (nt == 6) {
			id_load = st.nextToken();
			id_barra = st.nextToken();
			position = Double.parseDouble(st.nextToken());
			fx = Double.parseDouble(st.nextToken());
			fy = Double.parseDouble(st.nextToken());
			m = Double.parseDouble(st.nextToken());
			load = new PointLoad(id_load, position, fx, fy, m);

			modelo.getBarra(id_barra).addPointLoad(load);

		} else {
			System.out.println("Erro em preencher_PointLoad - O n�mero de tokens n�o � coerente ");
			System.exit(1);
		}
		return load;
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista de carregamentos distribu�dos presentes no
	 * modelo (sem retorno).
	 * 
	 * @param fr objeto do tipo FileReader
	 */
	private static void preencher_DistributedLoads(FileReader fr) {
		String linha = null;

		try {
//			linha=FileUtilities.leLinha(fr);
			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_DISTRIBUTED_LOADS").equals("")) {
				FileUtilities.leLinha(fr);
				linha = FileUtilities.leLinha(fr);
				while (!linha.startsWith("%END_DISTRIBUTED_LOADS")) {
					preencher_DistributedLoad(linha);
					linha = FileUtilities.leLinha(fr);
				}

			} else {
				System.out.println("Marcador \"%BEGIN_DISTRIBUTED_LOADS\" inexistente ou na posi��o incorreta.");
				System.exit(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista de carregamentos distribu�dos.
	 * 
	 * @param linha linha do arquivo de dados que cont�m as informa��es do material
	 */
	private static DistributedLoad preencher_DistributedLoad(String linha) {

		StringTokenizer st = new StringTokenizer(linha);
		int nt = st.countTokens();
		String id_load;
		String id_barra;
		double inicial_position;
		double final_position;
		double q_inicial;
		double q_final;
		DistributedLoad load = null;

		if (nt == 6) {
			id_load = st.nextToken();
			id_barra = st.nextToken();
			inicial_position = Double.parseDouble(st.nextToken());
			final_position = Double.parseDouble(st.nextToken());
			q_inicial = Double.parseDouble(st.nextToken());
			q_final = Double.parseDouble(st.nextToken());

			load = new DistributedLoad(id_load, inicial_position, final_position, q_inicial, q_final);

			modelo.getBarra(id_barra).addDistributeLoad(load);

		} else {
			System.out.println("Erro em preencher_DistributedLoad - O n�mero de tokens n�o � coerente ");
			System.exit(1);
		}
		return load;
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista de deslocamentos nodais presentes no modelo
	 * (sem retorno).
	 * 
	 * @param fr objeto do tipo FileReader
	 */
	private static void preencher_deslocamentos(FileReader fr) {
		String linha = null;

		try {
//			linha=FileUtilities.leLinha(fr);
			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_DISPLACEMENTS").equals("")) {
				FileUtilities.leLinha(fr);// subtitulo
				linha = FileUtilities.leLinha(fr);// dados
				while (!linha.startsWith("%END_DISPLACEMENTS")) {
					preencher_deslocamento(linha);
					linha = FileUtilities.leLinha(fr);
				}
			} else {
				System.out.println("Marcador \"%BEGIN_DISPLACEMENTS\" inexistente ou na posi��o incorreta.");
				System.exit(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio os deslocamentos.
	 * 
	 * @param linha linha do arquivo de dados que cont�m as informa��es do material
	 */
	private static void preencher_deslocamento(String linha) {
		StringTokenizer st = new StringTokenizer(linha);
		int nt = st.countTokens();
		String id_node = null;

		if (nt == modelo.get_n_DOF() + 1) {
			id_node = st.nextToken();
			if (modelo.procurar_Node(id_node)) {
				for (int j = 0; j < modelo.get_n_DOF(); j++) {
					modelo.getNode(id_node).set_deslocamento(Double.parseDouble(st.nextToken()), j);
				}
			} else {
				System.out.println("O id do n� informado n�o foi instanciado!");
				System.exit(1);
			}

		} else {
			System.out
					.println(" O n�mero de deslocamentos n�o � coerente com o n�mero de graus de liberdade do modelo!");
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio a lista de restri��es nodais presentes no modelo (sem
	 * retorno).
	 * 
	 * @param fr objeto do tipo FileReader
	 */
	private static void preencher_restricoes(FileReader fr) {
		String linha = null;

		try {
//			linha=FileUtilities.leLinha(fr);
			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_RESTRAINTS").equals("")) {
				FileUtilities.leLinha(fr);
				linha = FileUtilities.leLinha(fr);
				while (!linha.startsWith("%END_RESTRAINTS")) {
					preencher_restricao(linha);
					linha = FileUtilities.leLinha(fr);
				}
			} else {
				System.out.println("Marcador \"%BEGIN_RESTRAINTS\" inexistente ou na posi��o incorreta.");
				System.exit(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usu�rio as restri��es nodais.
	 * 
	 * @param linha linha do arquivo de dados que cont�m as informa��es do material
	 */
	private static void preencher_restricao(String linha) {
		StringTokenizer st = new StringTokenizer(linha);
		int nt = st.countTokens();
		String id_node = null;

		if (nt == modelo.get_n_DOF() + 1) {
			id_node = st.nextToken();
			if (modelo.procurar_Node(id_node)) {
				for (int j = 0; j < modelo.get_n_DOF(); j++) {
					modelo.getNode(id_node).set_restricao(Boolean.parseBoolean(st.nextToken()), j);
				}
			} else {
				System.out.println("O id do n� informado n�o foi instanciado!");
				System.exit(1);
			}
		} else {
			System.out.println(" O n�mero de restri��es n�o � coerente com o n�mero de graus de liberdade do modelo!");
		}
	}

}