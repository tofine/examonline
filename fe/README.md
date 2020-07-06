# Gatsby Website Demo For Static Website
---

## 1. 克隆网站
如果有一个新的网站需要用到
* 第一步，创建一个新的网站的gitlab仓库，例如project
* 第二步，git clone 此项目到本地,项目名称为gatsby_demo
* 第三步，删掉gatsby_demo项目的.git文件夹，并修改项目名称为新项目的名称project
* 第四步，git init，重新init，并推送到新的网站仓库project的仓库

## 2.项目结构
- src 是项目文件的存放地点
  - assets 存放静态资源，便于引用
  - pages 所有的路由界面，每一个js文件，代表一个路由，深层次的文件夹代表深层次的路由
  - styles 存放所有路由的样式，采用scss写法
  - html.js 返回静态界面的html文件
- static 能够静态访问到的资源
  
## 3. 项目开发

* 开发环境 
 执行 <code>
 npm run start
</code>进行开发环境，进入界面开发  

* 生产环境
  执行 <code>
 npm run build
</code>进行代码构建，构建完生成的public发布即可 

## 4. 注意事项
* pages每个文件都是一个路由




