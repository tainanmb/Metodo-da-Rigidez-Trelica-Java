package structureLoad;

import algebraLinear.Vetor;

/**
 * Interface que caracteriza o carregamento equivalente de uma carga pontual de
 * uma barra. descreve os métodos necessários em classes cuja interface é
 * implementada. herdando os métodos da classe EquivalentLoad.
 * 
 * @author Tainan Brandão
 * @version 1.0
 */

public interface PointEquivalentLoad extends EquivalentLoad {

	/**
	 * Método abstrato de acesso Get que retorna as forças equivalentes nos nós
	 * iniciais e finais, oriundas de cargas concentradas aplicadas ao longo da
	 * barra.
	 * 
	 * @return EquivalentLoadFromP vetor com as forças equivalentes devido as cargas
	 *         P
	 */
	public abstract Vetor getEquivalentLoadFromP();

}
