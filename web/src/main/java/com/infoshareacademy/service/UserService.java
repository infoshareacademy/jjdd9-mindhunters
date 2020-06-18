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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class.getName());
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
        LOGGER.info("User {} created in role as {}", user.getEmail(), user.getRole());
        save(user);
        return user;
    }

    public UserView login(UserGoogleView userGoogleView) {
        User user = userRepository.findByEmail(userGoogleView.getEmail()).orElseGet(() -> create(userGoogleView));
        LOGGER.info("User {} logged in as {}", user.getEmail(), user.getRole());
        return userMapper.toView(user);
    }

    public void saveOrDeleteFavourite(String email, String drinkId) {

        Drink drink = drinkRepository.findDrinkById(Long.parseLong(drinkId));
        User user = userRepository.findByEmail(email).orElseThrow();

        List<Drink> favouriteDrinks = user.getDrinks();

        if (!favouriteDrinks.contains(drink)) {
            user.getDrinks().add(drink);
            LOGGER.info("User email = {} added drink ID = {} to favourites", email, drinkId);

        } else {
            user.getDrinks().remove(drink);
            LOGGER.info("User email = {} deleted drink ID = {} from favourites", email, drinkId);

        }

    }

    public List<FullDrinkView> favouritesList(String email) {

        User user = userRepository.findByEmail(email).orElseThrow();

        return fullDrinkMapper.toView(user.getDrinks());

    }

    public List<FullDrinkView> favouritesList(String email, int pageNumber) {
        int startPosition = (pageNumber - 1) * PAGE_SIZE;
        int endPosition = PAGE_SIZE;

        List<Drink> drinks = userRepository.findFavouritesList(email, startPosition, endPosition);

        return fullDrinkMapper.toView(drinks);

    }

    public int countPagesFavouritesList(String email) {
        int maxPageNumber = userRepository.countPagesFindFavouritesList(email);

        return maxPageNumber;

    }

    public int getMaxPageNumber(String querySize) {
        return (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
    }

}
