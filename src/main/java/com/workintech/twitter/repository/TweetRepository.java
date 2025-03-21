package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//Kullanıcının tüm tweetlerini listeleyecek metod!!
//entityde JoinColumn name= "user_id"

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
    List<Tweet> findByUserId(Long userId);
}
