package com.exchange.utils;

import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.persistence.domain.Image;
import com.exchange.backend.persistence.domain.Contact;
import com.exchange.backend.persistence.domain.Type;
import com.exchange.backend.persistence.domain.Status;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Mrs Hoang on 08/02/2017.
 */
public class GoodUtils {

    private GoodUtils() {
        throw new AssertionError("Not instantiable");
    }

    public static Good createGood(long id, String title, User user, int price) {

        Good good = new Good();
        good.setId(id);
        good.setTitle(title);
        good.setSlug(title);
        good.setFeaturedImage("https://dantri4.vcmedia.vn/4c574598e6/2016/11/20/2016-macbook-pro-ports-1479644042609.jpg");

        Random generation = new Random();
        good.setPrice(generation.nextInt(price));

        Image image1 = new Image();
        image1.setTitle("Image 1");
        image1.setAlt(title);
        image1.setUrl("https://dantri4.vcmedia.vn/4c574598e6/2016/11/20/2016-macbook-pro-ports-1479644042609.jpg");

        Image image2 = new Image();
        image2.setTitle("Image 2");
        image2.setAlt(title);
        image2.setUrl("http://www.compuzonedirect.com/534-large_default/macbook-pro.jpg");

        Image image3 = new Image();
        image3.setTitle("Image 3");
        image3.setAlt(title);
        image3.setUrl("https://www.easyacc.com/media-center/wp-content/uploads/2016/11/%E6%9C%AA%E5%91%BD%E5%90%8D_meitu_22-1-960x576.jpg");

        good.setImages(Arrays.asList(image1, image2, image3));

        good.setDescription("This is content good for test case");
        good.setPostDate(LocalDateTime.now(Clock.systemDefaultZone()));
        good.setPostBy(user);

        Contact contact = new Contact();
        contact.setAddress("Viet name");
        contact.setPhoneNumber("0132894294");

        Contact contact1 = new Contact();
        contact1.setAddress("French");
        contact1.setPhoneNumber("0132894294");

        List<String> types = new ArrayList<>();
        types.add("Computer");
        types.add("Book");
        types.add("Clothes");
        int choiseType  = generation.nextInt(2);

        List<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Selling");

        int choiseStatus  = generation.nextInt(1);

        good.setType(new Type(types.get(choiseType), ""));
        good.setStatus(new Status(status.get(choiseStatus), ""));
        good.setContacts(Arrays.asList(contact, contact1));

        return good;
    }

}
