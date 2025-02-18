package org.example;
import java.util.ArrayList;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Blog {
    private List<BlogPost> posts;       // List of blog posts
    private List<Person> contributors;  // List of contributors (Persons)

    public Blog() {
        this.posts = new ArrayList<>();
        this.contributors = new ArrayList<>();
    }

    // Constructor for Blog, which takes lists of posts and contributors
    public Blog(List<BlogPost> posts, List<Person> contributors) {
        this.posts = posts;
        this.contributors = contributors;
    }

    public List<String> getPostsByAuthorAge(Integer age) {
        return posts.stream()
                .filter(post -> {
                    Person author = contributors.stream()
                            .filter(person -> person.getId().equals(post.getAuthorId()))
                            .findFirst()
                            .orElse(null);
                    return author != null && author.getAge().equals(age);
                })
                .map(BlogPost::getId)
                .collect(Collectors.toList());
    }
}
