package com.a105.apiServer.service;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender emailSender;

	public static String ePw = createKey();

	public MimeMessage createMessage(String to) throws Exception {
//		System.out.println("보내는 대상 : " + to);
//		System.out.println("인증 번호 : " + ePw);
//		ePw = createKey();
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper msgHelper = new MimeMessageHelper(message, true, "UTF-8"); //이미지 첨부용


		message.addRecipients(RecipientType.TO, to);// 보내는 대상
		message.setSubject("『Game World』 인증번호가 도착했습니다.");// 제목

		String msgg = "";
		msgg += "<div style='margin:100px;'>";
		msgg += "<h1> 안녕하세요  [Game World]입니다. </h1>";
//		msgg += "<br><img src=\"/static/bisBrown.png\">";
		msgg += "<br>";
		msgg += "<p>아래 코드를 인증창으로 돌아가 입력해주세요<p>";
		msgg += "<br>";
		msgg += "<p>감사합니다!<p>";
		msgg += "<br>";
		msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg += "<h3 style='color:blue;'>이메일인증 코드입니다.</h3>";
		msgg += "<div style='font-size:130%'>";
		msgg += "CODE : <strong>";
		msgg += ePw + "</strong><div><br/> ";
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");// 내용
		//message.setFrom(new InternetAddress("properties에 작성한 이메일", "A407"));// 보내는 사람

		return message;
	}

	
//		인증코드 만들기
	public static String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = rnd.nextInt(3); // 0~2 까지 랜덤

			switch (index) {
			case 0:
				key.append((char) ((int) (rnd.nextInt(26)) + 97));
				// a~z (ex. 1+97=98 => (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (rnd.nextInt(26)) + 65));
				// A~Z
				break;
			case 2:
				key.append((rnd.nextInt(10)));
				// 0~9
				break;
			}
		}

		return key.toString();
	}

	@Override
	public void sendSimpleMessage(String to) throws Exception {
		// TODO Auto-generated method stub
		MimeMessage message = createMessage(to);
//		try {// 예외처리
			emailSender.send(message);
//		} catch (MailException es) {
//			es.printStackTrace();
//			throw new IllegalArgumentException();
//		}
	}


	@Override
	public void testKey() throws Exception {
		ePw = createKey();
		System.out.println(ePw);
	}

}