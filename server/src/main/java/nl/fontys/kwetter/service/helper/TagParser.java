package nl.fontys.kwetter.service.helper;

import nl.fontys.kwetter.model.post.Tag;

import javax.ejb.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class TagParser {

    public Set<Tag> parseString(String string) {
        if (string.isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(string.split(" "))
                .filter(word -> word.toCharArray()[0] == '#')
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
