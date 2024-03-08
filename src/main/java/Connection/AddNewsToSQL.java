package Connection;

import Dto.News;
import org.hibernate.Transaction;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AddNewsToSQL {

    public static void add(Update update){

        ConnectionToSQL.connection();
        Transaction transaction = null;

        if(update.getChannelPost().isChannelMessage()){

            try {
                transaction = ConnectionToSQL.connection().beginTransaction();
                News news = new News();

                news.setData(update.getChannelPost().getText());
                ConnectionToSQL.connection().save(news);

                transaction.commit();
            } catch (Exception e){

                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();

            }
        }

    }

}
