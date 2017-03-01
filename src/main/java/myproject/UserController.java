package myproject;

import io.swagger.annotations.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import po.User;

import java.util.*;

/**
 *
 *
	 @Api：用在类上，说明该类的作用
	 @ApiOperation：用在方法上，说明方法的作用
	 @ApiImplicitParams：用在方法上包含一组参数说明
	 @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
		 paramType：参数放在哪个地方
		 header-->请求参数的获取：@RequestHeader
		 query-->请求参数的获取：@RequestParam
		 path（用于restful接口）-->请求参数的获取：@PathVariable
		 body（不常用）
		 form（不常用）
		 name：参数名
		 dataType：参数类型
		 required：参数是否必须传
		 value：参数的意思
		 defaultValue：参数的默认值
	 @ApiResponses：用于表示一组响应
	 @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
	 code：数字，例如400
	 message：信息，例如"请求参数没填好"
	 response：抛出异常的类
	 @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
	 @ApiModelProperty：描述一个model的属性
  * Created by clb on 17-3-1.
 */
@SpringBootApplication
@RestController
@RequestMapping("/users")
@Api("userController相关api")
public class UserController {
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	@ApiOperation(value="获取用户列表", notes="")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<User> getUserList() {
		// 处理"/users/"的GET请求，用来获取用户列表
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
		List<User> r = new ArrayList<User>(users.values());
		return r;
	}

	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String postUser(@ModelAttribute User user) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		users.put(user.getId(), user);
		return "success";
	}

	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		// 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return users.get(id);
	}


	@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	})

	@ApiResponses({
	       @ApiResponse(code=400,message="请求参数没填好"),
	       @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
	    })
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}

	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		// 处理"/users/{id}"的DELETE请求，用来删除User
		users.remove(id);
		return "success";
	}


	public static void main(String[] args) {
		SpringApplication.run(UserController.class,args);
	}
}
