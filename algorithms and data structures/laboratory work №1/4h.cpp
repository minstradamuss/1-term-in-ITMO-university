#include <iostream>
#include <deque>
#include <vector>
using namespace std;
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    deque <long long int> q;
    int n, k;
    std::cin >> n >> k;
    vector <long long int> a;
    for (int i = 0; i < n; i++) {
        long long int el;
        std::cin >> el;
        a.push_back(el);
    }
    
    for (int i = 0; i < k; i++) {
        q.emplace_back(a[i]);
        while ((q.size() > 1) && (q[q.size()-1] < q[q.size()-2])) {
            auto end = q.end(); 
            q.erase(end-2);
        }
    }
    int i = 0;
    cout << q[0] << endl;
    for (int i = k; i < a.size(); i++) {
        if (q[0] == a[i - k] || q.size() > k) {
            q.erase(q.begin());
        }
        q.emplace_back(a[i]);
        while (q.size() > 1 && (q[q.size()-1] < q[q.size()-2])) {
            auto end = q.end(); 
            q.erase(end-2);
        }
        std::cout << q[0] << '\n';
    }
    return 0;
}