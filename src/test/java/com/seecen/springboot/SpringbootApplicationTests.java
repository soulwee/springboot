package com.seecen.springboot;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import java.util.Date;
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
    public void testWrapper(){
        Page<User> page = new Page<>(1,5);
        QueryWrapper<User> wrapper = new QueryWrapper<>();//条件构造器

       /* wrapper.ge("usr_ent_id",3).isNotNull("usr_ste_usr_id").isNull("usr_bday");//ge、gt、le、lt、isNull、isNotNull
        userMapper.selectPage(page, wrapper);*/

       /* wrapper.eq("usr_ste_usr_id","admin");//eq、ne
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);*/

        /*wrapper.between("usr_ent_id",3,99);//between、notBetween包含大小边界
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);*/

       /* Map<String,Object> map = new HashMap<>();
        map.put("usr_ent_id",3);
        map.put("usr_ste_usr_id","admin");
        wrapper.allEq(map);//allEq
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);*/

       /* wrapper.like("usr_display_bil","gina").likeRight("usr_ste_usr_id","001");
        List<Map<String, Object>>  maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);*/

       // wrapper.in("usr_ent_id",3,4,5);
       /* wrapper.inSql("usr_ent_id","3,4,5");//in、notIn、inSql、notinSql、exists、notExists
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);*/

        User user = new User();
        //user.setBirth(new Date());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        //or、and 不调用or则默认为使用 and 连
        /*updateWrapper.like("usr_ste_usr_id","admin").or().between("usr_ent_id",1,100);*/

        //嵌套or、嵌套and
        /*updateWrapper.like("usr_ste_usr_id","admin")
                .or(i->i.eq("usr_ste_usr_id","admin").ne("usr_ent_id",33));
        */
        //set、setSql
        //最终的sql会合并 user.setAge()，以及 userUpdateWrapper.set()  和 setSql() 中 的字段
        updateWrapper
                .like("usr_ste_usr_id", "h")
                .set("usr_bday", new Date())//除了可以查询还可以使用set设置修改的字段
                .setSql(" usr_display_bil = '12'");//可以有子查询
        int update = userMapper.update(user, updateWrapper);
        System.out.println(update);

        //orderBy、orderByDesc、orderByAsc
        //wrapper.isNull("usr_bday").orderByDesc("usr_ent_id");

        //last 直接拼接到 sql 的最后
        //注意：只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
        //wrapper.last(",usr_ste_usr_id");

        //指定要查询的列
        //wrapper.select("usr_ent_id","usr_ste_usr_id");

        /*List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);*/

       /* userMapper.selectPage(page, wrapper);
          page.getRecords().forEach(System.out::println);*/
	}
	@Test
	public void contextLoads() {
		System.out.println(dataSource);
	}

}
