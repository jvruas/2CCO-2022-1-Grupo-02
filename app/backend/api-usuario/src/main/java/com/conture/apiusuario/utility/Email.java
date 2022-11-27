package com.conture.apiusuario.utility;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class Email {
	public String gerarCodigo(){
		Integer codigo = ThreadLocalRandom.current().nextInt(111111, 999999);
		return String.format("C-%s", codigo);
	}

	public String gerarEmail(String codigo) throws FileNotFoundException {

		StringBuilder html = new StringBuilder();
		String result = "";

		FileReader fr = new FileReader(
				"email.html");

		// Try block to check exceptions
		try {
			BufferedReader br = new BufferedReader(fr);

			String val;
			while ((val = br.readLine()) != null) {
				html.append(val);
			}

			result = html.toString();
			//String codigo = ThreadLocalRandom.current().nextInt(111111, 999999);
			result = result.replaceAll("var_codigo_ath", codigo);
			br.close();
			return result;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}


	public void sendEmail(String result, String destinatario){
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication("conture.cco@gmail.com", "ehjfkcxxbigxdcww");
					}
				});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("conture.cco@gmail.com"));
			//Remetente

			Address[] toUser = InternetAddress //Destinatário(s)
					.parse(destinatario);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Código de verificação");//Assunto
			message.setContent(result,"text/html");;
			/**Método para enviar a mensagem criada*/
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
