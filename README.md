# test

用来练习 git 命令，模拟真实环境

## 快速排序（Quick Sort）

本仓库包含一个 Java 实现的快速排序示例：

- `QuickSort.java`：`QuickSort.sort(int[])` 对整数数组进行原地升序排序，支持 `null`、空数组和重复元素。
- 随机选择基准值 + 三路划分，平均时间复杂度为 O(n log n)，最坏情况为 O(n²)，额外空间为 O(log n)。
- `QuickSortTest.java`：不依赖第三方库的测试程序。

### 环境要求

JDK 8 及以上。

### 编译与运行

```bash
# 编译
javac QuickSort.java

# 使用内置示例数据
java QuickSort

# 排序自定义数据
java QuickSort 5 3 9 1
```

内置示例输出：

```text
Input:  [10, 7, 8, 9, 1, 5, 3, 6, 2, 4]
Sorted: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```

### 运行测试

```bash
javac QuickSortTest.java
java QuickSortTest
```

测试覆盖空数组、单元素、重复元素、负数、整数极值、已排序/逆序数组以及随机数组（结果与 `Arrays.sort` 对比）。
