import java.util.Optional;

public class TestHibernateUtil {

    public static void main(String[] args) {

        String someText = "IJSE";

        Optional<String> optional = Optional.of(someText);

        System.out.println(optional.isPresent());

    }

}
