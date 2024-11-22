// The Singleton Pattern ensures that a class has only one instance and provides a global access point to that instance. This pattern is commonly used for scenarios 
//   where exactly one instance is required to coordinate actions across the system, such as logging, configuration management, or connection pools.

// Key Characteristics of Singleton Pattern
// Single Instance: Only one instance of the class is created.
// Global Access: The single instance is globally accessible.
// Lazy Initialization (optional): The instance is created only when it’s needed.
  
// Implementing Singleton Pattern in Java
// There are several ways to implement the Singleton Pattern in Java. Here are some common implementations:

// 1. Eager Initialization
// In this approach, the instance is created at the time of class loading. This is simple and thread-safe because the instance is created once when the class is loaded.

// java
class EagerSingleton {
    // Create the instance at the time of class loading
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    // Private constructor to prevent instantiation
    private EagerSingleton() {}

    // Public method to provide access to the instance
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
// Pros
// Simple and thread-safe without additional synchronization.
// Instance is created at class loading, so it’s fast to access.
// Cons
// The instance is created even if it’s never used, which can be wasteful if instantiation is costly.
// 2. Lazy Initialization
// In this approach, the instance is created only when it’s requested for the first time, rather than at the time of class loading.

// java
class LazySingleton {
    private static LazySingleton instance;

    // Private constructor to prevent instantiation
    private LazySingleton() {}

    // Public method to provide access to the instance, with lazy initialization
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
// Pros
// The instance is created only when it’s needed, saving memory if it’s never used.
// Cons
// This implementation is not thread-safe. Multiple threads could potentially create multiple instances.

// 3. Thread-Safe Singleton (Synchronized Method)
// To make the lazy initialization approach thread-safe, we can synchronize the getInstance() method. However, this synchronization can reduce performance due to locking overhead.

class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    // Synchronized method to control simultaneous access
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
// Pros
// Ensures that only one instance is created in a multithreaded environment.
// Cons
// Synchronizing the entire method can be slower and impact performance when getInstance() is called frequently.

  
// 4. Double-Checked Locking
// To address the performance issues with synchronized methods, we can use double-checked locking. This approach reduces the cost of synchronization by only locking when the instance is first created.

class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {  // First check
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {  // Second check
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
// Explanation
// The volatile keyword ensures visibility of changes to the instance variable across threads.
// The outer if check avoids synchronization once the instance is created, improving performance.
// The inner if check ensures that only one instance is created when multiple threads try to access it for the first time.
// Pros
// Provides thread safety with minimal synchronization overhead.
// Cons
// Slightly more complex to implement and understand.


// 5. Bill Pugh Singleton Design (Using Inner Static Helper Class)
// This approach uses a static inner class to hold the singleton instance. The instance is created only when the inner class is loaded, achieving both lazy initialization and thread safety.

class BillPughSingleton {
    private BillPughSingleton() {}

    // Inner static class responsible for holding the singleton instance
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
// Explanation
// The SingletonHelper class is not loaded until getInstance() is called, ensuring lazy initialization.
// Java guarantees that static class initialization is thread-safe, so no need for additional synchronization.
// Pros
// Thread-safe and lazy-loaded without synchronization overhead.
// Simple and easy to implement.
// Cons
// None specific to this method; it’s generally considered the best way to implement a singleton in Java.


// 6. Enum Singleton
// The enum type is a special class in Java that inherently ensures only one instance of each enum constant. Java enums provide a simple and safe way to create singletons without additional synchronization mechanisms.

enum EnumSingleton {
    INSTANCE;  // Single instance
    
    public void someMethod() {
        // Perform some action
    }
}

// Explanation
// Since enums are inherently serializable and provide built-in protection against multiple instantiation, they’re immune to serialization attacks.
// Enum values are instantiated only once by the Java Virtual Machine (JVM).
// Pros
// Thread-safe and serialization-safe by default.
// Simplest way to create a singleton with guaranteed safety.
// Cons
// Slightly unconventional and may be less familiar to developers unfamiliar with the enum pattern for singletons.
// When to Use Singleton Pattern
// The Singleton Pattern is useful in scenarios where:

// Only one instance of a class is needed, e.g., for managing a shared resource like a database connection or configuration.
// You want to provide a global point of access to the instance.
// Summary of Singleton Implementations
// Eager Initialization: Simple but not memory-efficient.
// Lazy Initialization: Memory-efficient but not thread-safe.
// Thread-Safe (Synchronized): Simple and thread-safe but can be slow.
// Double-Checked Locking: Thread-safe with reduced synchronization cost.
// Bill Pugh Singleton: Lazy-loaded and thread-safe with simplicity.
// Enum Singleton: Simple, thread-safe, and recommended for most use cases in Java.
// The Singleton Pattern is popular but should be used with caution. Overusing it can lead to code that's tightly coupled and difficult to test.
