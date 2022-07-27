package modelo;

import algebraLinear.Vetor;

/**
 * Classe que descreve um n� de um modelo estrutural reticulado espacial
 * <p>
 * Dados:
 * <ul>
 * <li>id - identificador do n�
 * <li>cx - coordenada X do n�
 * <li>cy - coordenada Y do n�
 * <li>cz - coordenada Z do n�
 * <li>forcas - array de for�as nodais
 * <li>deslocamentos - array de deslocamentos nodais;
 * <li>restricoes - array de restricoes nodais;
 * <li>equacoes - array de equa��es nodais;
 * </ul>
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public class Node {

	/**
	 * Declara��o dos campos da classe.
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
	 * Construtor sem par�metro.
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
	 * coordenada y (cy) e coordenada z (cz) e preenche todos os campos do n�.
	 * 
	 * @param id    identificador do n�
	 * @param n_DOF n�mero de graus de liberdade
	 * @param cx    coordenada x do n�
	 * @param cy    coordenada y do n�
	 * @param cz    coordenada z do n�
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
	 * M�todo que recebe os par�metros como argumento e inicializa o n�.
	 * 
	 * @param id    identificador do n�
	 * @param n_DOF n�mero de graus de liberadade
	 * @param cx    coordenada x do n�
	 * @param cy    coordenada y do n�
	 * @param cz    coordenada z do n�
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
	 * M�todo de acesso Get que retorna o identificador do n� (id).
	 * 
	 * @return Identificador do n�
	 */
	public String get_id() {
		return id;
	}

	/**
	 * M�todo de acesso Get que retorna a coordenada X do n� (cx).
	 * 
	 * @return Coordenada X do n�
	 */
	public double get_cx() {
		return cx;
	}

	/**
	 * M�todo de acesso Get que retorna a coordenada Y do n� (cy).
	 * 
	 * @return Coordenada Y do n�
	 */
	public double get_cy() {
		return cy;
	}

	/**
	 * M�todo de acesso Get que retorna a coordenada Z do n� (cz).
	 * 
	 * @return Coordenada Z do n�
	 */
	public double get_cz() {
		return cz;
	}

	/**
	 * M�todo de acesso Get que retorna o array de for�as (forcas).
	 * 
	 * @return forcas
	 */
	public double[] get_forcas() {
		return forcas;
	}

	/**
	 * M�todo de acesso Get que retorna a for�a em uma posi��o espec�fica do array.
	 * 
	 * @param i posi��o espec�fica
	 * @return forcas valor da for�a na posi��o espec�fica
	 */
	public double get_forca(int i) {
		if ((i >= 0) && (i < this.forcas.length))
			return forcas[i];
		else {
			System.out.println("M�todo get incorreto: Tentativa de acesso em posi��o inexistente do vetor de for�as");
			System.exit(1);
			return 0.d;
		}
	}

	/**
	 * M�todo de acesso Get que retorna o array de deslocamentos (deslocamentos).
	 * 
	 * @return deslocamentos
	 */
	public double[] get_deslocamentos() {
		return deslocamentos;
	}

	/**
	 * M�todo de acesso Get que retorna um Vetor com o array de deslocamentos
	 * (deslocamentos). Foi feito com o objetivo de imprimir adequadamente os
	 * deslocamentos no documento de sa�da J� que o Vetor j� tem o to.String
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
	 * M�todo de acesso Get que retorna o deslocamento em uma posi��o espec�fica do
	 * array.
	 * 
	 * @param i posi��o especifica
	 * @return deslocamentos valor do deslocamento na posi��o espec�fica
	 */
	public double get_deslocamento(int i) {
		if ((i >= 0) && (i < this.deslocamentos.length))
			return deslocamentos[i];
		else {
			System.out.println(
					"M�todo get incorreto: Tentativa de acesso em posi��o inexistente do vetor de deslocamentos");
			System.exit(1);
			return 0.d;
		}
	}

	/**
	 * M�todo de acesso Get que retorna o array de restri��es (restricoes).
	 * 
	 * @return restricoes
	 */
	public boolean[] get_restricoes() {
		return restricoes;
	}

	/**
	 * M�todo de acesso Getque retorna a restri��o em uma posi��o espec�fica do
	 * array.
	 * 
	 * @param i posi��o espec�fica
	 * @return restricoes valor da restri��o na posi��o espec�fica
	 */
	public boolean get_restricao(int i) {
		if ((i >= 0) && (i < this.restricoes.length))
			return restricoes[i];
		else {
			System.out
					.println("M�todo get incorreto: Tentativa de acesso em posi��o inexistente do vetor de restri��es");
			System.exit(1);
			return false;
		}
	}

	/**
	 * M�todo de acesso Get que retorna o array de equa��es.
	 * 
	 * @return equacoes
	 */
	public int[] get_equacoes() {
		return equacoes;
	}

	/**
	 * M�todo de acesso Get que retorna a equa��o em uma posi��o espec�fica do
	 * array.
	 * 
	 * @param i posi��o espec�fica
	 * @return equacoes valor da equa��o na posi��o espec�fica
	 */
	public int get_equacao(int i) {
		if ((i >= 0) && (i < this.equacoes.length))
			return equacoes[i];
		else {
			System.out.println("M�todo get incorreto: Tentativa de acesso em posi��o inexistente do vetor de equa��es");
			System.exit(1);
			return 0;
		}
	}

	/**
	 * M�todo modificador Set do identificador do n� (id) (sem retorno).
	 * 
	 * @param id identificador do n�
	 */
	public void set_id(String id) {
		this.id = id;
	}

	/**
	 * M�todo modificador Set da coordenada X do n� (cx) (sem retorno).
	 * 
	 * @param cx coordenada X do n�
	 */
	public void set_cx(double cx) {
		this.cx = cx;
	}

	/**
	 * M�todo modificador Set da coordenada Y do n� (cy) (sem retorno).
	 * 
	 * @param cy coordenada Y do n�
	 */
	public void set_cy(double cy) {
		this.cy = cy;
	}

	/**
	 * M�todo modificador Set da coordenada Z do n� (cz) (sem retorno).
	 * 
	 * @param cz coordenada Z do n�
	 */
	public void set_cz(double cz) {
		this.cz = cz;
	}

	/**
	 * M�todo modificador Set da lista de for�as (sem retorno).
	 * 
	 * @param forcas valor da for�a
	 */
	public void set_forcas(double[] forcas) {
		if (this.forcas.length == forcas.length)
			this.forcas = forcas;
		else {
			System.out.println("M�todo set incorreto: Atribui��o de for�as inconsistente");
			System.exit(1);
		}
	}

	/**
	 * M�todo modificador Set de uma posi��o espec�fica da lista de for�as (sem
	 * retorno).
	 * 
	 * @param i     posi��o espec�fica
	 * @param forca for�a na posi��o espec�fica
	 */
	public void set_forca(double forca, int i) {
		if ((i >= 0) && (i < this.forcas.length))
			this.forcas[i] = forca;
		else {
			System.out.println(
					"M�todo set incorreto: Tentativa de modifica��o de posi��o inexistente do vetor de for�as");
			System.exit(1);

		}
	}

	/**
	 * M�todo modificador Set da lista de deslocamentos (sem retorno).
	 * 
	 * @param deslocamentos array de deslocamento
	 */
	public void set_deslocamentos(double[] deslocamentos) {
		if (this.deslocamentos.length == deslocamentos.length)
			this.deslocamentos = deslocamentos;
		else {
			System.out.println("M�todo set incorreto: Atribui��o de deslocamentos inconsistente");
			System.exit(1);
		}
	}

	/**
	 * M�todo modificador Set de uma posi��o espec�fica da lista de deslocamentos
	 * (sem retorno)
	 * 
	 * @param i            posi��o espec�fica
	 * @param deslocamento deslocamento na posi��o espec�fica
	 */
	public void set_deslocamento(double deslocamento, int i) {
		if ((i >= 0) && (i < this.deslocamentos.length))
			this.deslocamentos[i] = deslocamento;
		else {
			System.out.println(
					"M�todo set incorreto: Tentativa de modifica��o de posi��o inexistente do vetor de deslocamentos");
			System.exit(1);

		}
	}

	/**
	 * M�todo modificador Set da lista de restricoes (sem retorno).
	 * 
	 * @param restricoes valor da restri��o
	 */
	public void set_restricoes(boolean[] restricoes) {
		if (this.restricoes.length == restricoes.length)
			this.restricoes = restricoes;
		else {
			System.out.println("M�todo set incorreto: Atribui��o de restri��es inconsistente");
			System.exit(1);
		}
	}

	/**
	 * M�todo modificador Set de uma posi��o espec�fica da lista de restricoes (sem
	 * retorno).
	 * 
	 * @param i         posi��o espec�fica
	 * @param restricao valor da restri��o na pois��o espec�fica
	 */
	public void set_restricao(boolean restricao, int i) {
		if ((i >= 0) && (i < this.restricoes.length))
			this.restricoes[i] = restricao;
		else {
			System.out.println(
					"M�todo set incorreto: Tentativa de modifica��o de posi��o inexistente do vetor de restri��es");
			System.exit(1);
		}
	}

	/**
	 * M�todo modificador Set da lista de equacoes (sem retorno).
	 * 
	 * @param equacoes lista de equa��es
	 */
	public void set_equacoes(int[] equacoes) {
		if (this.equacoes.length == equacoes.length)
			this.equacoes = equacoes;
		else {
			System.out.println("M�todo set incorreto: Atribui��o de equa��es inconsistente");
			System.exit(1);
		}
	}

	/**
	 * M�todo modificador Set de uma posi��o espec�fica da lista de equacoes (sem
	 * retorno).
	 * 
	 * @param i       posi��o espec�fica
	 * @param equacao equa��o da lista a ser modificada
	 */
	public void set_equacao(int equacao, int i) {
		if ((i >= 0) && (i < this.equacoes.length))
			this.equacoes[i] = equacao;
		else {
			System.out.println(
					"M�todo set incorreto: Tentativa de modifica��o de posi��o inexistente do vetor de equa��es");
			System.exit(1);

		}
	}

	/**
	 * M�todo que retorna um texto contendo os dados do n�: identificador (id),
	 * coordenada X (cx), coordenada Y (cy) e coordenada Z (cz).
	 * 
	 * @return Texto contendo os dados do n�
	 */
	public String toString() {
		String texto;
		texto = "";
		texto += id;
		texto += "\n Coordenadas (x, y, z): (" + cx + ", " + cy + ", " + cz + ")";
		texto += "\n For�as nodais: ";
		for (int i = 0; i < this.forcas.length; i++) {
			texto += this.forcas[i] + "   ";
		}
		texto += "\n Deslocamentos nodais: ";
		for (int i = 0; i < this.deslocamentos.length; i++) {
			texto += this.deslocamentos[i] + "   ";
		}
		texto = texto.trim() + "\n Restri��es nodais: ";
		for (int i = 0; i < this.restricoes.length; i++) {
			texto += this.restricoes[i] + "   ";
		}
		texto += "\n Equa��es nodais: ";
		for (int i = 0; i < this.equacoes.length; i++) {
			texto += this.equacoes[i] + "   ";
		}
		return texto;
	}

}