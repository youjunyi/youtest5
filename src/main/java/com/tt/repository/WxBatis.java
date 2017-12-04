package com.tt.repository;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface WxBatis {

	List<Map<String, String>> list(Long userid);

	List<Map<String, String>> querysywx(Map<String, Object> map);

}
