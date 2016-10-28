##########Xcomplie介绍

Xcomplie is to help with the reverse engineering of executable code or binaries and find potential bugs with out run the program.

Xcompile是通过静态分析的方法检测二进制程序可能存在的漏洞或者bug。
借助自行定义的反编译器将二进制程序/机器码转化成容易理解的中间语言IR，之后静态分析器去抽象解释该IR，通过符号执行以及数据流分析的方法，能够在不运行该程序的
情况下检测出内存越界访问、对象类型混淆等类型的漏洞。

目前实现的反编译器可以将Intel x86-32 、 x86-64 、 AVR-8 架构的机器码（ELF以及PE格式的文件）转化成相应的IR语言。
其中反编译器使用的标准是Generic Decoder Specification。

##########命令行使用

调用该分析器的命令:   
'java -jar Xcomplie.jar <options> <file>'  
老规矩帮助命令：
'java -jar Xcomplie.jar --help'
其中在Xcomplie中实现了基本的编译、转化、分析、日志等命令，具体可以使用help命令查看。


########## GUI图形界面使用
图形界面主要还是方便查看代码的CFG（控制流图），可以显示出CFG，当然这个主要是借助谷歌的开源框架BiNNavi。但是在实际检测漏洞时，GUI不用也行。


########## 安装依赖
由于该工程集成一些其他开源框架，因此大多数的安装依赖都包含在其他开源框架中， 由于需要兼容，分析器主要是用 Java 语言写的，因此Java的环境依赖肯定需要.
当然，不同操作系统所需要的安装库也要装上，我使用的环境如下：
* JDK 1.7 (或者更高)
* Maven 3 build tool
其中，我还用到的开源框架库如下：
- [GDSL disassembler frontend](https://github.com/gdslang/gdsl-toolkit)  
- [Apron library of abstract domains](http://apron.cri.ensmp.fr/library/)  


	


