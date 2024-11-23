

Here’s an example of the **Observer Pattern** in action:

### Use Case: Weather Station and Display Devices

**Scenario:**  
A weather station (Subject) tracks temperature and notifies multiple display devices (Observers) whenever the temperature changes.

---

### Code Implementation (in Java-like pseudocode)

#### 1. Subject Interface:
```java
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
```

#### 2. ConcreteSubject:
```java
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers(); // Notify all observers about the change
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
```

#### 3. Observer Interface:
```java
interface Observer {
    void update();
}
```

#### 4. ConcreteObservers:
```java
class PhoneDisplay implements Observer {
    private WeatherStation weatherStation;

    public PhoneDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Phone Display: Temperature updated to " + weatherStation.getTemperature() + "°C");
    }
}

class TVDisplay implements Observer {
    private WeatherStation weatherStation;

    public TVDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.attach(this);
    }

    @Override
    public void update() {
        System.out.println("TV Display: Temperature updated to " + weatherStation.getTemperature() + "°C");
    }
}
```

---

### 5. Client Code:
```java
public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        PhoneDisplay phoneDisplay = new PhoneDisplay(weatherStation);
        TVDisplay tvDisplay = new TVDisplay(weatherStation);

        weatherStation.setTemperature(25); // Updates both displays
        weatherStation.setTemperature(30); // Updates both displays again
    }
}
```

---

### Output:
```
Phone Display: Temperature updated to 25°C
TV Display: Temperature updated to 25°C
Phone Display: Temperature updated to 30°C
TV Display: Temperature updated to 30°C
```

---

### How It Works:
1. **WeatherStation (Subject)** maintains the state (temperature) and the list of observers.  
2. **PhoneDisplay and TVDisplay (Observers)** attach themselves to the WeatherStation.  
3. When the temperature changes, the WeatherStation notifies all observers via their `update()` method.  
4. Each observer reacts by fetching the updated temperature from the WeatherStation and displaying it.

This is a practical example of the **Observer Pattern** in action! Let me know if you'd like to adapt this to another context or programming language


















---------------------------------------------------------------------------------------------------------
public interface ObservableInterface{

    public void add(Observer obs);

    public void remove(Observer obs);

    public void notify();

    public void setCount(int n);

}

public interface ObserverInterface{
    public void update();
}

public class IphoneStockObservable implements ObservableInterface{
    List<ObserverInterface> observers = new ArrayList<>();
    int stockCount=0;

    @Override
    public void add(ObservableInterface observer){
        observers.add(observer);
    }
    
    @Override
    public void remove(ObservableInterface observer){
        Observers.remove(observer);
    }

    @Override
    public void notify(){
        for(ObservableInterface observer:observers){
            observer.update();
        }
    }


    @Override
    public void setCount(int newStockCount){
        if(stockCount==0){
            notify();
        }
        stockCount=newStockCount;
    }


}

public class MobileNotificationObserver implements ObserverInterface{

    public ObservableInterface observable;
    public Email email;
    public void MobileNotificationObserver(Email email, IphoneStockObservable observable){
        this.observable=observable;
        this.email=email;
        IphoneStockObservable.add(this);
    }
    @Override
    public void update(){
        //call logic to send notification to user mobile
    }
}

public class EmailNotificationObserver implements ObserverInterface{
    public ObservableInterface observable;
    public Email email;
    public void EmailNotificationObserver(Email email, IphoneStockObservable observable){
        this.observable=observable;
        this.email=email;
    }
    @Override
    public void update(){
        //call logic to send Email to user mail Id
    }
}

