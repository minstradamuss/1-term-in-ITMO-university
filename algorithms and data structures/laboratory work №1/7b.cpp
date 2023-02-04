#include <bits/stdc++.h>

#define MAX 150010

char op[20];
int mas[3][MAX], dsu[MAX], res[MAX];

int Repr(int n) {
    if (n == dsu[n]) {
        return n;
    }
    return (dsu[n] = Repr(dsu[n]));

}

int Union(int x, int y) {
  int x1 = Repr(x), y1 = Repr(y);
  dsu[x1] = y1;
  return (x1 == y1);

}

using namespace std;
int main () {
    int n, m, k, u, v;
    scanf("%d %d %d", &n, &m, &k);
    for(int i = 0; i < m; i++) {
        scanf("%d %d", &u, &v);
    }
    scanf("\n");
    
    for(int i = 0; i < k; i++) {
        scanf("%s %d %d\n", op, &mas[1][i], &mas[2][i]);
        if (op[0] == 'a') {
            mas[0][i] = 1;
        }
        else {
            mas[0][i] = 0;
        }
    }
    
    for (int i = 1; i <= n; i++) {
        dsu[i] = i;
    }
    
    for (int i = k - 1; i > -1; i--) {
        if (mas[0][i]) {
            bool cheker = Repr(mas[1][i]) == Repr(mas[2][i]);
            res[i] = cheker;
        }
        else Union(mas[1][i],mas[2][i]);
    }
    
    for (int i = 0; i < k; i++) {
        if (mas[0][i]) {
            if (res[i]) {
                cout << "YES" << "\n";
            }
            else {
                cout << "NO" << "\n";
            }
        }
    }
    
}
