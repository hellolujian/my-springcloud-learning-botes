### 一、简介
Hystrix是Netflix开源的一款容错框架，包含常用的容错方法：线程池隔离、信号量隔离、熔断、降级回退。在高并发访问下，系统所依赖的服务的稳定性对系统的影响非常大，依赖有很多不可控的因素，比如网络连接变慢，资源突然繁忙，暂时不可用，服务脱机等。

### 二、demo
- ribbon中使用Hystrix       
启动类加`@EnableHystrix`开启注解      
![@EnableHystrix](https://user-gold-cdn.xitu.io/2019/5/30/16b083018b84240e?w=1003&h=291&f=png&s=32784)   
调用方法上加`@HystrixCommand`，并指定fallbackMethod熔断方法：
![](https://user-gold-cdn.xitu.io/2019/5/30/16b083353aa851db?w=1166&h=278&f=png&s=42884)    
正常调用接口&调用接口失败           
![](https://user-gold-cdn.xitu.io/2019/5/30/16b0837c84b753a6?w=1430&h=103&f=png&s=4289)
![](https://user-gold-cdn.xitu.io/2019/5/30/16b0838eb41decaa?w=1334&h=85&f=png&s=3406)
- Feign中使用Hystrix   
`@FeignClient`加上fallback指定类
![](https://user-gold-cdn.xitu.io/2019/5/30/16b0841d610eb129?w=747&h=142&f=png&s=24968)
指定类需要实现调用接口，降级方法与调用方法同名
![](https://user-gold-cdn.xitu.io/2019/5/30/16b0843677e2501f?w=728&h=133&f=png&s=16077)

- Hystrix Dashboard    
`@EnableHystrixDashboard`开启仪表盘     
![](https://user-gold-cdn.xitu.io/2019/5/30/16b084742593dedb?w=1335&h=623&f=png&s=125065) 
![](https://user-gold-cdn.xitu.io/2019/5/30/16b084811009bb10?w=1353&h=441&f=png&s=59544)
- Hystrix turbine

### 三、源码解析  
**1. @HystrixCommand**     
![](https://user-gold-cdn.xitu.io/2019/5/31/16b0c79803060b15?w=1031&h=688&f=png&s=72165)
在切面HystrixCommandAspect中拦截了@HystrixCommand     

```
    @Pointcut("@annotation(com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand)")
    public void hystrixCommandAnnotationPointcut() {
    }

    @Pointcut("@annotation(com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser)")
    public void hystrixCollapserAnnotationPointcut() {
    }

    @Around("hystrixCommandAnnotationPointcut() || hystrixCollapserAnnotationPointcut()")
    public Object methodsAnnotatedWithHystrixCommand(final ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethodFromTarget(joinPoint);
        Validate.notNull(method, "failed to get method from joinPoint: %s", joinPoint);
        if (method.isAnnotationPresent(HystrixCommand.class) && method.isAnnotationPresent(HystrixCollapser.class)) {
            throw new IllegalStateException("method cannot be annotated with HystrixCommand and HystrixCollapser " +
                    "annotations at the same time");
        }
        MetaHolderFactory metaHolderFactory = META_HOLDER_FACTORY_MAP.get(HystrixPointcutType.of(method));
        MetaHolder metaHolder = metaHolderFactory.create(joinPoint);
        HystrixInvokable invokable = HystrixCommandFactory.getInstance().create(metaHolder);
        ExecutionType executionType = metaHolder.isCollapserAnnotationPresent() ?
                metaHolder.getCollapserExecutionType() : metaHolder.getExecutionType();

        Object result;
        try {
            if (!metaHolder.isObservable()) {
                result = CommandExecutor.execute(invokable, executionType, metaHolder);
            } else {
                result = executeObservable(invokable, executionType, metaHolder);
            }
        } catch (HystrixBadRequestException e) {
            throw e.getCause();
        } catch (HystrixRuntimeException e) {
            throw hystrixRuntimeExceptionToThrowable(metaHolder, e);
        }
        return result;
    }
```


### 四、Hystrix特性
1. 断路器机制   
当Hystrix Command请求后端服务失败数量超过一定比例（默认50%），断路器会切换到开路状态（Open）。这时所有请求会直接失败而不会发送到后端服务，断路器保持在开路状态一段时间后（默认5秒），自动切换到半开路状态（HALF-OPEN）。这时会判断下一次请求的情况，如果请求成功，断路器切回闭路状态（CLOSED），否则重新切换到开路状态（OPEN）。Hystrix的断路器就像我们家庭电路中的保险丝，一旦后端服务不可用，断路器会直接切断请求链，避免发送无效请求影响 系统吞吐量，并且断路器有自我检测并恢复的能力。    
注：还有一个参数，用于设置在一个滚动窗口中，打开断路器的最少请求数（这里暂时称为滚动窗口最小请求数），这里举个具体的例子：如果滚动窗口最小请求数为20，在一个窗口内（比如10秒，统计滚动窗口的时间可以设置，见下面的参数详解），收到19个请求，即使这19个请求都失败了，此时请求错误率高达95%，但是断路器也不会打开。      
![](https://user-gold-cdn.xitu.io/2019/6/11/16b4543f3d10bbbf?w=827&h=486&f=png&s=77861)

2. Fallback   
Fallback相当于是降级操作。   
触发fallback方法的情况：     

    名字 | 描述 | 触发fallback 
    :-      | :- | :-: 
    EMIT     | 值传递 | N 
    SUCCESS | 执行完成，没有错误| N 
    FAILURE|执行抛出异常| Y
    TIMEOUT|执行开始，但是没有在允许的时间内完成| Y
    BAD_REQUEST|执行抛出HystrixBadRequestException| N
    SHORT_CIRCUITED|断路器打开，不尝试执行| Y
    THREAD_POOL_REJECTED|线程池拒绝，不尝试执行|Y
    SEMAPHORE_REJECTED|信号量拒绝，不尝试执行|Y
4. 资源隔离     
在Hystrix中，主要通过线程池来实现资源隔离，优点是调用服务的代码如果存在bug或者由于其他原因导致自己所在线程池被耗尽时，不会对系统的其他服务造成影响。但是带来的代价就是维护多个线程池会对系统带来额外的性能开销。如果对性能要有严格要求并且确信 自己调用服务端的客户端代码不会出现问题的话，可以使用Hystrix的信号模式（Semaphores）来隔离资源。       
为每个依赖提供一个小的线程池（或信号），如果线程池已满调用将被立即拒绝，默认不采用排队（使用SynchronousQueue和拒绝策略），加速失败判定时间（快速失败）。    
依赖调用结果分为：成功，失败（抛出异常），超时，线程拒绝，短路。请求失败时执行fallback（降级）逻辑。    
5. 设计原则    
- 防止单个服务的故障，耗尽整个系统服务的容器（比如tomcat）的线程资源。    
- 减少负载并快速失败，而不是排队。    
- 在可行的情况下提供回退以保护用户免受故障。    
- 使用隔离技术（如隔板，永道和断路器模式）来限制任何一个依赖的影响。   
- 通过近乎实时的指标，监控和报警来优化发现故障的时间。    
- 保护整个依赖客户端执行中的故障，而不仅仅是在网络流量上进行保护降级、限流   
![](https://user-gold-cdn.xitu.io/2019/6/6/16b2c763dca42b0f?w=1953&h=768&f=png&s=396239)

### 五、链接
- hystrix线程池队列配置：https://www.cnblogs.com/seifon/p/9921774.html，https://www.jianshu.com/p/3dfe6855e1c5



































