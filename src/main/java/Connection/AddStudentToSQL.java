package Connection;

import Dto.Student;
import org.hibernate.Transaction;

public class AddStudentToSQL {

    public static void add(int year, String type, String tgId) {

        ConnectionToUsersSQL.connection();

        Transaction transaction = null;

        try {

            transaction = ConnectionToUsersSQL.connection().beginTransaction();

            Student registration = new Student();
            registration.setYear(year);
            registration.setClassType(type);
            registration.setTgUsername(tgId);

            ConnectionToUsersSQL.connection().save(registration);

            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
