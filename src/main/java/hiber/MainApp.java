package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);


      CarService carService = context.getBean(CarService.class);

      carService.add(new Car("Mazda", 6));
      carService.add(new Car("Haval", 189));
      carService.add(new Car("Toyota", 29));
      carService.add(new Car("LADA", 2107));

      List<Car> cars = carService.listCar();

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Vova", "vova@mail.ru", cars.get(0)));
      userService.add(new User("User2", "Petia", "petia@mail.ru", cars.get(1)));
      userService.add(new User("User3", "Roma", "roma@mail.ru", cars.get(2)));
      userService.add(new User("User4", "Gleb", "gleb@mail.ru", cars.get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user + "\n");
      }

      for (Car car : cars) {
         System.out.println(car + "\n");
      }

      System.out.println(userService.userSearch("Haval", 189));

      context.close();
   }
}
