
package com.cloudtec.modules.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.sys.entity.Dict;



@Repository("dictDao")
public interface DictDao extends JpaRepository<Dict, String> {

}
