package nl.fontys.kwetter.service.helper;

import nl.fontys.kwetter.model.post.Tag;

import javax.ejb.Singleton;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Singleton
public class TagParser {

    public Collection<Tag> parseString(String string) {
        return Arrays.stream(string.split(" "))
                .filter(word -> word.toCharArray()[0] == '#')
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
