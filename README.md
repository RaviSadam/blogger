# Blogging
https://blogger-8f9i.onrender.com

The blog application I developed is a comprehensive platform aimed at providing users with a seamless blogging experience. Leveraging various technologies and frameworks, including Thymeleaf, Spring Security, H2 Database, Lombok, Spring Web, Spring Data JPA, Spring Boot Validation, and Spring Boot DevTools, the application offers a robust set of features.

Users can register and login securely to access the available articles. The platform allows users to browse through the collection of articles and pick one to start writing their own content. Once a user picks an article, it is displayed in the "My Picked Articles" page, exclusively for draft articles. Users can then utilize a third-party text editor integrated into the application for an enhanced writing experience.

To maintain the quality and relevance of content, a unique feature is implemented where if a user hasn't written a blog within 5 days of picking an article, the article is automatically discarded from their account, making it available for another user.

Admin privileges enable the addition of new blog titles and categories to the portal, ensuring the platform remains updated with fresh content. The application also includes robust exception handling mechanisms to gracefully manage errors, enhancing the overall user experience.

Scheduled tasks are integrated into the system to automate processes such as garbage collection, executed every 10 minutes to optimize memory usage and ensure smooth application performance. In addition to the aforementioned features, the blog application incorporates Spring Boot Actuator to monitor and assess its performance. Spring Boot Actuator is a powerful tool that provides various endpoints to gather essential information about the application's runtime behavior and health.

In addition to the aforementioned features, the blog application incorporates Spring Boot Actuator to monitor and assess its performance. Spring Boot Actuator is a powerful tool that provides various endpoints to gather essential information about the application's runtime behavior and health.

Demo: https://blogger-8f9i.onrender.com
Login url: https://blogger-8f9i.onrender.com/login
**Front end not integrated properly**

End Points

![Screenshot 2024-02-04 011519](https://github.com/RaviSadam/blogger/assets/108484600/1ca2ae31-f3a9-4d09-97f7-678e62e508f8)  ![Screenshot 2024-02-04 011518](https://github.com/RaviSadam/blogger/assets/108484600/b55d9e3e-69db-4ed2-8688-a7fa5b68cdcb)
