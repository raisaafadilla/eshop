
### Module 1
#### Reflection 1

When implementing the edit and delete functionalities, I ensured the code was both comprehensible and secure. I used clear names for variables and functions, and I added spaces between lines and indented properly to make the code easier to read. Moreover, while working through this tutorial, I found it challenging to grasp the MVC concept because I haven't had much experience. 

#### Reflection 2

1. After writing tests for my program, I feel more confident in its functionality and trustworthiness. There's no set rule for the exact number of tests, but it's important to have enough to cover all possible scenarios and special cases. However, reaching 100% code coverage doesn't mean there are no bugs or errors, as it simply shows that every line of code has been run at least once during testing.
2. The cleanliness of the new functional test suite relies on how well it follows clean code rules. Possible problems include repeating code, unclear names, how easy it is to read, if each test can run by itself, how much of the code gets tested, and handling errors. To make it cleaner, we can cut down on repeating code, use clear names, make sure it's easy to read and change, keep each test separate, test everything we can, and handle errors well.

### Module 2

1. During the exercise, the main code quality issues addressed were related to the testing and implementation of the ProductService class. Specifically, the issues involved methods like getProductId() and create() not being defined, which were resolved by defining these methods in the ProductService interface and implementing them in the ProductServiceImpl class.
2. In my opinion, the implementation workflow in my program meets CI/CD resolutions. The current implementation achieves Continuous Integration by running automated tests on every push, ensuring smooth integration of changes with the existing code. However, full Continuous Deployment isn't guaranteed as additional steps like manual approvals might be needed before changes are deployed to production.