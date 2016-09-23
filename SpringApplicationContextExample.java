package com.devdaily.springtest1;

import com.devdaily.springtest1.dao.FileEventDao;
import com.devdaily.springtest1.bean.FileEventType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContextExample
{

  public static void main (String[] args)
  {
    new Main();
  }

  public SpringApplicationContextExample()
  {
    // open/read the application context file
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    // instantiate our spring dao object from the application context
    FileEventDao fileEventDao = (FileEventDao)ctx.getBean("fileEventDao");

    // create a FileEventType object from the application context
    FileEventType fileEventType = (FileEventType)ctx.getBean("fileEventType");

    // insert the file event with the spring dao
    fileEventDao.doInsert(fileEventType);
  }

}