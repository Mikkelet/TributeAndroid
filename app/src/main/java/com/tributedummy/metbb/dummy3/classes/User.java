package com.tributedummy.metbb.dummy3.classes;

import com.tributedummy.metbb.dummy3.R;

public class User {
    private String name;
    private int profilePic;

    public User(String name) {
        profilePic = R.mipmap.image1;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getProfilePic() {
        return profilePic;
    }
}
