package com.nick.product.manage.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MastersRepository extends JpaRepository<Master, String> {

}
