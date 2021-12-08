# solonhat-smartdoc 使用说明

- smartdoc是一个无代码侵入的文档生成工具，生成的文档是静态html/md 对项目运行无任何不良影响
- 目前版本暂只支持html生成


## 引用
    引用时，scope可设置为test，package时不用打包进去

## 使用
    copy Doc.java到你的项目中的test代码目录，运行 的Doc.testBuilderControllersApi 
    
## 输出
    生成html及css文件


## 配置

- config.setServerUrl("http://localhost:8089") 设置服务URL，用于生成请求示例
- config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH); 设置输出路径，默认src/main/resources/static/doc


## 示例
    solonhat-smartdoc-demo
    