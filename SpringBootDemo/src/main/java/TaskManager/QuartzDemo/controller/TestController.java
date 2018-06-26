/**
 * @author wang.pengfei
 *
 */
package TaskManager.QuartzDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }	
	@RequestMapping("/springBoot")
	public String springBoot() {
		return "this a demo for springBoot";		
	}
}