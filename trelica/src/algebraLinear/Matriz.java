package algebraLinear;

/**
 * Classe para defini??o, acesso, modifica??o e opera??es com matrizes.
 * <p>
 * Dados:
 * <ul>
 * <li>n_Linhas - N?mero de linhas da matriz
 * <li>n_Colunas - N?metro de colunas da matriz
 * <li>matriz - Array bidimensional que representa a matriz de tamanho n_Linhas
 * x n_Colunas
 * </ul>
 * 
 * @author Tainan Brand?o
 * @version 1.0
 */

public class Matriz {

	/**
	 * Declara??o dos campos da classe.
	 */
	private int n_Linhas, n_Colunas;
	private double[][] matriz;

	/**
	 * Construtor sem par?metro.
	 */
	public Matriz() {
		this.n_Linhas = 0;
		this.n_Colunas = 0;
		matriz = null;
	}

	/**
	 * Construtor que recebe como argumento o n?mero de linhas e de colunas da
	 * matriz e assume todos os elementos da matriz iguais a zero.
	 * 
	 * @param n_Linhas  n?mero de linhas da matriz
	 * @param n_Colunas n?mero de colunas da matriz
	 */
	public Matriz(int n_Linhas, int n_Colunas) {
		this.n_Linhas = n_Linhas;
		this.n_Colunas = n_Colunas;
		matriz = new double[n_Linhas][n_Colunas];
	}

	/**
	 * Construtor que recebe como argumento um array bidimensional que representa
	 * uma matriz e preenche todos os campos da matriz, verificando se todas as
	 * linhas da matriz tem o mesmo n?mero de colunas.
	 * 
	 * @param m matriz (array bidimensional de doubles)
	 */
	public Matriz(double[][] m) {
		// definindo o tamanho da matriz
		// o n?mero de colunas ? tomado como o tamanho da primeira linha
		this.n_Linhas = m.length;
		this.n_Colunas = m[0].length; // contando que todas as linhas tem o mesmo tamanho

		// verificar se todas as linhas tem o mesmo n?mero de colunas/mesmo tamanho da
		// primeira linha
		for (int i = 1; i < m.length; i++)
			if (m[i].length != n_Colunas) {
				System.out.println("Matriz com linhas de tamanhos diferentes.");
				System.exit(1);
			}

		// instanciando a matriz e preenchendo os valores
		matriz = new double[n_Linhas][n_Colunas];
		for (int i = 0; i < this.n_Linhas; i++)
			for (int j = 0; j < this.n_Colunas; j++)
				matriz[i][j] = m[i][j];
	}

	/**
	 * M?todo de acesso Get que retorna o n?mero de linhas da matriz (n_Linhas).
	 * 
	 * @return N?mero de linhas da matriz
	 */
	public int getN_Linhas() {
		return n_Linhas;
	}

	/**
	 * M?todo de acesso Get que retorna o n?mero de colunas da matriz (n_Colunas).
	 * 
	 * @return N?mero de colunas da matriz
	 */
	public int getN_Colunas() {
		return n_Colunas;
	}

	/**
	 * M?todo de acesso Get que retorna uma matriz.
	 * 
	 * @return matriz (array bidimensional de doubles)
	 */
	public double[][] getMatriz() {
		return matriz;
	}

	/**
	 * M?todo de acesso Get que retorna o valor da matriz em uma posi??o espec?fica
	 * (i, j) (linha, coluna) da matriz e verifica se a posi??o fornecida est?
	 * dentro do tamanho da matriz.
	 * 
	 * @param i posi??o i que indica a linha da matriz (inteiro)
	 * @param j posi??o j que indica a coluna da matriz (inteiro)
	 * @return valor da matriz na posi??o espec?fica (i,j) da matriz
	 */
	public double getMatriz(int i, int j) {
		if ((i < this.n_Linhas) && (i >= 0) && (j < this.n_Colunas) && (j >= 0))
			return matriz[i][j];
		else {
			System.out.println("Tentativa de acesso em posi??o inexistente.");
			System.exit(1);
			return 0;
		}
	}

	/**
	 * M?todo modificador Set do n?mero de linhas da matriz (n_Linhas) (sem
	 * retorno).
	 * 
	 * @param n_Linhas n?mero de linhas da matriz
	 */
	public void setN_Linhas(int n_Linhas) {
		this.n_Linhas = n_Linhas;
	}

	/**
	 * M?todo modificador Set do n?mero de colunas da matriz (n_Colunas) (sem
	 * retorno).
	 * 
	 * @param n_Colunas n?mero de colunas da matriz
	 */
	public void setN_Colunas(int n_Colunas) {
		this.n_Colunas = n_Colunas;
	}

