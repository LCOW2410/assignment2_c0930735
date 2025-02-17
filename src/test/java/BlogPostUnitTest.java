
import org.example.BlogPost;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlogPostTest {

    @Test
    void testBlogPostCreationValid() {
        // Valid inputs for creating a BlogPost
        BlogPost blogPost = BlogPost.builder()
                .id("BP123")
                .authorId("IN123")  // Person's ID as authorId
                .postContent("This is an insightful blog post about Indian culture and traditions.")
                .build();

        // Assert that the object is created successfully
        assertNotNull(blogPost);
        assertEquals("BP123", blogPost.getId());
        assertEquals("IN123", blogPost.getAuthorId());
        assertEquals("This is an insightful blog post about Indian culture and traditions.", blogPost.getPostContent());
    }

    @Test
    void testBlogPostCreationInvalidId() {
        // Invalid ID (null) should throw an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            BlogPost.builder()
                    .id(null) // Null ID
                    .authorId("IN123")
                    .postContent("This is an insightful blog post about Indian culture and traditions.")
                    .build();
        });
        assertEquals("ID cannot be null", exception.getMessage());
    }

    @Test
    void testBlogPostCreationInvalidAuthorId() {
        // Invalid authorId (null) should throw an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            BlogPost.builder()
                    .id("BP123")
                    .authorId(null) // Null authorId
                    .postContent("This is an insightful blog post about Indian culture and traditions.")
                    .build();
        });
        assertEquals("Author ID cannot be null", exception.getMessage());
    }
}
