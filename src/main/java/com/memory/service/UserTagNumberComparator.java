package com.memory.service;

import com.memory.pojo.UserTag;

import java.util.Comparator;

public class UserTagNumberComparator implements Comparator<UserTag> {
    @Override
    public int compare(UserTag o1, UserTag o2) {
        if(o1 == null){
            return 1;
        }
        if(o2 == null || !(o2 instanceof UserTag)){
            return -1;
        }

        long key1 = o1.getTagNumber();
        long key2 = o2.getTagNumber();
        return key1 > key2 ? 1 : key1 < key2 ? -1 : 0;
    }
}
