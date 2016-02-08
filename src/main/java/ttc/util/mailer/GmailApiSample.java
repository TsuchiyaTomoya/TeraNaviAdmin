package ttc.util.mailer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.Gmail;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

// ...

public class GmailApiSample {

  /**GoogleのGmail APIのコメントをわかりやすく日本語にしました。
  * 基本的な使い方の流れとして、
  * あらかじめGoogle Consoleでアプリを登録してクライアントIDを取得する。
  * 取得したIDをダウンロードして、client_secret.jsonとしてresources下に配置
  */

  /** アプリ名（Google Consoleで登録したもの */
  private static final String APPLICATION_NAME = "TeraNavi";

  /** Directory to store user credentials for this application. */
  private static final java.io.File DATA_STORE_DIR = new java.io.File(
      System.getProperty("user.home"), ".credentials/gmail-java-quickstart");

  /** Global instance of the {@link FileDataStoreFactory}. */
  private static FileDataStoreFactory DATA_STORE_FACTORY;

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY =
      JacksonFactory.getDefaultInstance();

  /** Global instance of the HTTP transport. */
  private static HttpTransport HTTP_TRANSPORT;

  /** Global instance of the scopes required by this quickstart. */
  private static final List<String> SCOPES =
      Arrays.asList(GmailScopes.GMAIL_COMPOSE);

  static {
      try {
          HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
          DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
      } catch (Throwable t) {
          t.printStackTrace();
      }
  }

  /**
   * 認証情報オブジェクトを作成するメソッド.
   * @return an authorized Credential object.
   * @throws IOException
   */
  public static Credential authorize() throws IOException {
      // 発行されたIDを読み込む処理.
      InputStream in =
          GmailApiSample.class.getResourceAsStream("/client_secret.json");
      GoogleClientSecrets clientSecrets =
          GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

      // Build flow and trigger user authorization request.
      GoogleAuthorizationCodeFlow flow =
              new GoogleAuthorizationCodeFlow.Builder(
                      HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
              .setDataStoreFactory(DATA_STORE_FACTORY)
              .setAccessType("online")
              .build();
      Credential credential = new AuthorizationCodeInstalledApp(
          flow, new LocalServerReceiver()).authorize("user");
      System.out.println(
              "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
      return credential;
  }

  /**
   * 認証されたGmailオブジェクトを返す.
   * @return an authorized Gmail client service
   * @throws IOException
   */
  public static Gmail getGmailService() throws IOException {
      Credential credential = authorize();
      return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
              .setApplicationName(APPLICATION_NAME)
              .build();
  }

  /**
   * 引数で宛先や送信元などを設定して、MimeMessageのオブジェクトを作成するメソッド.
   *
   * @param to Email address of the receiver.
   * @param from Email address of the sender, the mailbox account.
   * @param subject Subject of the email.
   * @param bodyText Body text of the email.
   * @return MimeMessage to be used to send email.
   * @throws MessagingException
   */
  public static MimeMessage createEmail(String to, String from, String subject,
      String bodyText) throws MessagingException {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    MimeMessage email = new MimeMessage(session);
    InternetAddress tAddress = new InternetAddress(to);
    InternetAddress fAddress = new InternetAddress(from);

    email.setFrom(new InternetAddress(from));
    email.addRecipient(javax.mail.Message.RecipientType.TO,
                       new InternetAddress(to));
    email.setSubject(subject);
    email.setText(bodyText);
    return email;
  }

  /**
   * 上記メソッドで作成したオブジェクトをエンコードなどしてMessageオブジェクトにして返すメソッド
   *
   * @param email Email to be set to raw of message
   * @return Message containing base64url encoded email.
   * @throws IOException
   * @throws MessagingException
   */
  public static Message createMessageWithEmail(MimeMessage email)
      throws MessagingException, IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    email.writeTo(bytes);
    String encodedEmail = Base64.encodeBase64URLSafeString(bytes.toByteArray());
    Message message = new Message();
    message.setRaw(encodedEmail);
    return message;
  }


  /**
   * メールを送るメソッド
   *
   * @param service 認証済みのGmail APIのインスタンス
   * @param userId ユーザのメールアドレス。
   * @param email 送るEmailの本体のインスタンス
   * @throws MessagingException
   * @throws IOException
   */
  public static void sendMessage(Gmail service, String userId, MimeMessage email)
      throws MessagingException, IOException {
    Message message = createMessageWithEmail(email);
    message = service.users().messages().send(userId, message).execute();

    System.out.println("Message id: " + message.getId());
    System.out.println(message.toPrettyString());
  }


}
