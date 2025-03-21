package com.workintech.twitter.repository;

import com.workintech.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//CRUD işlemlerini yönetir(Create,Read,Update,Delete ayrıca sorgulama!!!)
//kullanıcı adına göre kullanıcı bulma metodu eklenmeli.Kullancı adı yoksa boş dönmeli.

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
}
