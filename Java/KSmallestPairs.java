import java.util.*;

class Pair {
    int num1;
    int num2;
    int sum;

    Pair(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
        this.sum = num1 + num2;
    }
}

public class KSmallestPairs {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // Edge case
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Min Heap storing [sum, index1, index2]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add first row (nums1[i] + nums2[0])
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        // Extract k smallest sums
        while (k-- > 0 && !pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[1], j = top[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // Move to next element in nums2
            if (j + 1 < nums2.length) {
                pq.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }

        return result;
    }

    // Driver code
    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        List<List<Integer>> res = kSmallestPairs(nums1, nums2, k);
        System.out.println("K Pairs with Smallest Sums: " + res);
    }
}
