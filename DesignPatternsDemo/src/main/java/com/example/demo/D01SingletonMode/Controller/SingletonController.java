package com.example.demo.D01SingletonMode.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.D01SingletonMode.Instance.Signletion;

/**
 * @author wang.pengfei
 *	单例模式
 */
@RestController
@RequestMapping(value = "/singleton")
public class SingletonController {
	
	@RequestMapping(method = RequestMethod.GET)
	public void getResult() {
		for (int i = 0; i < 15; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					Signletion signletion = Signletion.getInstance();
					signletion.print();
				}
			};
			runnable.run();
		}

	}
}
