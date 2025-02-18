import org.example.Person;
import org.example.BlogPost;
import org.example.Blog;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlogUnitTest {

    @Test
    public void testGetPostsByAuthorAge_ValidCase() {
       
        Person person1 = Person.builder()
                .id("P1")
                .firstName("Ananya")
                .lastName("Gupta")
                .age(30)
                .gender("Female")
                .build();
        Person person2 = Person.builder()
                .id("P2")
                .firstName("Rajesh")
                .lastName("Sharma")
                .age(35)
                .gender("Male")
                .build();

        // Create sample blog posts
        BlogPost post1 = BlogPost.builder()
                .id("BP1")
                .authorId("P1")
                .postContent("Understanding Spring Boot with Indian examples.")
                .build();
        BlogPost post2 = BlogPost.builder()
                .id("BP2")
                .authorId("P2")
                .postContent("Advanced topics in Java, explained by Rajesh.")
                .build();
        BlogPost post3 = BlogPost.builder()
                .id("BP3")
                .authorId("P1")
                .postContent("Exploring microservices architecture in modern apps.")
                .build();

        // Create a Blog instance and set posts and contributors
        Blog blog = new Blog();
        blog.setPosts(Arrays.asList(post1, post2, post3));
        blog.setContributors(Arrays.asList(person1, person2));

        // Verify that filtering by age returns the correct post IDs
        List<String> postsByAge30 = blog.getPostsByAuthorAge(30);
        assertEquals(2, postsByAge30.size());
        assertTrue(postsByAge30.contains("BP1"));
        assertTrue(postsByAge30.contains("BP3"));

        List<String> postsByAge35 = blog.getPostsByAuthorAge(35);
        assertEquals(1, postsByAge35.size());
        assertTrue(postsByAge35.contains("BP2"));
    }

    @Test
    public void testGetPostsByAuthorAge_MissingAuthor() {
        // Create one valid person
        Person person1 = Person.builder()
                .id("P1")
                .firstName("Ananya")
                .lastName("Gupta")
                .age(30)
                .gender("Female")
                .build();

        // Create a blog post with an authorId that doesn't exist among the contributors
        BlogPost post1 = BlogPost.builder()
                .id("BP1")
                .authorId("P2")  // No Person with id "P2"
                .postContent("Post with missing author.")
                .build();

        Blog blog = new Blog();
        blog.setPosts(Collections.singletonList(post1));
        blog.setContributors(Collections.singletonList(person1));

        // Since the author for post1 is missing, no posts should match any age
        List<String> postsByAge30 = blog.getPostsByAuthorAge(30);
        assertNotNull(postsByAge30);
        assertTrue(postsByAge30.isEmpty());
    }

    @Test
    public void testGetPostsByAuthorAge_EmptyInput() {
        // Create a Blog with no posts and no contributors
        Blog blog = new Blog();
        blog.setPosts(Collections.emptyList());
        blog.setContributors(Collections.emptyList());

        List<String> result = blog.getPostsByAuthorAge(30);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPostsByAuthorAge_NullAge() {
        // Create a Blog with valid data
        Person person1 = Person.builder()
                .id("P1")
                .firstName("Ananya")
                .lastName("Gupta")
                .age(30)
                .gender("Female")
                .build();
        BlogPost post1 = BlogPost.builder()
                .id("BP1")
                .authorId("P1")
                .postContent("Test content.")
                .build();

        Blog blog = new Blog();
        blog.setPosts(Collections.singletonList(post1));
        blog.setContributors(Collections.singletonList(person1));

        // When null is passed, our filter comparison (author.getAge().equals(null)) should return false,
        // so we expect an empty list.
        List<String> result = blog.getPostsByAuthorAge(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPostsByAuthorAge_NegativeAge() {
        // Create a Blog with valid data
        Person person1 = Person.builder()
                .id("P1")
                .firstName("Ananya")
                .lastName("Gupta")
                .age(30)
                .gender("Female")
                .build();
        BlogPost post1 = BlogPost.builder()
                .id("BP1")
                .authorId("P1")
                .postContent("Test content.")
                .build();

        Blog blog = new Blog();
        blog.setPosts(Collections.singletonList(post1));
        blog.setContributors(Collections.singletonList(person1));

        // No author should have a negative age, so expect an empty list.
        List<String> result = blog.getPostsByAuthorAge(-5);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
