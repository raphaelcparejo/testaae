package br.com.adap.model;

import java.time.LocalDateTime;
import java.time.Period;

import javax.net.ssl.HttpsURLConnection;

import static java.time.temporal.ChronoUnit.DAYS;;

//alt shift j

public class certificadoVerificadoModel {
	private String url;
	private String mensagem;
	private String statusConexao;
	private LocalDateTime dataValidaMaxCertifcado;
	private LocalDateTime dataParaExpirarCertifcado;
	private HttpsURLConnection httpsURLConnection;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDataValidaMaxCertifcado() {
		return dataValidaMaxCertifcado;
	}

	public void setDataValidaMaxCertifcado(LocalDateTime dataValidaMaxCertifcado) {
		this.dataValidaMaxCertifcado = dataValidaMaxCertifcado;
	}

	public String getStatusConexao() {
		return statusConexao;
	}

	public void setStatusConexao(String statusConexao) {
		this.statusConexao = statusConexao;
	}

	public LocalDateTime getDataParaExpirarCertifcado() {
		return dataParaExpirarCertifcado;
	}

	public void setDataParaExpirarCertifcado(LocalDateTime dataParaExpirarCertifcado) {
		this.dataParaExpirarCertifcado = dataParaExpirarCertifcado;
	}

	public HttpsURLConnection getHttpsURLConnection() {
		return httpsURLConnection;
	}

	public void setHttpsURLConnection(HttpsURLConnection httpsURLConnection) {
		this.httpsURLConnection = httpsURLConnection;
	}

	/**
	 * @racarpa https://www.oracle.com/technical-resources/articles/java/jf14-date-time.html
	 *          <p>
	 *          Verifica a quantidade de dias entre o dia de hoje e o dia que o
	 *          certificado expira.
	 * 
	 *          <pre>
	 *          LocalDate diaDeHj = LocalDateTime.of(2012, 6, 29, 12, 00);
	 *          LocalDate diaQueOCertificadoExpira = LocalDateTime.of(2012, 7, 30, 12, 00);
	 *          getDiasParaExpirarCertificado = 1;
	 *          </pre>
	 *          <p>
	 */
	public long getDiasParaExpirarCertificado() {
		return DAYS.between(LocalDateTime.now(), dataValidaMaxCertifcado);
	}

	/**
	 * @racarpa
	 *          <p>
	 *          Verifica se esta data e hora nesse instante é anterior à data e hora
	 *          que o certificado expira.
	 * 
	 *          <pre>
	 *   LocalDate a = LocalDateTime.of(2012, 6, 30, 12, 00);
	 *   LocalDate b = LocalDateTime.of(2012, 7, 1, 12, 00);
	 *   a.isStatusCertificado(b) == true
	 *   a.isStatusCertificado(a) == false
	 *   b.isStatusCertificado(a) == false
	 *          </pre>
	 *          <p>
	 */
	public boolean isStatusCertificado() {
		return LocalDateTime.now().isBefore(dataParaExpirarCertifcado);
	}

	public certificadoVerificadoModel(String url, String mensagem, String statusConexao,
			LocalDateTime dataValidaMaxCertifcado, LocalDateTime dataParaExpirarCertifcado,
			HttpsURLConnection httpsURLConnection) {
		super();
		this.url = url;
		this.mensagem = mensagem;
		this.statusConexao = statusConexao;
		this.dataValidaMaxCertifcado = dataValidaMaxCertifcado;
		this.dataParaExpirarCertifcado = dataParaExpirarCertifcado;
		this.httpsURLConnection = httpsURLConnection;
	}

	public certificadoVerificadoModel() {

	}

}
