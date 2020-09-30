package br.com.adap.e2e.pages;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.time.ZoneId;
import java.util.List;

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

	public certificadoVerificadoModel validarCertificadoDaUrl(certificadoVerificadoModel certificadoValidado)
			throws IOException, NoSuchAlgorithmException, KeyManagementException {

		Certificate[] certs = certificadoValidado.getHttpsURLConnection().getServerCertificates();
		for (Certificate cert : certs) {
//			trocar por logger
			certificadoValidado.setDataValidaMaxCertifcado(((java.security.cert.X509Certificate) cert).getNotAfter()
					.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

			System.out.println(((java.security.cert.X509Certificate) cert).getNotAfter());
		}

		certificadoValidado.getHttpsURLConnection().disconnect();
		return certificadoValidado;
	}

	public List<certificadoVerificadoModel> obterListaDeCertificadosValidados(
			List<certificadoVerificadoModel> listaDeCertificados) {
		listaDeCertificados.forEach(certificado -> {
			try {
				validarCertificadoDaUrl(certificado);
			} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		return null;
	}
	
//	import java.security.cert.X509Certificate;
//	import com.nimbusds.jose.jwk.RSAKey;
//	import com.nimbusds.jose.util.X509CertUtils;
//
//	// Parse X.509 certificate
//	X509Certificate cert = X509CertUtils.parse(encodedCert);
//
//	// Retrieve public key as RSA JWK
//	RSAKey rsaJWK = RSAKey.parse(cert);

}
