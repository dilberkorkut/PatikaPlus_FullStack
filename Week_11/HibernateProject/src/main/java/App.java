import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {

    //Entitiy manager yok. Session yapisi var.

        //Session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class) // yaptigimiz entitiyleri tanimlamamiz gerekiyor.calistirdiginda veri tabaninda customer tablosu olusacak.
                .buildSessionFactory();

        Session session = factory.openSession();

        //CREATE - READ - UPDATE - DELETE (CRUD)

        session.beginTransaction(); // transaction basladi

         try{

            Customer customer = new Customer();
//
//            customer.setName("Hibernate Test");
//            customer.setGender(Customer.GENDER.FEMALE);
//            customer.setOnDate(LocalDate.now());
//            customer.setEmail("test@patika.dev");
//
//            session.persist(customer);




//            customer = session.get(Customer.class, 1);
////            System.out.println(customer.toString());
//            customer.setName("Degisti");
//            session.persist(customer);
//
//            session.remove(customer);

//             customer = session.byId(Customer.class).getReference(2);
  //           System.out.println(customer.toString());

             //hql olusturmakl icin
             // veri tabanindaki customerlari listeleyen bir yapi olusturmak istiyorum
             List<Customer> customers = session
                     .createSelectionQuery("FROM Customer", Customer.class)
                     .getResultList();
            // System.out.println(customers.toString());

             // veri tabanindan tek bir veri getiormek icin ise
             Customer customer1 = session
                     .createSelectionQuery("FROM Customer WHERE id = 2", Customer.class)
                     .getSingleResult();
            // System.out.println(customer1.toString());

             // olmayan bir id istendiginde
             Customer customer2 = session
                     .createSelectionQuery("FROM Customer WHERE id = 35", Customer.class)
                     .getSingleResultOrNull();
           //  System.out.println(customer2.toString());

            session.getTransaction().commit();
        }catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }
}


