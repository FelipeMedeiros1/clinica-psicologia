package psi.semeando_vinculos.api.domain;

public class ValidacaoException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

	public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
