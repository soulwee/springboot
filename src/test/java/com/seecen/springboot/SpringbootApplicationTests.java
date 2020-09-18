package com.seecen.springboot;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seecen.entity.User;
import com.seecen.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	DataSource dataSource;
	@Autowired
	UserMapper userMapper;
	@Test
	public void select() {
		//List<User> list = userMapper.selectList(null);//查所有

		//List<User> list = userMapper.selectBatchIds(Arrays.asList(1,2,3));//批量查询

		/*Map<String,Object> map = new HashMap<>();
		map.put("usr_ent_id",3);
		List<User> list = userMapper.selectByMap(map);//条件查询*/

		//list.forEach(System.out::println);

		Page<User> page = new Page<>(1,5);
	//	userMapper.selectPage(page, null);//分页查询

        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);//分页查询，结果集是map
        mapIPage.getRecords().forEach(System.out::println);
     //   page.getRecords().forEach(System.out::println);

	}
	@Test
	public void contextLoads() {
		System.out.println(dataSource);
	}

}
