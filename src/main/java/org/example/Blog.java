package org.example;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class Blog {
    private List<BlogPost> posts;       // List of blog posts
    private List<Person> contributors;  // List of contributors (Persons)

    // Constructor for Blog, which takes lists of posts and contributors
    public Blog(List<BlogPost> posts, List<Person> contributors) {
        this.posts = posts;
        this.contributors = contributors;
    }
}
