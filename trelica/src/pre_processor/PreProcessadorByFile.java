package pre_processor;

import java.io.FileReader;
import java.util.StringTokenizer;
import modelo.*;
import util.*;
import structureLoad.*;

/**
 * Classe que efetua o pré processamento de dados de um modelo estrutural
 * reticulado espacial, preenchendo o modelo a partir da leitura de dados via
 * arquivo.
 * <p>
 * 
 * @author Tainan Brandão
 * @version 1.0
 */

public class PreProcessadorByFile {

	/**
	 * Declaração dos campos da classe.
	 */
	static private Modelo modelo = null;

	/**
	 * Conjunto de métodos que gera o modelo estrutural: inicializar o modelo
	 * estrutural e preencher os campos dos nós, materiais, seções e barras,
	 * conforme leitura realizada no arquivo de dados. Caso algum dado necessário
	 * para preenchimento do modelo não seja informado será relatado ao usuário o
	 * erro ocorrido.
	 * 
	 * @return modelo com os dados preenchidos
	 * @param fileName nome do arquivo que contém as informações do modelo
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
	 * Método para inicializar e preencher por meio da leitura dos dados contidos no
	 * arquivo indicado pelo usuário o número de nós (n_nodes), número de materiais
	 * (n_materiais), número de seções transversais (n_secoes), número de barras
	 * (n_barras), tipo das barras (tipo_barra) e título do modelo estrutural.
	 * 
	 * @return modelo com os dados preenchidos
	 */
	private static void inicializar_modelo(FileReader fr) { // É provado pq não deve ser acessado fora da classe e é
															// static pq o inicializar modelo é static

		String titulo = null, linha = null, tipoBarra = null;
		StringTokenizer st;

		try {
			titulo = FileUtilities.leLinha(fr);

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_INITIAL_DATA").equals("")) {
				FileUtilities.leLinha(fr); // Lê a linha com palavra type
				linha = FileUtilities.leLinha(fr); // Lê o tipo de barra

				while (!linha.startsWith("%END_INITIAL_DATA")) { // enquanto a linha não for %END_INITIAL_DATA
					st = new StringTokenizer(linha);// Cria os tokens da linha, poderia ser separado por \ ao invés de
													// espaço, por exemplo
					int nt = st.countTokens(); // Conta o número de tokens da linha
					if (nt == 1) {// nesse caso deve ser um só com o tipo de barra do modelo
						tipoBarra = st.nextToken();
						modelo = new Modelo(titulo, tipoBarra);
						linha = FileUtilities.leLinha(fr);// Essa linha já será %END_INITIAL_DATA
					} // então o loop acaba
					else {
						System.out.println("Número de parâmetros iniciais incorreto");
						System.exit(1);
					}
				}
			} else {
				System.out.println("Marcador \"%BEGIN_INITIAL_DATA\" inexistente ou na posição incorreta.");
				System.exit(1);
			}

		} catch (Exception e) {
			String msg = "Erro na entrada de dados!!\n" + e.getMessage();
			System.out.println(msg);
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista com vários nós do modelo estrutural (sem
	 * retorno).
	 */
	private static void preencher_nodes(FileReader fr) {
		String linha = null;

		try {
//			FileUtilities.leLinha(fr); 			

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_NODES").equals("")) {
				FileUtilities.leLinha(fr); // essa linha possui o cabeçalho: Nodes, cx, cy, cz
				linha = FileUtilities.leLinha(fr);// Nessa já estão os dados referentes aos nós
				while (!linha.startsWith("%END_NODES")) {// Enquanto a linha não iniciar com %END_NODES
					preencher_node(linha); // Esse .startsWith é um método do próprio java
					linha = FileUtilities.leLinha(fr);
				}
			} else {
				System.out.println("Marcador \"%BEGIN_NODES\" inexistente ou na posição incorreta.");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário os dados dos nós existentes do modelo estrutural (sem
	 * retorno).
	 * 
	 * @param node  elemento do tipo Node que será preenchido
	 * @param linha linha do arquivo de dados que contém as informações do nó
	 */
	private static void preencher_node(String linha) {
		StringTokenizer st = new StringTokenizer(linha); // linha com parametros lida no método anterior
		int nt = st.countTokens();
		String id = null;
		Node node = new Node();
//		Node node;
		if (nt == 4) {
			id = st.nextToken();
			if (modelo.procurar_Node(id)) {
				System.out.println("O nó inserido já é existente");
				System.exit(1);
			}
//			node = new Node (id,modelo.get_n_DOF(), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			node.initNode(id, modelo.get_n_DOF(), Double.parseDouble(st.nextToken()),
					Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			modelo.addNode(node);// Como o array list não precisa especificar tamanho, aqui é só ir adicionando
									// os nós no array list de nós do modelo
		} else {
			System.out.println("Número de parâmetros iniciais para o nó incorreto");
			System.exit(1);
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista com vários materiais do modelo estrutural (sem
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
				System.out.println("Marcador \"%BEGIN_MATERIALS\" inexistente ou na posição incorreta.");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário os dados dos materiais existentes do modelo estrutural
	 * (sem retorno).
	 * 
	 * @param material elemento do tipo Material que será preenchido
	 * @param linha    linha do arquivo de dados que contém as informações do
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
				System.out.println("O material inserido já é existente");
				System.exit(1);
			}
			mat = new Material(id, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			// Nesse caso eu não criei método initMaterial, simplesmente usei o construtor
			// outra opção seria ter setado um por um após criar o material com construtor
			// sem argumento
			modelo.addMaterial(mat);
		} else {
			System.out.println("Número de parâmetros iniciais para o material incorreto");
			System.exit(1);
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista com várias seções transversais do modelo
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
				System.out.println("Marcador \"%BEGIN_SECTIONS\" inexistente ou na posição incorreta.");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário os dados das seções transversais existentes do modelo
	 * estrutural (sem retorno).
	 * 
	 * @param secao elemento do tipo SecaoTransversal que será preenchido
	 * @param linha linha do arquivo de dados que contém as informações da seção
	 *              transversal
	 */
	private static void preencher_secao(String linha) {

		StringTokenizer st = new StringTokenizer(linha);
		String id = st.nextToken();
		String tipoSecao = st.nextToken();
		SecaoTransversal sec;

		if (modelo.procurar_Secao(id)) {
			System.out.println("A seção inserida já é existente");
			System.exit(1);
		}

		sec = SecaoTransversalFac.factory(id, tipoSecao, st);
		modelo.addSecao(sec);

	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista com várias barras do modelo estrutural (sem
	 * retorno).
	 */
	private static void preencher_barras(FileReader fr) {
		String linha = null;

		try {
//			linha = FileUtilities.leLinha(fr); 			                                   

			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_BARS").equals("")) {
				FileUtilities.leLinha(fr); // lê a linha de titulo dos parâmetros
				linha = FileUtilities.leLinha(fr);// já lê os parâmetros
				while (!linha.startsWith("%END_BARS")) {
					preencher_barra(linha);
					linha = FileUtilities.leLinha(fr);
				}
			} else {
				System.out.println("Marcador \"%BEGIN_BARS\" inexistente ou na posição incorreta.");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura de arquivo os dados das barras
	 * existentes do modelo estrutural (sem retorno).
	 * 
	 * @param barra elemento do tipo Node que será preenchido
	 */
	private static void preencher_barra(String linha) {
		StringTokenizer st = new StringTokenizer(linha);
		int nt = st.countTokens();
		String id = null, id_no_inicial, id_no_final, id_mat, id_sec;
		Barra barra = Barra.factory(modelo.get_tipoBarra());// usa a fábrica para criar o objeto adequado
															// Até mesmo pq classe abstrata não cria objeto
		if (nt == 5) { // polimorfismo?
			id = st.nextToken();
			// verificar se o nome informado já foi utilizado
			if (modelo.procurar_Barra(id)) {
				System.out.println("A barra inserida já é existente");// É aqui que aquele get dá problema
				System.exit(1); // quando tem mensagem
			}

			id_no_inicial = st.nextToken();
			// Verifica o nó inicial foi lançado
			if (modelo.procurar_Node(id_no_inicial)) {
				id_no_final = st.nextToken();
				// Verifica se o nó final foi lançado
				if (modelo.procurar_Node(id_no_final)) {
					// Verifica se o nó inicial é igual ao nó final
					if (id_no_inicial.equals(id_no_final)) {
						System.out.println("O nó inicial não pode ser igual ao nó final da barra.");
						System.exit(1);
					}
					// Sendo os nós inicial e final diferentes continua o preenchimento da barra
					id_mat = st.nextToken();
					// Verifica se o material foi lançado
					if (modelo.procurar_Material(id_mat)) {
						id_sec = st.nextToken();
						// Verifica se a seção transversal foi lançada
						if (modelo.procurar_Secao(id_sec)) {
							barra.initBarra(id, modelo.getNode(id_no_inicial), modelo.getNode(id_no_final),
									modelo.getMaterial(id_mat), modelo.getSecao(id_sec));
							modelo.addBarra(barra);
						}
					}
				}
			}
		} else {
			System.out.println("Número de tokens inconsistente para a barra");
			System.exit(1);
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista com as várias forças nodais (sem retorno).
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
				System.out.println("Marcador \"%BEGIN_NODAL_FORCES\" inexistente ou na posição incorreta.");
				System.exit(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário as forças nodais.
	 * 
	 * @param linha linha do arquivo de dados que contém as informações do material
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
				System.out.println("O id do nó informado não foi instanciado!");
				System.exit(1);
			}
		} else {
			System.out.println(" O número de forças não é coerente com o número de graus de liberdade do modelo!");
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista de carregamentos pontuais presentes no modelo
	 * (sem retorno).
	 * 
	 * @param fr objeto do tipo FileReader
	 */
	private static void preencher_PointLoads(FileReader fr) {
		String linha = null;

		try {
//			linha=FileUtilities.leLinha(fr);
			if (!FileUtilities.findLineStartingWithString(fr, "%BEGIN_POINT_LOADS").equals("")) {
				FileUtilities.leLinha(fr);// Lê linha de subtitulo
				linha = FileUtilities.leLinha(fr);// lê os dados
				while (!linha.startsWith("%END_POINT_LOADS")) {
					preencher_PointLoad(linha);
					linha = FileUtilities.leLinha(fr);// lê a proxima linha com dados
				}

			} else {
				System.out.println("Marcador \"%BEGIN_POINT_LOADS\" inexistente ou na posição incorreta.");
				System.exit(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista de carregamentos pontuais.
	 * 
	 * @param linha linha do arquivo de dados que contém as informações do material
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
			System.out.println("Erro em preencher_PointLoad - O número de tokens não é coerente ");
			System.exit(1);
		}
		return load;
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista de carregamentos distribuídos presentes no
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
				System.out.println("Marcador \"%BEGIN_DISTRIBUTED_LOADS\" inexistente ou na posição incorreta.");
				System.exit(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista de carregamentos distribuídos.
	 * 
	 * @param linha linha do arquivo de dados que contém as informações do material
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
			System.out.println("Erro em preencher_DistributedLoad - O número de tokens não é coerente ");
			System.exit(1);
		}
		return load;
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista de deslocamentos nodais presentes no modelo
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
				System.out.println("Marcador \"%BEGIN_DISPLACEMENTS\" inexistente ou na posição incorreta.");
				System.exit(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário os deslocamentos.
	 * 
	 * @param linha linha do arquivo de dados que contém as informações do material
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
				System.out.println("O id do nó informado não foi instanciado!");
				System.exit(1);
			}

		} else {
			System.out
					.println(" O número de deslocamentos não é coerente com o número de graus de liberdade do modelo!");
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário a lista de restrições nodais presentes no modelo (sem
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
				System.out.println("Marcador \"%BEGIN_RESTRAINTS\" inexistente ou na posição incorreta.");
				System.exit(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para preencher por meio da leitura dos dados contidos no arquivo
	 * indicado pelo usuário as restrições nodais.
	 * 
	 * @param linha linha do arquivo de dados que contém as informações do material
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
				System.out.println("O id do nó informado não foi instanciado!");
				System.exit(1);
			}
		} else {
			System.out.println(" O número de restrições não é coerente com o número de graus de liberdade do modelo!");
		}
	}

}