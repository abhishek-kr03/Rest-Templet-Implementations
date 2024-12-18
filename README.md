
---

# **RestTemplate in Spring Boot**

## **What is RestTemplate?**
- **RestTemplate** is a Spring class used to make HTTP requests (GET, POST, PUT, DELETE, etc.) to consume RESTful web services.
- It acts as a client that communicates with other APIs or URLs.

---

## **Why Use RestTemplate?**
1. Helps fetch or send data to/from external REST APIs.
2. Simplifies HTTP request and response handling.
3. Supports all HTTP methods (GET, POST, PUT, DELETE, etc.).
4. Easily integrates into Spring Boot applications.

---

## **How to Use RestTemplate?**

### **1. Add Dependencies**
Ensure Spring Web is included in your `pom.xml` file:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

---

### **2. Create a Bean for RestTemplate**
You need to declare a RestTemplate bean in your configuration class or main application.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

---

### **3. Use RestTemplate in Your Service or Controller**

#### **a) Perform GET Request**
Fetch data from an API.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public String getUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";
        // Perform GET Request
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
}
```

---

#### **b) Perform POST Request**
Send data to an API.

```java
public void createUser() {
    String url = "https://jsonplaceholder.typicode.com/users";

    User newUser = new User("Alice", "Cooper", "alice@example.com", 36);
    User response = restTemplate.postForObject(url, newUser, User.class);

    System.out.println("Created User: " + response);
}
```

---

#### **c) Perform PUT Request**
Update existing data on the server.

```java
public void updateUser(Long userId) {
    String url = "https://jsonplaceholder.typicode.com/users/" + userId;

    User updatedUser = new User("Alice", "Johnson", "alice.johnson@example.com", 37);
    restTemplate.put(url, updatedUser);

    System.out.println("User updated successfully");
}
```

---

#### **d) Perform DELETE Request**
Delete data on the server.

```java
public void deleteUser(Long userId) {
    String url = "https://jsonplaceholder.typicode.com/users/" + userId;

    restTemplate.delete(url);
    System.out.println("User deleted successfully");
}
```

---

## **Common HTTP Methods in RestTemplate**

| **HTTP Method** | **RestTemplate Method**          | **Purpose**                             |
|-----------------|----------------------------------|-----------------------------------------|
| GET             | `getForObject()` or `getForEntity()` | Retrieve data from a URL.               |
| POST            | `postForObject()` or `postForEntity()` | Send data to a server to create a resource. |
| PUT             | `put()`                         | Update existing data on the server.     |
| DELETE          | `delete()`                      | Delete a resource on the server.        |
| Exchange        | `exchange()`                    | Flexible method supporting all HTTP methods. |

---

## **Key Methods in RestTemplate**

1. **`getForObject()`**:
   - Simplest way to fetch an object from a URL.
   - Example:  
     ```java
     String response = restTemplate.getForObject(url, String.class);
     ```

2. **`postForObject()`**:
   - Sends a POST request to create a resource.
   - Example:  
     ```java
     User response = restTemplate.postForObject(url, user, User.class);
     ```

3. **`put()`**:
   - Sends a PUT request to update data.
   - Example:  
     ```java
     restTemplate.put(url, updatedUser);
     ```

4. **`delete()`**:
   - Sends a DELETE request to remove a resource.
   - Example:  
     ```java
     restTemplate.delete(url);
     ```

5. **`exchange()`**:
   - A more advanced method for making requests with full control over HTTP methods and headers.
   - Example:  
     ```java
     ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
     ```

---

## **Handling Responses**

### **1. `getForObject()`**
- Directly fetches the response body.

```java
String result = restTemplate.getForObject(url, String.class);
```

### **2. `getForEntity()`**
- Fetches the response with additional details like HTTP status and headers.

```java
ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
System.out.println("Status Code: " + response.getStatusCode());
System.out.println("Response Body: " + response.getBody());
```

---

## **Handling Exceptions**
Wrap RestTemplate calls with `try-catch` blocks to handle errors.

```java
try {
    String response = restTemplate.getForObject(url, String.class);
} catch (RestClientException e) {
    System.out.println("Error occurred: " + e.getMessage());
}
```

---

## **When to Use RestTemplate?**
- Use **RestTemplate** for synchronous HTTP calls in simple applications.
- For more modern applications:
   - Use **WebClient** (part of Spring WebFlux) for reactive and asynchronous programming.

---

## **Summary**

- RestTemplate is used for making HTTP requests in Spring Boot.
- It supports all HTTP methods: GET, POST, PUT, DELETE.
- Always **define RestTemplate as a Bean** for reusability.
- Use exception handling (`try-catch`) to manage errors gracefully.

---
