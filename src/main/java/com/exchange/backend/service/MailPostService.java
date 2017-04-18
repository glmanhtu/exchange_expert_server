package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.MailPost;
import com.exchange.backend.persistence.repositories.mongodb.MailPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by greenlucky on 4/16/17.
 */
@Service
@Transactional(readOnly = true)
public class MailPostService {

    @Autowired
    private MailPostRepository mailPostRepository;

    @Transactional
    public MailPost create(String title, String content, String forUser, boolean read) {
        MailPost mailPost = new MailPost(title, content, forUser, read);
        mailPost = mailPostRepository.save(mailPost);
        return mailPost;
    }

    @Transactional
    public MailPost create(MailPost mailPost) {
        mailPost = mailPostRepository.save(mailPost);
        return mailPost;
    }

    @Transactional
    public void makeAsRead(MailPost mailPost) {
        mailPostRepository.save(mailPost);
    }

    public Page<MailPost> getMailPostofUser(String userId, Pageable pageable) {
        return mailPostRepository.findByForUserOrderByReadAscCreatedDateDesc(userId, pageable);
    }

    public MailPost getOne(String id) {
        return mailPostRepository.findOne(id);
    }

    @Transactional
    public void deleteAll() {
        mailPostRepository.deleteAll();
    }

    public int getMailPostofUserUnread(String userId) {
        List<MailPost> mailPosts = mailPostRepository.findByForUserAndRead(userId, false);
        return mailPosts.size();
    }
}