	/**
	 * M?todo modificador Set das duas dimens?es da matriz (n_Linhas, n_Colunas)
	 * (sem retorno).
	 * 
	 * @param n_Linhas  n?mero de linhas da matriz
	 * @param n_Colunas n?mero de linhas da matriz
	 */
	public void setDimensao(int n_Linhas, int n_Colunas) {
		this.n_Linhas = n_Linhas;
		this.n_Colunas = n_Colunas;
	}

	/**
	 * M?todo modificador Set da matriz (sem retorno).
	 * 
	 * @param m matriz (array bidimensional de doubles)
	 */
	public void setMatriz(double[][] m) {
		this.n_Linhas = m.length;
		this.n_Colunas = m[0].length;
		this.matriz = new double[n_Linhas][n_Colunas];
		for (int i = 0; i < this.n_Linhas; i++)
			for (int j = 0; j < this.n_Colunas; j++)
				matriz[i][j] = m[i][j];
	}

	/**
	 * M?todo modificador Set da matriz em uma posi??o espec?fica (i, j) da matriz
	 * (sem retorno), verifica se a posi??o fornecida est? dentro da matriz.
	 * 
	 * @param i     posi??o i que indica a linha da matriz (inteiro)
	 * @param j     posi??o j que indica a coluna da matriz (inteiro)
	 * @param valor valor da matriz na posi??o espec?fica (i,j) da matriz
	 */
	public void setMatriz(int i, int j, double valor) {
		if ((i < this.n_Linhas) && (i >= 0) && (j < this.n_Colunas) && (j >= 0))
			this.matriz[i][j] = valor;
		else {
			System.out.println("Tentativa de acesso em posi??o inexistente.");
		}
	}

	/**
	 * M?todo para adicionar uma matriz fornecido a matriz corrente atribuindo o
	 * valor obtido na soma a matriz corrente.
	 * 
	 * @param m matriz
	 */
	public void add(Matriz m) {
		if ((this.n_Linhas != m.n_Linhas) || (this.n_Colunas != m.n_Colunas))
			System.out.println("Erro no m?todo de adi??o de matrizes: dimens?es inconsistentes.");
		else {
			for (int i = 0; i < n_Linhas; i++)
				for (int j = 0; j < n_Colunas; j++)
					matriz[i][j] += m.matriz[i][j];
		}
	}

	/**
	 * M?todo para adicionar duas matrizes fornecidas atribuindo o valor obtido na
	 * soma a uma terceiro matriz.
	 * 
	 * @param m1 primeira matriz fornecida como argumento
	 * @param m2 segunda matriz fornecida como argumento
	 */
	public void add(Matriz m1, Matriz m2) {
		if ((m1.n_Linhas == m2.n_Linhas) && (m1.n_Colunas == m2.n_Colunas)) {
			matriz = new double[m1.n_Linhas][m1.n_Colunas];
			for (int i = 0; i < n_Linhas; i++)
				for (int j = 0; j < n_Colunas; j++)
					matriz[i][j] = m1.matriz[i][j] + m2.matriz[i][j];
		} else {
			System.out.println("Erro no m?todo de adi??o de matrizes: dimens?es inconsistentes.");
		}
	}

	/**
	 * M?todo multiplicar uma matriz um por escalar
	 * 
	 * @param s escalar que ser? multiplicado por cada termo da matriz
	 * @return mul vetor resultante
	 */
	public Matriz scale(double s) {
//	    Matriz mul = new Matriz (this.getN_Linhas(),this.getN_Linhas())	;
		double[][] aux = new double[this.getN_Linhas()][this.getN_Linhas()];
		for (int i = 0; i < this.getN_Linhas(); i++) {
			for (int j = 0; j < this.getN_Colunas(); j++) {
				aux[i][j] = this.matriz[i][j] * s;
//	    		mul.setMatriz(i, j, this.getMatriz(i, j)*s);
			}
		}
		Matriz mul = new Matriz(aux);
		return mul;
	}

	/**
	 * M?todo multiplicar uma matriz corrente por um vetor dado como par?metro
	 * 
	 * @param v vetor fornecido como par?metro
	 * @return resultante matriz resultante da multiplica??o de uma matriz corrente
	 *         pelo vetor par?metro
	 */
	public Vetor mulVetor(Vetor v) {
		double[] res = new double[this.n_Linhas];
		Vetor resultante = new Vetor(this.n_Linhas);
		if (this.getN_Colunas() == v.getN_Elementos()) {
			for (int i = 0; i < n_Linhas; i++) {
				for (int n = 0; n < v.getN_Elementos(); n++) {
					res[i] += matriz[i][n] * v.getVetor(n);
//					resultante.setVetor(i, resultante.getVetor(i)+(this.getMatriz(i, n)*v.getVetor(n)));
				}
			}

		} else {
			System.out.println("O n?mero de colunas da matriz n?o coincide com o n?mero de linhas do vetor");
			System.exit(1);
		}

		resultante.setVetor(res);
		return resultante;
	}

