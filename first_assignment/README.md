
Used recursion: first go deep by dividing by 10, then print the last digit on the way back. This way digits are printed in the correct order.

![first](image.png)

Used recursion to sum array elements from the last to the first. Then divided the total sum by n to get the average.

![second](image-1.png)

Recursively checked divisibility starting from n-1 down to 2. If n is divisible by any number, return "Composite". If we reach 1, return "Prime".

![third](image-2.png)

Classic recursive factorial: n * fact(n-1), base case is n == 1.

![fourth](image-3.png)

Classic recursive Fibonacci: fib(n-1) + fib(n-2), base cases are fib(0) = 0 and fib(1) = 1.

![fifth](image-4.png)

Used recursion: multiply n by itself count times. Base case is count == 0, which returns 1.

![sixth](image-5.png)

Used recursion: printed the element at index n-1 first, then called rec with n-1. This prints the array in reverse order.

![seventh](image-6.png)

Recursively checked each character with Character.isDigit(). If any character is not a digit, return "No". If we reach the end, return "Yes".

![eighth](image-7.png)

Used recursion: removed the first character each call and added 1. Base case is empty string, which returns 0.

![ninth](image-8.png)

Used the Euclidean algorithm recursively: gcd(b, a % b), base case is b == 0.

![tenth](image-9.png)
