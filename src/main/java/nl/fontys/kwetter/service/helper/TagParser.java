package nl.fontys.kwetter.service.helper;

import nl.fontys.kwetter.model.post.Tag;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Singleton
public class TagParser {

    public Collection<Tag> parseString(String string) {
        if (string.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(string.split(" "))
                .filter(word -> word.toCharArray()[0] == '#')
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
