package com.nick.product.manage.Repository;

import com.nick.product.manage.Entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MastersRepository extends JpaRepository<Master, String> {

}
