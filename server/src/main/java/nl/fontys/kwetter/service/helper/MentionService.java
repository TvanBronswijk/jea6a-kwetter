package nl.fontys.kwetter.service.helper;

import nl.fontys.kwetter.model.post.Tag;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.UserService;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class MentionService {

    @Inject
    protected UserService userService;

    public Set<User> parseString(String string) {
        if (string.isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(string.split(" "))
                .filter(word -> word.toCharArray()[0] == '@')
                .map(atUser -> userService.get(atUser.substring(1)))
                .collect(Collectors.toSet());
    }
}
