package com.nick.product.manage.supplier;

import com.nick.product.manage.master.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, String> {

}
