
import java.util.Optional;
import java.util.function.BiPredicate;

/**
 * Exam 1-3
 */
public class Exam1_3 {
	public static void main(String[] args) {
		final String input = "aaBBBcC";
		final Optional<String> result = maxSubstrWithCond(input, (left, right) -> {
			return left.equals(right);
		});

		System.out.println(result.orElse("none"));
	}

	/***
	 * 隣り合う文字が連続して条件を満たす最長の部分文字列を返す。<br/>
	 * 同一の長さの部分文字列があった場合は先頭の部分文字列を返す。
	 * @param inStr 対象文字列
	 * @param cond 隣り合う2つの文字を引数にブール値を返すBiPredicateインターフェースオブジェクト
	 * @return　部分文字列
	 */
	public static Optional<String> maxSubstrWithCond(String inStr, BiPredicate<String, String> cond) {
		int beginIdx = -1;
		int endIdx = -1;
		int maxLen = 0;
		int currentBeginIdx = -1;
		int currentEndIdx = -1;
		int currentMaxLen = 0;
		for(int i = 0; i < inStr.length() - 1; i++) {
			if(cond.test(String.valueOf(inStr.charAt(i)), String.valueOf(inStr.charAt(i + 1)))) {
				if(currentBeginIdx < 0) {
					currentBeginIdx = i;
				}
				currentEndIdx = i + 2;
				currentMaxLen += 1;
			} else {
				if(maxLen < currentMaxLen) {
					maxLen = currentMaxLen;
					beginIdx = currentBeginIdx;
					endIdx = currentEndIdx;
				}
				currentBeginIdx = -1;
				currentMaxLen = 0;
			}
		}
		
		if(maxLen < currentMaxLen) {
			maxLen = currentMaxLen;
			beginIdx = currentBeginIdx;
			endIdx = currentEndIdx;
		}
		
		return maxLen == 0 ? Optional.ofNullable(null) : Optional.of(inStr.substring(beginIdx, endIdx));
	}
}
