package ttc.command;

import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import ttc.bean.UserBean;
import ttc.context.RequestContext;
import ttc.context.ResponseContext;
import ttc.dao.AbstractDao;
import ttc.exception.BusinessLogicException;
import ttc.util.MySqlConnectionManager;
import ttc.util.factory.AbstractDaoFactory;
import ttc.util.mailer.GmailApiSample;

import com.google.api.services.gmail.Gmail;


public class GmailSampleCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc) throws BusinessLogicException{
            RequestContext reqc = getRequestContext();

            // String toUserId = reqc.getParameter("toUserId")[0];

            Map params = new HashMap();
            params.put("value","1");
            params.put("where","WHERE user_id = ?");

            try {
                    MySqlConnectionManager.getInstance().beginTransaction();

                    AbstractDaoFactory factory = AbstractDaoFactory.getFactory("users");
                    AbstractDao dao = factory.getAbstractDao();
                    UserBean ub = (UserBean)dao.read(params);

                    MySqlConnectionManager.getInstance().closeConnection();

                    //  ひとまず決め打ち。テストデータのアドレスが無効なので。
                    String toAddress = "j5fca7pm@gmail.com";
                    // ub.getMailAddress();

                    String fromAddress = "ha14304001@ga.tera-house.ac.jp";
                    //  送るメールのタイトル
                    String subject = "テストだよ";
                    // 本文
                    String bodyText = "とどけ！";

                    //  utilにいれたGmailApiSampleクラスのメソッドを使ってメールを送る処理
                    // 認証されたGmailオブジェクトの取得。
                    Gmail service = GmailApiSample.getGmailService();

                    // 宛先と送信元アドレスのセット、タイトルと本文のセット
                    MimeMessage email = GmailApiSample.createEmail(toAddress, fromAddress, subject, bodyText);

                    // メールの送信
                    GmailApiSample.sendMessage(service, fromAddress, email);

                    resc.setResult("メール送ったー");
                    resc.setTarget("index");
        } catch (Exception e) {
            throw new BusinessLogicException(e.getMessage(),e);
        }
        return resc;
    }
}
