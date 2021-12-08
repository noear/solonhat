# solonhat-smartdoc 使用说明

- smartdoc是一个无代码侵入的文档生成工具，生成的文档是静态html/md 对项目运行无任何不良影响
- 目前版本暂只支持html生成


## 使用说明

#### 1、引用依赖

```xml
<dependency>
    <groupId>org.noear</groupId>
    <artifactId>solonhat-smartdoc</artifactId>
    <scope>test</scope>
</dependency>
```
#### 2、创建测试工具类

> copy src/test/java/test/Doc.java 到你的项目中的test代码目录，运行 的 Doc.generate 
    
## 输出

> 生成html及css文件


## 配置

- config.setServerUrl("http://localhost:8089") 设置服务URL，用于生成请求示例
- config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH); 设置输出路径，默认src/main/resources/static/doc


## 示例
    solonhat-smartdoc-demo
    