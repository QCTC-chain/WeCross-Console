![](docs/images/menu_logo_wecross.png)

# WeCross-Console

[![CodeFactor](https://www.codefactor.io/repository/github/webankblockchain/WeCross-Console/badge)](https://www.codefactor.io/repository/github/webankblockchain/WeCross-Console) [![Build Status](https://travis-ci.org/WeBankBlockchain/WeCross-Console.svg?branch=dev)](https://travis-ci.org/WeBankBlockchain/WeCross-Console) [![Latest release](https://img.shields.io/github/release/WeBankBlockchain/WeCross-Console.svg)](https://github.com/WeBankBlockchain/WeCross-Console/releases/latest)
[![License](https://img.shields.io/github/license/WeBankBlockchain/WeCross-Console)](https://www.apache.org/licenses/LICENSE-2.0) [![Language](https://img.shields.io/badge/Language-Java-blue.svg)](https://www.java.com)

WeCross Console是[WeCross](https://github.com/WeBankBlockchain/WeCross)的重要交互式客户端工具。

## 关键特性

- 支持交互式命令
- 提供了针对跨链资源的操作命令

## 部署使用

可直接下载WeCross控制台压缩包，然后解压并使用，具体请参考[部署和使用文档](https://wecross.readthedocs.io/zh_CN/latest/docs/tutorial/networks.html#id7)。

## 源码编译

**环境要求**:

  - [JDK8及以上](https://www.oracle.com/java/technologies/javase-downloads.html)
  - Gradle 5.0及以上

**编译命令**:

```bash
# 由于更改过 wecross-java-sdk 源码，需要编译 wecross-java-skd 后将 dist/apps 和 dist/lib 目录下的 jar 包
# 拷贝值当前工程 lib 目录下
$ cd WeCross-Console
$ ./gradlew assemble
```

如果编译成功，将在当前目录的dist/apps目录下生成控制台jar包。

## 项目贡献

欢迎参与WeCross社区的维护和建设：

- 提交代码(Pull requests)，可参考[代码贡献流程](CONTRIBUTING.md)以及[wiki指南](https://github.com/WeBankBlockchain/WeCross/wiki/%E8%B4%A1%E7%8C%AE%E4%BB%A3%E7%A0%81)
- [提问和提交BUG](https://github.com/WeBankBlockchain/WeCross/issues/new)

您将成为贡献者，感谢各位贡献者的付出：

<img src="https://contrib.rocks/image?repo=WeBankBlockchain/WeCross-Console" alt="https://github.com/WeBankBlockchain/WeCross-Console/graphs/contributors" style="zoom:100%;" />

## 开源社区

参与meetup：[活动日历](https://github.com/WeBankBlockchain/WeCross/wiki#%E6%B4%BB%E5%8A%A8%E6%97%A5%E5%8E%86)

学习知识、讨论方案、开发新特性：[联系微信小助手，加入跨链兴趣小组（CC-SIG）](https://wecross.readthedocs.io/zh_CN/latest/docs/community/cc-sig.html#id3)

## License

WeCross Console的开源协议为Apache License 2.0，详情参考[LICENSE](./LICENSE)。
