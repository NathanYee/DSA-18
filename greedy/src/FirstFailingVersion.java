public class FirstFailingVersion {

    private static long setMid(long min, long max) {
        return (max + min) / 2;
    }

    private static boolean correctN(long n, IsFailingVersion isBadVersion) {
        return !(!isBadVersion.isFailingVersion(n - 1) && isBadVersion.isFailingVersion(n));
    }

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long min = 1;
        long max = n;
        long mid = setMid(min, max);

        // keep going until mid is correct version number
        while (correctN(mid, isBadVersion)) {
            // check if mid is too large
            if (isBadVersion.isFailingVersion(mid)) {
                // binary search lower half
                max = mid;
                mid = setMid(min, max);
            } else {
                // binary search upper half
                min = mid;
                mid = setMid(min, max);
            }
        }

        return mid;
    }
}
