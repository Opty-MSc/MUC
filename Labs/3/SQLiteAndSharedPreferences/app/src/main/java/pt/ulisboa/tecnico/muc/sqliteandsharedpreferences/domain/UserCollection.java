package pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.domain;

import java.util.ArrayList;
import java.util.List;

public class UserCollection {

    public static final List<User> ITEMS = new ArrayList<>();

    public static void addUser(long uid, String username, int age) {
        ITEMS.add(new User(uid, username, age));
    }

    public static class User {
        private final long uid;
        private final String username;
        private final int age;

        public User(Long uid, String username, Integer age) {
            this.uid = uid;
            this.username = username;
            this.age = age;
        }

        public long getUid() {
            return this.uid;
        }

        public String getUsername() {
            return this.username;
        }

        public int getAge() {
            return this.age;
        }
    }
}
