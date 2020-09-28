package br.com.adap.e2e.pages;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import br.com.adap.model.certificadoVerificadoModel;

public class TestarSsl {

	/**
	 * @author racarpa Método que valida o certificado de uma url
	 * @param string url a ser verificada
	 * @return Modelo de certificado valido
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 *
	 */

	public certificadoVerificadoModel validarCertificadoDaUrl(String urlBruta)
			throws IOException, NoSuchAlgorithmException, KeyManagementException {

		certificadoVerificadoModel certificadoValidado = new certificadoVerificadoModel();

		SSLContext sslCtx = SSLContext.getInstance("TLS");
		sslCtx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		SSLContext.setDefault(sslCtx);

		HttpsURLConnection conn = (HttpsURLConnection) new URL(urlBruta).openConnection();
		conn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});

		certificadoValidado.setStatusConexao(String.valueOf(conn.getResponseCode()));
//		trocar por logger
		System.out.println(conn.getResponseCode());
		Certificate[] certs = conn.getServerCertificates();
		for (Certificate cert : certs) {
//			trocar por logger
			certificadoValidado.setDataValidaMaxCertifcado(((java.security.cert.X509Certificate) cert).getNotAfter()
					.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

			System.out.println(((java.security.cert.X509Certificate) cert).getNotAfter());
		}

		conn.disconnect();
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

	public List<certificadoVerificadoModel> obterListaDeCertificadosValidados(List<String> listaDeUrls) {
		listaDeUrls.forEach(url -> {
			try {
				validarCertificadoDaUrl(url);
			} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		return null;
	}

}
