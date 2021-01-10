package ru.restaurants.util;

import ru.restaurants.model.Restaurant;
import ru.restaurants.model.Role;
import ru.restaurants.model.User;
import ru.restaurants.service.MenuService;
import ru.restaurants.service.VoteService;
import ru.restaurants.to.ToRestaurant;
import ru.restaurants.to.ToUser;

import java.util.HashSet;
import java.util.Set;

public class Convector {

    public static User covertToUser (ToUser u, VoteService voteService){
        String [] array = u.getRole().split("\\.");
        Set<Role> roleSet = new HashSet<>();
        for (String s : array) {
            switch (s) {
                case ("USER"):
                    roleSet.add(Role.USER);
                    break;
                case ("ADMIN"):
                    roleSet.add(Role.ADMIN);
                    break;
                default:
                    break;
            }
        }
        User user;
        if (u.isNew()) {
            user = new User(null, u.getEmail(), u.getName(), u.getPassword(), null, u.getVoteLast(), roleSet);
        }
        else {
            user = new User(u.id(), u.getEmail(), u.getName(), u.getPassword(), voteService.getAllVoteByUser(u.id()), u.getVoteLast(), roleSet);
        }
        return user;
    }

    public static Restaurant covertToRestaurant (ToRestaurant rest, MenuService menuService){
        Restaurant restaurant;
        if (rest.isNew()){
            restaurant = new Restaurant(null, rest.getName(), null, 0);

        } else {
            restaurant = new Restaurant(rest.id(), rest.getName(), menuService.getAllMenuOfRest(rest.id()), rest.getRating());
        }
        return restaurant;
    }

}
