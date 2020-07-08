package com.example.demo.Common.leetCode;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.sun.corba.se.impl.oa.poa.POAPolicyMediatorImpl_NR_USM;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import sun.security.util.Resources_sv;

import java.security.KeyStore;
import java.util.*;

/**
 * @author ccjh1
 * @creat 2020/5/30
 */
public class largestRectangleArea {
    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * @param heights
     * @return
     */
    public static int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        //使用栈
        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        for (int i=0 ; i<nums.length-1 ; i++){
            for ( int j=i+1;j<nums.length;j++){
                if (nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                }
            }
        }
        return result;
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

     如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

     您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result= new ListNode(0);
        ListNode p= l1;
        ListNode q = l2;
        ListNode curr =result;
        int start = 0;
        while (p!=null || q!=null){
            int x =( p != null)? p.val : 0;
            int y =( q != null)? q.val : 0;
            int sum = start + x + y ;
            start =sum/10;
            curr.next = new ListNode(sum %10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (start > 0) {
            curr.next = new ListNode(start);
        }
        return  result.next;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。

     对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
     输入：candies = [2,3,5,1,3], extraCandies = 3
     输出：[true,true,true,false,true]
     解释：
     孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
     孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
     孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
     孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     */
    public  static  List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result =new ArrayList<>();
        int max =0,min=100;
        for (int i=0 ; i<candies.length ; i++){
                if ( candies[i] > max ){
                    max =candies[i];
                }
//                if ( candies[i] < min)  {
//                    min =candies [i];
//                }
        }
//        int array =max - min ;
        for (int i=0 ; i<candies.length ; i++){
            if ( candies[i] + extraCandies >= max ){
                result.add(true);
            }else {
                result.add(false);
            }
        }
        return  result;
    }

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    /**
     *给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

     请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

     你可以假设 nums1 和 nums2 不会同时为空。
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result =0;
//        int ar = ( nums1.length+nums2.length )/2;
        int[] endnum =new int[nums1.length+nums2.length];
        int i=0,j=0,k=0;
        while ( i<nums1.length || j<nums2.length){
            if (i ==nums1.length){
                endnum[k] =nums2[j];
                j++;
            }else if ( j ==nums2.length ){
                endnum[k]=nums1[i];
                i++;
            }else if (nums1[i] < nums2[j] )  {
                endnum[k]=nums1[i];
                i++;
            } else {
                endnum[k] =nums2[j];
                j++;
            }
            k++;
        }
        System.out.println(endnum.length%2);
        if (endnum.length %2 != 0){
            return endnum[endnum.length/2];
        }else {
            double a = endnum[endnum.length / 2];
            double b = endnum[endnum.length / 2 - 1];
            return (a + b) / 2;
        }
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 暴力解法
     */
    public String longestPalindrome(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (!isPalindromic(test)){
                    break;
                }else if(test.length() > max){
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
//                if (isPalindromic(test) && test.length() > max) {
//                    ans = s.substring(i, j);
//                    max = Math.max(max, ans.length());
//                }
            }
        return ans;
    }
    /**
     *   判断是否是回文字符串
     */
    public boolean isPalindromic(String s){
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    //求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
    public int sumNums(int n) {
//        return (1 + n) * n / 2;

        if(n == 1) return 1;
        n += sumNums(n - 1);
        return n;
    }

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

     比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

     L   C   I   R
     E T O E S I I G
     E   D   H   N
     之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     */
    public  static String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }


    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

     说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for ( int i = 0 ;  i < result.length ; i++){
            int temp =1;
            for ( int j = 0 ;  j < result.length ; j++){
                if (i!=j) temp *=nums[j];
            }
            result[i] = temp;
        }
        return  result;
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

     示例 1：

     输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     输出：[1,2,3,6,9,8,7,4,5]
     */
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while(true) {
            for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if(++t > b) break;
            for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if(l > --r) break;
            for(int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if(t > --b) break;
            for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if(++l > r) break;
        }
        return res;
    }

    /**
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     要求算法的时间复杂度为 O(n)。
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    /**
     *
     请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     提示：
     本题中的空白字符只包括空格字符 ' ' 。
     假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     */
    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        if (!Character.isDigit(str.charAt(0))
                && str.charAt(0) != '-' && str.charAt(0) != '+')
            return 0;
        long ans = 0L;
        boolean neg = str.charAt(0) == '-';
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            ans = ans * 10 + (str.charAt(i++) - '0');
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }


    /**
     *给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

     只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
     */
    public boolean equationsPossible(String[] equations) {
        if ( equations.length == 0 ) return  false;
        Set<Character> set =new HashSet<>();
        for ( String str : equations) {
            if (!set.contains(str.charAt(0))) {
                set.add(str.charAt(0));
            }
            if (!set.contains(str.charAt(3))) {
                set.add(str.charAt(3));
            }
        }
        return false;
    }


