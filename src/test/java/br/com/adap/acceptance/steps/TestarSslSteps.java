package br.com.adap.acceptance.steps;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import br.com.adap.e2e.pages.Browser;
import br.com.adap.e2e.pages.TestarSsl;
import br.com.adap.model.certificadoVerificadoModel;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class TestarSslSteps {

//	private Browser browser;
//	@Dado("uma url")
//	public void uma_url() {
//		browser = new Browser();
//		browser.seed();		
//	}
	private TestarSsl testarSsl;
	@Dado("uma url")
	public void uma_url() throws KeyManagementException, NoSuchAlgorithmException, IOException {
		testarSsl = new TestarSsl();
		certificadoVerificadoModel resultado = testarSsl.validarCertificadoDaUrl("https://github.com");
	}

	@Quando("acessa a url")
	public void acessa_a_url() {
		
		
	}

	@Entao("testa o certificado")
	public void testa_o_certificado() {
	
		
	}

}
