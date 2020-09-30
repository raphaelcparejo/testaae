package br.com.adap.e2e.pages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import br.com.adap.model.certificadoVerificadoModel;

public class TestarConexao {
	/**
	 * @author racarpa Método que valida a conexao de uma url
	 * @param string url a ser verificada
	 * @return Modelo de certificado valido com status do teste
	 *
	 */

	public certificadoVerificadoModel validarConexao(String urlBruta)
			throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException, IOException {
		certificadoVerificadoModel certificadoValidado = new certificadoVerificadoModel();

		SSLContext sslCtx = SSLContext.getInstance("TLS");
		sslCtx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		SSLContext.setDefault(sslCtx);

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(urlBruta).openConnection();
		httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		certificadoValidado.setHttpsURLConnection(httpsURLConnection);

		certificadoValidado.setStatusConexao(String.valueOf(httpsURLConnection.getResponseCode()));
//		trocar por logger
		System.out.println(httpsURLConnection.getResponseCode());

		return certificadoValidado;

	}

	private static class DefaultTrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

}
