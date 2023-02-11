
#Author: minstradamuss

def get_max(heap, size_barrel):
    ans = heap[0]
    if heap[0] > size_barrel:
        heap[0] -= size_barrel
    else:
        heap[0] = heap[-1]
        heap.pop()
    restore(0, heap)
    return min(ans, size_barrel)


def restore(cur_index, heap):
    left_child = 2 * cur_index + 1
    right_child = 2 * cur_index + 2
    if left_child >= len(heap):
        return
    max_child = left_child
    if right_child < len(heap) and heap[left_child] < heap[right_child]:
        max_child = right_child
    if heap[cur_index] > heap[max_child]:
        return
    else:
        heap[cur_index], heap[max_child] = heap[max_child], heap[cur_index]
        restore(max_child, heap)


n, m, size_barrel = map(int, input().split())
a = list(map(int, input().split()))
heap = [0] * n
for i in range(n - 1, -1, -1):
    heap[i] = a[i]
    cur_index = i
    while 2 * cur_index + 1 < n:
        left_child = 2 * cur_index + 1
        right_child = 2 * cur_index + 2
        if right_child < n and heap[left_child] < heap[right_child]:
            max_child = right_child
        else:
            max_child = left_child
        if heap[cur_index] > heap[max_child]:
            break
        else:
            heap[cur_index], heap[max_child] = heap[max_child], heap[cur_index]
            cur_index = max_child
ans = 0
for i in range(m):
    ans += get_max(heap, size_barrel)
    if len(heap) == 0:
        break
print(ans)
