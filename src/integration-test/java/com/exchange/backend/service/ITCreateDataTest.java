package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.Category;
import com.exchange.backend.persistence.domain.Comment;
import com.exchange.backend.persistence.domain.Content;
import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.Image;
import com.exchange.backend.persistence.domain.Location;
import com.exchange.backend.persistence.domain.Rating;
import com.exchange.backend.persistence.domain.Status;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.persistence.repositories.elasticsearch.ElasticGoodRepository;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.text.TextProducer;
import org.joda.time.LocalDateTime;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * Created by optimize on 2/10/17.
 * Simple test CRUD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITCreateDataTest {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;

    @Autowired
    private ElasticGoodRepository elasticGoodRepository;

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    private int randomBetween(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public void moveGoodToElasticSearch() {
        List<Good> goods = goodService.getAll();
        for (Good good1 : goods) {
            ElasticGood elasticGood = new ElasticGood();
            elasticGood.setDescription(good1.getDescription());
            elasticGood.setId(good1.getId());
            elasticGood.setLocations(good1.getLocations());
            elasticGood.setPostBy(good1.getPostBy());
            elasticGood.setPostDate(good1.getPostDate());
            elasticGood.setPrice(good1.getPrice());
            elasticGood.setSlug(good1.getSlug());
            elasticGood.setTitle(good1.getTitle());
            elasticGood.setCategory(good1.getCategory());
            elasticGoodRepository.save(elasticGood);
        }
    }

    public void generateTestData() throws Exception {
        Fairy fairy = Fairy.create();
        for (int i = 0; i< 100; i++) {
            Person person = fairy.person();
            User user = new User();
            user.setFirstName(person.getFirstName());
            user.setLastName(person.getLastName());
            user.setAvatar("http://blog.florianlopes.io/wp-content/uploads/2016/04/spring-boot-project-logo.png");
            user.setBirthday(person.getDateOfBirth().getMillis());
            user.setCreateDate(new Date().getTime());
            user.setEnabled(true);
            int gender = person.getSex() == Person.Sex.MALE ? 1 : 0;
            user.setGender(gender);
            user.setId(person.getEmail());
            user.setPassword("123456");

            List<Content> contents = new ArrayList<>();
            Rating rating = new Rating();
            int numbRating = randomBetween(1, 20);
            double totalRating = 0;
            List<User> userList = userService.getAll();
            for (int j = 0; j < numbRating; j++) {
                Content content = new Content();
                if (userList.size() < 2) {
                    content.setBy(person.getEmail());
                } else {
                    content.setBy(userList.get(randomBetween(0, userList.size())).getId());
                }
                double r = randomBetween(1, 5);
                totalRating += r;
                content.setOn(new Date().getTime());
                content.setValue(r);
                contents.add(content);
            }
            rating.setAvg(totalRating/numbRating);
            rating.setContent(contents);
            user.setRating(rating);
            userService.create(user);
        }
    }

    public Category randomCategory() {
        List<String> categories = new ArrayList<>();
        categories.add("Book");
        categories.add("Screen");
        categories.add("CPU");
        categories.add("Mouse");
        categories.add("Laptop");
        categories.add("Guitar");
        categories.add("Wifi Card");
        categories.add("Chair");
        TextProducer text = Fairy.create().textProducer();
        return new Category(categories.get(randomBetween(0, categories.size())), text.loremIpsum());
    }

    public Status randomStatus() {
        TextProducer text = Fairy.create().textProducer();
        List<Status> statuses = new ArrayList<>();
        statuses.add(new Status("Pending", text.loremIpsum()));
        statuses.add(new Status("Publish", text.loremIpsum()));
        statuses.add(new Status("Banned", text.loremIpsum()));
        return statuses.get(randomBetween(0, statuses.size()));
    }

    public Comment randomComment(List<User> users) {
        TextProducer text = Fairy.create().textProducer();
        Comment comment = new Comment();
        comment.setBy(users.get(randomBetween(0, users.size())).getId());
        comment.setCommentDate(new LocalDateTime());
        comment.setMessage(text.loremIpsum());
        return comment;
    }

    public void generateGoodData() throws Exception {
        List<User> userList = userService.getAll();
        for (int i = 0; i< 10000; i++) {
            TextProducer text = Fairy.create().textProducer();
            Good good = new Good();
            User author = userList.get(randomBetween(0, userList.size()));
            good.setCategory(randomCategory());
            good.setStatus(randomStatus());
            good.setDescription(text.loremIpsum());
            good.setTitle(text.latinSentence(20));
            good.setSlug(toSlug(good.getTitle()));
            good.setPostBy(author);
            good.setId(author.getId() + good.getSlug());

            int numbComments = randomBetween(0, 50);
            List<Comment> comments = new ArrayList<>();
            for (int j = 0; j < numbComments; j++) {
                comments.add(randomComment(userList));
            }

            good.setComments(comments);
            good.setPublishDate(new LocalDateTime());
            Location location = new Location((double)randomBetween(-90, 90), (double)randomBetween(0, 180));
            good.setLocations(Arrays.asList(location));
            good.setPostDate(new Date().getTime());
            good.setFeaturedImage("http://lorempixel.com/1200/800/");

            int numbImages = randomBetween(0, 15);
            List<Image> images = new ArrayList<>();
            for (int k=0; k< numbImages; k++) {
                Image image = new Image("http://lorempixel.com/1200/800/", text.latinSentence(20), "");
                images.add(image);
            }
            good.setImages(images);
            goodService.create(good);
        }
    }

}