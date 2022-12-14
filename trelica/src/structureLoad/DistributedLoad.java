package structureLoad;

/**
 * Classe que descreve um carregamento distribu?do em uma barra.
 * <p>
 * Dados:
 * <ul>
 * <li>id - Identificador
 * <li>pos_i - Posi??o do carregamento em rela??o ao n? inicial
 * <li>pos_f - Posi??o do carregamento em rela??o ao n? final
 * <li>qi - Intensidade inicial do carregamento distribu?do
 * <li>qf - Intensidade final do carregamento distribu?do
 * </ul>
 * 
 * @author Tainan Brand?o
 * @version 1.0
 */

public class DistributedLoad {

	/**
	 * Declara??o dos campos da classe.
	 */
	private String id;
	private double pos_i;
	private double pos_f;
	private double qi;
	private double qf;

	/**
	 * Construtor sem par?metro.
	 */
	public DistributedLoad() {
		this.id = null;
		this.pos_i = 0;
		this.pos_f = 0;
		this.qi = 0;
		this.qf = 0;
	}

	/**
	 * Construtor que recebe argumentos e preenche todos os campos da carga
	 * distribu?da.
	 * 
	 * @param id    - Identificador
	 * @param pos_i posi??o do carregamento em rela??o ao n? inicial
	 * @param pos_f posi??o do carregamento em rela??o ao n? final
	 * @param qi    intensidade inicial do carregamento distribu?do
	 * @param qf    intensidade inicial do carregamento distribu?do
	 */
	public DistributedLoad(String id, double pos_i, double pos_f, double qi, double qf) {
		this.id = id;
		this.pos_i = pos_i;
		this.pos_f = pos_f;
		this.qi = qi;
		this.qf = qf;
	}

	/**
	 * M?todo de acesso Get que retorna o identificador.
	 * 
	 * @return Identificador
	 */
	public String get_id() {
		return this.id;
	}

	/**
	 * M?todo de acesso Get que retorna a posi??o inicial do carregamento em rela??o
	 * ao n? inicial.
	 * 
	 * @return Posi??o inicial do carregamento em rela??o ao n? inicial
	 */
	public double get_InicialPosition() {
		return this.pos_i;
	}

	/**
	 * M?todo de acesso Get que retorna a posi??o final do carregamento em rela??o
	 * ao n? inicial.
	 * 
	 * @return Posi??o final do carregamento em rela??o ao n? inicial
	 */
	public double get_FinalPosition() {
		return this.pos_f;
	}

	/**
	 * M?todo de acesso Get que retorna a intensidade inicial do carregamento
	 * distribu?do.
	 * 
	 * @return Intensidade inicial do carregamento distribu?do
	 */
	public double get_InicalLoad() {
		return this.qi;
	}

	/**
	 * M?todo de acesso Get que retorna a intensidade final do carregamento
	 * distribu?do.
	 * 
	 * @return Intensidade final do carregamento distribu?do
	 */
	public double get_FinalLoad() {
		return this.qf;
	}

	/**
	 * M?todo modificador Set do identificador (sem retorno).
	 * 
	 * @param id identificador
	 */
	public void set_id(String id) {
		this.id = id;
	}

	/**
	 * M?todo modificador Set da posi??o inicial da carga (sem retorno).
	 * 
	 * @param inicialPosition posi??o inicial da carga
	 */
	public void set_InicialPosition(double inicialPosition) {
		this.pos_i = inicialPosition;
	}

	/**
	 * M?todo modificador Set da posi??o inicial da carga (sem retorno).
	 * 
	 * @param finalPosition posi??o inicial da carga
	 */
	public void set_FinalPosition(double finalPosition) {
		this.pos_f = finalPosition;
	}

	/**
	 * M?todo modificador Set intensidade inicial do carregamento distribu?do (sem
	 * retorno).
	 * 
	 * @param inicialLoad intensidade inicial do carregamento distribu?do
	 */
	public void set_InicialLoad(double inicialLoad) {
		this.qi = inicialLoad;
	}

	/**
	 * M?todo modificador Set intensidade final do carregamento distribu?do (sem
	 * retorno).
	 * 
	 * @param finalLoad intensidade final do carregamento distribu?do
	 */
	public void set_FinalLoad(double finalLoad) {
		this.qf = finalLoad;
	}

	/**
	 * M?todo que retorna um texto contendo os dados da carga pontual: identificador
	 * (id), posi??o inicial e do carregamento (pos_i e pos_f), intensidade inicial
	 * e final do carregamento (qi e qf) intensidade das for?as na dire??o do eixo x
	 * e y (fx e fy) e intensidade do momento fletor em torno do eizo z (m).
	 * 
	 * @return Texto contendo os dados da carga pontual
	 */
	public String toString() {
		String texto;
		texto = "\n ";
		texto += id;
		texto += "\n Posi??o inicial-Posi??o final: (" + pos_i + " , " + pos_f + ")";
		texto += "\n Carga inicial-Carga final: (" + qi + " , " + qf + ")";
		return texto;
	}
}
