package algebraLinear;

/**
 * Classe para definição, acesso, modificação e operações com vetores.
 * <p>
 * Dados:
 * <ul>
 * <li>n_Elementos - número de elementos do vetor (inteiro)
 * <li>vetor - array unidimensional de doubles que representa um vetor com
 * n_Elementos
 * </ul>
 * 
 * @author  Tainan Brandão
 * @version 1.0
 */

public class Vetor {

	/**
	 * Declaração dos campos da classe.
	 */
	private int n_Elementos;
	private double[] vetor;

	/**
	 * Construtor sem parâmetro.
	 */
	public Vetor() {
		this.n_Elementos = 0;
		vetor = null;
	}

	/**
	 * Construtor que recebe como argumento o número de elementos do vetor e assume
	 * todos os elementos do vetor iguais a zero.
	 * 
	 * @param n_Elementos número de elementos do vetor (inteiro)
	 */
	public Vetor(int n_Elementos) {
		this.n_Elementos = n_Elementos;
		vetor = new double[n_Elementos];
	}

	/**
	 * Construtor que recebe como argumento um array unidimensional que representa
	 * um vetor preenche todos os campos do vetor.
	 * 
	 * @param v vetor (array unidimensional de doubles)
	 */
	public Vetor(double[] v) {
		this.n_Elementos = v.length;
		vetor = new double[n_Elementos];
		for (int i = 0; i < n_Elementos; i++)
			vetor[i] = v[i];
	}

	/**
	 * Método de acesso Get que retorna o número de elementos do vetor (inteiro).
	 * 
	 * @return número de elemenos do vetor (inteiro)
	 */
	public int getN_Elementos() {
		return this.n_Elementos;
	}

	/**
	 * Método de acesso get que retorna um vetor.
	 * 
	 * @return vetor (array unidimensional de doubles)
	 */
	public double[] getVetor() {
		return this.vetor;
	}

	/**
	 * Método de acesso Get que retorna o valor do vetor em uma posição específica
	 * (i) do vetor e verifica se a posição fornecida está entre 0 e o tamanho do
	 * vetor.
	 * 
	 * @param i posição específica (inteiro)
	 * @return vetor [i] valor do vetor na posição específica (i) do vetor
	 */
	public double getVetor(int i) {
		if ((i >= 0) && (i < this.n_Elementos))
			return vetor[i];
		else {
			System.out.println("Tentativa de acesso em posição inexistente.");
			System.exit(1);
			return 0;
		}
	}

	/**
	 * Método modificador Set do número de elementos do vetor (n_Elementos) (sem
	 * retorno).
	 * 
	 * @param n_Elementos número de elementos do vetor
	 */
	public void setN_Elementos(int n_Elementos) {
		this.n_Elementos = n_Elementos;
	}

	/**
	 * Método modificador Set do vetor (sem retorno).
	 * 
	 * @param v vetor (array unidimensinal de doubles)
	 */
	public void setVetor(double[] v) {
		this.n_Elementos = v.length;
		this.vetor = new double[n_Elementos];
		for (int i = 0; i < n_Elementos; i++)
			vetor[i] = v[i];
	}

	/**
	 * Método modificador Set do vetor em uma posição específica (i) do vetor (sem
	 * retorno), verifica se a posição fornecida está entre 0 e o tamanho do vetor.
	 * 
	 * @param i     posição específica (inteiro)
	 * @param valor valor do vetor na posição específica (i)
	 */
	public void setVetor(int i, double valor) {
		if ((i >= 0) && (i < this.n_Elementos))
			this.vetor[i] = valor;
		else {
			System.out.println("Tentativa de acesso em posição inexistente.");
			System.exit(1);
		}
	}

	/**
	 * Método para adicionar um vetor fornecido ao vetor corrente atribuindo o valor
	 * obtido na soma ao vetor corrente.
	 * 
	 * @param v vetor
	 */
	public void addVetor(Vetor v) {
		if (this.n_Elementos != v.n_Elementos)
			System.out.println("Erro no método de adição de vetores: dimensões inconsistentes.");
		else {
			for (int i = 0; i < n_Elementos; i++)
//				vetor[i] += v.vetor[i]; //O v.vetor[i] nesse caso é o mesmo que chamar o get
				vetor[i] = vetor[i] + v.getVetor(i);
		}
	}

	/**
	 * Método para adicionar dois vetores fornecidos atribuindo o valor obtido na
	 * soma a um terceiro vetor.
	 * 
	 * @param v1 primeiro vetor fornecido como argumento
	 * @param v2 segundo vetor fornecido como argumento
	 */
	public void addVetor(Vetor v1, Vetor v2) {
		if (v1.n_Elementos != v2.n_Elementos)
			System.out.println("Erro no método de adição de vetores: dimensões inconsistentes.");
		else {
			vetor = new double[v1.n_Elementos];
			for (int i = 0; i < n_Elementos; i++)
				vetor[i] = +v1.vetor[i] + v2.vetor[i];
		}
	}

	/**
	 * Método multiplicar um vetor um por escalar
	 * 
	 * @param s escalar que será multiplicado em cada termo do vetor
	 */
	public void scale(double s) {
		for (int i = 0; i < n_Elementos; i++)
			vetor[i] = vetor[i] * s;
	}

	/**
	 * Método que retorna um texto contendo os elementos do vetor
	 * 
	 * @return Texto contendo os elementos do vetor
	 */
	public String toString() {
		String texto;
		texto = "  ";
		for (int i = 0; i < n_Elementos; i++)
			texto += String.format("%10.3E", vetor[i]) + "  ";
		return texto;
	}

}
