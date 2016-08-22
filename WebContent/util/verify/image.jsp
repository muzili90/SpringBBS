<%@ page contentType="image/jpeg" import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" pageEncoding="UTF-8"%>
<%!
Color getRandColor(int fc,int bc){//随机获得颜色，RGB格式
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
        }
%>
<%
//清除缓存，每次访问该页面时都从服务器端读取
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);

// 定义显示图片的宽度和高度
int width=60, height=20;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

// 画图画板
Graphics g = image.getGraphics();

//定义一个随机数
Random random = new Random();

// 设置画板背景颜色
g.setColor(getRandColor(200,250));
//设置画板的填充范围
g.fillRect(0, 0, width, height);

//设置字体
g.setFont(new Font("Times New Roman",Font.PLAIN,18));

// 设置线条颜色并画线，155条
g.setColor(getRandColor(160,200));
for (int i=0;i<155;i++)
{
 int x = random.nextInt(width);
 int y = random.nextInt(height);
        int xl = random.nextInt(12);
        int yl = random.nextInt(12);
 g.drawLine(x,y,x+xl,y+yl);
}

// 显示数字，4位长度
String sRand="";
for (int i=0;i<4;i++){
    String rand=String.valueOf(random.nextInt(10));
    sRand+=rand;
    // 设置每个数字的颜色
    g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
//在画板上写数字，起始位置
    g.drawString(rand,13*i+6,16);
}

// 保存进session
session.setAttribute("yzkeyword",sRand);
//System.out.println("yzm:"+sRand);


// 显示图片
g.dispose();

%>

<%
//转换成一张图片，格式为JPEG
ImageIO.write(image, "JPEG", response.getOutputStream());
out.clear();//清空缓存的内容。

pageContext.pushBody();
%>