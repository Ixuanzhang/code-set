package sword.ch6;

/**
 * a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
 * <p>
 * 首先看十进制是如何做的： 5+7=12，三步走
 * 第一步：相加各位的值，不算进位，得到2。
 * 第二步：计算进位值，得到10. 如果这一步的进位值为0，那么第一步得到的值就是最终结果。
 * 第三步：重复上述两步，只是相加的值变成上述两步的得到的结果2和10，得到12。
 * <p>
 * 同样我们可以用三步走的方式计算二进制值相加： 5-101，7-111 第一步：相加各位的值，不算进位，得到010，二进制每位相加就相当于各位做异或操作，101^111。
 * 第二步：计算进位值，得到1010，相当于各位做与操作得到101，再向左移一位得到1010，(101&111)<<1。
 * 第三步重复上述两步， 各位相加 010^1010=1000，进位值为100=(010&1010)<<1。
 * 继续重复上述两步：1000^100 = 1100，进位值为0，跳出循环，1100为最终结果。
 * <p>
 * https://www.nowcoder.com/questionTerminal/59ac416b4b944300b617d4f7f111b215
 */
public class P310_AddTwoNumbers {
    public int Add(int num1, int num2) {
        int sum = 0, carry = 0;
        while (num2 != 0) {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

    public int Add2(int num1, int num2) {
        return num2 == 0 ? num1 : Add(num1 ^ num2, (num1 & num2) << 1);
    }
}
