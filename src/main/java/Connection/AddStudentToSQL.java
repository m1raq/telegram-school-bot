package Connection;

import UsersData.Student;
import org.hibernate.Transaction;

public class AddStudentToSQL {

    public static void add(int year, String type, String tgId) {

        ConnectionToSQL.connection();

        Transaction transaction = null;

        try {

            transaction = ConnectionToSQL.connection().beginTransaction();

            Student registration = new Student();
            registration.setYear(year);
            registration.setClassType(type);
            registration.setTgUsername(tgId);

            ConnectionToSQL.connection().save(registration);

            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
