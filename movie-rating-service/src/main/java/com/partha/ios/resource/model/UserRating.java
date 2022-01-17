package com.partha.ios.resource.model;

import java.util.Arrays;
import java.util.List;

public class UserRating {

    private List<Rating> userRating;
    public UserRating(){
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
    public void init(String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("5678",3)
        );
    }

}
