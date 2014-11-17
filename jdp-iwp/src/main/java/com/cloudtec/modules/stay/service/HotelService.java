package com.cloudtec.modules.stay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cloudtec.common.service.BaseService;
import com.cloudtec.modules.stay.dao.HotelDao;
import com.cloudtec.modules.stay.entity.Hotel;
import com.cloudtec.modules.sys.utils.PageBuildUtils;

@Service("hotelService")
public class HotelService extends BaseService {

	@Autowired
	@Qualifier("hotelDao")
	private HotelDao hotelDao;
	public Page<Hotel> findHotels(Hotel hotel, Integer pageNo,
			Integer pageSize, String sortType) {
		PageBuildUtils<Hotel> pageUtils = new PageBuildUtils<Hotel>();
		PageRequest pageRequest = pageUtils.buildPageRequest(pageNo, pageSize, sortType);
		return hotelDao.findAll(pageUtils.buildSpecification(hotel),pageRequest);
	}
	public Hotel getHotelByRrecid(String recid) {
		return hotelDao.findbyId(recid);
	}
	public Boolean saveHotel(Hotel hotel) {
		try {
			hotelDao.save(hotel);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
