package data_provider;

import dto.Car;
import org.testng.annotations.DataProvider;
import utils.Fuel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.RandomUtils.generateString;

public class CarDP {

    @DataProvider
    public Car[] addNewCarDP() {
        Car car1 = Car.builder()
                .city("Tel Aviv")
                .manufacture("")
                .model("Corolla")
                .year("2019")
                .fuel(Fuel.HYBRID.getValue())
                .seats(0)
                .carClass("Compact Sedan")
                .serialNumber(generateString(7))
                .pricePerDay(100.77)
                .about("Reliable and fuel-efficient sedan perfect for city driving and small families.")
                .image("")
                .build();
        Car car2 = Car.builder()
                .city("Tel Aviv")
                .manufacture("")
                .model("Corolla")
                .year("2019")
                .fuel(Fuel.HYBRID.getValue())
                .seats(0)
                .carClass("Compact Sedan")
                .serialNumber(generateString(7))
                .pricePerDay(100.77)
                .about("Reliable and fuel-efficient sedan perfect for city driving and small families.")
                .image("")
                .build();
        Car car3 = Car.builder()
                .city("Tel Aviv")
                .manufacture("")
                .model("Corolla")
                .year("2019")
                .fuel(Fuel.HYBRID.getValue())
                .seats(0)
                .carClass("Compact Sedan")
                .serialNumber(generateString(7))
                .pricePerDay(100.77)
                .about("Reliable and fuel-efficient sedan perfect for city driving and small families.")
                .image("")
                .build();
        return new Car[]{car1, car2, car3};
    }

    @DataProvider
    public Iterator<Car> addNewCarDPFile() {
        // iterator - интерфейс, который имеет два метода: next() и hasNext()
        // мы передаем сущность (лист), и итератор идет от нулевого элемента до последнего
        // стоит на нулевом -> hasNext(), если следующий элемент есть -> возвращает элемент, на котором стоит -> переходит на следующий
        // внутри for each используется итератор
        List<Car> list = new ArrayList<>();
        try(BufferedReader bufferReader = new BufferedReader(new FileReader("src/main/resources/data_provider/car_data.csv"))) {
            String line = bufferReader.readLine(); // Tel Aviv, ,Corolla,2019,gas,5,Toyota Sedan,SN-12345,200.45
            while (line != null) {
                // make object from data string
                String[] splitArray = line.split(",");
                list.add(Car.builder()
                                .city(splitArray[0])
                                .manufacture(splitArray[1])
                                .model(splitArray[2])
                                .year(splitArray[3])
                                .fuel(splitArray[4])
                                .seats(Integer.valueOf(splitArray[5]))
                                .carClass(splitArray[6])
                                .serialNumber(splitArray[7])
                                .pricePerDay(Double.valueOf(splitArray[8]))
                        .build());
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.listIterator();
    }
}
