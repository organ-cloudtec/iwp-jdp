package com.cloudtec.modules.stay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.stay.entity.Hotel;

@Repository("hotelDao")
public interface HotelDao extends JpaRepository<Hotel,String>,JpaSpecificationExecutor<Hotel>{

	@Query("select h from Hotel h where h.recid =?")
	Hotel findbyId(String recid);

}
