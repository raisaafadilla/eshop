

### Module 1
#### Reflection 1

When implementing the edit and delete functionalities, I ensured the code was both comprehensible and secure. I used clear names for variables and functions, and I added spaces between lines and indented properly to make the code easier to read. Moreover, while working through this tutorial, I found it challenging to grasp the MVC concept because I haven't had much experience. 

#### Reflection 2

1. After writing tests for my program, I feel more confident in its functionality and trustworthiness. There's no set rule for the exact number of tests, but it's important to have enough to cover all possible scenarios and special cases. However, reaching 100% code coverage doesn't mean there are no bugs or errors, as it simply shows that every line of code has been run at least once during testing.
2. The cleanliness of the new functional test suite relies on how well it follows clean code rules. Possible problems include repeating code, unclear names, how easy it is to read, if each test can run by itself, how much of the code gets tested, and handling errors. To make it cleaner, we can cut down on repeating code, use clear names, make sure it's easy to read and change, keep each test separate, test everything we can, and handle errors well.

### Module 2

1. During the exercise, the main code quality issues addressed were related to the testing and implementation of the ProductService class. Specifically, the issues involved methods like getProductId() and create() not being defined, which were resolved by defining these methods in the ProductService interface and implementing them in the ProductServiceImpl class.
2. In my opinion, the implementation workflow in my program meets CI/CD resolutions. The current implementation achieves Continuous Integration by running automated tests on every push, ensuring smooth integration of changes with the existing code. However, full Continuous Deployment isn't guaranteed as additional steps like manual approvals might be needed before changes are deployed to production.

### Module 3

**1. Explain what principles you apply to your project**
1. Single Reponsibility Principle: I separated the ‘ProductController.java’ and ‘CarController.java’. Each controller is responsible for handling operations related to its corresponding entity.
2. Open-Closed Principle: It already applied in model (’Car.java’ and ‘Product.java’) by enabling easy extension for future changes without needing to modify their existing code.
3. Liskov Subtitution Principle: In the project, the 'ProductServiceImpl' class is a subclass of 'ProductService' and implements all the methods defined in the interface.
4. Interface Segregation Principle: I removed "public" from interface methods by simplified things and focusing on what's needed.
5. Dependency Inversion Principle: I made the ‘CarController’ depend on the ‘CarService interface rather than the specific ‘CarServiceImpl’ class.

**2. Explain the advantages of applying SOLID principles to your project with examples.**
- Each class or component in the codebase focuses on a single task. For example, the ProductController and CarController each handle their own tasks, like dealing with products or cars. This makes it easier to understand, fix, and change the code.
- Allows for easy addition of new features without changing existing code. For example, the Product and Car classes can be extended with new attributes or behaviors without modifying their original structure. This keeps the code flexible and adaptable.
- Keeps interfaces focused on what clients need, avoiding unnecessary methods. For example, the CarService and ProductService interfaces only include the essential methods for managing cars and products. This makes the code simpler and easier to manage and expand.

**3. Explain the disadvantages of not applying SOLID principles to your project with examples.**
- If I don't use the Single Responsibility Principle it means the same code is repeated in many places, making it harder to manage and fix errors.
- Classes might rely too much on each other, causing problems when I try to change one thing without affecting others.
- The code might be inflexible and resistant to updates. Adding new features could mean changing existing code, which could lead to new problems.
- Testing can be difficult because things are too connected, and different tasks aren't separated well. This makes testing slower and less reliable.