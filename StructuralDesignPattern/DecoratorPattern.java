The **Decorator Pattern** is a structural design pattern that allows behavior to be added to individual objects, dynamically,
  without affecting the behavior of other objects from the same class. It’s commonly used to adhere to the **Open/Closed Principle**.

---

### **Conceptual Class Diagram**
```
+-----------------+         +----------------+
|    Component    |<--------| Decorator      |
| (interface/abstract) |     | (abstract class)|
+-----------------+         +----------------+
| + operation()   |         | + operation()   |
+-----------------+         +----------------+
          ^                          ^
          |                          |
          |                          |
+----------------+          +----------------+
| ConcreteComponent |       | ConcreteDecorator |
+----------------+          +----------------+
| + operation()  |          | - additionalData |
+----------------+          | + operation()    |
                            | + additionalBehavior() |
                            +----------------+
```

---

### **Example Use Case**: Coffee Shop

**Scenario:**  
A coffee shop allows customers to customize their coffee with add-ons like milk, sugar, and whipped cream. 
  Each add-on should be dynamically added to the base coffee object without modifying its original class.

---

### Code Implementation (in Java-like pseudocode)

#### 1. Component Interface:
```java
interface Coffee {
    String getDescription();
    double getCost();
}
```

#### 2. Concrete Component:
```java
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}
```

#### 3. Decorator (Abstract Class):
```java
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee; // Composition: Coffee object being decorated

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
```

#### 4. Concrete Decorators:
```java
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 1.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Whipped Cream";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 2.0;
    }
}
```

---

### 5. Client Code:
```java
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getDescription() + " -> $" + simpleCoffee.getCost());

        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println(milkCoffee.getDescription() + " -> $" + milkCoffee.getCost());

        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println(sugarMilkCoffee.getDescription() + " -> $" + sugarMilkCoffee.getCost());

        Coffee deluxeCoffee = new WhippedCreamDecorator(sugarMilkCoffee);
        System.out.println(deluxeCoffee.getDescription() + " -> $" + deluxeCoffee.getCost());
    }
}
```

---

### Output:
```
Simple Coffee -> $5.0
Simple Coffee, Milk -> $6.5
Simple Coffee, Milk, Sugar -> $7.0
Simple Coffee, Milk, Sugar, Whipped Cream -> $9.0
```

---

### **How It Works:**
1. **SimpleCoffee** is the base object, implementing the `Coffee` interface.
2. **MilkDecorator**, **SugarDecorator**, and **WhippedCreamDecorator** dynamically extend the behavior of the base object by adding features.
3. Each decorator wraps the existing `Coffee` object, adds its own behavior, and delegates other calls to the wrapped object.
4. The client dynamically combines behaviors by wrapping objects with decorators.

This pattern is particularly useful for scenarios where subclassing to achieve combinations of behaviors is impractical. Let me know if you'd like further examples!
