package modelo;

import algebraLinear.Vetor;

/**
 * Classe que descreve um nó de um modelo estrutural reticulado espacial
 * <p>
 * Dados:
 * <ul>
 * <li>id - identificador do nó
 * <li>cx - coordenada X do nó
 * <li>cy - coordenada Y do nó
 * <li>cz - coordenada Z do nó
 * <li>forcas - array de forças nodais
 * <li>deslocamentos - array de deslocamentos nodais;
 * <li>restricoes - array de restricoes nodais;
 * <li>equacoes - array de equações nodais;
 * </ul>
 * 
 * @author Tainan Brandão
 * @version 1.0
 */

public class Node {

	/**
	 * Declaração dos campos da classe.
	 */
	private String id;
	private double cx;
	private double cy;
	private double cz;
	private double[] forcas;
	private double[] deslocamentos;
	private boolean[] restricoes;
	private int[] equacoes;

	/**
	 * Construtor sem parâmetro.
	 */
	public Node() {
		this.id = null;
		this.cx = 0.0f;
		this.cy = 0.0f;
		this.cz = 0.0f;
		this.forcas = null;
		this.deslocamentos = null;
		this.restricoes = null;
		this.equacoes = null;
	}

	/**
	 * Construtor que recebe como argumento o identificador (id), coordenada x (cx),
	 * coordenada y (cy) e coordenada z (cz) e preenche todos os campos do nó.
	 * 
	 * @param id    identificador do nó
	 * @param n_DOF número de graus de liberdade
	 * @param cx    coordenada x do nó
	 * @param cy    coordenada y do nó
	 * @param cz    coordenada z do nó
	 */
	public Node(String id, int n_DOF, double cx, double cy, double cz) {
		this.id = id;
		this.cx = cx;
		this.cy = cy;
		this.cz = cz;
		this.forcas = new double[n_DOF];
		this.deslocamentos = new double[n_DOF];
		this.restricoes = new boolean[n_DOF];
		this.equacoes = new int[n_DOF];
	}

	/**
	 * Método que recebe os parâmetros como argumento e inicializa o nó.
	 * 
	 * @param id    identificador do nó
	 * @param n_DOF número de graus de liberadade
	 * @param cx    coordenada x do nó
	 * @param cy    coordenada y do nó
	 * @param cz    coordenada z do nó
	 */
	public void initNode(String id, int n_DOF, double cx, double cy, double cz) {
		this.id = id;
		this.cx = cx;
		this.cy = cy;
		this.cz = cz;
		this.forcas = new double[n_DOF];
		this.deslocamentos = new double[n_DOF];
		this.restricoes = new boolean[n_DOF];
		this.equacoes = new int[n_DOF];
	}

	/**
	 * Método de acesso Get que retorna o identificador do nó (id).
	 * 
	 * @return Identificador do nó
	 */
	public String get_id() {
		return id;
	}

	/**
	 * Método de acesso Get que retorna a coordenada X do nó (cx).
	 * 
	 * @return Coordenada X do nó
	 */
	public double get_cx() {
		return cx;
	}

	/**
	 * Método de acesso Get que retorna a coordenada Y do nó (cy).
	 * 
	 * @return Coordenada Y do nó
	 */
	public double get_cy() {
		return cy;
	}

	/**
	 * Método de acesso Get que retorna a coordenada Z do nó (cz).
	 * 
	 * @return Coordenada Z do nó
	 */
	public double get_cz() {
		return cz;
	}

	/**
	 * Método de acesso Get que retorna o array de forças (forcas).
	 * 
	 * @return forcas
	 */
	public double[] get_forcas() {
		return forcas;
	}

	/**
	 * Método de acesso Get que retorna a força em uma posição específica do array.
	 * 
	 * @param i posição específica
	 * @return forcas valor da força na posição específica
	 */
	public double get_forca(int i) {
		if ((i >= 0) && (i < this.forcas.length))
			return forcas[i];
		else {
			System.out.println("Método get incorreto: Tentativa de acesso em posição inexistente do vetor de forças");
			System.exit(1);
			return 0.d;
		}
	}

