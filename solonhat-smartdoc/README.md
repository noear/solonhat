# solonhat-smartdoc 使用说明

- smartdoc是一个无代码侵入的文档生成工具，生成的文档是静态html/md 对项目运行无任何不良影响
- 目前版本暂只支持html生成


## 使用说明

#### 1、引用依赖

```xml
<dependency>
    <groupId>org.noear</groupId>
    <artifactId>solonhat-smartdoc</artifactId>
    <version>${solonhat.ver}</version>
    <scope>test</scope>
</dependency>
```
#### 2、创建测试工具类

* [Copy] src/test/java/test/Doc.java 到你的项目中的test代码目录

如果有需要，可以做些修改：
```java
//设置服务URL，用于生成请求示例
config.setServerUrl("http://localhost:8089");

//设置输出路径，默认为 src/main/resources/static/doc
config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH);

```

* 然后，[Run] Doc.generate 

#### 3、输出

> 生成html及css文件到：src/main/java/resources/static/doc 目录下



## 具体可参考：solonhat-smartdoc-demo
    