    /**
    判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     */
    public static boolean isPalindrome(int x) {
        boolean result =true ;
//        if(x<0){
//            return false;
//        }
        String str =String.valueOf(x);
        for ( int i = 0 ,j = str.length()-1 ; i<j ; i++,j-- ){
            if (str.charAt(i) != str.charAt(j)){
                result= false;
                break;
            }
        }
        return  result;
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

     说明：你不能倾斜容器，且 n 的值至少为 2。
     */
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

    /**
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

     例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

     提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

     */
    public static int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        for ( int i = 0 ; i < T.length ; i++ ){
            for ( int j= i+1 ; j < T.length ; j++ ){
                if ( T[j] > T[i] ) {
                    res[i] = j-i;
                    break;
                }
            }
        }
        return  res;
    }


    /**
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     示例 1
     输入：[-4,-1,0,3,10]
     输出：[0,1,9,16,100]
     示例 2：
     输入：[-7,-3,2,3,11]
     输出：[4,9,9,49,121]
     */
    public static int[] sortedSquares(int[] A) {
        //进行排序
        for ( int i = 0; i< A.length-1; i++ ){
            for ( int j = i+1 ; j < A.length ; j++ ){
                if ( A[i] < 0 ) A[i] =0- A[i];
                if ( A[j] < 0 ) A[j] =0 - A[j];
                System.out.println(A[i]);
                if ( A[i] > A[j] ){
                     int temp = A[i];
                     A[i] = A[j] ;
                     A[j] = temp;
                }
            }
        }
        for ( int i = 0; i< A.length ; i++ ){
//            System.out.println(A[i]);
            A[i] *=A[i];
        }
        return  A;
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

     注意：答案中不可以包含重复的三元组。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result =new ArrayList<List<Integer>>();
        //先排序
        for ( int i = 0 ; i < nums.length ; i++ ){
            for ( int j = i+1 ; j < nums.length ; j++ ){
                if ( nums[i] > nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        for ( int i = 0 ; i < nums.length-1; i++ ){
            if ( i > 0 && nums[i] == nums[i-1])  {
//                System.out.println(i+"==="+(i-1));
                continue;
            }
            for ( int j = i+1 ; j < nums.length ; j++ ){
                if ( i > 0 && nums[i] == nums[i-1]) {
//                System.out.println(i+"==="+(i-1));
                    continue;
                }
                    for ( int k = j+1 ; k < nums.length ; k++ ){
                    if( nums[i] + nums[j] + nums[k] == 0){
                        List<Integer> re =new ArrayList<>();
                        re.add(nums[i]);
                        re.add(nums[j]);
                        re.add(nums[k]);
                        result.add(re);
                    }
                }
            }
        }
        return result;
    }


    //冒泡排序算法
    public static int[] Bubblesort ( int[] A){
        for ( int i = 0; i< A.length-1; i++ ){
            for ( int j = i+1 ; j < A.length ; j++ ){
                if ( A[i] < 0 ) A[i] =0- A[i];
                if ( A[j] < 0 ) A[j] =0 - A[j];
                System.out.println(A[i]);
                if ( A[i] > A[j] ){
                    int temp = A[i];
                    A[i] = A[j] ;
                    A[j] = temp;
                }
            }
        }
        return  A;
    }

    /**
     * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。

     如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。

     请注意，答案不一定是 arr 中的数字。
     */
    public static  int findBestValue(int[] arr, int target) {
        //先排序
        Arrays.sort(arr);
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }
        int r = arr[n - 1];
        int ans = 0, diff = target;
        for (int i = 1; i <= r; ++i) {
            int index = Arrays.binarySearch(arr, i);
            if (index < 0) {
                index = -index - 1;
            }
            int cur = prefix[index] + (n - index) * i;
            if (Math.abs(cur - target) < diff) {
                ans = i;
                diff = Math.abs(cur - target);
            }
        }
        return ans;
    }

    /**
     *
     给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。

     数学表达式如下:

     如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
     使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
     说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
     */
    public static boolean increasingTriplet(int[] nums) {
        boolean result =false;
        int min1 = Integer.MAX_VALUE,min2 =min1;
        for ( int i = 1 ; i < nums.length ; ++i){
            if ( min1 >= nums[i] ) min1 =nums[i];
            else  if ( min2 >= nums[i] ) min2 = nums[i];
            else  result =false;
//            System.out.println("min=="+ min + ",min2=="+min2+",i=="+i+","+nums[i]);
        }
        return result;
    }




    public static void main(String[] args){
        String st= "2,5,3,4,5";
        String[] str =st.split(",");
        int[] a =new int[str.length];
        for (  int i = 0 ; i < a.length ; i++ ) {
            a[i] = Integer.valueOf(str[i]);
//            System.out.println(a[i]);
        }
        System.out.println(increasingTriplet(a));
    }
}
