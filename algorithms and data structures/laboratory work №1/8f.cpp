#include <bits/stdc++.h>

using namespace std;

int n;
long long s;
const int maxn = 5e5 + 100;
pair <pair <long long, long long>, int> q[maxn];
bool cheker[maxn];

void print() {
    
    vector <int> a;
    vector <long long> ans(n - 2);
    long long ls = 0;
    long long rans = 0;
    
    for (int i = 0; i < n; i++) {
        if (cheker[i]) {
            a.push_back(i);
            ls += q[i].first.first;
            rans = min(s, rans + q[i].first.second);
            if (ls > s) {
                break;
            }
        }
    }
    
    reverse(a.begin(), a.end());
    
    while (ls > s) {
        ls -= q[a.back()].first.first;
        a.pop_back();
    }
    // res1 = how much
    cout << rans << endl;
    
    for (int i : a) {
        int id = q[i].second;
        if (id != -1) {
            ls -= q[i].first.first;
            ans[id] = min(q[i].first.second, rans - ls);
            rans -= ans[id];
        }
    }
    
    // res2 = what need to buy
    for (long long i : ans) {
        cout << i << ' ';
    }
}

int main() {
    
    cin >> n >> s;
    
    for (int i = 0; i < n; i++) {
        q[i].second = i;
        cin >> q[i].first.first >> q[i].first.second;
    }
    
    sort(q, q + n);
    
    int cnt = 0;
    q[n++] = {{0, 0}, -1};
    q[n++] = {{0, 0}, -1};
    
    while (cnt < n && q[cnt].first.first * 7 <= s * 2) {
        cnt++;
    }
    
    if (cnt + 4 < n && q[cnt].first.first + q[cnt + 1].first.first + q[cnt + 2].first.first <= s) {
        cheker[cnt] = cheker[cnt + 1] = cheker[cnt + 2] = 1;
    }
    else {
        for (int i = 0; i < cnt; i++) {
            cheker[i] = 1;
        }
        
        rotate(q + cnt, q + n - 2, q + n);
        
        pair <long long, pair <int, int> > found = {-1, {0, 0}};
        int id = cnt;
        int pos = id + 1;
        
        for (int i = n - 1; i >= cnt; i--) {
            
            while (pos < i && q[i].first.first + q[pos].first.first <= s) {
                if (q[pos].first.second > q[id].first.second) {
                    id = pos;
                }
                pos++;
            }
            
            if (q[i].first.first <= s) {
                found = max(found, {q[i].first.second + q[id].first.second, {i, id}});
            }
            
            if (pos == i) {
                break;
            }
        }
        
        cheker[found.second.first] = cheker[found.second.second] = 1;
    }
    
    print();
}