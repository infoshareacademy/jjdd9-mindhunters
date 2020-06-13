package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.UserGoogleView;
import com.infoshareacademy.domain.dto.UserView;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.repository.RoleRepositoryBean;
import com.infoshareacademy.repository.UserRepository;
import com.infoshareacademy.service.mapper.FullDrinkMapper;
import com.infoshareacademy.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    private static final Logger packageLogger = LoggerFactory.getLogger(UserService.class.getName());
    private static final Integer PAGE_SIZE = 5;

    @EJB
    private UserRepository userRepository;

    @EJB
    private DrinkRepository drinkRepository;

    @EJB
    private RoleRepositoryBean roleRepositoryBean;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    @Inject
    private UserMapper userMapper;

    public void save(User user) {
        userRepository.save(user);
    }

    public UserView getUserById(Long userId) {
        User foundUser = userRepository.findUserById(userId).orElseThrow();
        if (foundUser == null) {
            return null;
        }
        return userMapper.toView(foundUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(UserGoogleView userGoogleView) {
        User user = new User();
        user.setName(userGoogleView.getName());
        user.setEmail(userGoogleView.getEmail());
        user.setRole(roleRepositoryBean.findByRoleName("USER").orElseThrow());
        save(user);
        return user;
    }

    public UserView login(UserGoogleView userGoogleView) {
        User user = userRepository.findByEmail(userGoogleView.getEmail()).orElseGet(() -> create(userGoogleView));
        return userMapper.toView(user);
    }

    public void saveOrDeleteFavourite(String userId, String drinkId) {

        Drink drink = drinkRepository.findDrinkById(Long.parseLong(drinkId));
        User user = userRepository.findUserById(Long.parseLong(userId)).orElseThrow();

        List<Drink> favouriteDrinks = user.getDrinks();

        if (!favouriteDrinks.contains(drink)) {
            user.getDrinks().add(drink);
            packageLogger.info("User ID = {} added drink ID = {} to favourites", userId, drinkId);

        } else {
            user.getDrinks().remove(drink);
            packageLogger.info("User ID = {} deleted drink ID = {} from favourites", userId, drinkId);

        }

    }

    public List<FullDrinkView> favouritesList(String userId) {

        User user = userRepository.findUserById(Long.parseLong(userId)).orElseThrow();

        return fullDrinkMapper.toView(user.getDrinks());

    }

    public List<FullDrinkView> favouritesList(String userId, int pageNumber) {
        int startPosition = (pageNumber - 1) * PAGE_SIZE;
        int endPosition = PAGE_SIZE;

        List<Drink> drinks = userRepository.findFavouritesList(Long.valueOf(userId), startPosition, endPosition);

        return fullDrinkMapper.toView(drinks);

    }

    public int countPagesFavouritesList(String userId) {
        int maxPageNumber = userRepository.countPagesFindFavouritesList(Long.valueOf(userId));

        return maxPageNumber;

    }

    public int getMaxPageNumber(String querySize) {
        return (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
    }

}
