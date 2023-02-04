# include <bits/stdc++.h>

using namespace std;
int main() {
    int n;
    cin >> n;
    int INF = 10 ^ 10;
    vector<int> d(n + 1, -(10^10));
    vector<int> answer;
    vector<int> prev(n, -1);
    vector<int> index_answer(n + 1, -1);
    d[0] = INF;
    int first_inf;
    first_inf = 1;
    for (int i = 0; i < n; i++) {
        int elem, l, r;
        cin >> elem;
        l = 0;
        r = first_inf;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (elem <= d[mid]) {
                l = mid;
            }
            else {
                r = mid;
            }
        }
        if (d[r] == -INF) {
            first_inf++;
        }
        d[r] = elem;
        prev[i] = index_answer[r - 1];
        index_answer[r] = i;
    }
    int len_seq = n;
    while (index_answer[len_seq] == -1) {
        len_seq--;
    }
    cout << len_seq << '\n';
    int last_index;
    last_index = index_answer[len_seq];
    while (last_index != -1) {
        answer.push_back(last_index + 1);
        last_index = prev[last_index];
    }
    for (int i = answer.size() - 1; i > -1; i--) {
        cout << answer[i] << ' ';
    }
    
}
