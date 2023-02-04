def find_set(v):
    if parent[v] == v:
        return v, 0
    a, b = find_set(parent[v])
    parent[v] = a
    plus_w[v] += b
    return parent[v], plus_w[v]


def union_sets(x, y):
    x = find_set(x)[0]
    y = find_set(y)[0]
    if x != y:
        # x -> y
        if rank[x] > rank[y]:
            x, y = y, x
        parent[x] = y
        plus_w[x] -= plus_w[y]
        if rank[x] == rank[y]:
            rank[y] += 1


n, m = map(int, input().split())
parent = [i for i in range(n)]
rank = [0 for i in range(n)]
plus_w = [0 for i in range(n)]
weight = [0 for i in range(n)]
for i in range(m):
    command = input().split()
    if command[0] == "join":
        x = int(command[1])
        y = int(command[2])
        x -= 1
        y -= 1
        union_sets(x, y)
    elif command[0] == "add":
        v = int(command[1])
        v -= 1
        add_weight = int(command[2])
        plus_w[find_set(v)[0]] += add_weight
    elif command[0] == "get":
        v = int(command[1])
        v -= 1
        root_v = find_set(v)[0]
        if v == root_v:
            print(weight[v] + plus_w[v])
        else:
            print(weight[v] + plus_w[v] + plus_w[root_v])
