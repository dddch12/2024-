package com.cq.cd.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cq.cd.entity.Collection;
import com.cq.cd.entity.User;
import com.cq.cd.service.CollectionService;
import com.cq.cd.service.UserService;
import com.cq.cd.util.ApiResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    public UserService userService;
    @Test
    public void whetherVisible(){
        String setCode="1111";
        User user =new User();
        user.setUserName("用户2");
        int[] intCode =new int[4];
        for(int i=0;i<4;i++){
            char ch = setCode.charAt(i);
            intCode[i]=Character.getNumericValue(ch);
        }
        User user0 = userService.getuserbyName(user.getUserName());
        user0.setVisible(intCode[1]);
        user0.setChat(intCode[3]);
        boolean result = userService.updateById(user0);
        System.out.println(user0);
        if(result){
            System.out.println("成功");
        }
        else{
            System.out.println("失败");
        }
    }

    @Autowired
    public CollectionService collectionService;
    @Test
    public  void getCollectionTable(){
        String email ="user24@example.com";
        List<Collection> collections = collectionService.getCollectionTable(email);
        Map<String,Object> data = new HashMap<>();
        data.put("collection",data);
        System.out.println(collections);
    }
	//自动装配
//    @Autowired
//   	public UserMapper userMapper;
//
//	// 查询所有用户
//    @Test
//    public void getAllUsers() {
//        List<User> users = userMapper.selectList(null);
//        System.out.println("姓名");
//        users.forEach(user -> System.out.println(user.getUserName()));
//    }
//
//    // 根据用户名查询用户
//    @Test
//    public void getUserByUsername() {
//        String username = "Paul";
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
//        User user = userMapper.selectOne(queryWrapper);
//        System.out.println(user);
//    }
//
//    // 添加新用户
//    @Test
//    public void addUser() {
//        User user = new User();
//        user.setUserName("Paul");
//        user.setPassWord("222000");
//        user.setEmail("Paul@example.com");
//        int result = userMapper.insert(user);
//        System.out.println("Insert result: " + result);
//    }
//
//    // 更新用户信息
//    @Test
//    public void updateUser() {
//        String username = "Paul";
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
//        User user = userMapper.selectOne(queryWrapper);
//
//        if (user != null) {
//            user.setPassWord("445566");
//            user.setEmail("Paul@example.com");
//            int result = userMapper.updateById(user);
//            System.out.println("Update result: " + result);
//        }
//    }
//
//    // 删除用户
//    @Test
//    public void deleteUser() {
//        String username = "Paul";
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
//        int result = userMapper.delete(queryWrapper);
//        System.out.println("Delete result: " + result);
//    }
//	@Test
//	public void query(){
//		List<User> users = userMapper.selectList(null);
//		users.forEach(System.out::println);
//	}
//
//	//根据id获取信息
////    @Test
////   	public void getUserInfoById(){
////		User users = userMapper.selectById(1);
////		System.out.println(users);
////	}
//    @Autowired
//    public UserService userService;
//
//    @Test
//    public void savetest(){
//        // 假设有一个 User 实体对象
//        User user = new User();
//        user.setUserName("John Doe");
//        user.setPassWord("222000");
//        user.setEmail("john.doe@example.com");
//        boolean result = userService.save(user); // 调用 save 方法
//        if (result) {
//            System.out.println("User saved successfully.");
//        } else {
//            System.out.println("Failed to save user.");
//        }
//    }
//
//    @Test
//    public void saveOrUpdate(){
//        // 假设有一个 User 实体对象，其中 id 是 TableId 注解的属性
//        User user = new User();
//        user.setUserId(1);
//        user.setPassWord("999999");
//        boolean result = userService.saveOrUpdate(user); // 调用 saveOrUpdate 方法
//        if (result) {
//            System.out.println("User updated or saved successfully.");
//        } else {
//            System.out.println("Failed to update or save user.");
//        }
//    }
//
//    @Test
//    public void remove(){
//       QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//       queryWrapper.eq("Username", "Paul");
//       boolean result = userService.remove(queryWrapper);
//       if(result){
//           System.out.println("Record deleted successfully");
//       }else{
//           System.out.println("Failed to delete record");
//       }
//    }
//
//    @Test
//    public void removebyMap(){
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("Username", "John Doe");
//        columnMap.put("Password", "222000");
//        boolean result = userService.removeByMap(columnMap); // 调用 removeByMap 方法
//        if (result) {
//            System.out.println("Records deleted successfully.");
//        } else {
//            System.out.println("Failed to delete records.");
//        }
//    }
//
//    @Test
//    public void get(){
//        User user = userService.getById(1); // 调用 getById 方法
//        if (user != null) {
//            System.out.println("User found: " + user);
//        } else {
//            System.out.println("User not found.");
//        }
//    }
//    @Test
//    public void getone(){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("Username", "John Doe");
//        User user = userService.getOne(queryWrapper); // 调用 getOne 方法
//        if (user != null) {
//            System.out.println("User found: " + user);
//        } else {
//            System.out.println("User not found.");
//        }
//    }
//
//    @Test
//    public void list(){
//        List<User> user = userService.list();
//        user.forEach(System.out::println);
//    }
}
