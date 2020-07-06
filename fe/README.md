# Gatsby Website Demo For Static Website
---

## 项目结构
- src 是项目文件的存放地点
  - assets 存放静态资源，便于引用
  - pages 所有的路由界面，每一个js文件，代表一个路由，深层次的文件夹代表深层次的路由
  - styles 存放所有路由的样式，采用scss写法
  - html.js 返回静态界面的html文件
- static 能够静态访问到的资源
  
## 项目开发

* 开发环境 
 执行 <code>
 npm run start
</code>进行开发环境，进入界面开发  

* 生产环境
  执行 <code>
 npm run build
</code>进行代码构建，构建完生成的public发布即可 

## 注意事项
* pages每个文件都是一个路由

## 编码规范
html、css、js文件tab均为2个空格
js命名采用小驼峰形式，eg：userName
css类名采用-形式，eg：item-name




