# Search_and_Sort_Algorithm_Analysis
This problem is about implementing some sorting algorithms (Insertion Sort, Merge Sort, Counting Sort) and search algorithms (Linear Search, Binary Search) and comparing their performance. Search algorithms offer different methods for finding a specific element within a dataset. The time and space complexities of these algorithms can significantly vary depending on the size and organization of the dataset. Sorting algorithms rearrange datasets into a specific order.
For testing these algorithms, dataset used in three different types (random, sorted, reversely sorted) and 10 different sizes (500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000).

For each algorithm, a class implemented which have their own sorting/searching method. 
In the main class, there is a for loop iterating over different data sizes. In each iteration, the calculateAverageSortingTime and calculateSearchTime methods are called with parameters data, size, and search/sort type to measure the average runtime of the algorithms. Then, these durations are added to two-dimensional arrays, and these arrays are passed as parameters to the showAndSaveChart method for chart plotting.

As output, the timing plots are in the repository.

Insertion Sort exhibits the best performance on sorted datasets while performing the worst on reverse-sorted datasets. The expected Ω(n) and O(n^2) time complexities are confirmed.
Merge Sort demonstrates consistent performance across all data types and sizes. The expected Ω(n log n) and O(n log n) time complexities are verified. 
Counting Sort worked efficiently with reverse sorted and random sorted data, but not with sorted data. If there were too high datapoints(outlier) in sub datasets, they may have increased the runtime.
Binary Search works faster with sorted data than Linear Search, so search algorithms plot verified that.

