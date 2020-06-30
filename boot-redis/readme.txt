
(1) Spring Cache 结合Redis缓存
   案例地址：http://www.macrozheng.com/#/reference/spring_data_redis
   常用注解：
   1. @Cacheable : 将方法参数结果缓存起来，下一次方法执行参数相同时，将不执行方法，返回缓存中的结果
   2. @CacheEVict : 清空指定缓存
   3. @CachePut ： 标记该注解的方法总会执行，根据注解的配置将结果缓存
   4. @Caching : 可以指定相同类型的多个缓存注解，例如根据不同的条件

   5. @CacheConfig : 类级别的注解，可以设置一些共同的配置，@CacheConfig(cacheNames = "user"),代表该类下的方法均使用这个cacheNames
   6. @EnableCache : 开启缓存功能，一般放在启动类上