	/**
	 * M?todo multiplicar uma matriz corrente por um vetor ambos dados como
	 * par?metro
	 * 
	 * @param v vetor fornecido como par?metro
	 * @param m matriz fornecida como par?metro
	 * @return resultante matriz resultante da multiplica??o de uma matriz par?metro
	 *         por um vetor par?metro
	 */
	public static Vetor mulVetor(Matriz m, Vetor v) {
		double[] res = new double[m.n_Linhas];
		Vetor resultante = new Vetor(m.n_Linhas);
		if (m.getN_Colunas() == v.getN_Elementos()) {
			for (int i = 0; i < m.n_Linhas; i++) {
				for (int n = 0; n < v.getN_Elementos(); n++) {
					res[i] += m.getMatriz(i, n) * v.getVetor(n);
				}
			}

		} else {
			System.exit(1);
			System.out.println("O n?mero de colunas da matriz n?o coincide com o n?mero de linhas do vetor");
		}
		resultante.setVetor(res);
		return resultante;
	}

	/**
	 * M?todo transpor uma matriz corrente (sem retorno)
	 */
	public void transposta() {

		double[][] aux;
		aux = new double[n_Colunas][n_Linhas];
		for (int i = 0; i < n_Linhas; i++) {
			for (int j = 0; j < n_Colunas; j++) {
				aux[i][j] = matriz[j][i];

			}
		}
		setMatriz(aux);

	}

	/**
	 * M?todo est?tico para transpor uma matriz dada como argumento
	 * 
	 * @param m matriz fornecida como par?metro
	 * @return transposta matriz transposta de uma matriz dada como argumento
	 */
	public static Matriz transposta(Matriz m) {
		Matriz transposta = new Matriz(m.n_Colunas, m.n_Linhas);
		for (int i = 0; i < m.n_Linhas; i++) {
			for (int j = 0; j < m.n_Colunas; j++) {
				transposta.setMatriz(i, j, m.getMatriz(j, i));
			}
		}
		return transposta;
	}

	/**
	 * M?todo multiplicar a matriz corrente por outra matriz dada como par?metro
	 * 
	 * @param m matriz fornecida como par?metro
	 * @return mul matriz resultante da multiplica??o de uma matriz corrente por uma
	 *         matriz par?metro
	 */
	public Matriz mulMatriz(Matriz m) {
		double[][] aux = new double[n_Linhas][m.n_Colunas];
		Matriz mul = new Matriz(n_Linhas, m.n_Colunas);
		if (n_Colunas == m.n_Linhas) {
			for (int i = 0; i < n_Linhas; i++) {
				for (int j = 0; j < m.n_Colunas; j++) {
					for (int k = 0; k < m.n_Linhas; k++) {
						aux[i][j] += matriz[i][k] * m.getMatriz(k, j);
					}
				}
			}
		} else {
			System.out.println(
					"O n?mero de colunas da matriz corrente n?o coincide com o n?mero de linhas da matriz dada como argumento");
			System.exit(1);
		}
		mul.setMatriz(aux);
		return mul;
	}

	/**
	 * M?todo multiplicar duas matrizes dadas como par?metro e retornar a matriz
	 * resultante
	 * 
	 * @param n priemira matriz fornecida como par?metro
	 * @param m segunda matriz fornecida como par?metro
	 * @return mul matriz resultante da multiplica??o de duas matrizes par?mtro
	 */
	public static Matriz mulMatriz(Matriz n, Matriz m) {
		double[][] aux = new double[n.n_Linhas][m.n_Colunas];
		Matriz mul = new Matriz(n.n_Linhas, m.n_Colunas);
		if (n.n_Colunas == m.n_Linhas) {
			for (int i = 0; i < n.n_Linhas; i++) {
				for (int j = 0; j < m.n_Colunas; j++) {
					for (int k = 0; k < m.n_Linhas; k++) {
						aux[i][j] += n.getMatriz(i, k) * m.getMatriz(k, j);
					}
				}
			}

		} else {
			System.exit(1);
			System.out.println(
					"O n?mero de colunas da matriz corrente n?o coincide com o n?mero de linhas da matriz dada como argumento");
		}
		mul.setMatriz(aux);
		return mul;
	}

	/**
	 * M?todo que retorna um texto contendo os elementos da matriz
	 * 
	 * @return Texto contendo os elementos da matriz
	 */
	public String toString() {
		String texto = " \n [";
		for (int i = 0; i < n_Linhas; i++) {
			for (int j = 0; j < n_Colunas; j++) {
				texto = texto + " " + String.format("%10.3E", matriz[i][j]) + " ";
			}
			texto = texto + "\n";
		}
		texto = texto.trim() + "]";
		return texto;
	}

}
