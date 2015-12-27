import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * Inclusion-Exclusion principle implemented in Java 8
 * 
 * @author Vincent Poon
 *
 */
public class Sets {
	
	/**
	 * 
	 * @param n number of integers
	 * @param exclusions integers whose multiples should not be counted
	 * @return number of integers left over
	 */
	public static long countInclExcl(long n, Set<Long> exclusions) {
		//get only prime factor exclusions
		Set<Long> reducedExcl = exclusions.stream()
		.flatMap(i -> (i == 1 || isPrime(i)) ? Stream.of(i) : getPrimeFactors(i).stream())
		.collect(Collectors.toSet());

		Set<Set<Long>> powerSet = powerSetRecursive(reducedExcl);

		long sum = IntStream.rangeClosed(1, reducedExcl.size()).map(
				i -> 
				(int) powerSet.stream()
					.filter(s -> s.size()==i)
					.map(s -> s.stream().reduce(1L,(a,b) -> a*b))
					.mapToLong(p -> n/p)
					.sum() * (int)Math.pow(-1, i)
				
				).sum();		
		return n + sum;
	}

	/**
	 * Generates the powerset of a given set.
	 * @param originalSet
	 * @return
	 */
	public static <T> Set<Set<T>> powerSetRecursive(Set<T> originalSet) {
		//return empty set in base case
		if (originalSet.isEmpty()) return Stream.of(new HashSet<T>()).collect(Collectors.toSet());

		List<T> set = originalSet.stream().collect(Collectors.toList()); //list to ensure order

		return powerSetRecursive(set.stream().skip(1).collect(Collectors.toSet()))
				.stream()
				.flatMap(s -> {
					HashSet<T> dup = new HashSet<>(s);
					dup.add(set.get(0));
					return Stream.of(s,dup);
				})
				.collect(Collectors.toSet());
	}
	
	public static Set<Long> getPrimeFactors(long n) {
		Set<Long> factors = new HashSet<>();
		for (long i = 2; i >= n; i++) {
			factors.add(i);
			n /= i;
		}
		return factors;
	}
	
	public static boolean isPrime(long n) {
		if (n < 2) return false;
		if (n == 2) return true;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) return false; 
		}
		return true;
	}

}