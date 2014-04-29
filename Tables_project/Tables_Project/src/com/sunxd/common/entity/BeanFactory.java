package com.sunxd.common.entity;
import java.util.HashMap;
import java.util.Map;





public abstract class BeanFactory {
  protected abstract IMysqlBean create();
  private static Map staticFacMap = new HashMap();
  public static void 
  addFactory(String id, BeanFactory f) {
    staticFacMap.put(id, f);
  }
  // A Template Method:
  public static final 
  IMysqlBean createBean(String id) {
    if(!staticFacMap.containsKey(id)) {
      try {
        // Load dynamically
        Class.forName("com.entity." + id);//读入后自动生成该类，该类会把自己注册到Factory的map里
      } catch(ClassNotFoundException e) {
        throw new RuntimeException(
          "Bad bean creation: " + id);
      }
      // See if it was put in:
      if(!staticFacMap.containsKey(id))
        throw new RuntimeException(
          "Bad bean creation: " + id);
    }
    return 
      ((BeanFactory)staticFacMap.get(id)).create();
  }
}