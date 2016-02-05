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

public class GmailSampleCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resc) throws BusinessLogicException{
            RequestContext reqc = getRequestContext();

            String toUserId = reqc.getParameter("toUserId")[0];

            Map params = new HashMap();
            params.put("toUserId",toUserId);
            params.put("where","user_id = ?");

            try {
                    MySqlConnectionManager.getInstance().beginTransaction();

                    AbstractDaoFactory factory = AbstractDaoFactory.getFactory("user");
                    AbstractDao dao = factory.getAbstractDao();
                    UserBean ub = (UserBean)dao.read(params);

                    MySqlConnectionManager.getInstance().closeConnection();

                    String toAddress = ub.getMailAddress();
                    //  ひとまず決め打ち
                    String fromAddress = "ha14304001@ga.tera-house.ac.jp";
                    //  送るメールのタイトル
                    String subject = "テストだよ";
                    // 本文
                    String bodyText = "とどけ！";
                    
                    //  utilにいれたGmailApiSampleクラスのメソッドを使ってメールを送る処理
                    // 宛先と送信元アドレスのセット、タイトルと本文のセット
                    MimeMessage message = GmailApiSample.createEmail(toAddress, fromAddress, subject, bodyText);
                    
                    // 
                        

                    

        } catch (Exception e) {
            throw new BusinessLogicException(e.getMessage(),e);
        }
    }
}