	/**
	 * Método de acesso Get que retorna o array de deslocamentos (deslocamentos).
	 * 
	 * @return deslocamentos
	 */
	public double[] get_deslocamentos() {
		return deslocamentos;
	}

	/**
	 * Método de acesso Get que retorna um Vetor com o array de deslocamentos
	 * (deslocamentos). Foi feito com o objetivo de imprimir adequadamente os
	 * deslocamentos no documento de saída Já que o Vetor já tem o to.String
	 * 
	 * @return deslocamentos_vec
	 */
	public Vetor get_deslocamentos_vec() {
		Vetor deslocamentos_vec = new Vetor(this.deslocamentos.length);
		for (int i = 0; i < this.deslocamentos.length; i++) {
			deslocamentos_vec.setVetor(i, this.get_deslocamento(i));
		}
		return deslocamentos_vec;
	}

	/**
	 * Método de acesso Get que retorna o deslocamento em uma posição específica do
	 * array.
	 * 
	 * @param i posição especifica
	 * @return deslocamentos valor do deslocamento na posição específica
	 */
	public double get_deslocamento(int i) {
		if ((i >= 0) && (i < this.deslocamentos.length))
			return deslocamentos[i];
		else {
			System.out.println(
					"Método get incorreto: Tentativa de acesso em posição inexistente do vetor de deslocamentos");
			System.exit(1);
			return 0.d;
		}
	}

	/**
	 * Método de acesso Get que retorna o array de restrições (restricoes).
	 * 
	 * @return restricoes
	 */
	public boolean[] get_restricoes() {
		return restricoes;
	}

	/**
	 * Método de acesso Getque retorna a restrição em uma posição específica do
	 * array.
	 * 
	 * @param i posição específica
	 * @return restricoes valor da restrição na posição específica
	 */
	public boolean get_restricao(int i) {
		if ((i >= 0) && (i < this.restricoes.length))
			return restricoes[i];
		else {
			System.out
					.println("Método get incorreto: Tentativa de acesso em posição inexistente do vetor de restrições");
			System.exit(1);
			return false;
		}
	}

	/**
	 * Método de acesso Get que retorna o array de equações.
	 * 
	 * @return equacoes
	 */
	public int[] get_equacoes() {
		return equacoes;
	}

	/**
	 * Método de acesso Get que retorna a equação em uma posição específica do
	 * array.
	 * 
	 * @param i posição específica
	 * @return equacoes valor da equação na posição específica
	 */
	public int get_equacao(int i) {
		if ((i >= 0) && (i < this.equacoes.length))
			return equacoes[i];
		else {
			System.out.println("Método get incorreto: Tentativa de acesso em posição inexistente do vetor de equações");
			System.exit(1);
			return 0;
		}
	}

	/**
	 * Método modificador Set do identificador do nó (id) (sem retorno).
	 * 
	 * @param id identificador do nó
	 */
	public void set_id(String id) {
		this.id = id;
	}

	/**
	 * Método modificador Set da coordenada X do nó (cx) (sem retorno).
	 * 
	 * @param cx coordenada X do nó
	 */
	public void set_cx(double cx) {
		this.cx = cx;
	}

	/**
	 * Método modificador Set da coordenada Y do nó (cy) (sem retorno).
	 * 
	 * @param cy coordenada Y do nó
	 */
	public void set_cy(double cy) {
		this.cy = cy;
	}

	/**
	 * Método modificador Set da coordenada Z do nó (cz) (sem retorno).
	 * 
	 * @param cz coordenada Z do nó
	 */
	public void set_cz(double cz) {
		this.cz = cz;
	}

	/**
	 * Método modificador Set da lista de forças (sem retorno).
	 * 
	 * @param forcas valor da força
	 */
	public void set_forcas(double[] forcas) {
		if (this.forcas.length == forcas.length)
			this.forcas = forcas;
		else {
			System.out.println("Método set incorreto: Atribuição de forças inconsistente");
			System.exit(1);
		}
	}

	/**
	 * Método modificador Set de uma posição específica da lista de forças (sem
	 * retorno).
	 * 
	 * @param i     posição específica
	 * @param forca força na posição específica
	 */
	public void set_forca(double forca, int i) {
		if ((i >= 0) && (i < this.forcas.length))
			this.forcas[i] = forca;
		else {
			System.out.println(
					"Método set incorreto: Tentativa de modificação de posição inexistente do vetor de forças");
			System.exit(1);

		}
	}

