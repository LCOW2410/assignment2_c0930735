package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@EqualsAndHashCode
@Builder
@ToString
@Jacksonized
public class BlogPost {
    private String id;
    private String authorId;
    private String postContent;

    // Private constructor to enforce validation and use of Builder
    private BlogPost(String id, String authorId, String postContent) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (authorId == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }

        this.id = id;
        this.authorId = authorId;
        this.postContent = postContent;
    }
}
