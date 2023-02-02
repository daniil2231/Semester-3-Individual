package fontys.pls.work;

import fontys.pls.work.controller.PositionsController;
import fontys.pls.work.controller.TickerPriceController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan({"fontys.pls.work.persistence.Entity"})
@ComponentScan("fontys.pls.work.domain")
@ComponentScan(basePackageClasses = PositionsController.class)
@ComponentScan(basePackageClasses = TickerPriceController.class)
public class plsworkApp {
    public static void main(String[] args) {
        SpringApplication.run(plsworkApp.class, args);
    }
}