	/**
	 * Método modificador Set da lista de deslocamentos (sem retorno).
	 * 
	 * @param deslocamentos array de deslocamento
	 */
	public void set_deslocamentos(double[] deslocamentos) {
		if (this.deslocamentos.length == deslocamentos.length)
			this.deslocamentos = deslocamentos;
		else {
			System.out.println("Método set incorreto: Atribuição de deslocamentos inconsistente");
			System.exit(1);
		}
	}

	/**
	 * Método modificador Set de uma posição específica da lista de deslocamentos
	 * (sem retorno)
	 * 
	 * @param i            posição específica
	 * @param deslocamento deslocamento na posição específica
	 */
	public void set_deslocamento(double deslocamento, int i) {
		if ((i >= 0) && (i < this.deslocamentos.length))
			this.deslocamentos[i] = deslocamento;
		else {
			System.out.println(
					"Método set incorreto: Tentativa de modificação de posição inexistente do vetor de deslocamentos");
			System.exit(1);

		}
	}

	/**
	 * Método modificador Set da lista de restricoes (sem retorno).
	 * 
	 * @param restricoes valor da restrição
	 */
	public void set_restricoes(boolean[] restricoes) {
		if (this.restricoes.length == restricoes.length)
			this.restricoes = restricoes;
		else {
			System.out.println("Método set incorreto: Atribuição de restrições inconsistente");
			System.exit(1);
		}
	}

	/**
	 * Método modificador Set de uma posição específica da lista de restricoes (sem
	 * retorno).
	 * 
	 * @param i         posição específica
	 * @param restricao valor da restrição na poisção específica
	 */
	public void set_restricao(boolean restricao, int i) {
		if ((i >= 0) && (i < this.restricoes.length))
			this.restricoes[i] = restricao;
		else {
			System.out.println(
					"Método set incorreto: Tentativa de modificação de posição inexistente do vetor de restrições");
			System.exit(1);
		}
	}

	/**
	 * Método modificador Set da lista de equacoes (sem retorno).
	 * 
	 * @param equacoes lista de equações
	 */
	public void set_equacoes(int[] equacoes) {
		if (this.equacoes.length == equacoes.length)
			this.equacoes = equacoes;
		else {
			System.out.println("Método set incorreto: Atribuição de equações inconsistente");
			System.exit(1);
		}
	}

	/**
	 * Método modificador Set de uma posição específica da lista de equacoes (sem
	 * retorno).
	 * 
	 * @param i       posição específica
	 * @param equacao equação da lista a ser modificada
	 */
	public void set_equacao(int equacao, int i) {
		if ((i >= 0) && (i < this.equacoes.length))
			this.equacoes[i] = equacao;
		else {
			System.out.println(
					"Método set incorreto: Tentativa de modificação de posição inexistente do vetor de equações");
			System.exit(1);

		}
	}

	/**
	 * Método que retorna um texto contendo os dados do nó: identificador (id),
	 * coordenada X (cx), coordenada Y (cy) e coordenada Z (cz).
	 * 
	 * @return Texto contendo os dados do nó
	 */
	public String toString() {
		String texto;
		texto = "";
		texto += id;
		texto += "\n Coordenadas (x, y, z): (" + cx + ", " + cy + ", " + cz + ")";
		texto += "\n Forças nodais: ";
		for (int i = 0; i < this.forcas.length; i++) {
			texto += this.forcas[i] + "   ";
		}
		texto += "\n Deslocamentos nodais: ";
		for (int i = 0; i < this.deslocamentos.length; i++) {
			texto += this.deslocamentos[i] + "   ";
		}
		texto = texto.trim() + "\n Restrições nodais: ";
		for (int i = 0; i < this.restricoes.length; i++) {
			texto += this.restricoes[i] + "   ";
		}
		texto += "\n Equações nodais: ";
		for (int i = 0; i < this.equacoes.length; i++) {
			texto += this.equacoes[i] + "   ";
		}
		return texto;
	}

}