//--------------- The Prototype interface contains a clone() method, allowing classes that implement it to return a copy of themselves.


interface Prototype {
    Prototype clone();
}


//---------- Define concrete classes that implement the Prototype interface. Each class must implement the clone() method to provide a copy of itself.


class Circle implements Prototype {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public Circle clone() {
        return new Circle(this.radius);  // Creates a new Circle with the same radius
    }

    @Override
    public String toString() {
        return "Circle with radius: " + radius;
    }
}

class Rectangle implements Prototype {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this.width, this.height);  // Creates a new Rectangle with the same width and height
    }

    @Override
    public String toString() {
        return "Rectangle with width: " + width + " and height: " + height;
    }
}

//------------ The registry keeps a map of prototype instances. You can register a prototype with a key and clone it later by referencing the key.

import java.util.HashMap;
import java.util.Map;

class PrototypeRegistry {
    private Map<String, Prototype> prototypes = new HashMap<>();

    // Registers a prototype with a specific key
    public void addPrototype(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }

    // Returns a cloned object based on the key
    public Prototype getPrototype(String key) {
        Prototype prototype = prototypes.get(key);
        if (prototype != null) {
            return prototype.clone();  // Clone the prototype when returning
        }
        return null;
    }
}

//---------Here's how to use the prototype registry to clone instances of Circle and Rectangle.

public class PrototypeRegistryDemo {
    public static void main(String[] args) {
        // Create prototype registry
        PrototypeRegistry registry = new PrototypeRegistry();

        // Register prototypes
        registry.addPrototype("Large Circle", new Circle(10));
        registry.addPrototype("Small Rectangle", new Rectangle(5, 10));

        // Clone objects using the registry
        Circle clonedCircle = (Circle) registry.getPrototype("Large Circle");
        Rectangle clonedRectangle = (Rectangle) registry.getPrototype("Small Rectangle");

        // Print cloned objects
        System.out.println(clonedCircle);    // Output: Circle with radius: 10
        System.out.println(clonedRectangle); // Output: Rectangle with width: 5 and height: 10

        // Modify cloned objects independently
        clonedCircle.setRadius(15);
        clonedRectangle.setWidth(20);

        System.out.println("Modified Cloned Circle: " + clonedCircle);    // Output: Circle with radius: 15
        System.out.println("Original Large Circle Prototype: " + registry.getPrototype("Large Circle")); // Unchanged
    }
}


