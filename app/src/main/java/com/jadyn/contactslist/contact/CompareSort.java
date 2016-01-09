package com.jadyn.contactslist.contact;

import java.util.Comparator;

/**
 * 排序类
 * Created by tian on 16-1-9.
 */
public class CompareSort implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        if(user1.getLetter().equals("@") || user2.getLetter().equals("@")){
            //通讯录前面的ｉｔｅｍ
            return user1.getLetter().equals("@") ? -1:1;
        }
        else if(!user1.getLetter().matches("[A-z]+")){
            return 1;
        }else if(!user2.getLetter().matches("[A-z]+")){
            return -1;
        }else {
            return user1.getLetter().compareTo(user2.getLetter());
        }
    }
}
