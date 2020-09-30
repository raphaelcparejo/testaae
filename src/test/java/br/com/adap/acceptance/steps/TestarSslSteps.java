package br.com.adap.acceptance.steps;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.adap.e2e.pages.Browser;
import br.com.adap.e2e.pages.TestarConexao;
import br.com.adap.e2e.pages.TestarSsl;
import br.com.adap.model.certificadoVerificadoModel;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class TestarSslSteps {

	private TestarSsl testarSsl;
	private TestarConexao testarConexao;
	private certificadoVerificadoModel conexaoModel;

	@Dado("uma url {string}")
	public void uma_lista_de_urls(String urlBruta) {

		this.testarConexao = new TestarConexao();
		this.testarSsl = new TestarSsl();

	}

	@Quando("a conexao retorna HTTPstatus ok para cada {string}")
	public void a_conexao_retorna_httpstatus_para_cada(String urlBruta)
			throws KeyManagementException, NoSuchAlgorithmException, MalformedURLException, IOException {
		this.conexaoModel = this.testarConexao.validarConexao(urlBruta);

	}

	@Entao("testo o certificado SSL para cada {string} e ele deve ter pelo menos {int} dia restante")
	public void testo_o_certificado_ssl_para_cada_e_ele_deve_ter_pelo_menos_dia_restante(String string, Integer int1) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		certificadoVerificadoModel validadah = this.testarSsl.validarCertificadoDaUrl(this.conexaoModel);
		long diasParaExpirar = validadah.getDiasParaExpirarCertificado();
		int i=1;
	}

}
