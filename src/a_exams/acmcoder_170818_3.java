package a_exams;

/**
 * Created by xu on 18/08/2017.
 * <p>
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 有n个人去KTV唱歌，每个人都有自己想唱的一些歌曲。已知该KTV每个房间都只有x个麦克风，同一首歌可以同时多人一起唱，
 * 但是同时唱的人不能超过x人，同一时刻只能唱一首歌。一共只有y首歌的时间，所有人想唱的歌都唱完或者y首歌唱完了他们就会离开。
 * 他们想知道在最优的安排策略下（让每个人尽量唱完自己想唱的歌），当他们离开时是否还有人有想唱的歌没有唱。输入保证每个人想唱的歌都不同。
 * 输入
 * 第一行一个整数T，表示测试的数据组数1≤T≤10；
 * 对于每组测试数据，第一行三个整数n，x，y，含义见题面，1≤n≤100，1≤x≤100，1≤y≤1000；
 * 接下来n行按行从上到下顺序分别给出了第1到第n个人想唱的歌曲，其中每行开头一个整数a[i]表示第i个人想唱歌的数量，后面a[i]个整数，
 * 表示歌曲编号1≤a[i]≤10。KTV可选歌曲总数不超过1000，即编号不大于1000。
 * 输出
 * 对于每组测试数据，输出”YES”，表示离开时有人还有歌没唱完，否则输出”NO”。（不包括引号）。
 * <p>
 * 样例输入
 * 1
 * 3 3 3
 * 1 2
 * 1 3
 * 1 4
 * 样例输出
 * YES
 * <p>
 * Hint
 * 输入样例2：
 * 2
 * 1 1 1
 * 2 1 2
 * 2 2 1
 * 1 1
 * 1 1
 * 输出样例2：
 * NO
 * YES
 */
public class acmcoder_170818_3 {
}