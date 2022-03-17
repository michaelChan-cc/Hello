# [Spring Bean生命周期，好像人的一生](https://www.cnblogs.com/three-fighter/p/16007800.html)

大家好，我是老三，上节我们手撸了一个简单的IOC容器[五分钟，手撸一个Spring容器！](https://mp.weixin.qq.com/s/MYUfMxyaaJzEwGXA2VHv-w)，这节我们来看一看Spring中Bean的生命周期，我发现，和人的一生真的很像。

# 简单说说IoC和Bean

IoC，控制反转，想必大家都知道，所谓的**控制反转**，就是把new对象的权利交给容器，所有的对象都被容器控制，这就叫所谓的控制反转。

![控制反转](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220303092314.png)

Bean，也不是什么新鲜玩意儿，它们就是一帮身不由己的Java对象，生命周期受到容器控制。

# Bean生命周期和人生

## Bean生命周期四大阶段

我们知道，bean的作用域有好几种，这篇文章只讨论完全被IoC容器控制的单例Bean。

对于普通的Java对象来说，它们的生命周期就是：

- 实例化
- 对象不再被使用时通过垃圾回收机制进行回收

这就像是生活在大自然里的动物，悄然出生，悄然死亡。

![大象-图片来源网络](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220303093415.jpeg)

而对于Spring Bean的生命周期来说，可以分为四个阶段，其中初始化完成之后，就代表这个Bean可以使用了：

- 实例化 Instantiation
- 属性赋值 Populate
- 初始化 Initialization
- 销毁 Destruction

人和动物不一样，存在非常复杂的社会。

![高楼大厦中的行人](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220306112453.png)

我们来看看社会里的人，一生要经历哪些阶段，是不是和Bean的生命周期很像呢？

- 出生：作为一个自然人降临在这个世界
- 登记：登记身份证号，姓名，正式成为人类社会的一份子
- 成长：接受教育，成为对社会有用的人
- 工作：为社会创造价值
- 死亡：人死如灯灭，不过人这盏灯灭了，还要把灯台埋起来

![image-20220303101042089](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220303101043.png)

Bean实例化的时机也分为两种，BeanFactory管理的Bean是在使用到Bean的时候才会实例化Bean，ApplicantContext管理的Bean在容器初始化的时候就回完成Bean实例化。

BeanFactory就是相对不那么健全的原始一些的社会，ApplicantContext是发达健全的现代社会。

![BeanFactory和Applicantcontext](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220306111151.png)

## Bean详细生命周期

我们讲到了Bean容器四个阶段，会有一些容器级的方法，进行前置和后置的处理，比如InstantiationAwareBeanPostProcessor、BeanPostProcessor接口方法。这些方法独立于Bean之外，并且会注册到Spring容器中，在Spring容器创建Bean的时候，进行一些处理。

![后处理器](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220311081438.png)

这就好像，孩子出生之前，需要做一些准备，比如备孕、养胎、备产什么的，出生之后，需要做一些护理。孩子上学前后，也需要做一些学籍的管理。

那么有了各种各样的扩展之后，我们再接着看看Bean的详细的生命周期。首先，我们面临一个问题——Bean的生命周期从什么时候开始的呢？

上面写了，Bean实例化前后，可以进行一些处理，但是如果从Bean实例化前算开始，那么就要追溯到容器的初始化、beanDefiinition的加载开始。

所以这篇文章里，我们取生命周期直接从Bean实例化开始，但是大家也要知道，Bean实例化前后，可以使用后处理器进行处理，例如BeanFactoryPostProcessor、InstantiationAwareBeanPostProcessor。

大家也不要困扰，就像计算人生的起点，是从母亲怀孕算起，还是从孩子出生算起？我们这里取了出生开始而已。

![Bean生命周期](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220306152802.png)

- **实例化**：第 1 步，实例化一个 Bean 对象
- **属性赋值**：第 2 步，为 Bean 设置相关属性和依赖
- **初始化**：初始化的阶段的步骤比较多，5、6步是真正的初始化，第 3、4 步为在初始化前执行，第 7 步在初始化后执行，初始化完成之后，Bean就可以被使用了
- **销毁**：第 8~10步，第8步其实也可以算到销毁阶段，但不是真正意义上的销毁，而是先在使用前注册了销毁的相关调用接口，为了后面第9、10步真正销毁 Bean 时再执行相应的方法

我们发现Bean生命周期的详细过程，是不是也像人生的历程，出生、登记，不过是很短的事情。慢慢长大成人，要经历人生的四分之一，而成长，来源于教育，不管是学校的还是社会的，接受教育前，要登记学籍，上学的时候，自己还要努力……，到最后，要发一纸薄薄的文凭，标志着我们成为可以捶打的“社会人”。

然后，为社会奉献四十年。最后老去，离世。不过Bean的世界，没有退休——当然，也许，人的世界也没有退休。

![人的曲线](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220306155710.png)

我们发现中间的一些扩展过程也可以分四类：

![Bean周期四类过程](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220311081609.png)

- **一：获取社会资源/Aware接口**：Aware接口的作用是让Bean能拿到容器的一些资源，例如BeanNameAware可以拿到BeanName。就好像上学之前，要取一个学名——不知道多少人上学之前不知道自己大名叫什么，是吧？二毛。
- **二：必备各种手续和证/后处理器**：在Bean的生命周期里，会有一些后处理器，它们的作用就是进行一些前置和后置的处理，就像上学之前，需要登记学籍，上学之后，会拿到毕业证。
- **三：个人选择/生命周期接口**：人可能无法选择如何出生，但也许可以选择如何活着和如何死去，InitializingBean和DisposableBean 接口就是用来定义初始化方法和销毁方法的。
- **四：主观能动/配置生命周期方法**：环境影响人，人也在影响环境，成长的时候认真努力，衰亡的时候也可以豁达乐观。可以通过配置文件，自定义初始化和销毁方法。

# PersonBean的一生

话不多说，接下来我们拿一个例子，来看看PersonBean的一生，我们先来看一下它的流程！

![PersonBean的一生](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220311085133.jpg)

用文字描述一下这个过程：

1. Bean容器在配置文件中找到Person Bean的定义，这个可以说是妈妈怀上了。
2. Bean容器使用Java 反射API创建Bean的实例，孩子出生了。
3. Person声明了属性no、name，它们会被设置，相当于注册身份证号和姓名。如果属性本身是Bean，则将对其进行解析和设置。
4. Person类实现了`BeanNameAware`接口，通过传递Bean的名称来调用`setBeanName()`方法，相当于起个学名。
5. Person类实现了`BeanFactoryAware`接口，通过传递BeanFactory对象的实例来调用`setBeanFactory()`方法，就像是选了一个学校。
6. PersonBean实现了BeanPostProcessor接口，在初始化之前调用用`postProcessBeforeInitialization()`方法，相当于入学报名。
7. PersonBean类实现了`InitializingBean`接口，在设置了配置文件中定义的所有Bean属性后，调用`afterPropertiesSet()`方法，就像是入学登记。
8. 配置文件中的Bean定义包含`init-method`属性，该属性的值将解析为Person类中的方法名称，初始化的时候会调用这个方法，成长不是走个流程，还需要自己不断努力。
9. Bean Factory对象如果附加了Bean 后置处理器，就会调用`postProcessAfterInitialization()`方法，毕业了，总得拿个证。
10. Person类实现了`DisposableBean`接口，则当Application不再需要Bean引用时，将调用`destroy()`方法，简单说，就是人挂了。
11. 配置文件中的Person Bean定义包含`destroy-method`属性，所以会调用Person类中的相应方法定义，相当于选好地儿，埋了。

我们来看看代码！

## PersonBean类

创建一个PersonBean，让它实现几个特殊的接口，我们来观察一下它的生命周期的流转。

```java
public class PersonBean implements InitializingBean, BeanFactoryAware, BeanNameAware, DisposableBean {

    /**
     * 身份证号
     */
    private Integer no;

    /**
     * 姓名
     */
    private String name;

    public PersonBean() {
        System.out.println("1.调用构造方法：我出生了！");
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("2.设置属性：我的名字叫"+name);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3.调用BeanNameAware#setBeanName方法:我要上学了，起了个学名");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4.调用BeanFactoryAware#setBeanFactory方法：选好学校了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6.InitializingBean#afterPropertiesSet方法：入学登记");
    }

    public void init() {
        System.out.println("7.自定义init方法：努力上学ing");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9.DisposableBean#destroy方法：平淡的一生落幕了");
    }

    public void destroyMethod() {
        System.out.println("10.自定义destroy方法:睡了，别想叫醒我");
    }

    public void work(){
        System.out.println("Bean使用中：工作，只有对社会没有用的人才放假。。");
    }

}
```

- 实现了InitializingBean, BeanFactoryAware, BeanNameAware, DisposableBean四个接口
- 定义了no、name两个属性和对应的getter、setter方法
- 定义了一个实例方法work

## MyBeanPostProcessor

自定义了一个后处理器MyBeanPostProcessor：

```java
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5.BeanPostProcessor.postProcessBeforeInitialization方法：到学校报名啦");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("8.BeanPostProcessor#postProcessAfterInitialization方法：终于毕业，拿到毕业证啦！");
        return bean;
    }
}
```

## 配置文件

定义一个配置文件spring-config.xml：

- 使用setter注入
- 定义init-method和destroy-method

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean name="myBeanPostProcessor" class="cn.fighter3.spring.life.MyBeanPostProcessor" />
    <bean name="personBean" class="cn.fighter3.spring.life.PersonBean"
          init-method="init" destroy-method="destroyMethod">
        <property name="idNo" value= "80669865"/>
        <property name="name" value="张铁钢" />
    </bean>

</beans>
```

## 测试

最后测试一下，观察PersonBean的生命周期的流转：

```java
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        PersonBean personBean = (PersonBean) context.getBean("personBean");
        personBean.work();
        ((ClassPathXmlApplicationContext) context).destroy();
    }
}
```

运行结果：

```java
1.调用构造方法：我出生了！
2.设置属性：我的名字叫张铁钢
3.调用BeanNameAware#setBeanName方法:我要上学了，起了个学名
4.调用BeanFactoryAware#setBeanFactory方法：选好学校了
5.BeanPostProcessor#postProcessBeforeInitialization方法：到学校报名啦
6.InitializingBean#afterPropertiesSet方法：入学登记
7.自定义init方法：努力上学ing
8.BeanPostProcessor#postProcessAfterInitialization方法：终于毕业，拿到毕业证啦！
Bean使用中：工作，只有对社会没有用的人才放假。。
9.DisposableBean#destroy方法：平淡的一生落幕了
10.自定义destroy方法:睡了，别想叫醒我
```

看看，是不是和我们图中的流程一致。

这篇文章就不带大家跟进更多的源码了，如果大家对源码级别的Bean的生命周期感兴趣，可以看看`AbstractApplicationContext`类里的`refresh`方法，这个方法是AplicationContext容器初始化的关键点。在这个方法里，调用了`finishBeanFactoryInitialization`方法，这个方法里调用了`getBean`方法，`getBean`方法里调用了`AbstractBeanFactory`的`getBean`方法。

![Bean生命周期源码追踪](D:\JetBrains\IntelliJ_directory\Learning\doc\origin\spring\springbean\Spring Bean生命周期.assets\20220313215741.png)

最终经过一阵七拐八绕，到达了我们的目标——Bean创建的方法：`doGetBean`方法，在这个方法里可以看到Bean的实例化，赋值、初始化的过程，至于最终的销毁，可以看看`ConfigurableApplicationContext#close()`。

# 结语

到这，这篇Bean的生命周期文章就走向destory了，自定义destory方法——回顾一下这篇文章的“一生”。

- Bean的生命周期大致可以分为四个阶段：实例化、属性赋值、初始化、销毁，对应人生的出生、登记、成长、离世。
- Bean生命周期中可以有很多扩展，就像人生的走向，会受很多影响，社会的环境、自身的选择、自己的努力。