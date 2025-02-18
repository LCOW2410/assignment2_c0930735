package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read data from JSON files
            List<Person> persons = objectMapper.readValue(new File("src/main/resources/person.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));
            List<BlogPost> blogPosts = objectMapper.readValue(new File("src/main/resources/blogPosts.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, BlogPost.class));

            Blog blog = new Blog(blogPosts, persons);
            blog.setPosts(blogPosts);
            blog.setContributors(persons);

            // Call the getPostsByAuthorAge method and print the results
            Integer age = 31;  // Example age input
            List<String> postIds = blog.getPostsByAuthorAge(age);
            System.out.println("Posts by authors aged " + age + ": " + postIds);

            // Print total number of blog posts and contributors
            System.out.println("Total number of blog posts: " + blogPosts.size());
            System.out.println("Total number of contributors: " + persons.size());

        } catch (IOException e) {
            // Gracefully handle missing or corrupt JSON files
            System.out.println("Error reading JSON files: " + e.getMessage());
        }
    }
}
